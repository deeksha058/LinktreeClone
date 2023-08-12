package com.LinkTree.LinktreeClone.Controller;

import com.LinkTree.LinktreeClone.Model.Link;
import com.LinkTree.LinktreeClone.Model.User;
import com.LinkTree.LinktreeClone.Service.LinkService;
import com.LinkTree.LinktreeClone.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/links")
public class LinkController {


    @Autowired
    private LinkService linkService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> saveLoadsData(@RequestBody Link links){

        try {
            User user = userService.getUserById(links.getUserId());

            linkService.saveLinksData(links);
            return  new ResponseEntity<>("Data added successfully!" , HttpStatus.CREATED);
        }catch (Exception e) {

            return  new ResponseEntity<>("User with UserId " + links.getUserId() +" not present int the Database!" , HttpStatus.OK);
        }
    }

    @GetMapping("/{linkId}")
    public ResponseEntity<Link> loadsDataById(@PathVariable("linkId") Long id){

        Link dataById = linkService.getDataById(id);
        return new ResponseEntity<>(dataById, HttpStatus.OK);
    }

    @PutMapping("/{linkId}")
    public ResponseEntity<String> updateLoadsData(@RequestBody Link link , @PathVariable("linkId") Long id ) {

        Link newLinkData = linkService.updateLinkData(link, id);
        if (newLinkData == null){
            return  new ResponseEntity<>("Data with  Id " + id +  " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<String> deleteLoadsData(@PathVariable("loadId") Long id) {

        Link deleteLoadsData = linkService.deleteLinkData(id);
        if(deleteLoadsData == null){
            return  new ResponseEntity<>("Data with this Id " + id  + " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Deleted Successfully!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> loadsData(){

        return new ResponseEntity<>(linkService.getAllData() , HttpStatus.OK);
    }
}
