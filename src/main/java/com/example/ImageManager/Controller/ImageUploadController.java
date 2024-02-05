package com.example.ImageManager.Controller;

import com.example.ImageManager.Entity.ImageEntity;
import com.example.ImageManager.Service.CatPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// Controller handles CRUD operations for cat pictures
@RestController
@RequestMapping("/images")
public class ImageUploadController {

    @Autowired
    CatPictureService catPictureService;

    // get one cat picture
    @RequestMapping(value="/catpicture/one",method = RequestMethod.GET)
    public ResponseEntity<?> getImageById(@RequestParam("id") String id) {
        if(id == null)
            return new ResponseEntity<>("Invalid cat picture ID", HttpStatus.BAD_REQUEST);
        try {
           ImageEntity cp = catPictureService.getPictureById(id);
           return new ResponseEntity<>(cp,HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("No data found for the ID, error "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // get all cat pictures
    @RequestMapping(value="/catpicture/all",method=RequestMethod.GET)
    public ResponseEntity<?> getAllCatPictures() {
        try {
           List<ImageEntity> allCatPics = catPictureService.getAllCatPictures();
           return new ResponseEntity<>(allCatPics,HttpStatus.OK);
        } catch(NullPointerException e) {
           return new ResponseEntity<>("No cat pictures found, error "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // upload a new cat picture
    @RequestMapping(value="/catpicture/upload",method = RequestMethod.POST)
    public ResponseEntity<?> uploadCatPicture(@RequestParam("image") MultipartFile file) {
        if(file == null || file.isEmpty()) {
            return new ResponseEntity<>("Cat picture file is missing ", HttpStatus.BAD_REQUEST);
        }
        try {
            catPictureService.uploadPicture(file);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(IOException e) {
            return new ResponseEntity<>("Upload failed, error "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete a cat picture
    @RequestMapping(value="/catpicture/delete",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCatPicture(@RequestParam("id") String id) {
        if(id == null)
            return new ResponseEntity<>("Invalid cat picture ID", HttpStatus.BAD_REQUEST);
        try {
            catPictureService.deleteCatPicture(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete cat picture with ID "+id+" , error "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // update an existing cat picture
    @RequestMapping(value="/catpicture/update",method=RequestMethod.PUT)
    public ResponseEntity<?> updateCatPicture(@RequestParam("id") String id,@RequestParam("newimage") MultipartFile file) {
        if(id == null || file == null || file.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            catPictureService.updateCatPicture(id,file);
        } catch (IOException e) {
            return new ResponseEntity<>("Update cat picture failed, with error "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return null;
    }

}
