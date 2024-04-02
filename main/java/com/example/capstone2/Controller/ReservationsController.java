package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Model.Reservations;
import com.example.capstone2.Service.ReservationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationsController {


    private final ReservationsService reservationsService;
    Logger logger= LoggerFactory.getLogger(ReservationsController.class);

    @GetMapping("/get")
    public ResponseEntity getAllReservations() {
        logger.info("get All reservations");
        return ResponseEntity.status(200).body(reservationsService.getAllReservations());
    }


    @PostMapping("/add")
    public ResponseEntity addReservations(@RequestBody @Valid Reservations reservations) {
        logger.info("inside add reservations");
        reservationsService.addReservations(reservations);
        return ResponseEntity.status(200).body(new ApiResponse("Reservations Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateReservations(@PathVariable Integer id, @RequestBody @Valid Reservations reservations) {
        logger.info("inside update reservations");
        reservationsService.updateReservations(id, reservations);
        return ResponseEntity.status(200).body(new ApiResponse("Reservation updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservations(@PathVariable Integer id) {
        logger.info("inside delete reservations");
        reservationsService.deleteReservations(id);
        return ResponseEntity.status(200).body(new ApiResponse("Reservations deleted!"));
    }


    @GetMapping("/getReservationsByStarDate/{starDate}")
    public ResponseEntity getReservationsByStarDate(@PathVariable LocalDate starDate) {
        logger.info("get reservations by star date");
        return ResponseEntity.status(200).body(reservationsService.getReservationsByStarDate(starDate));

    }

    ////////////////////////////////////////////////////////////////////////////////



    @GetMapping("/reservations/{id}")
    public ResponseEntity getReservationById(@PathVariable Integer id) {
        logger.info("get reservation by id");
        return ResponseEntity.status(200).body(reservationsService.getReservationById(id));
    }



    @GetMapping("/reservations/customerId")
    public ResponseEntity getReservationsByCustomerId(@PathVariable Integer customerId) {
        logger.info("get reservation by customer id");
        return ResponseEntity.status(200).body(reservationsService.getReservationsByCustomerId(customerId));

    }


    @PostMapping("/calculateReservationCost/{carId}/{numberOfDays}")
    public ResponseEntity calculateReservationCost(@PathVariable Integer carId,@PathVariable Integer numberOfDays) {
        logger.info("calculate reservation cost");
        double reservationCost = reservationsService.calculateReservationCost(carId,numberOfDays);
        return ResponseEntity.status(200).body(new ApiResponse("Reservation cost calculated: " + reservationCost));
    }


    @PutMapping("/{id}/endDate")
    public ResponseEntity updateReservationEndDate(@PathVariable Integer id, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        logger.info("inside update reservation end date");
        reservationsService.updateReservationEndDate(id, endDate);
        return ResponseEntity.status(200).body(new ApiResponse("Reservation end date updated successfully"));
    }


    @GetMapping("/customers/{customerId}/totalCostOfReservations")
    public ResponseEntity<Double> getTotalCostOfCustomerReservations(@PathVariable Integer customerId) {
        logger.info("get total cost of customer reservations ");
        double totalCost = reservationsService.calculateTotalCostOfCustomerReservations(customerId);
        return ResponseEntity.ok(totalCost);
    }


    @DeleteMapping("/reservations/{id}/checkAndCancel")
    public ResponseEntity checkAndCancelReservation(@PathVariable Integer id) {
        logger.info("inside check and cancel reservation ");
        reservationsService.checkAndCancelReservation(id);
        return ResponseEntity.ok("Reservation checked and possibly cancelled successfully");
    }


}
