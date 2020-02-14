package com.lang.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lang.gmall.dto.FlashPromotionVo;
import com.lang.gmall.model.SmsFlashPromotionSession;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsFlashPromotionSessionService extends IService<SmsFlashPromotionSession> {

    List<FlashPromotionVo> getListVo(Long flashPromotionId);
}
