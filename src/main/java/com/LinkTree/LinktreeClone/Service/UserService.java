package com.LinkTree.LinktreeClone.Service;

import com.LinkTree.LinktreeClone.Model.User;
import com.LinkTree.LinktreeClone.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){

        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public String deleteUserById(Long id) {

        try{

            User user = userRepository.findById(id).orElseThrow();
            userRepository.deleteById(id);
            return "user Deleted Successfully!";
        }catch (Exception e){

            return "User is Not present int the Database";
        }
    }

    public String updateUserData(User user , Long id ) {

        try {
            User userDataById = userRepository.findById(id).orElseThrow();

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user);
            User userData = mapper.readValue(jsonString, User.class);
            userData.setId(id);
            User saveUserData = userRepository.save(userData);
            return "DataUpdated Successfully";

        } catch (Exception e) {

            return "linkData not found in database ";
        }
    }
}
