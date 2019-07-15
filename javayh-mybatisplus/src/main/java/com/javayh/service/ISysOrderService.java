package com.javayh.service;

import com.javayh.entity.SysOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-07-15
 */
public interface ISysOrderService extends IService<SysOrder> {
    int updateLock(SysOrder sysOrder);
}
