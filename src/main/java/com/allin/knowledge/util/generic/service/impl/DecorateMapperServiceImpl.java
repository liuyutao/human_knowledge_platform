package com.allin.knowledge.util.generic.service.impl;

import com.allin.knowledge.util.BaseResponseObject;
import com.allin.knowledge.util.generic.dao.GenericMapperDAO;
import com.comm.util.MapUtil;
import com.comm.util.page.Page;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 没有事物,如需事物,子类需重写父类方法
 */
public abstract class DecorateMapperServiceImpl<T1 extends GenericMapperDAO<?>, T2> {
    // 实体类类型(由构造方法自动赋值)
    private Class<T1> t1Class;

    private GenericMapperServiceImpl<T1, T2> genericService;

    protected GenericMapperServiceImpl<T1, T2> getGenericService() {
        if (genericService == null) {
            genericService = new GenericMapperServiceImpl<T1, T2>(getT1Class());
        }
        return genericService;
    }

    /*************** 封装固定模式的一些方法 ****************/

    public Map create(Map paramMap) {

        BaseResponseObject responseObject = new BaseResponseObject();
        responseObject.setResponseStatus(Boolean.FALSE);
        try {
            long id = getGenericService().create(paramMap);
            responseObject.setResponsePk(id);//返回的主键ID
            responseObject.setResponseStatus(Boolean.TRUE);
            responseObject.setResponseMessage("create success");
        } catch (Exception ex) {
            responseObject.setResponseStatus(Boolean.FALSE);
            responseObject.setResponseMessage("create error");
            ex.printStackTrace();
        }
        return MapUtil.transBean2Map(responseObject);

    }

    public Map update(Map paramMap) {
        BaseResponseObject responseObject = new BaseResponseObject();
        responseObject.setResponseStatus(Boolean.FALSE);
        try {
            Long themeId = System.currentTimeMillis();
            paramMap.put("themeId", themeId);

            getGenericService().update(paramMap);

            responseObject.setResponsePk(themeId);
            responseObject.setResponseStatus(Boolean.TRUE);
            responseObject.setResponseMessage("update success");
        } catch (Exception ex) {
            responseObject.setResponseStatus(Boolean.FALSE);
            responseObject.setResponseMessage("update error");
            ex.printStackTrace();
        }
        return MapUtil.transBean2Map(responseObject);

    }

    public Map getMapList(Map paramMap) {

        BaseResponseObject responseObject = new BaseResponseObject(
                Boolean.FALSE, "", "");
        try {
            List<Map> respList = new ArrayList<Map>();
            List<T2> data_list = getGenericService().getList(paramMap);
            if (!CollectionUtils.isEmpty(data_list)) {
                for (T2 bo : data_list) {
                    respList.add(MapUtil.transBean2Map(bo));
                }
            }
            HashMap responseData = new HashMap();
            if (!CollectionUtils.isEmpty(respList)) {
                responseData.put("data", respList);
            } else {
                responseObject.setResponseMessage("NO DATA");
            }
            responseObject.setResponseData(responseData);
            responseObject.setResponseStatus(Boolean.TRUE);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setResponseStatus(Boolean.FALSE);
        }
        return MapUtil.transBean2Map(responseObject);
    }

    public Map getList(Map paramMap) {


        BaseResponseObject responseObject = new BaseResponseObject(Boolean.FALSE, "", "");
        try {
            List<Map> respList = new ArrayList<Map>();
            List<T2> data_list = getGenericService().getList(paramMap);
            if (!CollectionUtils.isEmpty(data_list)) {
                for (T2 bo : data_list) {
                    respList.add(MapUtil.transBean2Map(bo));
                }
            }
            HashMap responseData = new HashMap();
            if (!CollectionUtils.isEmpty(respList)) {
                responseData.put("data", respList);
            } else {
                responseObject.setResponseMessage("NO DATA");
            }
            responseObject.setResponseData(responseData);
            responseObject.setResponseStatus(Boolean.TRUE);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setResponseStatus(Boolean.FALSE);
        }
        return MapUtil.transBean2Map(responseObject);
    }

    public Map getById(long id) {

        BaseResponseObject responseObject = new BaseResponseObject(
                Boolean.FALSE, "", "");
        try {
            T2 object = getGenericService().getById(id);
            HashMap responseData = new HashMap();
            if (null != object) {
                responseData.put("data", object);
            } else {
                responseObject.setResponseMessage("NO DATA");
            }
            responseObject.setResponseData(responseData);
            responseObject.setResponseStatus(Boolean.TRUE);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setResponseStatus(Boolean.FALSE);
        }
        return MapUtil.transBean2Map(responseObject);
    }


    public Map getMapById(long id) {
        BaseResponseObject responseObject = new BaseResponseObject(
                Boolean.FALSE, "", "");
        try {
            T2 object = getGenericService().getById(id);
            HashMap responseData = new HashMap();
            if (null != object) {
                responseData.put("data", object);
            } else {
                responseObject.setResponseMessage("NO DATA");
            }
            responseObject.setResponseData(responseData);
            responseObject.setResponseStatus(Boolean.TRUE);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setResponseStatus(Boolean.FALSE);
        }
        return MapUtil.transBean2Map(responseObject);

    }

    public Integer getCount(Map paramMap) {
        return getGenericService().getCount(paramMap);
    }

    public Map getPageList(Map paramMap) {
        BaseResponseObject responseObject = new BaseResponseObject(Boolean.FALSE, "", "");
        Page page = null;
        try {
            Map responseData = new HashMap();
            List<Map> respList = new ArrayList<Map>();
            page = getGenericService().getPageList(paramMap);
            List<T2> data_list = page.getItems();
            if (!CollectionUtils.isEmpty(data_list)) {
                for (T2 bo : data_list) {
                    respList.add(MapUtil.transBean2Map(bo));
                }
            }
            page.setItems(respList);
            Map pageMap = MapUtil.transBean2Map(page);
            responseData.put("data", pageMap);
            responseObject.setResponseData(responseData);
            responseObject.setResponseStatus(Boolean.TRUE);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObject.setResponseStatus(Boolean.FALSE);
        }
        return MapUtil.transBean2Map(responseObject);
    }


    private void buildT1Class() {
        this.t1Class = null;
        Class c = this.getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.t1Class = (Class<T1>) p[0];
        }
    }

    private Class<T1> getT1Class() {
        if (t1Class == null) {
            buildT1Class();
        }
        return t1Class;
    }


}
