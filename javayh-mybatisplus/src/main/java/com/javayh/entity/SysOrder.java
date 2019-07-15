package com.javayh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrder implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 订单号
     */
    @TableId(type=IdType.UUID)
    private String orderNum;

    /**
     * 订单名称
     */
    private String orderName;

    /**
     * 订单状态
     */
    private String orderType;

    /**
     * 订单数量
     */
    private String orderQuantity;

    /**
     * 版本号
     */
    private Integer version;


}
