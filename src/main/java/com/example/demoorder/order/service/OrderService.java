package com.example.demoorder.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demoorder.order.dto.GetOrderResponse;
import com.example.demoorder.order.repository.impl.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public List<GetOrderResponse> getOrderByProductCategory(String category) {

    orderRepository.getOrderByProductCategory(category)
            .forEach(e -> {
              System.out.println("e = " + e);
            });

    return orderRepository.getOrderByProductCategory(category).stream()
        .map(m -> GetOrderResponse.builder()
            .userEmail(m.getUser().getEmail())
            .productName(m.getProduct().getName())
            .productPrice(m.getProduct().getPrice())
            .productCategory(m.getProduct().getCategory())
            .build())
        .collect(Collectors.toList());
  }
}
