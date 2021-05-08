package com.bishe.exam.service.impl;

import com.bishe.exam.domain.Classroom;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Relation;
import com.bishe.exam.dao.RelationDao;
import com.bishe.exam.domain.Student;
import com.bishe.exam.service.ExamService;
import com.bishe.exam.service.RelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-07
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationDao, Relation> implements RelationService {

    @Autowired
    private ExamService examService;
    @Autowired
    private StudentService studentService;

    @Override
    public void arrange() {
        List<Exam> exams = examService.list();
        exams.forEach(exam -> {
            List<Student> students = studentService.listByGradeAndMajor(exam.getGrade(), exam.getMajor());

        });

    }

    /**
     *
     * @param classrooms 可排课，没有被占用
     * @param students
     */
    private void select(List<Classroom> classrooms, List<Student> students){

    }

    private void sum(List<Student> students, int limit){
        
    }
}
