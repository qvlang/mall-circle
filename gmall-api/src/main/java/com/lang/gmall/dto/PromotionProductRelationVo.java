package com.lang.gmall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SmsFlashPromotionProductRelation的vo对象", description = "创建秒杀产品的必须属性")
public class PromotionProductRelationVo {
    @ApiModelProperty("商品对应id")
    private Long productId;
    @ApiModelProperty("秒杀活动id")
    private Long flashPromotionId;
    @ApiModelProperty("秒杀时刻id")
    private Long flashPromotionSessionId;
}
