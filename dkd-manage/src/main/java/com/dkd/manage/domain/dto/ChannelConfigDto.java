package com.dkd.manage.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: dkd-parent
 * @BelongsPackage: com.dkd.manage.domain.dto
 * @Author: yanhongwei
 * @CreateTime: 2024-12-27  14:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
//对应售货机信息
public class ChannelConfigDto {
    private String innerCode;//售货机编号
    private List<ChannelSkuDto> channelList;//货道dto集合
}
