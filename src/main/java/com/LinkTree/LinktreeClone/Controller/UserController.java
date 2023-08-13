package com.LinkTree.LinktreeClone.Controller;

import com.LinkTree.LinktreeClone.Model.User;
import com.LinkTree.LinktreeClone.Service.LinkService;
import com.LinkTree.LinktreeClone.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LinkService linkService;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){

        return new ResponseEntity<>(userService.saveUser(user) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long id){

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK) ;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id){

        linkService.deleteDataByUserId(id);
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK) ;
    }


    @PutMapping("/{userId}")
    public  ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("userId") Long id){

        return new ResponseEntity<>(userService.updateUserData(user,id), HttpStatus.OK);
    }

    @GetMapping("/links/{userId}")
    public ResponseEntity<?> getAllLinksWithUserId(@PathVariable("userId") Long id){

        return new ResponseEntity<>(linkService.getLinksByUserId(id) , HttpStatus.OK);
    }

}
