package com.example.ImageManager.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection="images")
@AllArgsConstructor
@NoArgsConstructor
public class CatPicture {

    @Id
    @Indexed(unique=true)
    private String id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private byte[] image;

}
