package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Student;
import com.bishe.exam.domain.User;
import com.bishe.exam.dao.UserDao;
import com.bishe.exam.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public User login(String account, String password) {
        return getOne(new QueryWrapper<User>().eq("account", account).eq("password", password));
    }

    @Override
    public void saveStudentAccount(List<Student> students) {
        List<User> users = students.stream().map(x -> new User(x.getId(), "123456", "student")).collect(Collectors.toList());
        saveBatch(users);
    }
}
