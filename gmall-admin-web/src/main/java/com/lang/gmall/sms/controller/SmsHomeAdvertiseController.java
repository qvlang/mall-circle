package com.lang.gmall.sms.controller;


import com.lang.gmall.model.PmsBrand;
import com.lang.gmall.model.SmsHomeAdvertise;
import com.lang.gmall.result.CommonPage;
import com.lang.gmall.result.CommonResult;
import com.lang.gmall.sms.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/home/advertise")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "SmsHomeAdvertiseController", description = "主页广告管理")
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService advertiseService;

    //获取广告列表
    @GetMapping("/list")
    @ApiOperation("分页查询列表")
    public CommonResult<CommonPage<SmsHomeAdvertise>> getPageList(int pageNum, int pageSize) {
        List<SmsHomeAdvertise> list = advertiseService.getPageList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
    //创建广告
    @PostMapping("/create")
    @ApiOperation("创建广告")
    public CommonResult createAdvertise(@RequestBody SmsHomeAdvertise advertise) {
        boolean isSuccess = advertiseService.save(advertise);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    //查找
    @GetMapping("/{id}")
    @ApiOperation("根据id查找广告")
    public CommonResult<SmsHomeAdvertise> getAdvertiseById(@PathVariable Long id) {
        SmsHomeAdvertise advertise = advertiseService.getById(id);
        return CommonResult.success(advertise);
    }

    //更新
    @PostMapping("/update/{id}")
    @ApiOperation("更新广告")
    public CommonResult updateAdvertise(@PathVariable Long id, @RequestBody SmsHomeAdvertise advertise) {
        boolean isSuccess = advertiseService.saveOrUpdate(advertise);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //更新广告上线状态
    @PostMapping("/update/status/{id}")
    @ApiOperation("更新广告上线状态")
    public CommonResult updateStatus(@PathVariable Long id, int status) {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setId(id);
        advertise.setStatus(status);
        boolean isSuccess = advertiseService.updateById(advertise);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除
    //删除退货原因
    @PostMapping("/delete")
    @ApiOperation("删除广告")
    public CommonResult deleteAdvertise(int ids) {
        boolean isSuccess = advertiseService.removeById(ids);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }
}
