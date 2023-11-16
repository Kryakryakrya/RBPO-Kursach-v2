package com.otziv.krya.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "new")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ApplicationUser  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name ="role")
    private ApplicationUserRole role;
//    private final List<SimpleGrantedAuthority> authorities;

//    private Set<Role> roles = new HashSet<>();

    public ApplicationUserRole getAuthority() {
        return getRole();
    }
}
