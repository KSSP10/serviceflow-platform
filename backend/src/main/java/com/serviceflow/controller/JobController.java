package com.serviceflow.controller;

import com.serviceflow.entity.Job;
import com.serviceflow.entity.Location;
import com.serviceflow.repository.CustomerRepository;
import com.serviceflow.repository.JobRepository;
import com.serviceflow.repository.LocationRepository;
import com.serviceflow.repository.TenantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;
    private final TenantRepository tenantRepository;

    public JobController(JobRepository jobRepository, CustomerRepository customerRepository, LocationRepository locationRepository, TenantRepository tenantRepository) {
        this.jobRepository = jobRepository;
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
        this.tenantRepository = tenantRepository;
    }

    @GetMapping
    public List<Job> list() {
        return jobRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> request) {
        var tenant = tenantRepository.findBySlug("demo").orElseThrow();
        var customer = customerRepository.findById(Long.valueOf(request.get("customerId").toString())).orElseThrow();
        var location = locationRepository.findById(Long.valueOf(request.get("locationId").toString())).orElseThrow();
        Job job = new Job(tenant, customer, location, request.getOrDefault("title", "New job").toString());
        return ResponseEntity.ok(jobRepository.save(job));
    }
}
