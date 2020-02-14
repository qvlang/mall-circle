package com.lang.gmall.sms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lang.gmall.model.SmsFlashPromotion;
import com.lang.gmall.model.SmsHomeAdvertise;
import com.lang.gmall.sms.mapper.SmsFlashPromotionMapper;
import com.lang.gmall.sms.service.SmsFlashPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements SmsFlashPromotionService {
    @Autowired
    private SmsFlashPromotionMapper mapper;

    @Override
    public List<SmsFlashPromotion> getPageList(int pageNum, int pageSize) {
        IPage<SmsFlashPromotion> iPage = new Page(pageNum, pageSize);
        iPage = mapper.selectPage(iPage, null);
        List<SmsFlashPromotion> records = iPage.getRecords();
        return records;
    }
}
