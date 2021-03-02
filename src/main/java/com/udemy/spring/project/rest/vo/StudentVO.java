package com.udemy.spring.project.rest.vo;

public class StudentVO {

    private Integer no;
    private String name;
    private Integer grade;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}