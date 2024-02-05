package com.example.ImageManager.Service;

import com.example.ImageManager.Dao.CatPictureInterface;
import com.example.ImageManager.Entity.CatPicture;
import com.example.ImageManager.Entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CatPictureService {

   @Autowired
   private CatPictureInterface catPictureRepo;

   // implements get a cat picture
    public ImageEntity getPictureById(String id) throws NoSuchElementException {
        Optional<CatPicture> image = catPictureRepo.findById(id);
      try {
          return convertDatatoImageEntity(image.get());
      } catch(NoSuchElementException ne) {
          throw new NoSuchElementException();
      }
    }

    // implements get all cat pictures
    public List<ImageEntity> getAllCatPictures() throws NullPointerException{
        List<ImageEntity> catPics = new ArrayList<>();

        try {
            Iterable<CatPicture> allPics = catPictureRepo.findAll();
            for (CatPicture pic : allPics) {
                catPics.add(convertDatatoImageEntity(pic));
            }
        }catch(NullPointerException ne) {
            System.out.println("No cat pictures found in the repository");
            throw new NullPointerException();
        } catch(Exception e) {
            System.out.println("Exception in get all cat pictures "+e.getMessage());
            throw new NullPointerException();
        }
         return catPics;
    }


    // delete a cat picture
    public void deleteCatPicture(String id) throws IllegalArgumentException,NoSuchElementException {
        try {
            if(catPictureRepo.existsById(id))
              catPictureRepo.deleteById(id);
            else throw new NoSuchElementException();
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    // update a cat picture
    public void updateCatPicture(String id,MultipartFile file) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            String fn = fileName;
            assert fileName != null;
            if(fileName.contains("/"))
             fn = fileName.substring(fileName.lastIndexOf("/"), fileName.length()-1);
            Optional<CatPicture> newEntry = catPictureRepo.findById(id);
            CatPicture cpEntry = newEntry.get();
            cpEntry.setName(fn);
            cpEntry.setImage(file.getBytes());
            cpEntry.setUpdatedDate(new Date());
            catPictureRepo.save(cpEntry);
        } catch(Exception e) {
            throw new IOException();
        }
    }

    // upload a cat picture
    public void uploadPicture(MultipartFile file) throws IOException {
        try {
            CatPicture pic = new CatPicture();
            System.out.println("file name "+file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            if(fileName.contains("/"))
              pic.setName(fileName.substring(fileName.lastIndexOf("/"), fileName.length()-1));
            else
              pic.setName(fileName);
            pic.setImage(file.getBytes());
            pic.setCreatedDate(new Date());
            catPictureRepo.save(pic);
        } catch (IOException io) {
            System.out.println("error is "+io.getMessage());
            throw new IOException();
        }
    }

    // convert CatPicture DB object to response object ImageEntity
    public ImageEntity convertDatatoImageEntity(CatPicture cp) throws NoSuchElementException {
        ImageEntity ie = new ImageEntity();
        try {
            ie.setId(cp.getId());
            ie.setName(cp.getName());
            ie.setImage(cp.getImage());
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        return ie;
    }

}

