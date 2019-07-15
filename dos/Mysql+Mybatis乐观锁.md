## 乐观锁

#### Mysql + Mybatis实现乐观锁

#### 1.案例sql
    SET FOREIGN_KEY_CHECKS=0;
    
    -- ----------------------------
    -- Table structure for sys_order
    -- ----------------------------
    DROP TABLE IF EXISTS `sys_order`;
    CREATE TABLE `sys_order` (
      `order_num` varchar(255) NOT NULL COMMENT '订单号',
      `order_name` varchar(255) DEFAULT NULL COMMENT '订单名称',
      `order_type` varchar(255) DEFAULT NULL COMMENT '订单状态',
      `order_quantity` varchar(255) DEFAULT NULL COMMENT '订单数量',
      `version` int(11) DEFAULT '1' COMMENT '版本号',
      PRIMARY KEY (`order_num`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    -- ----------------------------
    -- Records of sys_order
    -- ----------------------------
    INSERT INTO `sys_order` VALUES ('123456fdxw3456', '秒杀', '已付款', '1', '1');
    INSERT INTO `sys_order` VALUES ('1234td', '双十一', '代发货', '22', '1');
#### Dao创建
    public interface SysOrderMapper extends BaseMapper<SysOrder> {
    
        int updateLock(SysOrder sysOrder);
    }
#### Mapper文件
    <update id="updateLock" parameterType="com.javayh.entity.SysOrder">
            update
              sys_order
            set
              order_type=#{orderType},
              version= version+1
            where
              order_num=#{orderNum}
            and
              version = #{version}
        </update>
 #### Service实现
    @Service
    public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements ISysOrderService {
    
        @Autowired
        private SysOrderMapper sysOrderMapper;
    
        @Override
        public int updateLock(SysOrder sysOrder) {
            return sysOrderMapper.updateLock(sysOrder);
        }
    }
 #### 接口调用
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
            //1.查询
            SysOrder sysOrder1 = sysOrderMapper.selectById("123456fdxw3456");
            System.out.println(sysOrder1);
            //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=秒杀, orderQuantity=1, version=1)
            
            SysOrder sysOrder2 = sysOrderMapper.selectById("123456fdxw3456");
            System.out.println(sysOrder2);
            //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=秒杀, orderQuantity=1, version=1)
            
            //2.修改字段
            sysOrder1.setOrderType("买一赠一");
            System.out.println(sysOrder1);
            //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=买一赠一, orderQuantity=1, version=1)
            
            sysOrder2.setOrderType("买五赠一");
            System.out.println(sysOrder2);
            //SysOrder(orderNum=123456fdxw3456, orderName=秒杀, orderType=买五赠一, orderQuantity=1, version=1)
            
            //3.执行修改
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