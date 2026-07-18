package com.serviceflow.bootstrap;

import com.serviceflow.entity.AuditEvent;
import com.serviceflow.entity.Tenant;
import com.serviceflow.entity.UserAccount;
import com.serviceflow.repository.AuditEventRepository;
import com.serviceflow.repository.TenantRepository;
import com.serviceflow.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedDataRunner implements CommandLineRunner {

    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final AuditEventRepository auditEventRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SeedDataRunner(TenantRepository tenantRepository, UserRepository userRepository, AuditEventRepository auditEventRepository, BCryptPasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.auditEventRepository = auditEventRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (tenantRepository.findBySlug("demo").isEmpty()) {
            Tenant tenant = tenantRepository.save(new Tenant("Demo Tenant", "demo"));
            userRepository.save(new UserAccount(tenant, "admin", passwordEncoder.encode("password"), "ADMIN"));
            auditEventRepository.save(new AuditEvent(tenant, "system", "seed", "Created default tenant and admin user"));
        }
    }
}
