package com.bishe.exam.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Student;

import java.util.List;

public class RelationDTO {

    private Exam exam;

    List<Student> students;


}
