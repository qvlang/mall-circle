package com.lang.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lang.gmall.dto.RelationAndProductVo;
import com.lang.gmall.model.SmsFlashPromotionProductRelation;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
public interface SmsFlashPromotionProductRelationService extends IService<SmsFlashPromotionProductRelation> {

    List<RelationAndProductVo> getPageList(int pageNum, int pageSize, Long flashPromotionId, Long flashPromotionSessionId);

    boolean updateRelationAndProduct(RelationAndProductVo relationAndProductVo);
}
