package com.example.capstone2.Repository;

import com.example.capstone2.Model.Car;
import com.example.capstone2.Model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    Car findCarById(Integer id);

    List<Car> findCarByCarName(String carName);

    @Query("select c from Car c where c.year=?1")
    List<Car> findCarByYear(Integer year);

//    @Query("select c from Car c where c.id not in (select r.carId from Reservations r where :startDate <= r.endDate and :endDate >= r.startDate)")
//    List<Car> findAvailableCars(LocalDate startDate,LocalDate endDate);


    @Query("select c from Car c where c.available=true")
    List<Car> availableCars();


    @Query("SELECT c FROM Car c ORDER BY c.countCar DESC")
    List<Car> findCountCar();

//   Car findCarsByEmployeeId(Integer employeeId);

}
