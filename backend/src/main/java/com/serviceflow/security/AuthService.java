package com.serviceflow.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.serviceflow.entity.UserAccount;
import com.serviceflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret:serviceflow-secret}")
    private String jwtSecret;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean isValidCredentials(String username, String password) {
        Optional<UserAccount> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        UserAccount user = userOpt.get();
        return passwordEncoder.matches(password, user.getPasswordHash());
    }

    public String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                .sign(algorithm);
    }
}
