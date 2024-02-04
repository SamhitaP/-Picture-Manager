package com.example.ImageManager.ControllerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ImageUploadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
    private MockMvc mockMvc;

    private String URI = "/images";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Disabled
    @Test
    public void uploadImageTestSuccess() throws Exception {
        String path = "src/main/resources/static/media.png";
        File fi = new File(path);
        String originalFileName = path.substring(path.lastIndexOf("/")+1,path.length());
        MockMultipartFile mockedFile = new MockMultipartFile("image", originalFileName, "multipart/form-data", Files.readAllBytes(fi.toPath()));
        MvcResult result = mockMvc.perform(multipart(URI+"/catpicture/upload")
                                .file(mockedFile)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .accept(MediaType.ALL))
                                .andReturn();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(201, result.getResponse().getStatus());
    }


    @Disabled
    @Test
    void uploadImageBadRequest() throws Exception {

        MockMultipartFile invalidFile  = new MockMultipartFile("image", null, "multipart/form-data", new byte[0]);
        MvcResult result = mockMvc.perform(multipart(HttpMethod.POST,URI+"/catpicture/upload")
                        .file(invalidFile)
                        .accept(MediaType.ALL))
                        .andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void getImageValidId() throws Exception {
        String validId = "65bf95bc9765a86be6600240";

        MvcResult result = mockMvc.perform(get(URI+"/catpicture/one")
                .param("id",validId))
                .andReturn();


        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    void getImageByInvalidId() throws Exception {
        String invalidId = null;

        MvcResult result = mockMvc.perform(get(URI+"/catpicture/one")
                        .param("id",invalidId))
                .andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus());
        Assertions.assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void getAllCatPicturesSuccess() throws Exception {
        MvcResult result = mockMvc.perform(get("/images/catpicture/all"))
                           .andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertFalse( result.getResponse().getContentAsString().isEmpty());
    }

    @Disabled
    @Test
    public void updateImageById() throws Exception {
        String path = "src/main/resources/static/media.png";
        String validId = "65bf95bc9765a86be6600240";
        File fi = new File(path);
        String originalFileName = path.substring(path.lastIndexOf("/")+1,path.length());
        MockMultipartFile mockedFile = new MockMultipartFile("newimage", originalFileName, "multipart/form-data", Files.readAllBytes(fi.toPath()));

       MvcResult result = mockMvc.perform(multipart(HttpMethod.PUT,"/images/catpicture/update")
                        .file(mockedFile)
                        .param("id",validId))
                        .andReturn();
       Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void updateImageInvalidId()throws Exception {
        String path = "src/main/resources/static/media.png";
        String validId = "abc";
        File fi = new File(path);
        String originalFileName = path.substring(path.lastIndexOf("/")+1,path.length());
        MockMultipartFile mockedFile = new MockMultipartFile("newimage", originalFileName, "multipart/form-data", Files.readAllBytes(fi.toPath()));

        MvcResult result = mockMvc.perform(multipart(HttpMethod.PUT,"/images/catpicture/update")
                        .file(mockedFile)
                        .param("id",validId))
                .andReturn();
        Assertions.assertNotEquals(200,result.getResponse().getStatus());
    }

    @Disabled
    @Test
    public void deleteImageId() throws Exception {
        String validId = "65bfabdc43366e31b628cdee";

        MvcResult result = mockMvc.perform(delete("/images/catpicture/delete")
                .param("id",validId))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void deleteImageByInvalidId() throws Exception {
        String invalidId =null;

        MvcResult result = mockMvc.perform(delete("/images/catpicture/delete")
                .param("id",invalidId))
                .andReturn();
        Assertions.assertEquals(400,result.getResponse().getStatus());
    }

}
