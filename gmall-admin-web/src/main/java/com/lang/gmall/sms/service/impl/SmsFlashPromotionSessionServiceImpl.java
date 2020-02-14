package com.lang.gmall.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lang.gmall.dto.FlashPromotionVo;
import com.lang.gmall.model.SmsFlashPromotionSession;
import com.lang.gmall.sms.mapper.SmsFlashPromotionProductRelationMapper;
import com.lang.gmall.sms.mapper.SmsFlashPromotionSessionMapper;
import com.lang.gmall.sms.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession> implements SmsFlashPromotionSessionService {

    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper sessionMapper;

    @Override
    public List<FlashPromotionVo> getListVo(Long flashPromotionId) {
        List<FlashPromotionVo> list = new ArrayList<>();

        //在通过遍历时刻id来获取当前活动的所有商品数量
        QueryWrapper<SmsFlashPromotionSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<SmsFlashPromotionSession> sessions = sessionMapper.selectList(queryWrapper);
        sessions.stream().forEach((promotionSession) -> {
            FlashPromotionVo flashPromotionVo = new FlashPromotionVo();
            //将数据库查询出来的对象属性赋值到vo中
            BeanUtils.copyProperties(promotionSession, flashPromotionVo);
            //查询当前活动的所有商品数量
            int count = relationMapper.countProduct(flashPromotionId, promotionSession.getId());
            flashPromotionVo.setProductCount(count);
            //放入集合中
            list.add(flashPromotionVo);
        });
        return list;
    }
}
