package com.allin.knowledge.business.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MyBatis基础配置
 *
 * @author wyb
 * @since 2016-07-21 10:11
 * 此配置文件可以嵌套list、内部类==
 */
@ConfigurationProperties("classpath:myBatis.properties")
public class MyBatisProperty {
    // 分页配置
    private String reasonable;
    private String supportMethodsArguments;
    private String returnPageInfo;
    private String params;


    public String getReasonable() {
        return reasonable;
    }

    public void setReasonable(String reasonable) {
        this.reasonable = reasonable;
    }

    public String getSupportMethodsArguments() {
        return supportMethodsArguments;
    }

    public void setSupportMethodsArguments(String supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
    }

    public String getReturnPageInfo() {
        return returnPageInfo;
    }

    public void setReturnPageInfo(String returnPageInfo) {
        this.returnPageInfo = returnPageInfo;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
