package com.api.jwt.Service;

import com.api.jwt.Model.UserEntity;
import com.api.jwt.repository.UserEntityJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
@Data
@Service
// public class UserService implements UserDetailsService {
public class UserService {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserEntityJpaRepository userEntityJpaRepository;

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        UserEntity user = userEntityJpaRepository
                            .findUserWithName(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
    */

    // Renvoie toutes les instances du type.
    public Iterable<UserEntity> getAll() {
        return userEntityJpaRepository.findAll();
    }

    // Récupère une entité par son identifiant
    public Optional<UserEntity> getById(Long id){
        return userEntityJpaRepository.findById(id);
    }

    public UserEntity add(@RequestBody UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    public UserEntity edit(@RequestBody UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    public void delete(Long id) {
        userEntityJpaRepository.deleteById(id);
    }

}
