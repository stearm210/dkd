package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.Task;
import com.dkd.manage.domain.TaskType;
import lombok.Data;

/**
 * @BelongsProject: dkd-parent
 * @BelongsPackage: com.dkd.manage.domain.vo
 * @Author: yanhongwei
 * @CreateTime: 2024-12-28  10:05
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class TaskVo extends Task {
    //工单类型
    private TaskType taskType;
}
