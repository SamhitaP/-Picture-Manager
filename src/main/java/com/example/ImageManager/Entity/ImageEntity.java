package com.example.ImageManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

// Response Entity for cat picture file
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    private String id;
    private String name;
    private byte[] image;

}
