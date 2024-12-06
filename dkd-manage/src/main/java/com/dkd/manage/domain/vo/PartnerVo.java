package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.Partner;
import lombok.Data;

//Data可以代替get和set方法
@Data
public class PartnerVo extends Partner {
    //点位数量
    private Integer nodeCount;

}
