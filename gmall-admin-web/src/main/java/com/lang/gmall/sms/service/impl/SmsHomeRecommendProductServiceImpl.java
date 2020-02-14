package com.lang.gmall.sms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lang.gmall.model.SmsHomeAdvertise;
import com.lang.gmall.model.SmsHomeRecommendProduct;
import com.lang.gmall.sms.mapper.SmsHomeRecommendProductMapper;
import com.lang.gmall.sms.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements SmsHomeRecommendProductService {

    @Autowired
    private SmsHomeRecommendProductMapper mapper;

    @Override
    public List<SmsHomeRecommendProduct> getPageList(int pageNum, int pageSize) {
        IPage<SmsHomeRecommendProduct> iPage = new Page(pageNum, pageSize);
        iPage = mapper.selectPage(iPage, null);
        List<SmsHomeRecommendProduct> records = iPage.getRecords();
        return records;
    }
}
