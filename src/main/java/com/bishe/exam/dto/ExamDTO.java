package com.bishe.exam.dto;

import com.bishe.exam.domain.Exam;

import java.util.List;

public class ExamDTO extends Exam {

    private List<ClassroomDTO> classrooms;


    public ExamDTO(Exam exam, List<ClassroomDTO> classrooms) {
        super(exam);
        this.classrooms = classrooms;
    }

    public List<ClassroomDTO> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<ClassroomDTO> classrooms) {
        this.classrooms = classrooms;
    }
}
