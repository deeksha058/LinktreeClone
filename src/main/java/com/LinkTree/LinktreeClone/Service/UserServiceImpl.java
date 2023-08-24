package com.LinkTree.LinktreeClone.Service;

import com.LinkTree.LinktreeClone.Model.ConfirmationToken;
import com.LinkTree.LinktreeClone.Model.User;
import com.LinkTree.LinktreeClone.Repository.ConfirmationTokenRepository;
import com.LinkTree.LinktreeClone.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    @Override
    public ResponseEntity<?> saveUser(User user) {


        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8082/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

        System.out.println(confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUserEntity().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
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

    @Override
    public User findUser(String email) {
        return userRepository.findByUserName(email);
    }

}


