package com.procesos.parcial.model;

import com.procesos.parcial.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the full name")
    @Size(min = 10, max = 200, message = "The full name must be between 10 and 200 characters")
    private String fullName;

    @NotBlank(message = "Please provide the document")
    @Column(unique = true, nullable = false)
    @Size(min = 5, max = 10, message = "The document must be between 5 and 10 characters")
    private String document;

    @NotBlank(message = "Please provide the document type")
    @Pattern(regexp = "^(passport|id_card|driver_license)$", message = "The document type must be either 'passport', 'id_card' or 'driver_license'")
    private String documentType;

    @Past(message = "The birth date must be in the past")
    private LocalDate birthDay;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Please provide a valid phone number")
    private String phoneNumber;

    @NotBlank(message = "Please provide the email")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Please provide the password")
    @Size(min = 8, max = 255,message = "The password must be between 8 and 255 characters")
    private String password;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull(message = "Please provide a role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}