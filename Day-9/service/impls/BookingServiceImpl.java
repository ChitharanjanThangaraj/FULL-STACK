package com.sk.wrapit.service.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.wrapit.dto.request.BookingReq;
import com.sk.wrapit.dto.response.BasicRes;
import com.sk.wrapit.model.Booking;
import com.sk.wrapit.repository.BookingRepo;
import com.sk.wrapit.service.BookingService;
import com.sk.wrapit.util.Patcher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class BookingServiceImpl implements BookingService {
    
    private final BookingRepo bookingRepo;

    @Override
    public BasicRes<String> addbooking(BookingReq bookingReq){
        var response = Booking.builder()
                        .bookingStatus(bookingReq.getBookingStatus())
                        .headCount(bookingReq.getHeadCount())
                        .eventDate(bookingReq.getEventDate())
                        .submissionDate(bookingReq.getSubmissionDate())
                        .build();

        bookingRepo.save(response);

        return BasicRes.<String>builder()
                .message("Booking Created Successfully")
                .build();

    }

    @Override
    public BasicRes<String> updateBooking (Booking booking)throws IllegalArgumentException, IllegalAccessException{

        Booking book  =  bookingRepo.findById(booking.getBookingId()).orElse(null);

        book = Patcher.patcher(book, booking);

        bookingRepo.save(book);
        
        return BasicRes.<String>builder()
                .message("Booking Updated Successfully")
                .build();
    }

    @Override
    public BasicRes<String> deleteBooking(String bookingId){

        bookingRepo.deleteById(bookingId);

        return BasicRes.<String>builder()
                .message("Booking Deleted Successfully")
                .build();
    }

    @Override
    public BasicRes<List<Booking>> viewAllBooking(){
        return BasicRes.<List<Booking>>builder()
                .message("All Data Are Viewed SuccessFully")
                .data(bookingRepo.findAll())
                .build();
    }

    @Override
    public BasicRes<Booking> ViewOneBooking(String bookingId){
        return BasicRes.<Booking>builder()
                .message("One Booking is Viewed SuccessFully")
                .data(bookingRepo.findById(bookingId).orElse(null))
                .build();
    }
}
