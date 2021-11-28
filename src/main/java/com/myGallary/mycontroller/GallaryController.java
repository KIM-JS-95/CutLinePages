package com.myGallary.mycontroller;


import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryDto;
import com.myGallary.entity.Review;
import com.myGallary.entity.ReviewDTO;
import com.myGallary.service.GallaryService;
import com.myGallary.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class GallaryController {

    @Autowired
    private GallaryService gallaryService;

    @Autowired
    private ReviewService reviewService;

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


    // 게임 추천글 검색
    @GetMapping("/gallary/view")
    private String view(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                        @RequestParam("title") String title){

        // gallaryService.search(title);

        List<Gallary> gallaryDtos = gallaryService.search(title);

        Integer[] pagelist = gallaryService.getSearchList(pageNum, title);


        model.addAttribute("boardList",gallaryDtos);
        model.addAttribute("pageList",pagelist);

        return "home/guest";
    }



    /* -------------------------------------------------------  관리자 페이지 ----------------------------------------------------------*/

    // TODO: 관리자 리뷰 링크 "/adminpages/review"
    @GetMapping("/adminpages/review")
    private String review(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                          @RequestParam("title") String title){

        // gallaryService.search(title);

        List<Gallary> gallaryDtos = gallaryService.search(title);

        Integer[] pagelist = gallaryService.getSearchList(pageNum, title);


        model.addAttribute("boardList",gallaryDtos);
        model.addAttribute("pageList",pagelist);

        return "adminpages/review";
    }

    // TODO: 관리자 리뷰 작성 링크 "/adminpages/reviewcreate"
    @GetMapping("/adminpages/reviewcreate")
    public String reviewcreate(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {

        List<Review> gallaryDtos = reviewService.getBoardlist(pageNum);
        Integer[] pagelist = gallaryService.getPageList(pageNum);

        model.addAttribute("boardList", gallaryDtos);
        model.addAttribute("pageList", pagelist);

        return "adminpages/reviewcreate";
    }

    // TODO: 관리자 리뷰 디테일 링크 "/adminpages/reviewdetail"
    @GetMapping("/adminpages/reviewdetail")
    private String reviewdetail(@PathVariable("id") Long index, Model model) {
        Review review = reviewService.findIndex(index).orElseThrow(()
                -> new IllegalArgumentException("error"));

        model.addAttribute("details", review);
        return "adminpages/reviewdetail";
    }

}
