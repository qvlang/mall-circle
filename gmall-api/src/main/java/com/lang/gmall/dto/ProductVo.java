package com.lang.gmall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ProductVo对象", description = "推荐产品Vo")
public class ProductVo {
    @ApiModelProperty("推荐产品Id")
    private Long productId;
    @ApiModelProperty("推荐产品名称")
    private String productName;
}
