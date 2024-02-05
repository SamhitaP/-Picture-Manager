package com.example.ImageManager.Dao;

import com.example.ImageManager.Entity.CatPicture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

//Interface to perform MongoDB operations using JPA
@Component
public interface CatPictureInterface extends MongoRepository<CatPicture,String> {
       Optional<CatPicture> findById(String id);

}
