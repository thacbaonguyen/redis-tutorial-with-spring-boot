package com.thacbao.spring.redis_tutorial.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String title;
    private String paper;
    private String input;
    private String output;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getPaper() {
        return paper;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Exercise(Integer id, String code, String title, String paper, String input, String output) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.paper = paper;
        this.input = input;
        this.output = output;
    }

    public Exercise() {
    }
}
