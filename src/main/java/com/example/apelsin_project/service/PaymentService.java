package com.example.apelsin_project.service;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.PaymentDto;
import com.example.apelsin_project.entity.Invoice;
import com.example.apelsin_project.entity.Payment;
import com.example.apelsin_project.repository.InvoiceRepository;
import com.example.apelsin_project.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    final PaymentRepository paymentRepository;
    final InvoiceRepository invoiceRepository;

    public ApiResponse add(PaymentDto dto) {
        Optional<Invoice> byId = invoiceRepository.findById(dto.getInvoice_id());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik",false);
        }
        Invoice invoice=byId.get();
        Payment payment=new Payment();
        payment.setAmount(dto.getAmount());
        payment.setInvoice(invoice);
        Payment save = paymentRepository.save(payment);
        return new ApiResponse("Added",true,save);
    }

}
