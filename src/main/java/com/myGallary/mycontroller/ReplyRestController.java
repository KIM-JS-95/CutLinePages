package com.myGallary.mycontroller;


import com.myGallary.entity.GallaryReply;
import com.myGallary.service.ReplyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyRestController {

    private ReplyService replyService;

    @PostMapping("/gallery/reply")
    public void ReplyCreate(@RequestBody GallaryReply gallaryReply){

        replyService.createReply(gallaryReply);
    }
}
