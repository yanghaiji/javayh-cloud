package com.javayh.service;

import com.javayh.aop.OperationType;
import com.javayh.aop.OperationUnit;
import com.javayh.aop.WebLogAspect;
import com.javayh.entity.SysMenu;
import com.javayh.mapper.SysMeunMapper;
import com.javayh.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Dylan Yang
 * @Description: SysMenuService
 * @Title: SysMenuService
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 21:22
 */
@Service
public class SysMenuService extends BaseService<SysMenu>{

    @Autowired
    private SysMeunMapper sysMeunMapper;

    /**
     * 查询所有菜单
     * @return
     */
    @WebLogAspect(detail="查询所有菜单",level = 3,operationType =OperationType.SELECT,operationUnit = OperationUnit.UNKNOWN)
    public List<SysMenu> query(){
        return sysMeunMapper.selectAll();
    }

    /**
     * spring 线程池有返回值
     * @param deleteId
     * @return
     */
    @Async
    public Future<String> delete(Integer deleteId){
        SysMenu sysMenu = (SysMenu)this.findById(deleteId);
        if(sysMenu == null){
            Future<String> objectFuture = new AsyncResult<>("delete filed");
            return objectFuture;
        }else {
            SysMenu sysMenuDelete = (SysMenu)this.findById(deleteId);
            if(sysMenuDelete == null){
                Future<String> objectFuture = new AsyncResult<>("delete filed");
                return objectFuture;
            }else {
                Future<String> objectFuture = new AsyncResult<>("delete success");
                this.deleteById(deleteId);
                return objectFuture;
            }
        }
    }

    /**
     * 查询 异步
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Async
    public Future<List<SysMenu>> queryFuture() {
        Future<List<SysMenu>> future = new AsyncResult<>(sysMeunMapper.selectAll());
        return future;
    }

}

