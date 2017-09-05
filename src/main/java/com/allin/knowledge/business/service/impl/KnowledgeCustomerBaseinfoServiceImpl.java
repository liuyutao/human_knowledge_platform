package com.allin.knowledge.business.service.impl;

import com.allin.knowledge.business.service.KnowledgeCustomerBaseinfoService;
import com.allin.knowledge.mapper.KnowledgeCustomerBaseinfoMapper;
import com.allin.knowledge.model.KnowledgeCustomerBaseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author aubergine
 * @date 2017-9-5 21:04:14
 */
@Service
public class KnowledgeCustomerBaseinfoServiceImpl implements KnowledgeCustomerBaseinfoService{

    @Autowired
    private KnowledgeCustomerBaseinfoMapper baseinfoMapper;

    public List<KnowledgeCustomerBaseinfo> getList(Map paramMap) {
        return baseinfoMapper.getList(paramMap);
    }

}
