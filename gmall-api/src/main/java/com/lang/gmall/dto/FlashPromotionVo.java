package com.lang.gmall.dto;

import com.lang.gmall.model.SmsFlashPromotion;
import com.lang.gmall.model.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FlashPromotionVo", description = "秒杀活动的vo")
public class FlashPromotionVo extends SmsFlashPromotionSession {
    @ApiModelProperty("某活动的某时刻商品参与活动的总数")
    private int productCount;

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
