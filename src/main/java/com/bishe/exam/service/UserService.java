package com.bishe.exam.service;

import com.bishe.exam.domain.Student;
import com.bishe.exam.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-05-13
 */
public interface UserService extends IService<User> {

    User login(String account, String password);

    void saveStudentAccount(List<Student> students);
}
