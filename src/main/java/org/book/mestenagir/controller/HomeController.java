package org.book.mestenagir.controller;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;


@Controller
public class HomeController {


    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
            return "index";
    }


}
