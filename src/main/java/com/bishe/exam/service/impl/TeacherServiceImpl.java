package com.bishe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bishe.exam.domain.Relation;
import com.bishe.exam.domain.Teacher;
import com.bishe.exam.dao.TeacherDao;
import com.bishe.exam.service.RelationService;
import com.bishe.exam.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {
    @Autowired
    private RelationService relationService;

    @Override
    public void save(List<Teacher> teachers) {
        saveBatch(teachers);
    }

    @Override
    public List<Integer> getTeacherWithFreeTime(String exceptMajor, Integer limit) {
        List<Teacher> all = list(new QueryWrapper<Teacher>().ne("major", exceptMajor));
        if (limit > all.size()) {
            return new ArrayList<>();
        }
        List<Integer> allIds = all.stream().map(Teacher::getId).collect(Collectors.toList());

        List<Relation> relations = relationService.list();
        Map<Integer, Long> id2Count = relations.stream().collect(Collectors.groupingBy(Relation::getTeacherId, Collectors.counting()));

        List<TeachBusy> teachBusyList = new ArrayList<>();
        allIds.forEach((id) -> {
            teachBusyList.add(new TeachBusy(id, id2Count.getOrDefault(id, 0L)));
        });
        List<TeachBusy> sortTeachBusyList = teachBusyList.stream().sorted(Comparator.comparing(TeachBusy::getBusy)).collect(Collectors.toList());
        return sortTeachBusyList.subList(0, limit).stream().map(TeachBusy::getTeacherId).collect(Collectors.toList());
    }

    class TeachBusy {
        private Integer teacherId;
        private Long busy;

        public TeachBusy(Integer teacherId, Long busy) {
            this.teacherId = teacherId;
            this.busy = busy;
        }

        public Integer getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(Integer teacherId) {
            this.teacherId = teacherId;
        }

        public Long getBusy() {
            return busy;
        }

        public void setBusy(Long busy) {
            this.busy = busy;
        }
    }
}
