package com.bishe.exam.api;

import com.bishe.exam.domain.Setting;
import com.bishe.exam.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingApi {
    @Autowired
    private SettingService settingService;

    @GetMapping("/record")
    public List<Setting> getSettings(){
        return settingService.list();
    }


}
