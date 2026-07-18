package com.serviceflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tenants")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Tenant() {}

    public Tenant(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSlug() { return slug; }
    public Instant getCreatedAt() { return createdAt; }
}
