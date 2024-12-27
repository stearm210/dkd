package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.Sku;
import lombok.Data;

/**
 * @BelongsProject: dkd-parent
 * @BelongsPackage: com.dkd.manage.domain.vo
 * @Author: yanhongwei
 * @CreateTime: 2024-12-27  11:01
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class ChannelVo extends Channel {
    // 商品对象
    private Sku sku;

}
