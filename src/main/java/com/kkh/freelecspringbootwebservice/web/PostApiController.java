package com.kkh.freelecspringbootwebservice.web;

import com.kkh.freelecspringbootwebservice.service.posts.PostService;
import com.kkh.freelecspringbootwebservice.web.dto.PostUpdateRequsetDto;
import com.kkh.freelecspringbootwebservice.web.dto.PostsResponseDto;
import com.kkh.freelecspringbootwebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequsetDto requsetDto) {
        return postService.update(id, requsetDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
