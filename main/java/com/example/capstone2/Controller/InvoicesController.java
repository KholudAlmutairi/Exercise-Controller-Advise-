package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Invoices;
import com.example.capstone2.Service.InvoicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ap1/v1/invoices")
@RequiredArgsConstructor
public class InvoicesController {


    private final InvoicesService invoicesService;
    Logger logger= LoggerFactory.getLogger(InvoicesController.class);

    @GetMapping("/get")
    public ResponseEntity getAllInvoices(){
        logger.info("get All invoices");
        return ResponseEntity.status(200).body(invoicesService.getAllInvoices());}


    @PostMapping("/add")
    public ResponseEntity addInvoices(@RequestBody @Valid Invoices invoices){
        logger.info("inside add invoice");
        invoicesService.addInvoice(invoices);
        return ResponseEntity.status(200).body(new ApiResponse("Invoices Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateInvoices(@PathVariable Integer id, @RequestBody @Valid Invoices invoices){
        logger.info("inside update invoice");
        invoicesService.updateInvoices(id,invoices);
        return ResponseEntity.status(200).body(new ApiResponse( "Invoices updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoices(@PathVariable Integer id){
        logger.info("inside delete invoice");
        invoicesService.deleteInvoices(id);
        return ResponseEntity.status(200).body(new ApiResponse("Invoices deleted!"));
    }


    @GetMapping("/invoices/{startDate}/{endDate}")
    public ResponseEntity getInvoicesWithinPeriod(@PathVariable LocalDate startDate1, @PathVariable LocalDate endDate1,@PathVariable LocalDate startDate2,@PathVariable LocalDate endDate2)
    {
        logger.info("get invoices within period");
        List<Invoices> invoices = invoicesService.getInvoicesWithinPeriod(startDate1,endDate1,startDate2,endDate2);
        //invoicesService.getInvoicesWithinPeriod(startDate, endDate);
        return ResponseEntity.status(200).body(invoices);
    }

























}
