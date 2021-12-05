package com.myGallary.mycontroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myGallary.entity.Review;
import com.myGallary.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class ReviewRestController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReviewService reviewService;

    // 게시글 작성
    @PostMapping("/adminpages/create")
    @JsonProperty
    private void create(@RequestBody Review review){

        reviewService.create(review);
    }

    // 게시글 수정
    // 수정용 엔티티 추가
    @PutMapping("/adminpages/update/{id}")
    private void update(@PathVariable("id") Long id, @RequestBody(required = false) Review review){

        reviewService.update(id, review);
    }

    // 게시글 삭제
    @DeleteMapping("/adminpages/delete/{id}")
    private void delete(@PathVariable("id") Long id){

        reviewService.delete(id);
    }


}
