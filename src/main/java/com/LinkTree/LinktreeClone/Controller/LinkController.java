package com.LinkTree.LinktreeClone.Controller;

import com.LinkTree.LinktreeClone.Exception.LinkNotFoundException;
import com.LinkTree.LinktreeClone.Exception.UserNotFoundException;
import com.LinkTree.LinktreeClone.Model.Link;
import com.LinkTree.LinktreeClone.Model.User;
import com.LinkTree.LinktreeClone.Service.LinkService;
import com.LinkTree.LinktreeClone.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/links")
public class LinkController {


    @Autowired
    private LinkService linkService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> saveLinkData(@RequestBody Link links)  throws MethodArgumentNotValidException {

        Long count = 0L;
        links.setLinkVisitCount(count);

        Optional<User> user = userService.getUserById(links.getUserId());
        if(user.isEmpty()) {
            return  new ResponseEntity<>(new UserNotFoundException().getMessage(), HttpStatus.OK);
        }

        try{

            linkService.saveLinksData(links);
            return  new ResponseEntity<>("Data added successfully!" , HttpStatus.CREATED);
        }catch (Exception e){

            return  new ResponseEntity<>( e.getLocalizedMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> linkData(){

        return new ResponseEntity<>(linkService.getAllData() , HttpStatus.OK);
    }

    @GetMapping("/get/{linkId}")
    public ResponseEntity<?> linkDataById(@PathVariable("linkId") Long id){

        Optional<Link> dataById = linkService.getDataById(id);
        if(dataById.isEmpty()) {
            return  new ResponseEntity<>(new LinkNotFoundException().getMessage(), HttpStatus.OK);
        }
        Long count = dataById.get().getLinkVisitCount();

        count++;
        dataById.get().setId(id);
        dataById.get().setLinkVisitCount(count);

        linkService.saveLinksData(dataById.get());
        return new ResponseEntity<>(dataById, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllLinksWithUserId(@PathVariable("userId") Long id){

        return new ResponseEntity<>(linkService.getLinksByUserId(id) , HttpStatus.OK);
    }

    @PutMapping("/{linkId}")
    public ResponseEntity<String> updateLinkData(@RequestBody Link link , @PathVariable("linkId") Long id ) {

        Link newLinkData = linkService.updateLinkData(link, id);
        if (newLinkData == null){
            return  new ResponseEntity<>("Data with  Id " + id +  " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<String> deleteLinkData(@PathVariable("linkId") Long id) {

        Optional<Link> deleteLoadsData = linkService.deleteLinkData(id);
        if(deleteLoadsData.isEmpty()) {
            return  new ResponseEntity<>(new LinkNotFoundException().getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Deleted Successfully!", HttpStatus.OK);
    }


}
