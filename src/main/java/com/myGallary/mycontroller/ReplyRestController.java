package com.myGallary.mycontroller;


import com.myGallary.entity.GallaryReply;
import com.myGallary.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReplyRestController {

    @Autowired
    ReplyService replyService;

    //@PostMapping("/gallery/reply/{id}")
    @RequestMapping(value = "/gallary/reply/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public void ReplyCreate(@PathVariable Long id,
                            @RequestBody GallaryReply gallaryReply) {

        replyService.createReply(id,gallaryReply);
    }
}
