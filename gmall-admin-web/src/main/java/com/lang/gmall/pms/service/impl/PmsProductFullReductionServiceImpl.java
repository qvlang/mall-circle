package com.lang.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lang.gmall.model.PmsProductFullReduction;
import com.lang.gmall.pms.mapper.PmsProductFullReductionMapper;
import com.lang.gmall.pms.service.PmsProductFullReductionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @author qll
 * @since 2020-01-15
 */
@Service
public class PmsProductFullReductionServiceImpl extends ServiceImpl<PmsProductFullReductionMapper, PmsProductFullReduction> implements PmsProductFullReductionService {

}
