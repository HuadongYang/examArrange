package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Classroom;
import com.bishe.exam.dao.ClassroomDao;
import com.bishe.exam.domain.Exam;
import com.bishe.exam.domain.Relation;
import com.bishe.exam.service.ClassroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.exam.service.ExamService;
import com.bishe.exam.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-06
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomDao, Classroom> implements ClassroomService {
    @Autowired
    private RelationService relationService;
    @Autowired
    private ExamService examService;

    @Override
    public void save(List<Classroom> classrooms) {
        saveBatch(classrooms);
    }

    @Override
    public List<Classroom> getUnusedRooms(Date start, Date end) {
        List<Relation> relations = relationService.list();
        Map<Integer, List<Relation>> classRoomId2Relations = relations.stream().collect(Collectors.toMap(Relation::getClassroomId,
                x -> new ArrayList<Relation>() {{
                    add(x);
                }},
                (List<Relation> oldList, List<Relation> newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                }));

        List<Exam> exams = examService.list();
        Map<Integer, Exam> examId2Item = exams.stream().collect(Collectors.toMap(Exam::getId, x -> x));

        List<Classroom> allRooms = list(new QueryWrapper<Classroom>().eq("canExam", "是"));
        return allRooms.stream().filter((room) -> {
            List<Relation> relationList = classRoomId2Relations.get(room.getId());
            if (CollectionUtils.isEmpty(relationList)) {
                return true;
            }
            for (Relation relation : relationList) {
                Exam exam = examId2Item.get(relation.getExamId());
                if (!(exam.getStartTime().after(end) || exam.getEndTime().before(start))) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

    }


}
