package com.lang.gmall.sms.controller;


import com.lang.gmall.model.SmsFlashPromotion;
import com.lang.gmall.model.SmsHomeAdvertise;
import com.lang.gmall.result.CommonPage;
import com.lang.gmall.result.CommonResult;
import com.lang.gmall.sms.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购表 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/flash")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "SmsFlashPromotionController", description = "秒杀活动管理")
public class SmsFlashPromotionController {
    @Autowired
    private SmsFlashPromotionService promotionService;

    @GetMapping("/list")
    @ApiOperation("分页查询列表")
    public CommonResult<CommonPage<SmsFlashPromotion>> getPageList(int pageNum, int pageSize) {
        List<SmsFlashPromotion> list = promotionService.getPageList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    //创建
    //创建秒杀
    @PostMapping("/create")
    @ApiOperation("创建秒杀")
    public CommonResult createPromotion(@RequestBody SmsFlashPromotion promotion) {
        boolean isSuccess = promotionService.save(promotion);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    //更新
    @PostMapping("/update/{id}")
    @ApiOperation("更新秒杀")
    public CommonResult updatePromotion(@PathVariable Long id, @RequestBody SmsFlashPromotion promotion) {
        promotion.setId(id);
        boolean isSuccess = promotionService.updateById(promotion);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //更新上线状态
    @PostMapping("/update/status/{id}")
    @ApiOperation("更新秒杀上线状态")
    public CommonResult updateStatus(@PathVariable Long id, int status) {
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setId(id);
        promotion.setStatus(status);
        boolean isSuccess = promotionService.updateById(promotion);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除
    @PostMapping("/delete/{id}")
    @ApiOperation("删除广告秒杀")
    public CommonResult deletePromotion(@PathVariable Long id) {
        boolean isSuccess = promotionService.removeById(id);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

}
