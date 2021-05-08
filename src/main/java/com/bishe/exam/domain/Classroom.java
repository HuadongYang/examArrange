package com.bishe.exam.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Classroom implements Serializable  {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ExcelProperty(index=0)
    private String name;

    @ExcelProperty(index=1)
    private String area;

    @ExcelProperty(index=2)
    private String type;

    @ExcelProperty(index=3)
    @TableField("floorNum")
    private String floorNum;

    @ExcelProperty(index=5)
    @TableField("numTotal")
    private Integer numTotal;

    @ExcelProperty(index=6)
    @TableField("numExam")
    private Integer numExam;

    @ExcelProperty(index=7)
    @TableField("canClass")
    private String canClass;

    @ExcelProperty(index=8)
    @TableField("canBorrow")
    private String canBorrow;

    @ExcelProperty(index=9)
    @TableField("canExam")
    private String canExam;

    public Classroom() {
    }

    public Classroom(Classroom classroom) {
        BeanUtils.copyProperties(classroom, this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getNumTotal() {
        return numTotal;
    }

    public void setNumTotal(Integer numTotal) {
        this.numTotal = numTotal;
    }

    public Integer getNumExam() {
        return numExam;
    }

    public void setNumExam(Integer numExam) {
        this.numExam = numExam;
    }

    public String getCanClass() {
        return canClass;
    }

    public void setCanClass(String canClass) {
        this.canClass = canClass;
    }

    public String getCanBorrow() {
        return canBorrow;
    }

    public void setCanBorrow(String canBorrow) {
        this.canBorrow = canBorrow;
    }

    public String getCanExam() {
        return canExam;
    }

    public void setCanExam(String canExam) {
        this.canExam = canExam;
    }

    @Override
    public String toString() {
        return "Classroom{" +
        "id=" + id +
        ", name=" + name +
        ", area=" + area +
        ", type=" + type +
        ", floorNum=" + floorNum +
        ", numTotal=" + numTotal +
        ", numExam=" + numExam +
        ", canClass=" + canClass +
        ", canBorrow=" + canBorrow +
        ", canExam=" + canExam +
        "}";
    }
}
