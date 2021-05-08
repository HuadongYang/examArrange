package com.bishe.exam.service.impl;

import com.bishe.exam.domain.Classroom;
import com.bishe.exam.dao.ClassroomDao;
import com.bishe.exam.service.ClassroomService;
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
public class ClassroomServiceImpl extends ServiceImpl<ClassroomDao, Classroom> implements ClassroomService {

    @Override
    public void save(List<Classroom> classrooms) {
        saveBatch(classrooms);
    }
}
