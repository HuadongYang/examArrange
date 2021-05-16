package com.bishe.exam.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.*;
import com.bishe.exam.dto.*;
import com.bishe.exam.service.*;
import com.bishe.exam.utils.ArrangeType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static com.bishe.exam.utils.ArrangeType.NAME;
import static com.bishe.exam.utils.ArrangeType.SNO;

@RestController
@RequestMapping("/info")
public class InfoApi {
    @Autowired
    private ExamService examService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private RelationStudentService relationStudentService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SettingService settingService;

    @GetMapping("/examAndClasses")
    public Map getExamsAndClasses() {
        List<Exam> exams = examService.list();
        List<ClassNum> classes = studentService.getClasses(exams);
        return new HashMap() {{
            put("exams", exams);
            put("classes", classes);
        }};
    }

    @GetMapping("/examInfo")
    public List<ExamInfoDTO> getExamsInfo() {
        List<Exam> exams = examService.list();
        List<Teacher> teachers = teacherService.list();
        Map<Integer, String> teacherId2Name = teachers.stream().collect(Collectors.toMap(Teacher::getId, Teacher::getName));
        List<Classroom> classrooms = classroomService.list();
        Map<Integer, Classroom> classroomId2Name = classrooms.stream().collect(Collectors.toMap(Classroom::getId, x -> x));

        List<ExamInfoDTO> res = new ArrayList<>();
        exams.forEach(exam -> {
            List<Relation> relations = relationService.list(new QueryWrapper<Relation>().eq("examId", exam.getId()));
            List<Integer> relationIds = relations.stream().map(Relation::getId).collect(Collectors.toList());
            List<RelationStudent> relationStudents = relationStudentService.list(
                    new QueryWrapper<RelationStudent>().in("relationId", relationIds));
            Set<String> studentIds = relationStudents.stream().map(RelationStudent::getStudentId).collect(Collectors.toSet());
            Collection<Student> students = studentService.listByIds(studentIds);
            Map<String, Student> studentId2Std = students.stream().collect(Collectors.toMap(Student::getId, x -> x));

            Map<Integer, List<RelationStudent>> relationId2Rss = relationStudents.stream().collect(Collectors.groupingBy(RelationStudent::getRelationId));

            relations.forEach(relation -> {
                List<RelationStudent> rss = relationId2Rss.get(relation.getId());
                List<String> stdIds = rss.stream().map(RelationStudent::getStudentId).collect(Collectors.toList());
                Set<String> classN = new HashSet<>();
                stdIds.forEach(x -> classN.add(studentId2Std.get(x).getClassNum()));
                Classroom classroom = classroomId2Name.get(relation.getClassroomId());
                ExamInfoDTO examInfoDTO = new ExamInfoDTO(exam,
                        teacherId2Name.get(relation.getTeacherId()),
                        classroom.getFloorNum() + "--" + classroom.getName(),
                        classroom.getNumExam(),
                        StringUtils.collectionToCommaDelimitedString(classN),
                        relation.getId()
                );
                res.add(examInfoDTO);
            });
        });
        return res;
    }

    @GetMapping("/exam/student")
    public List<StudentDTO> examStudents(@RequestParam Integer relationId) {
        Relation relation = relationService.getById(relationId);
        Classroom classroom = classroomService.getById(relation.getClassroomId());

        List<RelationStudent> relationStudents = relationStudentService.list(
                new QueryWrapper<RelationStudent>().eq("relationId", relationId));
        List<String> studentIds = relationStudents.stream().map(RelationStudent::getStudentId).collect(Collectors.toList());
        Collection<Student> students = studentService.listByIds(studentIds);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            studentDTOS.add(new StudentDTO(student));
        }
        Map<String, Integer> studentId2Seat = relationStudents.stream().collect(Collectors.toMap(RelationStudent::getStudentId, RelationStudent::getOrder));
        studentDTOS.forEach(x->{
            x.setSeat(studentId2Seat.getOrDefault(x.getId(), 0));
            x.setClassroomName(classroom.getName());
        });

        List<Setting> settings = settingService.list();
        ArrangeType arrangeType = SNO;
        if (!CollectionUtils.isEmpty(settings)) {
            arrangeType = ArrangeType.getByStr(settings.get(settings.size() - 1).getType());
        }
        return sortByGroup(studentDTOS, arrangeType);

    }

    private List<StudentDTO> sortByGroup(List<StudentDTO> students, ArrangeType arrangeType) {
        Map<String, List<StudentDTO>> classNum2Students = students.stream().collect(Collectors.groupingBy(StudentDTO::getClassNum));
        List<StudentDTO> res = new ArrayList<>();
        classNum2Students.forEach((classNum, stds
        ) -> {
            if (arrangeType.equals(SNO)) {
                stds = stds.stream().sorted(Comparator.comparing(StudentDTO::getId)).collect(Collectors.toList());
            } else if (arrangeType.equals(NAME)) {
                stds = stds.stream().sorted(Comparator.comparing(StudentDTO::getName)).collect(Collectors.toList());
            }
            res.addAll(stds);
        });
        return res;
    }
}
