package com.myGallary.mycontroller;

import com.myGallary.entity.Gallary;
import com.myGallary.service.GallaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@RunWith(SpringRunner.class)
@WebMvcTest(GallaryRestController.class)
public class GallaryRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GallaryService gallaryService;

    @Test
    public void create() throws Exception{
        String title = "Hello";
        String content = "hi";
        String link = "잡담";

        Gallary gallary = Gallary.builder()
                .id(1L)
                .title(title)
                .content(content)
                .link(link)
                .build();

        given(gallaryService.create(gallary)).willReturn(gallary);

        mvc.perform(post("/gallary/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\" : \"1\", \"content\" : \"1\", \"type\" : \"잡담\"}"))
                .andExpect(status().isOk());

    }

}