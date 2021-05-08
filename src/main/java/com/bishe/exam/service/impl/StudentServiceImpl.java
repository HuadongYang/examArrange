package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Student;
import com.bishe.exam.dao.StudentDao;
import com.bishe.exam.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
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
    public List<Student> listByGradeAndMajor(String grade, String major) {

        List<Student> list = list(new QueryWrapper<Student>().likeLeft("id", grade).eq("major", major));
        return list;
    }
}
