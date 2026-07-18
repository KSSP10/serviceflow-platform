package com.serviceflow.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "audit_events")
public class AuditEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String action;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public AuditEvent() {}

    public AuditEvent(Tenant tenant, String actor, String action, String details) {
        this.tenant = tenant;
        this.actor = actor;
        this.action = action;
        this.details = details;
    }

    public Long getId() { return id; }
    public Tenant getTenant() { return tenant; }
    public String getActor() { return actor; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
    public Instant getCreatedAt() { return createdAt; }
}
