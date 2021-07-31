package com.kkh.freelecspringbootwebservice.domain.posts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PostsTest {

    @Test
    void update후_미변경_테스트() {
        // given
        String beforeTitle = "beforeTitle";
        String beforeContent = "beforeContent";

        Posts posts = Posts.builder()
                .title(beforeTitle)
                .content(beforeContent)
                .author("author")
                .build();

        String changedTitle = "next title";
        String changedContent = "next content";

        // when
        posts.update(changedTitle, changedContent);

        // then
        assertNotEquals(beforeTitle, posts.getTitle());
        assertNotEquals(beforeContent, posts.getContent());
    }

    @Test
    void update_성공_테스트() throws Exception {
        // given
        String beforeTitle = "beforeTitle";
        String beforeContent = "beforeContent";

        Posts posts = Posts.builder()
                .title(beforeTitle)
                .content(beforeContent)
                .author("author")
                .build();

        String changedTitle = "next title";
        String changedContent = "next content";

        // when
        posts.update(changedTitle, changedContent);

        // then
        assertEquals(changedTitle, posts.getTitle());
        assertEquals(changedContent, posts.getContent());
    }
}