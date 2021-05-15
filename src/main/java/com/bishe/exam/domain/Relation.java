package com.bishe.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-05-07
 */
public class Relation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("examId")
    private Integer examId;

    @TableField("teacherId")
    private Integer teacherId;

    @TableField("classroomId")
    private Integer classroomId;

    public Relation() {
    }

    public Relation(Integer examId, Integer teacherId, Integer classroomId) {
        this.examId = examId;
        this.teacherId = teacherId;
        this.classroomId = classroomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }


    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    @Override
    public String toString() {
        return "Relation{" +
        "id=" + id +
        ", examId=" + examId +
        ", teacherId=" + teacherId +
        ", classroomId=" + classroomId +
        "}";
    }
}
