package com.dkd.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.dkd.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dkd.common.annotation.Log;
import com.dkd.common.core.controller.BaseController;
import com.dkd.common.core.domain.AjaxResult;
import com.dkd.common.enums.BusinessType;
import com.dkd.manage.domain.TaskDetails;
import com.dkd.manage.service.ITaskDetailsService;
import com.dkd.common.utils.poi.ExcelUtil;
import com.dkd.common.core.page.TableDataInfo;

/**
 * 工单详情Controller
 */
@RestController
@RequestMapping("/manage/taskDetails")
@Api(tags = "工单详情管理")
public class TaskDetailsController extends BaseController {

    @Autowired
    private ITaskDetailsService taskDetailsService;

    /**
     * 查询工单详情列表
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:list')")
    @GetMapping("/list")
    @ApiOperation(value = "获取工单详情列表", notes = "根据查询条件返回工单详情列表")
    public TableDataInfo list(@ApiParam(name = "taskDetails", value = "查询参数", required = false) TaskDetails taskDetails) {
        startPage();
        List<TaskDetails> list = taskDetailsService.selectTaskDetailsList(taskDetails);
        return getDataTable(list);
    }

    /**
     * 导出工单详情列表
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:export')")
    @Log(title = "工单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出工单详情列表为Excel文件", notes = "根据查询条件导出工单详情列表到Excel文件")
    public void export(HttpServletResponse response, @ApiParam(name = "taskDetails", value = "查询参数", required = false) TaskDetails taskDetails) {
        List<TaskDetails> list = taskDetailsService.selectTaskDetailsList(taskDetails);
        ExcelUtil<TaskDetails> util = new ExcelUtil<>(TaskDetails.class);
        util.exportExcel(response, list, "工单详情数据");
    }

    /**
     * 获取工单详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:query')")
    @GetMapping("/{detailsId}")
    @ApiOperation(value = "获取工单详情", notes = "根据ID获取工单详情")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "未找到资源")
    })
    public AjaxResult getInfo(@ApiParam(name = "detailsId", value = "工单详情ID", required = true) @PathVariable("detailsId") Long detailsId) {
        return success(taskDetailsService.selectTaskDetailsByDetailsId(detailsId));
    }

    /**
     * 新增工单详情
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:add')")
    @Log(title = "工单详情", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增工单详情", notes = "创建一个新的工单详情记录")
    public AjaxResult add(@ApiParam(name = "taskDetails", value = "工单详情对象", required = true) @RequestBody TaskDetails taskDetails) {
        return toAjax(taskDetailsService.insertTaskDetails(taskDetails));
    }

    /**
     * 修改工单详情
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:edit')")
    @Log(title = "工单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改工单详情", notes = "更新已有的工单详情记录")
    public AjaxResult edit(@ApiParam(name = "taskDetails", value = "工单详情对象", required = true) @RequestBody TaskDetails taskDetails) {
        return toAjax(taskDetailsService.updateTaskDetails(taskDetails));
    }

    /**
     * 删除工单详情
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:remove')")
    @Log(title = "工单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{detailsIds}")
    @ApiOperation(value = "删除工单详情", notes = "根据ID数组删除一个或多个工单详情")
    public AjaxResult remove(@ApiParam(name = "detailsIds", value = "工单详情ID数组", required = true) @PathVariable Long[] detailsIds) {
        return toAjax(taskDetailsService.deleteTaskDetailsByDetailsIds(detailsIds));
    }

    /**
     * 查看补货详情
     */
    @PreAuthorize("@ss.hasPermi('manage:taskDetails:list')")
    @GetMapping("/byTaskId/{taskId}")
    @ApiOperation(value = "根据工单ID查看补货详情", notes = "根据工单ID获取关联的工单详情列表")
    public R<List<TaskDetails>> byTaskId(@ApiParam(name = "taskId", value = "工单ID", required = true) @PathVariable("taskId") Long taskId) {
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTaskId(taskId);
        return R.ok(taskDetailsService.selectTaskDetailsList(taskDetails));
    }
}