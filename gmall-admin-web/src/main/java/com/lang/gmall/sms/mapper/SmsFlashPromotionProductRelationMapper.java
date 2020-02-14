package com.lang.gmall.sms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lang.gmall.model.PmsProductCategory;
import com.lang.gmall.model.SmsFlashPromotionProductRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 Mapper 接口
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsFlashPromotionProductRelationMapper extends BaseMapper<SmsFlashPromotionProductRelation> {
   // @Select("SELECT COUNT(1) from sms_flash_promotion_product_relation ${ew.customSqlSegment}")
   // int countProduct(@Param(Constants.WRAPPER) Wrapper<Long> userWrapper, @Param(Constants.WRAPPER) Wrapper<Long> userWrapper1);

    @Select("SELECT COUNT(1) from sms_flash_promotion_product_relation WHERE flash_promotion_id = #{promotionId} AND flash_promotion_session_id = #{sessionId}")
    int countProduct(@Param("promotionId") Long promotionId, @Param("sessionId") Long sessionId);
}
