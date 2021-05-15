package com.bishe.exam.service;

import com.bishe.exam.domain.Relation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.exam.utils.ArrangeType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-05-07
 */
public interface RelationService extends IService<Relation> {

    void arrange(ArrangeType arrangeType);

    List<Relation> getRelationsByExam(Integer examId);
}
