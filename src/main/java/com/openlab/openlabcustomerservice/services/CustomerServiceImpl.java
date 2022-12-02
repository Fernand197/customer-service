package com.openlab.openlabcustomerservice.services;

import com.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import com.openlab.openlabcustomerservice.dto.CustomerResponseDTO;
import com.openlab.openlabcustomerservice.entities.Customer;
import com.openlab.openlabcustomerservice.mappers.CustomerMapper;
import com.openlab.openlabcustomerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCostumer(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);

        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::customerToCustomerResponseDTO).collect(Collectors.toList());
    }
}
