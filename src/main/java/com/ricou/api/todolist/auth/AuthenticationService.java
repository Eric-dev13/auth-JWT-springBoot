package com.ricou.api.todolist.auth;

import com.ricou.api.todolist.Model.UserEntity;
import com.ricou.api.todolist.configuration.JwtService;
import com.ricou.api.todolist.repository.UserEntityJpaRepository;
import com.ricou.api.todolist.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityJpaRepository userEntityJpaRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
       var user = UserEntity.builder()
               .firstname(request.getFirstname())
               .lastname(request.getLastname())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .role(Role.USER)
               .build();
        userEntityJpaRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
        );
        var user = userEntityJpaRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
