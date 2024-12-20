package com.dkd.manage.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.dkd.common.constant.DkdContants;
import com.dkd.common.utils.DateUtils;
import com.dkd.common.utils.uuid.UUIDUtils;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.Node;
import com.dkd.manage.domain.VmType;
import com.dkd.manage.service.IChannelService;
import com.dkd.manage.service.INodeService;
import com.dkd.manage.service.IVmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.VendingMachineMapper;
import com.dkd.manage.domain.VendingMachine;
import com.dkd.manage.service.IVendingMachineService;


/**
 * 设备管理Service业务层处理
 * 
 * @author itheima
 * @date 2024-12-18
 */
@Service
public class VendingMachineServiceImpl implements IVendingMachineService 
{
    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    @Autowired
    private IVmTypeService vmTypeService;

    @Autowired
    private INodeService nodeService;

    @Autowired
    private IChannelService channelService;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public VendingMachine selectVendingMachineById(Long id)
    {
        return vendingMachineMapper.selectVendingMachineById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param vendingMachine 设备管理
     * @return 设备管理
     */
    @Override
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine)
    {
        return vendingMachineMapper.selectVendingMachineList(vendingMachine);
    }

    /**
     * 新增设备管理
     * 需要将属性从一张表中拷贝到另一张表中
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Override
    public int insertVendingMachine(VendingMachine vendingMachine)
    {
        // 需要将属性从一张表中拷贝到另一张表中
        //1.新增设备
        //1-1 随机生成8位唯一标识，补充货道编号
        String innerCode = UUIDUtils.getUUID();
        vendingMachine.setInnerCode(innerCode);
        //1-2 查询售货机类型表，补充设备容量
        VmType vmType = vmTypeService.selectVmTypeById(vendingMachine.getVmTypeId());//查到对应设备
        vendingMachine.setChannelMaxCapacity(vmType.getChannelMaxCapacity());//设置对应容量
        //1-3 查询点位表，补充区域、点位、合作商等信息
        Node node = nodeService.selectNodeById(vendingMachine.getNodeId());
        //属性快速拷贝
        BeanUtil.copyProperties(node,vendingMachine,"id");//包括商圈类型、区域id、合作商id
        vendingMachine.setAddr(node.getAddress());//设备地址
       // 1-4 设备状态
        //调用常量更快. 0L表示没有投放
        vendingMachine.setVmStatus(DkdContants.VM_STATUS_NODEPLOY);
        vendingMachine.setCreateTime(DateUtils.getNowDate());//创建时间
        vendingMachine.setUpdateTime(DateUtils.getNowDate());//更新时间
        // 1-5 保存
        int result = vendingMachineMapper.insertVendingMachine(vendingMachine);
        //2.新增货道
        //双层for循环设置行与列
        for (int i = 1; i <= vmType.getVmRow(); i++){//外层行遍历
            for (int j = 1; j <= vmType.getVmCol(); j++){//外层列变换
                //遍历获得对应结果
                //封装channel对象
                Channel channel = new Channel();
               channel.setChannelCode(i+"-"+j);//货道编号
               //售货机id
               channel.setVmId(vendingMachine.getId());
               //获取售货机编号
                channel.setInnerCode(innerCode);
                //货道最大容量
                channel.setMaxCapacity(vmType.getChannelMaxCapacity());
                //创建时间
                channel.setCreateTime(DateUtils.getNowDate());
                //修改时间
                channel.setUpdateTime(DateUtils.getNowDate());
                //保存货道
               channelService.insertChannel(channel);
            }
        }

        return result;
    }

    /**
     * 修改设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Override
    public int updateVendingMachine(VendingMachine vendingMachine)
    {
        vendingMachine.setUpdateTime(DateUtils.getNowDate());
        return vendingMachineMapper.updateVendingMachine(vendingMachine);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineByIds(Long[] ids)
    {
        return vendingMachineMapper.deleteVendingMachineByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineById(Long id)
    {
        return vendingMachineMapper.deleteVendingMachineById(id);
    }
}
