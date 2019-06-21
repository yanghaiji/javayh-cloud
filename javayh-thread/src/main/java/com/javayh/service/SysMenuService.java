package com.javayh.service;

import com.javayh.aop.OperationType;
import com.javayh.aop.OperationUnit;
import com.javayh.aop.WebLogAspect;
import com.javayh.entity.SysMenu;
import com.javayh.mapper.SysMeunMapper;
import com.javayh.mybatis.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dylan Yang
 * @Description: SysMenuService
 * @Title: SysMenuService
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 21:22
 */
@Slf4j
@Service
public class SysMenuService extends BaseService<SysMenu>{

    @Autowired
    private SysMeunMapper sysMeunMapper;

    @Autowired
    private ExecutorService executorService;

    //创建一个读写锁实例
    private ReadWriteLock rw = new ReentrantReadWriteLock();
    //创建一个读锁
    private Lock r = rw.readLock();
    //创建一个写锁
    private Lock w = rw.writeLock();



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
        SysMenu sysMenu = this.findById(deleteId);
        if(sysMenu == null){
            Future<String> objectFuture = new AsyncResult<>("delete filed");
            return objectFuture;
        }else {
            SysMenu sysMenuDelete = this.findById(deleteId);
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
        Future<List<SysMenu>> future =null;
        try {
            r.lock();
            log.info("thread id : " + Thread.currentThread().getId());
            log.info("thread name : " + Thread.currentThread().getName());
            log.info("thread thread group : " + Thread.currentThread().getThreadGroup());
            future = new AsyncResult<>(sysMeunMapper.selectAll());
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            r.unlock();
            return future;
        }
    }

    /*-----------------------------------------JDK------------------------------------------*/

    /**
     * 无返回值
     * @param id
     */
    public void deleteFutureJdk(String id) {
        try {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int i = sysMeunMapper.deleteByIds(id);
                    log.info("delete num : " + i);
                    log.info("thread id : " + Thread.currentThread().getId());
                    log.info("thread name : " + Thread.currentThread().getName());
                    log.info("thread thread group : " + Thread.currentThread().getThreadGroup());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    /**
     * 有返回值得线程
     * @return
     */
    public Future<List<SysMenu>> queryFutureJdkSubmit(SysMenu sysMenu) {
        Future<List<SysMenu>>  submit = null;
        try {
            Callable<List<SysMenu>> callable = new Callable<List<SysMenu>>() {
                @Override
                public List<SysMenu> call() {
                    List<SysMenu> sysMenus = sysMeunMapper.select(sysMenu);
                    log.info("thread name : " + Thread.currentThread().getName());
                    return sysMenus;
                }
            };
            submit = executorService.submit(callable);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        return submit;
    }

}

