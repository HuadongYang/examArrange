package com.bishe.exam.dto;

import com.bishe.exam.domain.Student;


public class StudentDTO extends Student {
    private Integer seat;
    private String classroomName;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        super(student);
    }

    public StudentDTO(Student student, Integer seat, String classroomName) {
        super(student);
        this.seat = seat;
        this.classroomName = classroomName;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
