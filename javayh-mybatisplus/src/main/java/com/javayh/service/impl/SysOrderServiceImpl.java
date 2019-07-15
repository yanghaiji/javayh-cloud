package com.javayh.service.impl;

import com.javayh.entity.SysOrder;
import com.javayh.mapper.SysOrderMapper;
import com.javayh.service.ISysOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-07-15
 */
@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements ISysOrderService {

    @Autowired
    private SysOrderMapper sysOrderMapper;

    @Override
    public int updateLock(SysOrder sysOrder) {
        return sysOrderMapper.updateLock(sysOrder);
    }

}
