package com.dkd.manage.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dkd.common.annotation.Excel;
import com.dkd.common.core.domain.BaseEntity;

/**
 * 工单详情对象 tb_task_details
 * 
 * @author itheima
 * @date 2024-12-27
 */
@ApiModel(description = "工单详情")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单详情ID */
    @ApiModelProperty(value = "工单详情ID", required = true, example = "1")
    private Long detailsId;

    /** 工单Id */
    @Excel(name = "工单Id")
    @ApiModelProperty(value = "工单Id", required = true, example = "1001")
    private Long taskId;

    /** 货道编号 */
    @Excel(name = "货道编号")
    @ApiModelProperty(value = "货道编号", example = "A1-01")
    private String channelCode;

    /** 补货期望容量 */
    @Excel(name = "补货期望容量")
    @ApiModelProperty(value = "补货期望容量", example = "50")
    private Long expectCapacity;

    /** 商品Id */
    @Excel(name = "商品Id")
    @ApiModelProperty(value = "商品Id", required = true, example = "2001")
    private Long skuId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称")
    private String skuName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    @ApiModelProperty(value = "商品图片的URL")
    private String skuImage;

    public void setDetailsId(Long detailsId) 
    {
        this.detailsId = detailsId;
    }

    public Long getDetailsId() 
    {
        return detailsId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setExpectCapacity(Long expectCapacity) 
    {
        this.expectCapacity = expectCapacity;
    }

    public Long getExpectCapacity() 
    {
        return expectCapacity;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setSkuName(String skuName) 
    {
        this.skuName = skuName;
    }

    public String getSkuName() 
    {
        return skuName;
    }
    public void setSkuImage(String skuImage) 
    {
        this.skuImage = skuImage;
    }

    public String getSkuImage() 
    {
        return skuImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailsId", getDetailsId())
            .append("taskId", getTaskId())
            .append("channelCode", getChannelCode())
            .append("expectCapacity", getExpectCapacity())
            .append("skuId", getSkuId())
            .append("skuName", getSkuName())
            .append("skuImage", getSkuImage())
            .toString();
    }
}
