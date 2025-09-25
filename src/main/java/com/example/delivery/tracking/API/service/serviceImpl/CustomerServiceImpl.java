package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.CustomerRequestDto;
import com.example.delivery.tracking.API.dto.response.CustomerResponseDto;
import com.example.delivery.tracking.API.entity.Customer;
import com.example.delivery.tracking.API.mapper.CustomerMapper;
import com.example.delivery.tracking.API.repository.CustomerRepository;
import com.example.delivery.tracking.API.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto create(CustomerRequestDto customerRequestDto) {
        Customer customer = customerMapper.toEntity(customerRequestDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponseDto> getAll() {

        List<Customer>  customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getByID(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found with id: "+ id));
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerResponseDto update(CustomerRequestDto customerRequestDto, Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found with id: "+ id));
        customerMapper.updateEntity(customerRequestDto, customer);
        return customerMapper.toDto(customerRepository.save(customer));

    }

    @Override
    public void delete(Long id) {

        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not found with id: "+id);
        }
        customerRepository.deleteById(id);
    }
}
