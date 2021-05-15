package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Student;
import com.bishe.exam.dao.StudentDao;
import com.bishe.exam.dto.ClassNum;
import com.bishe.exam.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Override
    public void save(List<Student> students) {
        saveBatch(students);
    }

    @Override
    public List<Student> listByCondition(String grade, String major) {
        return listByCondition(grade, major, null);
    }
    @Override
    public List<Student> listByCondition(String grade, String major, String classNum) {

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.likeRight("id", grade).eq("major", major);
        if (classNum != null) {
            wrapper.eq("classNum", classNum);
        }
        List<Student> list = list(wrapper);
        return list;
    }

    @Override
    public List<ClassNum> getClasses(List<Exam> exams) {
        List<ClassNum> res = new ArrayList<>();
        exams.forEach(exam -> {
            List<Student> students = listByCondition(exam.getGrade(), exam.getMajor());
            if (CollectionUtils.isEmpty(students)) {
                return;
            }
            res.addAll(students.stream().filter(distinctByKey(x -> x.getClassNum())).map(x -> new ClassNum(x.getClassNum(), x.getMajor(), exam.getId())).collect(Collectors.toList()));
        });
        return res;
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
