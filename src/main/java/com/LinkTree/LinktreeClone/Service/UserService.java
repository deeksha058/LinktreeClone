package com.LinkTree.LinktreeClone.Service;

import com.LinkTree.LinktreeClone.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface UserService {

    ResponseEntity<?> saveUser(User user);

    ResponseEntity<?> confirmEmail(String confirmationToken);

    Optional<User> getUserById(Long userId);

    List<User> getAllUser();

    public String deleteUserById(Long id);

    public String updateUserData(User user , Long id );
}
