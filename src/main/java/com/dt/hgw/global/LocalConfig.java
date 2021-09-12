package com.dt.hgw.global;

import com.dt.hgwqa.autumn.utils.readWriteUtil.PropertyUtils;

import java.util.Arrays;
import java.util.List;

public class LocalConfig {

    public static final String BaseURL;
    public static final String TENANT;
    public static final String ENVIRONMENT;
    public static final String MICROSOFT_TEAMS_URI;
    public static final String HGW_QA_MONGODB;


    static {
        try {
            PropertyUtils.getInstance().load("config.properties");
            BaseURL = PropertyUtils.getInstance().getValue("BaseURL");
            TENANT=PropertyUtils.getInstance().getValue("TENANT");
            ENVIRONMENT=PropertyUtils.getInstance().getValue("ENVIRONMENT");
            MICROSOFT_TEAMS_URI = PropertyUtils.getInstance().getValue("MICROSOFT_TEAMS_URI");
            HGW_QA_MONGODB = PropertyUtils.getInstance().getValue("HGW_QA_MONGODB");


        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", t);
        }
    }

}
