package com.serviceflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "jobs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status = "CREATED";

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Job() {}

    public Job(Tenant tenant, Customer customer, Location location, String title) {
        this.tenant = tenant;
        this.customer = customer;
        this.location = location;
        this.title = title;
    }

    public Long getId() { return id; }
    public Tenant getTenant() { return tenant; }
    public Customer getCustomer() { return customer; }
    public Location getLocation() { return location; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
}
