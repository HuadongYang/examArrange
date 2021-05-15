package com.bishe.exam.service;

import com.bishe.exam.domain.Classroom;
import com.bishe.exam.domain.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.exam.dto.ExamInfoDTO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author
 * @since 2021-05-06
 */
public interface ExamService extends IService<Exam> {

    void save(List<Exam> exams);

    List<ExamInfoDTO> getExamInfo();

}
