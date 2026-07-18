package com.serviceflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String address;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Location() {}

    public Location(Tenant tenant, Customer customer, String address) {
        this.tenant = tenant;
        this.customer = customer;
        this.address = address;
    }

    public Long getId() { return id; }
    public Tenant getTenant() { return tenant; }
    public Customer getCustomer() { return customer; }
    public String getAddress() { return address; }
    public Instant getCreatedAt() { return createdAt; }
}
