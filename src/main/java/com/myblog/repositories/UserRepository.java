package com.myblog.repositories;

import com.myblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //keep convention in mind i.e. findBy-> optional  exists->Boolean
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
   Boolean  existsByEmail(String email);


}
