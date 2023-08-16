package com.LinkTree.LinktreeClone.Repository;

import com.LinkTree.LinktreeClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    @Query(value = "SELECT * FROM user WHERE email = :email" ,nativeQuery = true)
    User findByUserName(String email);

    Optional<User> findByEmail(String email);


    User findByEmailIgnoreCase(String emailId);

    Boolean existsByEmail(String email);

}
