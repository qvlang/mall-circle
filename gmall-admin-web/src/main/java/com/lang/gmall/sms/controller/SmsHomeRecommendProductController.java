package com.lang.gmall.sms.controller;


import com.lang.gmall.dto.ProductVo;
import com.lang.gmall.model.SmsHomeAdvertise;
import com.lang.gmall.model.SmsHomeRecommendProduct;
import com.lang.gmall.result.CommonPage;
import com.lang.gmall.result.CommonResult;
import com.lang.gmall.sms.service.SmsHomeRecommendProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/home/recommendProduct")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "SmsHomeRecommendProductController", description = "人气推荐管理")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService productService;

    //获取广告列表
    @GetMapping("/list")
    @ApiOperation("分页查询列表")
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> getPageList(int pageNum, int pageSize) {
        List<SmsHomeRecommendProduct> list = productService.getPageList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    //创建产品推荐
    @PostMapping("/create")
    @ApiOperation("创建产品推荐")
    public CommonResult createRecommendProduct(@RequestBody List<ProductVo> list) {
        //使用流对象处理
        List<SmsHomeRecommendProduct> recommendProducts = new ArrayList<>();
        list.stream().forEach((productVo) -> {
            SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
            product.setRecommendStatus(1);
            product.setSort(0);
            product.setProductId(productVo.getProductId());
            product.setProductName(productVo.getProductName());
            recommendProducts.add(product);
        });
        boolean isSuccess = productService.saveBatch(recommendProducts);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    //更新推荐排序
    @PostMapping("/update/sort/{idn}")
    @ApiOperation("更新广告上线状态")
    public CommonResult updateSort(@PathVariable Long idn, int sort, Long id) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        boolean isSuccess = productService.updateById(recommendProduct);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //更新推荐状态
    @PostMapping("/update/recommendStatus")
    @ApiOperation("更新推荐状态")
    public CommonResult updateRecommendStatus(int recommendStatus, Long id) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setRecommendStatus(recommendStatus);
        boolean isSuccess = productService.updateById(recommendProduct);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除推荐
    @PostMapping("/delete")
    @ApiOperation("删除推荐")
    public CommonResult deleteRecommendProduct(int ids) {
        boolean isSuccess = productService.removeById(ids);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

}
