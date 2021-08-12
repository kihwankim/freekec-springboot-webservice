package com.kkh.freelecspringbootwebservice.web;

import com.kkh.freelecspringbootwebservice.config.auth.LoginUser;
import com.kkh.freelecspringbootwebservice.config.auth.dto.SessionUser;
import com.kkh.freelecspringbootwebservice.service.posts.PostService;
import com.kkh.freelecspringbootwebservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostService postService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
