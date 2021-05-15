package com.bishe.exam.service;

import com.bishe.exam.domain.Classroom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.exam.domain.Exam;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-05-06
 */
public interface ClassroomService extends IService<Classroom> {

    void save(List<Classroom> classrooms);

    List<Classroom> getUnusedRooms(Date start, Date end);
}
