package com.lang.gmall.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lang.gmall.dto.RelationAndProductVo;
import com.lang.gmall.model.PmsProduct;
import com.lang.gmall.model.SmsFlashPromotion;
import com.lang.gmall.model.SmsFlashPromotionProductRelation;
import com.lang.gmall.pms.service.PmsProductService;
import com.lang.gmall.sms.mapper.SmsFlashPromotionProductRelationMapper;
import com.lang.gmall.sms.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl extends ServiceImpl<SmsFlashPromotionProductRelationMapper, SmsFlashPromotionProductRelation> implements SmsFlashPromotionProductRelationService {

    @Autowired
    private SmsFlashPromotionProductRelationMapper mapper;
    @Autowired
    private PmsProductService productService;

    @Override
    public List<RelationAndProductVo> getPageList(int pageNum, int pageSize, Long flashPromotionId, Long flashPromotionSessionId) {
        //
        List<RelationAndProductVo> voList = new ArrayList<>();
        //查询所有flash_promotion_id和flash_promotion_session_id为条件 符合的商品
        IPage<SmsFlashPromotionProductRelation> iPage = new Page(pageNum, pageSize);
        QueryWrapper<SmsFlashPromotionProductRelation> queryWrapper = new QueryWrapper<>();
        Map<String, Long> params = new HashMap<>();
        params.put("flash_promotion_id", flashPromotionId);
        params.put("flash_promotion_session_id", flashPromotionSessionId);
        queryWrapper.allEq(params);
        iPage = mapper.selectPage(iPage, queryWrapper);
        List<SmsFlashPromotionProductRelation> records = iPage.getRecords();
        records.stream().forEach((relation) -> {
            Long productId = relation.getProductId();
            PmsProduct product = productService.getById(productId);
            RelationAndProductVo relationAndProductVo = new RelationAndProductVo();
            BeanUtils.copyProperties(relation, relationAndProductVo);
            relationAndProductVo.setProduct(product);
            //将vo放入集合中
            voList.add(relationAndProductVo);
        });
        return voList;
    }

    //加上事务保证两个操作必须同时成功或失败
    @Override
    @Transactional
    public boolean updateRelationAndProduct(RelationAndProductVo relationAndProductVo) {
        //商品信息更新
        productService.updateById(relationAndProductVo.getProduct());
        //保存商品关系
        SmsFlashPromotionProductRelation relation = new SmsFlashPromotionProductRelation();
        BeanUtils.copyProperties(relationAndProductVo, relation);
        int i = mapper.updateById(relation);
        return i > 0;
    }
}
