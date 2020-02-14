package com.lang.gmall.pms.controller;

import com.lang.gmall.file.FastFdsFile;
import com.lang.gmall.model.PmsBrand;
import com.lang.gmall.pms.service.PmsBrandService;
import com.lang.gmall.result.CommonPage;
import com.lang.gmall.result.CommonResult;
import com.lang.gmall.utils.FastFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Api(tags = "PmsBrandController", description = "品牌管理")
@CacheConfig(cacheNames = "brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //获取品牌列表
    @GetMapping("/list")
    @ApiOperation("分页查询列表")
    @Cacheable(key = "#p1")
    public CommonResult<CommonPage<PmsBrand>> getPageList(int pageNum, int pageSize) {
        List<PmsBrand> list = pmsBrandService.getPageList(pageNum, pageSize);
        stringRedisTemplate.boundValueOps("boo55t").set("ggg666");
        System.out.println("查了");
        return CommonResult.success(CommonPage.restPage(list));
    }

    //创建品牌
    @PostMapping("/create")
    @ApiOperation("创建品牌")
    public CommonResult createBrande(@RequestBody PmsBrand brand) {
        boolean isSuccess = pmsBrandService.save(brand);
        return isSuccess ? CommonResult.success("创建成功") : CommonResult.failed("创建失败");
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查找品牌")
    @Cacheable(key = "#id")
    public CommonResult<PmsBrand> getBrandById(@PathVariable Long id) {
        PmsBrand brand = pmsBrandService.getById(id);
        System.out.println("查了");
        return CommonResult.success(brand);
    }

    @PostMapping("/update/{id}")
    @ApiOperation("更新品牌")
    public CommonResult updateBrand(@PathVariable Long id, @RequestBody PmsBrand brand) {
        boolean isSuccess = pmsBrandService.saveOrUpdate(brand);
        return isSuccess ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    //删除品牌
    @GetMapping("/delete/{id}")
    @ApiOperation("删除品牌")
    public CommonResult updateBrand(@PathVariable Long id) {
        boolean isSuccess = pmsBrandService.removeById(id);
        return isSuccess ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

    //上传文件
    @PostMapping("/file")
    @ApiOperation("上传文件")
    public CommonResult<String[]> uploadFile(MultipartFile multipartFile) {
        try {
            //获取上传文件的字节流
            byte[] bytes = multipartFile.getBytes();
            //获取上传文件的名字
            String originalFilename = multipartFile.getOriginalFilename();
            //拿到文件的后缀名
            String extension = StringUtils.getFilenameExtension(originalFilename);
            //拿到文件对象
            FastFdsFile fastFdsFile = new FastFdsFile();
            fastFdsFile.setContent(bytes);
            fastFdsFile.setAuthor("qll");
            fastFdsFile.setExt(extension);
            //上传文件
            String[] strings = FastFileUtil.FileUpload(fastFdsFile);
            return CommonResult.success(strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
