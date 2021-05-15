package com.bishe.exam.api;

import com.bishe.exam.domain.Setting;
import com.bishe.exam.service.RelationService;
import com.bishe.exam.service.RelationStudentService;
import com.bishe.exam.service.SettingService;
import com.bishe.exam.utils.ArrangeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/arrange")
public class ArrangeApi {
    @Autowired
    private RelationService relationService;

    @Autowired
    private RelationStudentService relationStudentService;
    @Autowired
    private SettingService settingService;


    @GetMapping("/first")
    public Boolean arrangeFirst(@RequestParam ArrangeType arrangeType){
        try {
            relationService.arrange(arrangeType);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        settingService.save(new Setting(arrangeType.toString(), new Date()));
        return true;
    }

    @GetMapping("/student")
    public Boolean arrange(@RequestParam ArrangeType arrangeType) {

        try {
            relationStudentService.arrange(arrangeType);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
