package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    Logger logger= LoggerFactory.getLogger(CarController.class);


    @GetMapping("/get")
    public ResponseEntity getAllCars() {
        logger.info("get all cars");
        return ResponseEntity.status(200).body(carService.getAllCars());
    }


    @PostMapping("/add")
    public ResponseEntity addCar(@RequestBody @Valid Car car) {
        logger.info("inside add car");
        carService.addCar(car);
        return ResponseEntity.status(200).body(new ApiResponse("Car Added"));

    }

    // Update Car
    @PutMapping("/update/{id}")
    public ResponseEntity updateCar(@PathVariable Integer id, @RequestBody @Valid Car car) {
        logger.info("inside update car");
        carService.updateCar(id, car);
        return ResponseEntity.status(200).body(new ApiResponse("Car updated"));
    }

    //Delete Car
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        logger.info("inside delete car");
        carService.deleteCar(id);
        return ResponseEntity.status(200).body(new ApiResponse("Car deleted!"));
    }


    @GetMapping("/getCarByName/{carName}")
    public ResponseEntity getCarByName(@PathVariable String carName) {
        logger.info("inside get Car By Name");
        return ResponseEntity.status(200).body(carService.getCarByName(carName));
    }

//
    @GetMapping("/availableCar")
    public ResponseEntity availableCars(){
        logger.info("inside available Cars");
        List<Car> availableCars = carService.getAvailableCars();
        return ResponseEntity.ok(availableCars);
    }


    @GetMapping("/most-rented-car")
    public ResponseEntity getMostRentedCar() {
        logger.info("inside get Most Rented Car");
        Car mostRentedCar = carService.getMostRentedCar();
        return ResponseEntity.ok(mostRentedCar);
    }



    @GetMapping("/getCarByYear/{year}")
    public ResponseEntity getCarByYear(@PathVariable Integer year) {
        logger.info("inside get Car By Year");
        return ResponseEntity.status(200).body(carService.getCarByYear(year));
    }


    @PostMapping("/applydiscount/{year}/{discountPercentage}")

    public ResponseEntity applyDiscountForYear(@PathVariable Integer year,
                                               @PathVariable double discountPercentage) {
        logger.info("inside apply Discount For Year");
        carService.applyDiscountForYear(year, discountPercentage);

        return ResponseEntity.ok("Discount applied successfully");
    }





}
