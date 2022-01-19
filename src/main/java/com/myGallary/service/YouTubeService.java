package com.myGallary.service;

import com.myGallary.Repository.YoutubeTableRepository;
import com.myGallary.entity.YoutubeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class YouTubeService {

    @Autowired
    private YoutubeTableRepository youtubeTableRepository;

    public List<YoutubeTable> getVideoInfo(){
        return youtubeTableRepository.findAll();
    }
}
