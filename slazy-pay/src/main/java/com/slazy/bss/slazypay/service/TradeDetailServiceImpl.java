package com.slazy.bss.slazypay.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slazy.bss.slazypay.model.TradeDetail;

@Service("tradeDetailService")
@Transactional
public class TradeDetailServiceImpl extends BaseServiceImpl<TradeDetail>{

}
