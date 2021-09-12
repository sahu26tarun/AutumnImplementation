package com.dt.hgw.global;

public class Constants {

    public static final Boolean JENKINSRUN = Boolean.parseBoolean(System.getProperty("JenkinsRun", "false"));
    public static final String TENNANT = LocalConfig.TENANT;
    public static final String ENVIRONMENT = LocalConfig.ENVIRONMENT;


    //Value added for null MacAddress value in response
    public static final String IP_WITH_NULL_MAC="";

    //Values for now are regression, sanity, smoke
    public static final String TESTTYPE = System.getProperty("TestingType","regression");

    //Values can be methods, system, package, group, class
    public static final String SUITETYPE = System.getProperty("suiteType","group");
    public static final Boolean DBVALIDATION = Boolean.parseBoolean(System.getProperty("DBVALIDATION","true"));
    public static final Boolean REDISVALIDATION = Boolean.parseBoolean(System.getProperty("REDISVALIDATION","false"));

    public static final boolean FLAG_REMOVE_RETRIEDTESTS = true;
    public static final boolean FLAG_UPDATE_ZEPHYR = Boolean.parseBoolean(System.getProperty("ZEPHYR","false"));
    public static final boolean FLAG_UPDATE_SCREENSHOTS = false;

    public static final boolean FLAG_ENCRYPTION_DB = true;
    public static final boolean FLAG_ENCRYPTION_ONEAPP = false;

    public static final Boolean DBENCRYPTION=true;
    public static final Boolean TRANSITENCRYPTION=true;
    public static final Boolean NATCOEXPOSEDAPI = Boolean.parseBoolean(System.getProperty("NATCOEXPOSEDAPI", "false"));

    public static final Integer DATAPROVIDERCOUNTER = 3;
    public static final Integer RDKDATAPROVIDERCOUNTER = 1;
    public static final String PARTNER_ID = "";

}
