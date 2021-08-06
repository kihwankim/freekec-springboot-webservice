package com.kkh.freelecspringbootwebservice.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    void 게시글_불러오기() throws Exception {
        // given
        String title = "test title";
        String content = "test title";
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("kkh@gmail.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertEquals(title, posts.getTitle());
        assertEquals(content, posts.getContent());
    }

    @Test
    void BaseTimeEntity_등록() throws Exception {
        // given
        LocalDateTime prevDate = LocalDateTime.of(2021, 7, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("conten")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts post = postsList.get(0);
        assertThat(post.getCreateDate()).isAfter(prevDate);
        assertThat(post.getModifiedDate()).isAfter(prevDate);
    }
}