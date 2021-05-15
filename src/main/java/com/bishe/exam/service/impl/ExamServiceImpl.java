package com.bishe.exam.service.impl;

import com.bishe.exam.domain.Exam;
import com.bishe.exam.dao.ExamDao;
import com.bishe.exam.domain.Student;
import com.bishe.exam.dto.ClassNum;
import com.bishe.exam.dto.ExamInfoDTO;
import com.bishe.exam.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-06
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamDao, Exam> implements ExamService {
    @Autowired
    private StudentService studentService;

    @Override
    public void save(List<Exam> exams) {
        saveBatch(exams);
    }

    @Override
    public List<ExamInfoDTO> getExamInfo() {
        List<Exam> exams = list();
        exams.forEach(exam -> {
            List<Student> students = studentService.listByCondition(exam.getGrade(), exam.getMajor());
            if (CollectionUtils.isEmpty(students)) {
                return;
            }
            List<ClassNum> classNums = students.stream().filter(distinctByKey(x -> x.getClassNum())).map(x -> new ClassNum(x.getClassNum(), x.getMajor(), exam.getId())).collect(Collectors.toList());

        });
        return null;
    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> {
            if(keyExtractor.apply(t) == null) {
                return false;
            }
            return seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
        };
    }
}
