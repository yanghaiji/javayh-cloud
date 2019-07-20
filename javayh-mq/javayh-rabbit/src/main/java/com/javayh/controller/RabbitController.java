package com.javayh.controller;

import com.javayh.entity.Result;
import com.javayh.entity.SysMenu;
import com.javayh.service.JavaYhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Yang
 * @Description: RabbitController
 * @Title: RabbitController
 * @ProjectName javayh-cloud
 * @date 2019/7/16 23:50
 */
@RestController
@RequestMapping(value = "/javayh/rabbit/")
public class RabbitController {

    @Autowired
    JavaYhService javaYhService;

    @GetMapping(value = "send")
    public Result send(){
        SysMenu sysMenu = new SysMenu.
                            SysMenuBuid().
                            setId("wsjsn").
                            setCode("ertyuio").
                            setPcode("wertyui").
                            build();
        return  Result.javaYhQuerySuccess(javaYhService.save(sysMenu));
    }

    @GetMapping(value = "sendFanout")
    public Result sendFanout(){
        SysMenu sysMenu = new SysMenu.
                        SysMenuBuid().
                        setId("wsjsn").
                        setCode("ertyuio").
                        setPcode("wertyui").
                        build();
        return  Result.javaYhQuerySuccess(javaYhService.saveFanout(sysMenu));
    }

    @GetMapping(value = "sendTopic")
    public Result sendTopic(){
        javaYhService.saveTopic("Javayh");
        javaYhService.saveTopic("Dylan");
        javaYhService.saveTopic("Yhj");
        return  Result.javaYhQuerySuccess(1);
    }
}

