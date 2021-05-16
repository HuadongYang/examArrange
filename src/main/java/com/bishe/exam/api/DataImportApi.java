package com.bishe.exam.api;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Classroom;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Student;
import com.bishe.exam.domain.Teacher;
import com.bishe.exam.service.*;
import com.bishe.exam.utils.DataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/import")
public class DataImportApi {

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;


    @PostMapping("/classroom")
    public void uploadClassroom(MultipartFile file) throws Exception {
        DataListener<Classroom> dataListener = new DataListener();
        EasyExcel.read(file.getInputStream(), Classroom.class, dataListener).sheet().doRead();
        System.out.println(dataListener.getList().size());
        classroomService.remove(new QueryWrapper<>());
        classroomService.save(dataListener.getList());
    }

    @PostMapping("/student")
    @Transactional
    public void uploadStudent(MultipartFile file) throws Exception {
        DataListener<Student> dataListener = new DataListener();
        EasyExcel.read(file.getInputStream(), Student.class, dataListener).sheet().doRead();
        studentService.remove(new QueryWrapper<>());
        studentService.save(dataListener.getList());
        userService.saveStudentAccount(dataListener.getList());

        DataListener<Exam> dataExamListener = new DataListener();
        EasyExcel.read(file.getInputStream(), Exam.class, dataExamListener).sheet(2).doRead();
        examService.remove(new QueryWrapper<>());
        examService.save(dataExamListener.getList());

        DataListener<Teacher> dataTeacherListener = new DataListener();
        EasyExcel.read(file.getInputStream(), Teacher.class, dataTeacherListener).sheet(1).doRead();
        teacherService.remove(new QueryWrapper<>());
        teacherService.save(dataTeacherListener.getList());
    }

}
