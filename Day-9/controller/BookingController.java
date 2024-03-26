package com.sk.wrapit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.wrapit.dto.request.BookingReq;
import com.sk.wrapit.dto.response.BasicRes;
import com.sk.wrapit.model.Booking;
import com.sk.wrapit.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wrapit/api/v1/booking")
public class BookingController {
    
    private final BookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody BookingReq bookingReq){
        BasicRes<String> response = new BasicRes<>();

        try {
            response = bookingService.addbooking(bookingReq);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Oops!... Something went wrong. Please try again.");
            response.setData("");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }

    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateBooking(@RequestBody Booking booking){
        BasicRes<String> response = new BasicRes<>();

        try {
            response = bookingService.updateBooking(booking);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Oops!... Something went wrong. Please try again.");
            response.setData("");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }

    }

    @DeleteMapping("/delete?{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable String bookingId){
        BasicRes<String> response = new BasicRes<>();

        try {
            response = bookingService.deleteBooking(bookingId);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Oops!... Something went wrong. Please try again.");
            response.setData("");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAllBooking(){
        BasicRes<List<Booking>> response = new BasicRes<>();

        try {
            response = bookingService.viewAllBooking();
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Oops!... Something went wrong. Please try again.");
            response.setData(null);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }

    }

    @GetMapping("/view/{bookingId}")
    public ResponseEntity<?> viewOneBooking(@PathVariable String bookingId){
        BasicRes<Booking> response = new BasicRes<>();

        try {
            response = bookingService.ViewOneBooking(bookingId);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Oops!... Something went wrong. Please try again.");
            response.setData(null);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }

    }
}
