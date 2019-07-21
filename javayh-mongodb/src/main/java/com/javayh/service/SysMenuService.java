package com.javayh.service;

import com.javayh.entity.SysMenu;
import com.javayh.id.UuidUtils;
import com.javayh.repository.SysMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: 服务
 * @Title: SysMenuService
 * @ProjectName javayh-cloud
 * @date 2019/7/21 0:30
 */
@Service
public class SysMenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;

    /**
     * 新增
     * @param sysMenu
     * @return
     */
    public SysMenu save(SysMenu sysMenu){
        return sysMenuRepository.save(sysMenu);
    }

    /**
     * 查询所有并分页
     * @return
     */
    public Page<SysMenu> findAll(Pageable pageable) {
        return sysMenuRepository.findAll(pageable);
    }

    /**
     * 模糊查询所有 分页
     * @param pageable
     * @return
     */
    public Page<SysMenu> findPag(Pageable pageable) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setCode("e");
        sysMenu.setId("e");
        sysMenu.setPcode("qqqqq");
        Example<SysMenu> example = Example.of(sysMenu,generater());
        return sysMenuRepository.findAll(example,pageable);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    public String delete(String id){
        sysMenuRepository.deleteById(id);
        boolean exists = sysMenuRepository.existsById(id);
        return exists == false ? "1":"0";
    }

    /**
     * StartsWith    起始位置开始匹配
     * EndsWith      结束位置
     * @param code
     * @return
     */
    public List<SysMenu> findByCodeStartsWith(String code){
        return sysMenuRepository.findByCodeStartsWith(code);
    }

    private ExampleMatcher generater() {
        return ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("pcode", match->match.exact()) // 精确查询pcode，模糊查询其他
//                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.regex()) // 查询
//                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.regex()) // 查询
//                .withMatcher("pcode", ExampleMatcher.GenericPropertyMatchers.regex()) // 查询
                .withIgnoreNullValues();//hu忽略null
    }

}

