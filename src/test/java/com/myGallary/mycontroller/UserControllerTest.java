package com.myGallary.mycontroller;

import com.myGallary.Repository.GallaryRepository;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserControllerTest {

    @Mock
    GallaryRepository gallaryRepository;

    @Test
    public void error(){
        assertThat(gallaryRepository.findById(1L),is("kim"));
    }

}