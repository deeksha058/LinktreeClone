package com.LinkTree.LinktreeClone.Controller;

import com.LinkTree.LinktreeClone.Model.User;
import com.LinkTree.LinktreeClone.Repository.UserRepository;
import com.LinkTree.LinktreeClone.Service.LinkService;
import com.LinkTree.LinktreeClone.Service.UserService;
import com.LinkTree.LinktreeClone.config.AuthRequest;
import com.LinkTree.LinktreeClone.config.AuthResponse;
import com.LinkTree.LinktreeClone.jwt.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CRUD Rest APIs for Post resources")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	LinkService linkService;

	@GetMapping
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long id){

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK) ;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id){

        linkService.deleteDataByUserId(id);
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK) ;
    }


    @PutMapping("/{userId}")
    public  ResponseEntity<?> updateUser(@RequestBody User user, @Valid @PathVariable("userId") Long id){
        return new ResponseEntity<>(userService.updateUserData(user,id), HttpStatus.OK);
    }

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {

			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);

			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid AuthRequest request) {

            User user1 = new User();
			user1.setEmail(request.getEmail());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode(request.getPassword());
			user1.setPassword(password);

		    return userService.saveUser(user1);
	}

	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> confirmUserAccount(@RequestParam(required=false,name="token")String confirmationToken) {
		return userService.confirmEmail(confirmationToken);
	}
}
