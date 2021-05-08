package com.bishe.exam.service.impl;

import com.bishe.exam.domain.Teacher;
import com.bishe.exam.dao.TeacherDao;
import com.bishe.exam.service.TeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {

    @Override
    public void save(List<Teacher> teachers) {
        saveBatch(teachers);
    }
}
