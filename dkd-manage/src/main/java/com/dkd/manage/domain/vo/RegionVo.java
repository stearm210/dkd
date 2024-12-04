package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.Region;
import lombok.Data;

@Data
public class RegionVo extends Region {
    //点位数量
    private Integer nodeCount;

    //使用alt+insert快捷键生成get set方法
//    public Integer getNodeCount() {
//        return nodeCount;
//    }
//
//    public void setNodeCount(Integer nodeCount) {
//        this.nodeCount = nodeCount;
//    }
}
