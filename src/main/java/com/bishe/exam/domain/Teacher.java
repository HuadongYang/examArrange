package com.bishe.exam.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-05-06
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID=1L;


    @TableId(value = "id")
    @ExcelProperty(index=2)
    private Integer id;

    @ExcelProperty(index=0)
    private String major;
    @ExcelProperty(index=1)
    private String name;
    @ExcelProperty(index=3)
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        "id=" + id +
        ", major=" + major +
        ", name=" + name +
        ", phone=" + phone +
        "}";
    }
}
