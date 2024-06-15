package com.procesos.parcial.service;
import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.AuthenticationFailedException;
import com.procesos.parcial.model.User;
import com.procesos.parcial.model.dto.AuthRequest;
import com.procesos.parcial.model.dto.AuthResponse;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.model.enums.Role;
import com.procesos.parcial.model.enums.SuccessMessage;
import com.procesos.parcial.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword()
            ));
        } catch (Exception e) {
            throw new AuthenticationFailedException(ErrorMessage.INVALID_CREDENTIAL.getMessage());
        }

        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        User user = userOptional.orElseThrow(() -> new AuthenticationFailedException(ErrorMessage.INVALID_CREDENTIAL.getMessage()));

        // Verify password
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationFailedException(ErrorMessage.INVALID_CREDENTIAL.getMessage());
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), Collections.emptyList());

        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .message(SuccessMessage.LOGIN_SUCCESSFUL.getMessage())
                .token(token)
                .email(user.getUsername())
                .role(user.getRole().toString())
                .expiresIn(jwtService.getExpirationTime(token))
                .build();
    }

    public Response register(@Valid User registerRequest) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXISTS.getMessage());
        }
        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .document(registerRequest.getDocument())
                .documentType(registerRequest.getDocumentType())
                .birthDay(registerRequest.getBirthDay())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .address(registerRequest.getAddress())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        Response response = Response.builder()
                .date(LocalDate.now())
                .message(List.of(SuccessMessage.USER_CREATED.getMessage()))
                .statusCode(HttpStatus.OK.name())
                .data(user)
                .build();

        return response;
    }
}

