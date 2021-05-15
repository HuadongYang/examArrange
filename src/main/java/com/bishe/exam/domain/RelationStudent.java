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
 * @since 2021-05-10
 */
public class RelationStudent implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("relationId")
    private Integer relationId;

    @TableField("studentId")
    private String studentId;

    @TableField("`order`")
    private Integer order;

    public RelationStudent() {
    }

    public RelationStudent(Integer relationId, String studentId, Integer order) {
        this.relationId = relationId;
        this.studentId = studentId;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "RelationStudent{" +
        "id=" + id +
        ", relationId=" + relationId +
        ", studentId=" + studentId +
        ", order=" + order +
        "}";
    }
}
