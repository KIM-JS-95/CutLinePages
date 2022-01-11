package com.myGallary.mycontroller;


import com.myGallary.entity.Account;
import com.myGallary.entity.GallaryReply;
import com.myGallary.service.ReplyService;
import com.myGallary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class ReplyRestController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReplyService replyService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    //@PostMapping("/gallery/reply/{id}")
    @RequestMapping(value = "/gallary/reply/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public void ReplyCreate(@PathVariable Long id,
                            @RequestBody GallaryReply gallaryReply) {

        //TODO : 회원 정보 호출
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = null;

        try {
            // search user by name
            account = userService.getUserByUsername(auth.getName());
        } catch (Exception e) {
            log.error("[CutLine]" + e.getMessage());
        }

        replyService.createReply(id,gallaryReply, account);
    }
}
