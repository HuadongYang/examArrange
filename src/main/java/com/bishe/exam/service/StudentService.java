package com.bishe.exam.service;

import com.bishe.exam.domain.Classroom;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.exam.dto.ClassNum;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author
 * @since 2021-05-06
 */
public interface StudentService extends IService<Student> {

    void save(List<Student> students);

    List<Student> listByCondition(String grade, String major);

    List<Student> listByCondition(String grade, String major, String classNum);

    List<ClassNum> getClasses(List<Exam> exams);

}
