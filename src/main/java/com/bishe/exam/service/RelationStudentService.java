package com.bishe.exam.service;

import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.RelationStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.exam.utils.ArrangeType;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-05-10
 */
public interface RelationStudentService extends IService<RelationStudent> {

    void arrange(ArrangeType arrangeType, Exam exam);
    void arrange(ArrangeType arrangeType);
}
