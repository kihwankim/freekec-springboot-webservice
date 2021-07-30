package com.kkh.freelecspringbootwebservice.web.dto;


import lombok.Data;

@Data
public class HelloResponseDto {
    private String name;
    private int amount;

    public HelloResponseDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
