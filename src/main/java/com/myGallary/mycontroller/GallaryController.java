package com.myGallary.mycontroller;


import com.myGallary.entity.*;
import com.myGallary.service.GallaryService;
import com.myGallary.service.ReviewService;
import com.myGallary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    protected Logger log = LoggerFactory.getLogger(this.getClass());


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
                        @RequestParam("title") String title) {

        List<Gallary> gallaryDtos = gallaryService.search(title);

        Integer[] pagelist = gallaryService.getSearchList(pageNum, title);


        model.addAttribute("boardList", gallaryDtos);
        model.addAttribute("pageList", pagelist);

        return "home/guest";
    }



    /* -------------------------------------------------------  관리자 페이지 ----------------------------------------------------------*/


    // 리뷰 전체 출력
    @GetMapping("/adminpages/review")
    private String review(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {


        List<ReviewDTO> review = reviewService.getBoardlist(pageNum);
        Integer[] pagelist = reviewService.getPageList(pageNum);

        // TODO: 현재 유저의 정보 가져오기
        Account account = Getuser();
        if (account != null) {
            // TODO: 유저 이름 side.html으로 보내기
            nick(model, account.getUsername());
        }

        model.addAttribute("boardList", review);
        model.addAttribute("pageList", pagelist);

        return "home/adminpages/review";
    }

    // 리뷰 검색 출력
    @GetMapping("/adminpages/review/search")
    private String reviewsearch(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum
    ,@RequestParam("title") String title) {

        List<Review> review = reviewService.search(title);
        Integer[] pagelist = reviewService.getSearchList(pageNum,title);

        // TODO: 현재 유저의 정보 가져오기
        Account account = Getuser();
        if (account != null) {
            // TODO: 유저 이름 side.html으로 보내기
            nick(model, account.getUsername());
        }

        model.addAttribute("boardList", review);
        model.addAttribute("pageList", pagelist);

        return "home/adminpages/review";
    }

    // TODO: 관리자 리뷰 작성 링크 "/adminpages/reviewcreate"
    @GetMapping("/adminpages/reviewcreate")
    public String reviewcreate(Model model) {


        // TODO: 현재 유저의 정보 가져오기
        Account account = Getuser();

        // TODO: 유저 이름 side.html으로 보내기
        nick(model, account.getUsername());

        return "home/adminpages/reviewcreate";
    }

    // TODO: 관리자 리뷰 디테일 링크 "/adminpages/reviewdetail"
    @GetMapping("/adminpages/view/{id}")
    private String reviewdetail(@PathVariable("id") Long index, Model model) {
        Review review = reviewService.findIndex(index).orElseThrow(()
                -> new IllegalArgumentException("error"));



        Account account = Getuser();
        if (account != null) {
            nick(model, account.getUsername());
        }
        model.addAttribute("details", review);
        return "home/adminpages/reviewdetail";
    }

    // TODO: 수정기능
    @GetMapping("/adminpages/update/{id}")
    private String reviewupdate(@PathVariable("id") Long index, Model model) {
        Review review = reviewService.findIndex(index).orElseThrow(()
                -> new IllegalArgumentException("error"));


        // TODO: 현재 유저의 정보 가져오기
        Account account = Getuser();

        // TODO: 유저 이름 side.html으로 보내기
        nick(model, account.getUsername());

        model.addAttribute("details", review);
        return "home/adminpages/adminmodipage";
    }

    public Account Getuser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = null;

        try {
            account = userService.getUserByUsername(auth.getName());
        } catch (Exception e) {
            log.error("[CutLine]" + e.getMessage());
        }
        return account;
    }

    public Model nick(Model model, String nickname) {
        model.addAttribute("username", "" + nickname);
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        return model;
    }
}
