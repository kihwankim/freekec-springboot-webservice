package com.kkh.freelecspringbootwebservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequsetDto {
    private String title;
    private String content;

    @Builder
    public PostUpdateRequsetDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
