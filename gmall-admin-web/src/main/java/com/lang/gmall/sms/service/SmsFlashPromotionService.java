package com.lang.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lang.gmall.model.SmsFlashPromotion;

import java.util.List;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsFlashPromotionService extends IService<SmsFlashPromotion> {

    List<SmsFlashPromotion> getPageList(int pageNum, int pageSize);
}
