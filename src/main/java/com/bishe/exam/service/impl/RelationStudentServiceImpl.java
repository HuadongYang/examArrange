package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.*;
import com.bishe.exam.dao.RelationStudentDao;
import com.bishe.exam.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.exam.utils.ArrangeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.bishe.exam.utils.ArrangeType.NAME;
import static com.bishe.exam.utils.ArrangeType.SNO;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-10
 */
@Service
public class RelationStudentServiceImpl extends ServiceImpl<RelationStudentDao, RelationStudent> implements RelationStudentService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ExamService examService;

    @Override
    public void arrange(ArrangeType arrangeType, Exam exam) {
        List<Student> students = studentService.listByCondition(exam.getGrade(), exam.getMajor());

        students = shuffleByGroup(students, arrangeType);

        List<Relation> relations = relationService.getRelationsByExam(exam.getId());
        List<Integer> roomIds = relations.stream().map(Relation::getClassroomId).collect(Collectors.toList());
        Collection<Classroom> classrooms = classroomService.listByIds(roomIds);
        Map<Integer, Classroom> id2Classroom = classrooms.stream().collect(Collectors.toMap(Classroom::getId, x -> x));

        List<RelationStudent> res = new ArrayList<>();
        for (Relation relation : relations) {
            Classroom classroom = id2Classroom.get(relation.getClassroomId());
            int limit = classroom.getNumExam() < students.size() ? classroom.getNumExam() : students.size();
            for (int i = 0; i < limit; i++) {
                res.add(new RelationStudent(relation.getId(), students.get(i).getId(), i));
            }
            students = students.subList(limit, students.size());
        }
        saveBatch(res);
    }

    private List<Student> shuffleByGroup(List<Student> students, ArrangeType arrangeType) {
        Map<String, List<Student>> classNum2Students = students.stream().collect(Collectors.groupingBy(Student::getClassNum));
        List<Student> res = new ArrayList<>();
        classNum2Students.forEach((classNum, stds
        ) -> {
            if (arrangeType.equals(SNO)) {
                stds = stds.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
            } else if (arrangeType.equals(NAME)) {
                stds = stds.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList());
            }
            Collections.shuffle(stds);
            res.addAll(stds);
        });
        return res;
    }

    @Override
    public void arrange(ArrangeType arrangeType) {
        List<Exam> exams = examService.list();
        remove(new QueryWrapper<>());
        exams.forEach(exam -> arrange(arrangeType, exam));
    }
}
