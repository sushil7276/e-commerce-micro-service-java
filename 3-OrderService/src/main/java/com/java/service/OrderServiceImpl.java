package com.java.service;

import com.java.dto.*;
import com.java.exception.ResourceNotFoundException;
import com.java.fignclients.CartFeignClient;
import com.java.fignclients.ProductFeignClient;
import com.java.fignclients.UserFeignClient;
import com.java.model.Order;
import com.java.model.OrderItem;
import com.java.model.OrderStatus;
import com.java.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private CartFeignClient cartFeignClient;


    @Override
    public OrderResponseDto placeOrder(PlaceOrderDto placeOrderDto) {
        UserDto userDto = validateUser(placeOrderDto.getUserId());
        if (userDto == null) {
            throw new ResourceNotFoundException("User Not Found");
        }

        List<CartResponseDto> cartItems = fetchCartItems(placeOrderDto.getUserId());
        if (cartItems == null || cartItems.isEmpty()) {
            throw new ResourceNotFoundException("Cart is Empty");
        }

        BigDecimal totalPrice = calculateTotalPrice(cartItems);
        List<OrderItem> orderItems = buildOrderItems(cartItems);

        // Create an Order entity
        Order order = createOrderEntity(placeOrderDto, totalPrice, orderItems);
        Order savedOrder = orderRepository.save(order);

        cartFeignClient.clearCart(placeOrderDto.getUserId());

        return mapToOrderResponseDto(savedOrder, userDto);
    }

    private OrderResponseDto mapToOrderResponseDto(Order dbOrder, UserDto userDto) {
        OrderResponseDto dto = new OrderResponseDto();
        BeanUtils.copyProperties(dbOrder, dto, "items");
        dto.setOrderId(dbOrder.getId());
        dto.setStatus(dbOrder.getOrderStatus().getStatus());
        dto.setUserDto(userDto);

        List<OrderItemResponseDto> collect = dbOrder.getItems().stream()
                .map(item -> {
                    OrderItemResponseDto itemDto = new OrderItemResponseDto();
                    BeanUtils.copyProperties(item, itemDto);
                    return itemDto;
                }).collect(Collectors.toList());
        dto.setItems(collect);
        return dto;
    }

    private Order createOrderEntity(PlaceOrderDto request, BigDecimal totalPrice, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.STATUS_PLACED);

        // Set the order reference in each order item
        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }

        order.setItems(orderItems);
        return order;
    }

    private List<OrderItem> buildOrderItems(List<CartResponseDto> cartItem) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartResponseDto item : cartItem) {
            ProductResponseDto productById = productFeignClient.getProductById(item.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(productById.getPrice());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private BigDecimal calculateTotalPrice(List<CartResponseDto> cartItems) {

        BigDecimal total = BigDecimal.ZERO;

        for (CartResponseDto cartItem : cartItems) {
            ProductResponseDto product = productFeignClient.getProductById(cartItem.getProductId());
            BigDecimal perItemPrice = product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(perItemPrice);
        }
        return total;
    }

    private List<CartResponseDto> fetchCartItems(Long userId) {
        return cartFeignClient.getCartByUserId(userId);
    }

    private UserDto validateUser(Long userId) {
        return userFeignClient.findById(userId.intValue());
    }

    @Override
    public OrderStatusResponseDto updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        OrderStatus newStatus = OrderStatus.from(status);
        order.setOrderStatus(newStatus);

        orderRepository.save(order);
        System.out.println("Updating order status to: " + status);

        return mapToOrderStatusResponseDto(order);
    }

    private OrderStatusResponseDto mapToOrderStatusResponseDto(Order order) {
        OrderStatusResponseDto dto = new OrderStatusResponseDto();
        dto.setOrderId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getOrderStatus().getStatus());
        return dto;
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        UserDto userDto = validateUser(userId);
        List<Order> orders = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No orders found for user with id: " + userId));
        return orders.stream().map(order ->
                mapToOrderResponseDto(order, userDto)
        ).toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        UserDto userDto = validateUser(order.getUserId());
        return mapToOrderResponseDto(order, userDto);

    }

}
