package com.lang.gmall.sms.controller;


import com.lang.gmall.dto.PromotionProductRelationVo;
import com.lang.gmall.dto.RelationAndProductVo;
import com.lang.gmall.model.SmsFlashPromotion;
import com.lang.gmall.model.SmsFlashPromotionProductRelation;
import com.lang.gmall.result.CommonPage;
import com.lang.gmall.result.CommonResult;
import com.lang.gmall.sms.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/flashProductRelation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "SmsFlashPromotionProductRelationController", description = "秒杀商品管理")
public class SmsFlashPromotionProductRelationController {
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @GetMapping("/list")
    @ApiOperation("分页查询列表")
    public CommonResult<CommonPage<RelationAndProductVo>> getPageList(int pageNum, int pageSize, Long flashPromotionId, Long flashPromotionSessionId) {
        List<RelationAndProductVo> list = relationService.getPageList(pageNum, pageSize, flashPromotionId, flashPromotionSessionId);
        return CommonResult.success(CommonPage.restPage(list));
    }

    //创建秒杀产品关系
    @PostMapping("/create")
    @ApiOperation("创建秒杀商品关系")
    public CommonResult createPromotionProductRelation(@RequestBody List<PromotionProductRelationVo> list) {
        List<SmsFlashPromotionProductRelation> relations = new ArrayList<>();
        //
        list.stream().forEach((vo) -> {
            SmsFlashPromotionProductRelation relation = new SmsFlashPromotionProductRelation();
            relation.setProductId(vo.getProductId());
            relation.setFlashPromotionSessionId(vo.getFlashPromotionSessionId());
            relation.setFlashPromotionId(vo.getFlashPromotionId());
            //添加到列表
            relations.add(relation);
        });
        boolean isSuccess = relationService.saveBatch(relations);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    //更新
    @PostMapping("/update/{id}")
    @ApiOperation("更新秒杀商品关系")
    public CommonResult updatePromotionProductRelation(@PathVariable Long id, @RequestBody RelationAndProductVo relationAndProductVo) {
        relationAndProductVo.setId(id);
        boolean isSuccess = relationService.updateRelationAndProduct(relationAndProductVo);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除
    @PostMapping("/delete/{id}")
    @ApiOperation("删除秒杀商品关系")
    public CommonResult deletePromotionProductRelation(@PathVariable Long id) {
        boolean isSuccess = relationService.removeById(id);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

}
