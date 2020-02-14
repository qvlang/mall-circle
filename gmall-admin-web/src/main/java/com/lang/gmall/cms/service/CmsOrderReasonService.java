package com.lang.gmall.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lang.gmall.model.CmsOrderReason;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface CmsOrderReasonService extends IService<CmsOrderReason> {

    List<CmsOrderReason> getPageList(int pageNum, int pageSize);
}
