package com.dkd.manage.domain.dto;

import lombok.Data;

/**
 * @BelongsProject: dkd-parent
 * @BelongsPackage: com.dkd.manage.domain.dto
 * @Author: yanhongwei
 * @CreateTime: 2024-12-29  10:01
 * @Description: TODO
 * @Version: 1.0
 */
//工单详情
@Data
public class TaskDetailsDto {
    private String channelCode;//货道编号
    private Long expectCapacity;//补货数量
    private Long skuId;//商品id
    private String skuName;//商品名称
    private String skuImage;//商品图片
}
