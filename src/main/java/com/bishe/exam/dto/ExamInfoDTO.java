package com.bishe.exam.dto;

import com.bishe.exam.domain.Exam;

public class ExamInfoDTO extends Exam {
    private String teachName;
    private String classroom;
    private Integer classroomNum;
    private String classNum;
    private Integer relationId;

    public ExamInfoDTO(Exam exam, String teachName,String classroom,Integer classroomNum, String classNum, Integer relationId) {
        super(exam);
        this.teachName = teachName;
        this.classNum = classNum;
        this.classroom = classroom;
        this.classroomNum = classroomNum;
        this.relationId = relationId;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getClassroomNum() {
        return classroomNum;
    }

    public void setClassroomNum(Integer classroomNum) {
        this.classroomNum = classroomNum;
    }
}
