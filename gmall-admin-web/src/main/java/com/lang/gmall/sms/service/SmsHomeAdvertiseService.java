package com.lang.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lang.gmall.model.SmsHomeAdvertise;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    List<SmsHomeAdvertise> getPageList(int pageNum, int pageSize);
}
