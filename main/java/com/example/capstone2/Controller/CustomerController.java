package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Customers;
import com.example.capstone2.Service.CustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomersService customersService;
    Logger logger= LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        logger.info("get all customers");
        return ResponseEntity.status(200).body(customersService.getAllCustomers());
    }


    @PostMapping("/add")
    public ResponseEntity addCustomers(@RequestBody @Valid Customers customers){
        logger.info("inside add customer");
        customersService.addCustomer(customers);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customers customers){
        logger.info("inside update customer");
        customersService.updateCustomer(id,customers);
        return ResponseEntity.status(200).body(new ApiResponse( "Customer updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        logger.info("inside delete customer");
        customersService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted!"));
    }




    @GetMapping("/searchCustomerByEmail/{email}")
    public ResponseEntity searchCustomerByEmail (@PathVariable String email){
        logger.info("inside search customer By Email");

        return ResponseEntity.status(200).body(customersService.searchCustomerByEmail(email));

    }


    @PostMapping("/authenticate/{email}/{password}")
    public ResponseEntity authenticateCustomer(@PathVariable String email, @PathVariable String password) {
        logger.info("inside authenticate Customer");
        customersService.authenticateCustomer(email,password);
        return ResponseEntity.status(200).body(new ApiResponse("Authentication successful!"));
    }

//
    @GetMapping("/topcustomers")
    public List<Customers> getTop3CustomersWithMostCarPurchases() {
        logger.info("get top customers");
        return customersService.getTop3CustomersWithMostCarPurchases();
    }


    @GetMapping("/customers/olderThan/{age}")
    public ResponseEntity getCustomersOlderThan(@PathVariable Integer age) {
        logger.info("get Customers Older Than");
        List<Customers> customers = customersService.getCustomersOlderThan(age);

        return ResponseEntity.status(200).body(customers);

    }


}
