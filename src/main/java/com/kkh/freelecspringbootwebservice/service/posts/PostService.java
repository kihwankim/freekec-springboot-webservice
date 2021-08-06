package com.kkh.freelecspringbootwebservice.service.posts;

import com.kkh.freelecspringbootwebservice.domain.posts.Posts;
import com.kkh.freelecspringbootwebservice.domain.posts.PostsRepository;
import com.kkh.freelecspringbootwebservice.web.dto.PostListResponseDto;
import com.kkh.freelecspringbootwebservice.web.dto.PostUpdateRequsetDto;
import com.kkh.freelecspringbootwebservice.web.dto.PostsResponseDto;
import com.kkh.freelecspringbootwebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final String NOT_EXIST_POST_MSG = "해당 게시글은 없습니다 : ";

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto) {
        return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequsetDto updateRequsetDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_POST_MSG + id));
        posts.update(updateRequsetDto.getTitle(), updateRequsetDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_POST_MSG + id));

        return new PostsResponseDto(entity);
    }

    public List<PostListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
