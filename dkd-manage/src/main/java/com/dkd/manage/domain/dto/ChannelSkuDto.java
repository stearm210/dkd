package com.dkd.manage.domain.dto;

import lombok.Data;

/**
 * @BelongsProject: dkd-parent
 * @BelongsPackage: com.dkd.manage.domain.dto
 * @Author: yanhongwei
 * @CreateTime: 2024-12-27  14:48
 * @Description: TODO
 * @Version: 1.0
 */
//对应货道商品信息
@Data
public class ChannelSkuDto {
    private String innerCode;//售货机编号
    private String channelCode;//货道编号
    private Long skuId;//商品编号
}
