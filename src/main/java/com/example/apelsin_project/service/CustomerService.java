package com.example.apelsin_project.service;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.CustomerDto;
import com.example.apelsin_project.entity.Customer;
import com.example.apelsin_project.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;

    public ApiResponse add(CustomerDto dto) {
        if (customerRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Xatolik", false);
        }
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setCountry(dto.getCountry());
        customer.setPhone(dto.getPhone());
        Customer save = customerRepository.save(customer);
        return new ApiResponse("Added", true, save);
    }

    public ApiResponse edit(Integer id, CustomerDto dto) {
        if (customerRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunday om bor", false);
        }
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer = byId.get();
        customer.setPhone(dto.getPhone());
        customer.setName(dto.getName());
        customer.setCountry(dto.getCountry());
        customer.setAddress(dto.getAddress());
        Customer save = customerRepository.save(customer);
        return new ApiResponse("Edit!", true, save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer = byId.get();
        customer.setActive(false);
        customerRepository.save(customer);
        return new ApiResponse("Blocked",true);
    }
}
