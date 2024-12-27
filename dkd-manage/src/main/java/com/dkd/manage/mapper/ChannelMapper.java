package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.vo.ChannelVo;

/**
 * 售货机货道Mapper接口
 * 
 * @author itheima
 * @date 2024-12-18
 */
public interface ChannelMapper 
{
    /**
     * 查询售货机货道
     * 
     * @param id 售货机货道主键
     * @return 售货机货道
     */
    public Channel selectChannelById(Long id);

    /**
     * 查询售货机货道列表
     * 
     * @param channel 售货机货道
     * @return 售货机货道集合
     */
    public List<Channel> selectChannelList(Channel channel);

    /**
     * 新增售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int insertChannel(Channel channel);

    /**
     * 修改售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int updateChannel(Channel channel);

    /**
     * 删除售货机货道
     * 
     * @param id 售货机货道主键
     * @return 结果
     */
    public int deleteChannelById(Long id);

    /**
     * 批量删除售货机货道
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChannelByIds(Long[] ids);
    
     /*
      * @Title: 批量新增售货机货道
      * @Author: pyzxW
      * @Date: 2024-12-20 16:43:22
      * @Params:  chanelList
      * @Return: 结果
      * @Description: 
      */
    public int batchInsertChannels(List<Channel> channelList);

    /*
     * @Title: countChannelBySkuIds
     * @Author: pyzxW
     * @Date: 2024-12-25 16:48:12
     * @Params:  skuIds
     * @Return: null
     * @Description: 根据商品id集合统计货道数量
     */
    int countChannelBySkuIds(Long[] skuIds);


     /*
      * @Title: selectChannelVoListByInnerCode
      * @Author: pyzxW
      * @Date: 2024-12-27 11:05:42
      * @Params:  
      * @Return: null
      * @Description: 根据售货机编号查询货道列表信息
      */
    List<ChannelVo> selectChannelVoListByInnerCode(String innerCode);
}
