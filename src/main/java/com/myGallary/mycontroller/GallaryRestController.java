package com.myGallary.mycontroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myGallary.entity.Account;
import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryDto;
import com.myGallary.service.GallaryService;
import com.myGallary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


// 모든 게시글은 @Controller에서 담당

@RestController
public class GallaryRestController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GallaryService gallaryService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    // 게시글 작성
    @PostMapping("/gallary/create")
    private void create(@RequestBody Gallary gallary) throws IOException {
    System.out.println(gallary.getTitle());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = null;

        try {
            // search user by name
            account = userService.getUserByUsername(auth.getName());
        } catch (Exception e) {
            log.error("[CutLine]" + e.getMessage());
        }

        gallaryService.create(gallary, account);

    }

    // 게시글 수정
    // 수정용 엔티티 추가
    @PutMapping("/gallary/update/{id}")
    private void update(@PathVariable("id") Long id,
                        @RequestBody GallaryDto gallary){

        gallaryService.update(id, gallary);
    }

    // 게시글 삭제
    @DeleteMapping("/gallary/delete/{id}")
    private void delete(@PathVariable("id") Long id){
        gallaryService.delete(id);
    }




}
