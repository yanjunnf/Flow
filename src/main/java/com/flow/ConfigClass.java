package com.flow;
import java.util.logging.Level;

import javax.ws.rs.core.MediaType;

public class ConfigClass {
    public static String version = "1.83";
    public static String localIPAddr = "localhost";
    public static String webProtocol = "http://";
    public static int port = 8080;
    public static String baseUrl = webProtocol + ConfigClass.localIPAddr + ":" + ConfigClass.port + "/flow";
    public static boolean bEnableMemcache = false;
    public static String remoteCacheServer = "192.168.116.128";
    public static int cachePort = 12345;
    //public static String cacheKey = "weibo_tc_topnews_async_web";
    public static String cacheKey = "q1";
    public static int mcThreadPoolSize = 3;
    public static int maxDriverCount = 50;
    public static int pageLoadTimeout = 30;
    public static int jsExecuteTimeout = 30;
    public static int findElementTimeout = 100; //Milliseconds
    public static int browserWindowWidth = 1024;
    public static int browserWindowHeight = 768;
    public static int connectionTimeout = 5000;
    public static int readTimeout = 5000;
    public static String pcUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
    public static String mobileUserAgent = "Mozilla/5.0 (Linux; Android 4.4.2; GT-I9505 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Version/1.5 Chrome/28.0.1500.94 Mobile Safari/537.36";
    public static int retryTimes = 3;
    public static String availableServerIPs = "10.75.14.148;10.75.14.150;10.73.13.145;10.73.13.156;10.77.26.117;10.210.229.241;10.210.229.242;10.210.229.243;10.210.229.244";
    public static Thread WaitSignalThread = null;
    public static int logFileLimit = 20; //MB
    public static int logFileCount = 100; //The number of file to use
    public static Level logLevel = Level.INFO;
    public static String phantomjsLogLevel = "ERROR";
    public static long PhantomJSMaxExecuteTime = 120000; //2 minutes
    public static boolean bDisablePhantomJSLog = true;
    public static boolean bLoadImage = false;
    public static int requestWaitToHandleTimeout = 30; //in seconds
    public static int waitForElementPresentTimeout = 10; //in seconds
    public static int maxTimesForExtractContent = 100;
    public static Integer newFoundTemplates = 0;
    
    public final static String phantomJSPath = "../awp/";
    public final static String resourcePath = "../webapps/awp/conf/";
    public final static String logPath = "/logs/";
    public final static String configFile = resourcePath + "config.properties";
    public final static String commentConfFile = resourcePath + "comment.cfg";
    public final static String siteConfigFile = resourcePath + "siteConfig.cfg";
    public final static String contentType = MediaType.APPLICATION_JSON + "; charset=utf-8";
    
    public static String remoteHttpServerUrl = "http://localhost:12345/myapp/myresource/sendWebContent";
    public static boolean sendToRemoteHttpServer = true;
    //VIPS
    /*
     Adapt 2015-01-02 12:00:12 or 2015-01-02T12:00:12 or 2015/01/02 12:00:12 or 2015/01/02 12:01 or 2015-01-02 12:01 
     Or 11-25 09:11 and so on
     */

    
    public final static int UNKNOWN_ERROR = 9999;
}
