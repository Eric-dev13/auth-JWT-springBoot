package com.ricou.api.todolist.Model;

import com.ricou.api.todolist.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements Serializable, UserDetails {
    /* ID
    L’identifiant est indiqué avec l’annotation @Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="email", length = 255, unique = true)
    private String email;

    @Column(name="password", length = 255)
    private String password;

    @Column(name = "lastname", length = 255)
    private String lastname;

    @Column(name="firstname", length = 255)
    private String firstname;

    /*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoEntity> todos = new ArrayList<>();
    */
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TodoEntity> todos = new ArrayList<>();
    //private Set<TodoEntity> todos = new HashSet<TodoEntity>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return email;
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

    @Override
    public String toString() {
        return this.id + " : " + this.lastname + " " + this.firstname + " " + this.email;
    }

}