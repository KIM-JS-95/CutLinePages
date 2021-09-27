package com.myGallary.mycontroller;

import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryDto;
import com.myGallary.service.GallaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 모든 게시글은 @Controller에서 담당

@RestController
public class GallaryRestController {

    @Autowired
    GallaryService gallaryService;

    // 게시글 작성
    @PostMapping("/gallary/create")
    private void create(@RequestBody Gallary gallary){
        gallaryService.create(gallary);

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

    // 게시글 검색
//    @GetMapping("/gallary/view/{id}")
//    private void view(@PathVariable Long id){
//        gallaryService.view(id);
//    }

    // 게시글 검색
//    @GetMapping("/gallary/view/?title={title}")
//    private void view(@PathVariable("title") String title){
//        gallaryService.search(title);
//    }



}
