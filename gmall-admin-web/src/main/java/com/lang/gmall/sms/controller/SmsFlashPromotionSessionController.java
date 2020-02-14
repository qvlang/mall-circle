package com.lang.gmall.sms.controller;


import com.lang.gmall.dto.FlashPromotionVo;
import com.lang.gmall.model.SmsFlashPromotionSession;
import com.lang.gmall.result.CommonResult;
import com.lang.gmall.sms.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/flashSession")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "SmsFlashPromotionSessionController", description = "秒杀时间管理")
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService sessionService;

    @GetMapping("/list")
    @ApiOperation("查询所有列表")
    public CommonResult<List<SmsFlashPromotionSession>> getList() {
        List<SmsFlashPromotionSession> list = sessionService.list();
        return CommonResult.success(list);
    }

    @GetMapping("/selectList")
    @ApiOperation("查询时刻活动商品数")
    public CommonResult<List<FlashPromotionVo>> getListVo(Long flashPromotionId) {
        List<FlashPromotionVo> listVo = sessionService.getListVo(flashPromotionId);
        return CommonResult.success(listVo);
    }

    //创建
    //创建广告
    @PostMapping("/create")
    @ApiOperation("创建秒杀")
    public CommonResult createPromotionSession(@RequestBody SmsFlashPromotionSession promotion) {
        boolean isSuccess = sessionService.save(promotion);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    //更新
    @PostMapping("/update/{id}")
    @ApiOperation("更新秒杀")
    public CommonResult updatePromotionSession(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotion) {
        promotion.setId(id);
        boolean isSuccess = sessionService.updateById(promotion);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //更新启用状态
    @PostMapping("/update/status/{id}")
    @ApiOperation("更新秒杀上线状态")
    public CommonResult updateStatus(@PathVariable Long id, int status) {
        SmsFlashPromotionSession promotion = new SmsFlashPromotionSession();
        promotion.setId(id);
        promotion.setStatus(status);
        boolean isSuccess = sessionService.updateById(promotion);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除
    @PostMapping("/delete/{id}")
    @ApiOperation("删除广告秒杀")
    public CommonResult deletePromotionSession(@PathVariable Long id) {
        boolean isSuccess = sessionService.removeById(id);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

}
