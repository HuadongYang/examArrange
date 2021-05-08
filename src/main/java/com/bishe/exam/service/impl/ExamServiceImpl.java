package com.bishe.exam.service.impl;

import com.bishe.exam.domain.Exam;
import com.bishe.exam.dao.ExamDao;
import com.bishe.exam.service.ExamService;
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
public class ExamServiceImpl extends ServiceImpl<ExamDao, Exam> implements ExamService {

    @Override
    public void save(List<Exam> exams) {
        saveBatch(exams);
    }
}
