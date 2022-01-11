package com.myGallary.service;


import com.myGallary.Repository.GallaryReplyRepository;
import com.myGallary.Repository.GallaryRepository;
import com.myGallary.entity.Account;
import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryReply;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    @Autowired
    private GallaryReplyRepository gallaryReplyRepository;

    @Autowired
    private GallaryRepository gallaryRepository;

    public List<GallaryReply> findAll() {

        List<GallaryReply> gallaryReply = gallaryReplyRepository.findAll();
        return gallaryReply;
    }

    public void createReply(Long id, GallaryReply gallaryReply, Account account){

        // TODO: 불러온 데이터 정보를
        Gallary gallary = gallaryRepository.findById(id).orElse(null);

        gallaryReply.setGallary(gallary);
        gallaryReplyRepository.save(gallaryReply);
    }
}
