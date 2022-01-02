package com.myGallary.service;


import com.myGallary.Repository.GallaryReplyRepository;
import com.myGallary.Repository.GallaryRepository;
import com.myGallary.entity.GallaryReply;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    @Autowired
    private GallaryReplyRepository gallaryReplyRepository;

    public List<GallaryReply> findAll() {

        List<GallaryReply> gallaryReply = gallaryReplyRepository.findAll();
        return gallaryReply;
    }

    public void createReply(GallaryReply gallaryReply){
        gallaryReplyRepository.save(gallaryReply);
    }
}
