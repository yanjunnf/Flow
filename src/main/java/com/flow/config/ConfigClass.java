package com.flow.config;

import javax.ws.rs.core.MediaType;

public class ConfigClass {
    public static String version = "1.00";
    public static String localIPAddr = "localhost";
    public static String webProtocol = "http://";
    public static int port = 8080;
    public static String baseUrl = webProtocol + ConfigClass.localIPAddr + ":" + ConfigClass.port + "/flow";
    public static int retryTimes = 3;
    public static int connectionTimeout = 5000;
    public static int readTimeout = 5000;
    public static String pcUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
    public static String mobileUserAgent = "Mozilla/5.0 (Linux; Android 4.4.2; GT-I9505 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Version/1.5 Chrome/28.0.1500.94 Mobile Safari/537.36";
    //Unit: second
    public static int interval = 1;
    
    public static int fixThreadPoolSize = 100;
    public static int scheduledThreadPoolSize = 50;
    
    public final static String contentType = MediaType.APPLICATION_JSON + "; charset=utf-8";
    public final static int UNKNOWN_ERROR = 9999;
}
