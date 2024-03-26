package com.sk.wrapit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.wrapit.dto.request.PaymentReq;
import com.sk.wrapit.dto.response.BasicRes;
import com.sk.wrapit.model.Payment;
import com.sk.wrapit.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wrapit/api/v1/payment")
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<?> addPayment(@RequestBody PaymentReq paymentReq){

        BasicRes<String> response = new BasicRes<>();
        try {
            response = paymentService.addPayment(paymentReq);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAllPayment(){

        BasicRes<List<Payment>> response = new BasicRes<>();
        try {
            response = paymentService.viewAllPayment();
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/view/{paymentId}")
    public ResponseEntity<?> viewOnePayment(@PathVariable String paymentId){

        BasicRes<Payment> response = new BasicRes<>();
        try {
            response = paymentService.viewOnePayment(paymentId);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
    }
}
