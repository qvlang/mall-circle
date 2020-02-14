package com.lang.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lang.gmall.model.SmsHomeRecommendProduct;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsHomeRecommendProductService extends IService<SmsHomeRecommendProduct> {

    List<SmsHomeRecommendProduct> getPageList(int pageNum, int pageSize);
}
