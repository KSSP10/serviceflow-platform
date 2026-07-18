package com.serviceflow.controller;

import com.serviceflow.entity.Customer;
import com.serviceflow.repository.CustomerRepository;
import com.serviceflow.repository.TenantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final TenantRepository tenantRepository;

    public CustomerController(CustomerRepository customerRepository, TenantRepository tenantRepository) {
        this.customerRepository = customerRepository;
        this.tenantRepository = tenantRepository;
    }

    @GetMapping
    public List<Customer> list(@RequestParam(required = false) String query) {
        if (query == null || query.isBlank()) {
            return customerRepository.findAll();
        }
        String term = query.toLowerCase();
        return customerRepository.findAll().stream()
                .filter(customer -> (customer.getName() + " " + customer.getEmail() + " " + customer.getPhone()).toLowerCase().contains(term))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) {
        var tenant = tenantRepository.findBySlug("demo").orElseThrow();
        Customer customer = new Customer(tenant, request.getOrDefault("name", "Unnamed"), request.getOrDefault("phone", ""), request.getOrDefault("email", ""));
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<?> timeline(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    Map<String, Object> payload = new HashMap<>();
                    payload.put("customer", customer);
                    payload.put("events", List.of(
                            Map.of("type", "created", "message", "Customer record created"),
                            Map.of("type", "note", "message", "Initial intake captured")
                    ));
                    return ResponseEntity.ok(payload);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
