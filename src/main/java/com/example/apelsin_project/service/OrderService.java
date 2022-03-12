package com.example.apelsin_project.service;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.DetailDto;
import com.example.apelsin_project.dto.DetailOrder;
import com.example.apelsin_project.dto.OrderDto;
import com.example.apelsin_project.entity.*;
import com.example.apelsin_project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;
    final DetailRepository detailRepository;
    final ProductRepository productRepository;
    final InvoiceRepository invoiceRepository;
    public ApiResponse add(OrderDto dto) {
        Optional<Customer> byId = customerRepository.findById(dto.getCustId());
        if (!byId.isPresent()){
            return new ApiResponse("This Customer Not found!",false);
        }
        Order order=new Order();
        order.setCustomer(byId.get());
        Order save = orderRepository.save(order);
        double totalSum=0;
        for (DetailDto detailDto:dto.getDetailDtoList()){
            Optional<Product> byId1 = productRepository.findById(detailDto.getProductId());
            if (!byId1.isPresent()) {
                continue;
            }
            Product product = byId1.get();
           Detail detail=new Detail();
           detail.setOrder(save);
           detail.setProduct(product);
           detail.setQuantity((short) detailDto.getQuantity());
           totalSum+=product.getPrice().doubleValue()*detail.getQuantity();
           detailRepository.save(detail);
        }
        Date date = new Date(LocalDate.now().plusDays(3).getLong(ChronoField.DAY_OF_MONTH));
        Invoice invoice = new Invoice();
        invoice.setOrder(save);
        invoice.setAmount(BigDecimal.valueOf(totalSum));
        invoice.setDue(date);
        Invoice invoice1 = invoiceRepository.save(invoice);
        return new ApiResponse("Add order", true, invoice1.getId());
    }

    public ApiResponse edit(UUID id, OrderDto dto) {
        Optional<Customer> byId = customerRepository.findById(dto.getCustId());
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Optional<Order> byId1 = orderRepository.findById(id);
        Order order = byId1.get();
        List<Detail> allByOrderId = detailRepository.findAllByOrderId(order.getId());
        detailRepository.deleteAll(allByOrderId);
        Optional<Invoice> byOrderId = invoiceRepository.findByOrderId(order.getId());
        Invoice invoice = byOrderId.get();
        double totalSum=0;
        for (DetailDto detailDto:dto.getDetailDtoList()){
            Optional<Product> byId2 = productRepository.findById(detailDto.getProductId());
            Product product = byId2.get();
            Detail detail=new Detail();
            detail.setOrder(order);
            detail.setProduct(product);
            detail.setQuantity((short) detailDto.getQuantity());
            totalSum+=product.getPrice().doubleValue()*detail.getQuantity();
        }
        invoice.setAmount(BigDecimal.valueOf(totalSum));
        invoice.setIssued( new java.sql.Date(LocalDate.now().get(ChronoField.EPOCH_DAY)));
        invoice.setDue(new Date(LocalDate.now().plusDays(3).getLong(ChronoField.EPOCH_DAY)));
        Invoice save = invoiceRepository.save(invoice);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(UUID id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (!byId.isPresent()) {
           return new ApiResponse("Not found",false);
        }
        Order order = byId.get();
        order.setActive(false);
        Order save = orderRepository.save(order);
        return new ApiResponse("Deleted",true,save);
    }

    public ApiResponse getOrderProduct(UUID id) {

        List<DetailOrder> collect=detailRepository.findAllByOrderId(id).stream().map(this::getDetailOrder).collect(Collectors.toList());
        return new ApiResponse("MAna",true,collect);
    }

    private DetailOrder getDetailOrder(Detail detail) {
           return new DetailOrder(
                   detail.getProduct().getName(),detail.getQuantity()
           );
    }


}
