package com.flow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtils {
    public static String curl(String url, String charset, boolean bMobile) {
        String result = null;
        BufferedReader br = null;
        try {
            StringBuilder builder = new StringBuilder(1024);
            URL urlRequest = new URL(url);
            URLConnection connection = urlRequest.openConnection();
            connection.setConnectTimeout(ConfigClass.connectionTimeout);
            connection.setReadTimeout(ConfigClass.readTimeout);
            connection.setRequestProperty("acccept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", bMobile ? ConfigClass.mobileUserAgent : ConfigClass.pcUserAgent);
            
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line = null;
            while ((line = br.readLine()) != null)
                builder.append(line);
            br.close();
            br = null;
            result = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static String getPageEncoding(String url, boolean bMobile) {
        String result = null;
        BufferedReader br = null;
        try {
            StringBuilder builder = new StringBuilder(1024);
            URL urlRequest = new URL(url);
            URLConnection connection = urlRequest.openConnection();
            connection.setConnectTimeout(ConfigClass.connectionTimeout);
            connection.setReadTimeout(ConfigClass.readTimeout);
            connection.setRequestProperty("acccept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", bMobile ? ConfigClass.mobileUserAgent : ConfigClass.pcUserAgent);
            
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("<body>"))
                    break;
                builder.append(line);
            }
            br.close();
            br = null;
            result = builder.toString();
            String base = "charset=";
            int begin = result.indexOf(base);
            if (begin >= 0) {
                int end = result.indexOf(">", begin);
                if (end > begin) {
                    result = result.substring(begin + base.length(), end);
                    String charset = result.replaceAll("[ \'\"/\\/]", "");
                    if (!charset.isEmpty()) {
                        if (charset.startsWith("gb"))
                            result = "gbk";
                        else
                            result = "utf-8";
                    } else
                        result = "";
                } else
                    result = "";
                
            } else
                result = "";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return result;
    }
    
    public static List<String> getMatchedStringList(String pattern, String content, int groups) {
        List<String> params = new ArrayList<String>();
        try {
            Pattern reg = Pattern.compile(pattern);
            Matcher m = reg.matcher(content);
            while(m.find()) {
                for (int i = 1; i <= groups; ++i) {
                    String param = m.group(i);
                    if (param != null && !param.isEmpty())
                        params.add(param);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return params;
    }
    
    public static boolean isNotEmptyString(String str) {
        return str != null && !str.isEmpty() ? true : false;
    }
    
    public static String loadCfgFile(String fileName) {
        String currDir = System.getProperty("user.dir");
        String configFile = currDir + File.separator + fileName;
        
        return readFile(configFile);
    }
    
    public static String readFile(String filePath) {
        File cf = new File(filePath);
        InputStreamReader fr = null;
        BufferedReader br = null;
        StringBuilder builder = null;
        if (cf.exists()) {
            try {
                builder = new StringBuilder(1024);
                fr = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
                br = new BufferedReader(fr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.startsWith("#"))
                        builder.append(line);
                }
                fr.close();
                br.close();
                fr = null;
                br = null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fr != null) {
                    try {
                        fr.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                if (br != null) {
                    try {
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return builder != null ? builder.toString() : null;
    }
    
    public static String removeHTMLNoisy(String source) {
        source = source.replaceAll("[\r|\n|\t]+", "");
        source = source.replaceAll("<!--[\\d\\D]*?-->", "");
        source = source.replaceAll("\\s{2,}", " ");
        return source;
    }
}
