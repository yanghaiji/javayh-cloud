package com.javayh.feign;

import com.javayh.entity.Result;
import com.javayh.vo.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Dylan Yang
 * @Description: MpService
 * @Title: MpService
 * @ProjectName javayh-oauth2
 * @date 2019/6/22 0:32
 */

@FeignClient(name = "javayh-mybatismp",fallback = Mpfallback.class)
public interface MpService {

    @PostMapping(value = "/sysRole/save")
    Result saveAll(@RequestBody SysRole sysRole);
}
@Component
class Mpfallback implements MpService{

    @Override
    public Result saveAll(SysRole sysRole) {
        return Result.javaYhResultFailed("MP Faild");
    }
}
