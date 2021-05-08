package com.bishe.exam.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-05-06
 */
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

    @ExcelProperty(index=0)
    private String id;
    @ExcelProperty(index=1)
    private String name;

    @ExcelProperty(index=3)
    private String level;

    @ExcelProperty(index=6)
    private String major;

    @ExcelProperty(index=4)
    private String classNum;

    public Student() {
    }

    public Student(Student student) {
        BeanUtils.copyProperties(student, this);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
