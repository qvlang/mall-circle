package com.lang.gmall.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.lang.gmall.cms.mapper.CmsOrderReasonMapper;
import com.lang.gmall.cms.service.CmsOrderReasonService;
import com.lang.gmall.model.CmsOrderReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@Service
public class CmsOrderReasonServiceImpl extends ServiceImpl<CmsOrderReasonMapper, CmsOrderReason> implements CmsOrderReasonService {
    @Autowired
    private CmsOrderReasonMapper mapper;

    @Override
    public List<CmsOrderReason> getPageList(int pageNum, int pageSize) {
        //Page<CmsOrderReason> reasonPage = PageHelper.startPage(pageNum, pageSize);
        IPage<CmsOrderReason> reasonPage = new Page(pageNum, pageSize);
        IPage<CmsOrderReason> iPage = mapper.selectPage(reasonPage, null);
        List<CmsOrderReason> records = iPage.getRecords();
        return records;
    }
}
