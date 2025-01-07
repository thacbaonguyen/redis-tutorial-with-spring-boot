package com.thacbao.spring.redis_tutorial.dto;

public class ExerciseDto {
    private String code;
    private String title;
    private String paper;
    private String input;
    private String output;

    public ExerciseDto(String code, String title, String paper, String input, String output) {
        this.code = code;
        this.title = title;
        this.paper = paper;
        this.input = input;
        this.output = output;
    }

    public ExerciseDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
