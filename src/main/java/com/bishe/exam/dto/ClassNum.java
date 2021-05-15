package com.bishe.exam.dto;

public class ClassNum {

    private Integer examId;
    private String classNum;

    private String major;

    public ClassNum() {
    }

    public ClassNum(String classNum, String major, Integer examId) {
        this.classNum = classNum;
        this.major = major;
        this.examId = examId;
    }

    public String getClassNum() {
        return classNum;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
