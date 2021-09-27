package com.myGallary.service;

import com.myGallary.Repository.GallaryRepository;
import com.myGallary.entity.Gallary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class GallaryServiceTest {

    @Mock
    private GallaryRepository gallaryRepository;

    @Test
    public void create() {
        Gallary mockgal = Gallary.builder()
                .id(1L)
                .title("123")
                .content("123")
                .build();

        given(gallaryRepository.save(mockgal)).willReturn(mockgal);
    }

    @Test
    public void update(){

        Gallary mockgal = Gallary.builder()
                .id(1L)
                .title("123")
                .content("123")
                .build();

        given(gallaryRepository.save(mockgal)).willReturn(mockgal);

        mockgal.setContent("111");

        given(gallaryRepository.save(mockgal)).willReturn(mockgal);

        assertThat(mockgal.getContent(), is("111"));

    }


}