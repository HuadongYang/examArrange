package com.bishe.exam.service;

import com.bishe.exam.domain.Student;
import com.bishe.exam.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author
 * @since 2021-05-06
 */
public interface TeacherService extends IService<Teacher> {

    void save(List<Teacher> teachers);

}
