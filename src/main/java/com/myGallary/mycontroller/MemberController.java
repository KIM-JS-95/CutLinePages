package com.myGallary.mycontroller;

import javax.annotation.Resource;
import javax.validation.Valid;


import com.myGallary.entity.Account;
import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryDto;
import com.myGallary.entity.Review;
import com.myGallary.service.GallaryService;
import com.myGallary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://www.cutlinepage.ml:8081")
@Controller
public class MemberController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("account", new Account());
        return "auth/registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(Model model, @Valid Account account, BindingResult bindingResult) {
        try {

            //< check the user name already exist or not
            Account userExists = userService.getUserByUsername(account.getUsername());

            if (userExists != null) {
                bindingResult.rejectValue("username", "error.user", "There is already a user registered with the user name provided");
            }

            //< check the password
            if (!account.getPassword().equals(account.getConfirmPassword())) {
                bindingResult.rejectValue("confirmPassword", "error.user", "Password not matched");
            }

            if (bindingResult.hasErrors()) {
                log.error("[CutLine] : " + bindingResult.getFieldError().toString());
            } else {
                //< save the user information
                userService.setUser(account);

                //< set the user information
                model.addAttribute("user", new Account());
                model.addAttribute("successMessage", "User has been registered successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("successMessage", "FAIL : " + e.getMessage());
        }

        return "auth/registration";
    }


    @RequestMapping(value = {"/", "/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Model model) {
        return "auth/login";
    }

    // 로그아웃 api
    @RequestMapping(value = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void logout() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = null;

        try {
            account = userService.getUserByUsername(auth.getName());
        } catch (Exception e) {
            log.error("[ykson]" + e.getMessage());
        }

        account.setIsActive(false);

    }


    @Autowired
    private GallaryService gallaryService;

    @GetMapping("/home")
    public String home(Model model) {

        // TODO: 현재 유저의 정보 가져오기
        Account account=Getuser();

        // TODO: 유저 이름 side.html으로 보내기
        nick(model,account.getUsername());

        return "index";
    }


    //	추천 게임 등록 화면
    @GetMapping("/home/user")
    public String userHome(Model model) {

        // TODO: 현재 유저의 정보 가져오기
        Account account=Getuser();

        // TODO: 유저 이름 side.html으로 보내기
        nick(model,account.getUsername());

        return "home/user";
    }

    //	게임 리스트 확인 화면
    @GetMapping("/home/guest")
    public String guestHome(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {

        List<GallaryDto> gallaryDtos = gallaryService.getBoardlist(pageNum);
        Integer[] pagelist = gallaryService.getPageList(pageNum);

        // TODO: 현재 유저의 정보 가져오기
        Account account=Getuser();

        // TODO: 유저 이름 side.html으로 보내기
        nick(model,account.getUsername());

        model.addAttribute("boardList", gallaryDtos);
        model.addAttribute("pageList", pagelist);

        return "home/guest";
    }

    public Account Getuser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = null;

        try {
            account = userService.getUserByUsername(auth.getName());
        } catch (Exception e) {
            log.error("[CutLine]" + e.getMessage());
        }
        return account;
    }

    public Model nick(Model model, String nickname){
        model.addAttribute("username", "" + nickname);
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        return model;
    }
}
