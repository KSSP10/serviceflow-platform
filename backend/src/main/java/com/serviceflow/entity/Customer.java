package com.serviceflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Customer() {}

    public Customer(Tenant tenant, String name, String phone, String email) {
        this.tenant = tenant;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() { return id; }
    public Tenant getTenant() { return tenant; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public Instant getCreatedAt() { return createdAt; }
}
