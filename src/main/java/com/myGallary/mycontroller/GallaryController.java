package com.myGallary.mycontroller;


import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryDto;
import com.myGallary.service.GallaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class GallaryController {

    @Autowired
    private GallaryService gallaryService;

    // 게시판
//    @GetMapping("/gallary")
//    public String gallary(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
//
//        List<GallaryDto> gallaryDtos =  gallaryService.getBoardlist(pageNum);
//        Integer[] pagelist = gallaryService.getPageList(pageNum);
//
//
//        model.addAttribute("boardList",gallaryDtos);
//        model.addAttribute("pageList",pagelist);
//        return "gallary";
//    }


    // 게시판 수정
    @GetMapping("/gallary/update/{id}")
    public String modify(Model model, @PathVariable("id") Long index) {
        Gallary gallary = gallaryService.findIndex(index).orElseThrow(()
                -> new IllegalArgumentException("error"));

        model.addAttribute("details", gallary);
        return "home/gallary/modipage";
    }


    // 게시판 상세 내용 출력
    @GetMapping("/gallary/view/{id}")
    private String find(@PathVariable("id") Long index, Model model) {
        Gallary gallary = gallaryService.findIndex(index).orElseThrow(()
                -> new IllegalArgumentException("error"));

        model.addAttribute("details", gallary);
        return "home/gallary/gallarydetail";
    }


    // 게시글 검색
    @GetMapping("/gallary/view")
    private String view(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                        @RequestParam("title") String title){

        // gallaryService.search(title);

        List<Gallary> gallaryDtos = gallaryService.search(title);

        System.out.println(title);

        Integer[] pagelist = gallaryService.getSearchList(pageNum, title);


        model.addAttribute("boardList",gallaryDtos);
        model.addAttribute("pageList",pagelist);

        return "home/guest";
    }




}
