package com.javayh.mapper;

import com.javayh.entity.SysOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-07-15
 */
public interface SysOrderMapper extends BaseMapper<SysOrder> {

    int updateLock(SysOrder sysOrder);
}
