package com.lang.gmall.cms.controller;


import com.lang.gmall.cms.service.CmsOrderSetService;
import com.lang.gmall.model.CmsOrderSet;
import com.lang.gmall.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 秒杀订单过期时间 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/orderSetting")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "CmsOrderReasonController")
public class CmsOrderSetController {
    @Autowired
    private CmsOrderSetService orderSetService;

    //获取订单设置
    @GetMapping("/{id}")
    @ApiOperation("获取订单设置")
    public CommonResult<CmsOrderSet> getOrderSet(@PathVariable Long id) {
        CmsOrderSet orderSet = orderSetService.getById(id);
        return CommonResult.success(orderSet);
    }

    //创建更新订单设置
    @PostMapping("/update/{id}")
    @ApiOperation("创建更新订单设置")
    public CommonResult getOrderSet(@PathVariable Long id, @RequestBody CmsOrderSet orderSet) {
        //如果存在id即是更新 否则创建
        if (!StringUtils.isEmpty(id)) {
            orderSet.setId(id);
        }
        boolean isSuccess = orderSetService.saveOrUpdate(orderSet);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }
}
