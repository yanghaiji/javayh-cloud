package com.javayh.controller;


import com.javayh.entity.SysOrder;
import com.javayh.mapper.SysOrderMapper;
import com.javayh.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/sys-order")
public class SysOrderController {

    @Autowired
    private SysOrderMapper sysOrderMapper;
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 流程：
     * 1.根据订单号查询出订单
     * 2.修改订单的某个值，这里存在并发
     * 3.修改时判断版本号，如果这是有其他的线程已修改，
     * 则本次修改失败，若无修改，则修改成功；对应本案例的order1 与 order2
     * 此方法的实现主要是跟根据version字段进行区分，
     * 若version以被修改，where version 时无匹配的字段，自然失败
     */
    @GetMapping(value = "test")
    public void contextLoads() {
        SysOrder sysOrder1 = sysOrderMapper.selectById("123456fdxw3456");
        System.out.println(sysOrder1);
        //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=秒杀, orderQuantity=1, version=1)
        SysOrder sysOrder2 = sysOrderMapper.selectById("123456fdxw3456");
        System.out.println(sysOrder2);
        //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=秒杀, orderQuantity=1, version=1)
        sysOrder1.setOrderType("买一赠一");
        System.out.println(sysOrder1);
        //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=买一赠一, orderQuantity=1, version=1)
        sysOrder2.setOrderType("买五赠一");
        System.out.println(sysOrder2);
        //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=买五赠一, orderQuantity=1, version=1)
        int order1 = sysOrderService.updateLock(sysOrder1);
        SysOrder sysOrder3 = sysOrderMapper.selectById("123456fdxw3456");
        System.out.println(sysOrder3);
        System.out.println(order1>0 ? "成功":"失败");
        /**
         * SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=买一赠一, orderQuantity=1, version=2)
         * 成功
         * SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=买一赠一, orderQuantity=1, version=2)
         * 失败
         */
        int order2 = sysOrderService.updateLock(sysOrder2);
        SysOrder sysOrder4 = sysOrderMapper.selectById("123456fdxw3456");
        System.out.println(sysOrder4);
        System.out.println(order2>0 ? "成功":"失败");
    }
}

