package com.lang.gmall.cms.controller;


import com.lang.gmall.cms.service.CmsOrderReasonService;
import com.lang.gmall.model.CmsOrderReason;
import com.lang.gmall.result.CommonPage;
import com.lang.gmall.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/returnReason")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "CmsOrderReasonController")
public class CmsOrderReasonController {
    @Autowired
    private CmsOrderReasonService reasonService;

    @GetMapping("/list")
    @ApiOperation("分页查询退货原因")
    public CommonResult<CommonPage<CmsOrderReason>> getPageList(int pageNum, int pageSize) {
        List<CmsOrderReason> reasons = reasonService.getPageList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(reasons));
    }

    //创建退货原因
    @PostMapping("/create")
    @ApiOperation("创建退货原因")
    public CommonResult createReason(@RequestBody CmsOrderReason reason) {
        reason.setCreateTime(new Date());
        boolean isSuccess = reasonService.save(reason);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    //获取退货原因
    @GetMapping("/{id}")
    @ApiOperation("获取退货原因")
    public CommonResult<CmsOrderReason> getReason(@PathVariable Long id) {
        CmsOrderReason reason = reasonService.getById(id);
        return CommonResult.success(reason);
    }

    //更新退货原因
    @PostMapping("/update/{id}")
    @ApiOperation("更新退货原因")
    public CommonResult updateReason(@PathVariable Long id, @RequestBody CmsOrderReason reason) {
        reason.setId(id);
        boolean isSuccess = reasonService.updateById(reason);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除退货原因
    @PostMapping("/delete")
    @ApiOperation("删除退货原因")
    public CommonResult deleteReason(int ids) {
        boolean isSuccess = reasonService.removeById(ids);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

    //更新是否可用选项
    @PostMapping("/update/status")
    @ApiOperation("更新是否可用选项")
    public CommonResult updateStatus(Long ids, int status) {
        CmsOrderReason reason = new CmsOrderReason();
        reason.setId(ids);
        reason.setStatus(status);
        boolean isSuccess = reasonService.updateById(reason);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

}
