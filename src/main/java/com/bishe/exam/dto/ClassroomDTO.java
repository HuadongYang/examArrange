package com.bishe.exam.dto;

import com.bishe.exam.domain.Classroom;
import com.bishe.exam.domain.Teacher;

import java.util.List;

public class ClassroomDTO extends Classroom {

    private Teacher teacher;
    private List<StudentDTO> studentDTOs;

    public ClassroomDTO(Classroom classroom, Teacher teacher, List<StudentDTO> studentDTOs) {
        super(classroom);
        this.teacher = teacher;
        this.studentDTOs = studentDTOs;
    }
}
