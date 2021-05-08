package com.bishe.exam.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-05-06
 */
public class Exam implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ExcelProperty(index = 1)
    private String grade;

    @ExcelProperty(index=0)
    private String major;

    @ExcelProperty(index=2)
    private String name;

    @TableField("startTime")
    @ExcelProperty(index=3)
    private Date startTime;

    @TableField("endTime")
    @ExcelProperty(index=4)
    private Date endTime;

    private String subject;

    private String examcol;

    private Integer duration;

    public Exam() {
    }

    public Exam(Exam exam) {
        BeanUtils.copyProperties(exam, this);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExamcol() {
        return examcol;
    }

    public void setExamcol(String examcol) {
        this.examcol = examcol;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Exam{" +
        "id=" + id +
        ", grade=" + grade +
        ", major=" + major +
        ", name=" + name +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", subject=" + subject +
        ", examcol=" + examcol +
        ", duration=" + duration +
        "}";
    }
}
