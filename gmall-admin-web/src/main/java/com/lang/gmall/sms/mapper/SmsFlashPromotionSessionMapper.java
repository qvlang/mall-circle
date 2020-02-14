package com.lang.gmall.sms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lang.gmall.model.PmsProductCategory;
import com.lang.gmall.model.SmsFlashPromotionSession;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 限时购场次表 Mapper 接口
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsFlashPromotionSessionMapper extends BaseMapper<SmsFlashPromotionSession> {
    @Select("SELECT id from sms_flash_promotion_session ${ew.customSqlSegment}")
    List<Long> getIds(@Param(Constants.WRAPPER) Wrapper<PmsProductCategory> userWrapper);

    @Select("SELECT id from sms_flash_promotion_session WHERE status = #{status}")
    List<Long> getIds(@Param("status") int status);
}
