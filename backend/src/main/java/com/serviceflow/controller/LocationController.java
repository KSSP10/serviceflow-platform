package com.serviceflow.controller;

import com.serviceflow.entity.Location;
import com.serviceflow.repository.CustomerRepository;
import com.serviceflow.repository.LocationRepository;
import com.serviceflow.repository.TenantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;
    private final TenantRepository tenantRepository;

    public LocationController(LocationRepository locationRepository, CustomerRepository customerRepository, TenantRepository tenantRepository) {
        this.locationRepository = locationRepository;
        this.customerRepository = customerRepository;
        this.tenantRepository = tenantRepository;
    }

    @GetMapping
    public List<Location> list(@RequestParam(required = false) Long customerId) {
        if (customerId == null) {
            return locationRepository.findAll();
        }
        return locationRepository.findAll().stream()
                .filter(location -> location.getCustomer() != null && customerId.equals(location.getCustomer().getId()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> request) {
        var tenant = tenantRepository.findBySlug("demo").orElseThrow();
        var customer = customerRepository.findById(Long.valueOf(request.get("customerId").toString())).orElseThrow();
        Location location = new Location(tenant, customer, request.getOrDefault("address", "Unknown address").toString());
        return ResponseEntity.ok(locationRepository.save(location));
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<?> timeline(@PathVariable Long id) {
        return locationRepository.findById(id)
                .map(location -> {
                    Map<String, Object> payload = new HashMap<>();
                    payload.put("location", location);
                    payload.put("events", List.of(
                            Map.of("type", "created", "message", "Location created"),
                            Map.of("type", "job", "message", "Service visit scheduled")
                    ));
                    return ResponseEntity.ok(payload);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
