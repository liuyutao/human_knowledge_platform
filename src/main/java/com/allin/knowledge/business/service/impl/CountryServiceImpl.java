package com.allin.knowledge.business.service.impl;

import com.allin.knowledge.business.service.CountryService;
import com.allin.knowledge.mapper.CountryMapper;
import com.allin.knowledge.model.Country;
import com.allin.knowledge.util.generic.service.impl.DecorateMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liuzh
 * @since 2015-12-19 11:09
 */
@Service
public class CountryServiceImpl extends DecorateMapperServiceImpl<CountryMapper,Country> implements CountryService{

    @Autowired
    private CountryMapper countryMapper;

    public Map getList(Map paramMap) {
        return super.getList(paramMap);
    }

}
