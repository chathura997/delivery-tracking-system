package com.example.delivery.tracking.API.controller;

import com.example.delivery.tracking.API.dto.request.CustomerRequestDto;
import com.example.delivery.tracking.API.dto.response.CustomerResponseDto;
import com.example.delivery.tracking.API.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerRequestDto));
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerDTO) {
        return ResponseEntity.ok(customerService.update(customerDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
