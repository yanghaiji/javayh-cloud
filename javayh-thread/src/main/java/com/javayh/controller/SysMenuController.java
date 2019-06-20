package com.javayh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javayh.entity.Result;
import com.javayh.entity.SysMenu;
import com.javayh.service.SysMenuService;
import com.javayh.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: SysMenuController
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 21:21
 */
@RestController
@RequestMapping(value = "/javayh/thread/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单
     * @param sysMenu
     * @return
     */
    @GetMapping(value = "query")
    public Result query(SysMenuVO sysMenu){
        PageHelper.startPage(sysMenu.getPageNum(),sysMenu.getPageSize());
        List<SysMenu> all = sysMenuService.query();
        PageInfo<SysMenu> sysMenuPageInfo = new PageInfo<>(all);
        return Result.javaYhQuerySuccess(sysMenuPageInfo);
    }

    /**
     * 删除 多线程异步
     * @param deleteId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping(value = "delete/{deleteId}")
    public Result delete(@PathVariable Integer deleteId) throws ExecutionException, InterruptedException {
        Future<String>  all= sysMenuService.delete(deleteId);
        String s = all.get();
        return "delete filed".equals(s)?Result.javaYhResultFailed(s):Result.javaYhDeleteSuccess(s);
    }

    /**
     * 查询所有 多线程异步
     * @return
     */
    @GetMapping(value = "queryFuture")
    public Result queryFuture() throws ExecutionException, InterruptedException {
        List<SysMenu> all = sysMenuService.queryFuture().get();
        return Result.javaYhQuerySuccess(all);
    }

    /*-----------------------------------------JDK-----------------------------------------------*/

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping(value = "deleteFutureJdk/{id}")
    public Result deleteFutureJdk(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.findById(Integer.valueOf(id));
        if(sysMenu == null){
            return Result.javaYhResultFailed("menu is null");
        }else {
            sysMenuService.deleteFutureJdk(id);
            SysMenu sysMenuTwo = sysMenuService.findById(Integer.valueOf(id));
            if(sysMenuTwo == null){
                return Result.javaYhResultFailed("menu delete failed");
            }
            return Result.javaYhQuerySuccess("menu delete success");
        }
    }

    /**
     * 查询对应菜单
     * @param sysMenu
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping(value = "queryFutureJdkSubmit")
    public Result queryFutureJdk(@RequestBody SysMenu sysMenu) throws ExecutionException, InterruptedException {
        List<SysMenu> listFuture = sysMenuService.queryFutureJdkSubmit(sysMenu).get();
        return Result.javaYhQuerySuccess(listFuture);
    }


}

