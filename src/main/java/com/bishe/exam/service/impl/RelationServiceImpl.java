package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Classroom;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Relation;
import com.bishe.exam.dao.RelationDao;
import com.bishe.exam.domain.Student;
import com.bishe.exam.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.exam.utils.ArrangeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RelationStudentService relationStudentService;

    @Override
    public void arrange(ArrangeType arrangeType) {
        List<Exam> exams = examService.list();
        remove(new QueryWrapper<>());
        relationStudentService.remove(new QueryWrapper<>());
        exams.forEach(exam -> {
            List<Classroom> classrooms = classroomService.getUnusedRooms(exam.getStartTime(), exam.getEndTime());
            List<Student> students = studentService.listByCondition(exam.getGrade(), exam.getMajor());
            List<Classroom> roomsByChosen = chooseClassroom(classrooms, students.size());
            List<Integer> teacherIds = teacherService.getTeacherWithFreeTime(exam.getMajor(), roomsByChosen.size());
            List<Relation> relations = new ArrayList<>();
            for (int i = 0; i < roomsByChosen.size(); i++) {
                relations.add(new Relation(exam.getId(), teacherIds.get(i), roomsByChosen.get(i).getId()));
            }
            saveBatch(relations);
            relationStudentService.arrange(arrangeType, exam);
        });
    }

    @Override
    public List<Relation> getRelationsByExam(Integer examId) {
        return list(new QueryWrapper<Relation>().eq("examId", examId));
    }

    /**
     * 选择教室
     *
     * @param classrooms 可排课，没有被占用
     * @param studentNum
     */
    private List<Classroom> chooseClassroom(List<Classroom> classrooms, int studentNum) {
        List<Classroom> res = new ArrayList<>();
        //List<Classroom> sortRooms = classrooms.stream().sorted((x, y) -> y.getNumExam().compareTo(x.getNumExam())).collect(Collectors.toList());
        int sum = 0;
        for (Classroom room : classrooms) {
            res.add(room);
            sum += room.getNumExam();
            if (sum >= studentNum) {
                return res;
            }
        }
        return res;
    }

    private void sum(List<Student> students, int limit) {

    }
}
