package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.CustomerRequestDto;
import com.example.delivery.tracking.API.dto.response.CustomerResponseDto;

public interface CustomerService extends GenericCrudService<CustomerResponseDto, CustomerRequestDto, Long> {

}
