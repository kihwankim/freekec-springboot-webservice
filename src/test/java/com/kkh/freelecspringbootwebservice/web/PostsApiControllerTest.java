package com.kkh.freelecspringbootwebservice.web;

import com.kkh.freelecspringbootwebservice.domain.posts.Posts;
import com.kkh.freelecspringbootwebservice.domain.posts.PostsRepository;
import com.kkh.freelecspringbootwebservice.web.dto.PostUpdateRequsetDto;
import com.kkh.freelecspringbootwebservice.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = String.format("http://localhost:%s/api/v1/posts", port);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertEquals(title, all.get(0).getTitle());
        assertEquals(content, all.get(0).getContent());
    }

    @Test
    public void Posts_수정된다() throws Exception {
        // given
        Posts savedPost = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long updateId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequsetDto requestDto = PostUpdateRequsetDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = String.format("http://localhost:%s/api/v1/posts/%s", port, updateId);
        HttpEntity<PostUpdateRequsetDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertEquals(expectedTitle, all.get(0).getTitle());
        assertEquals(expectedContent, all.get(0).getContent());
    }
}
