package com.flow.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ReadFileAction extends AbstractAction {
    private String fileName;
    private String charset;
    private Logger logger = Logger.getLogger(ReadFileAction.class.toString());
    
    public ReadFileAction(String name, boolean asyncAction) {
        super(name, asyncAction);
        fileName = "";
        charset = "UTF-8";
    }
    
    public ReadFileAction(String name, boolean asyncAction, String fileName, String charset) {
        super(name, asyncAction);
        this.fileName = fileName;
        this.charset = charset;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Object execute() throws Exception{
        String result = null;
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(fileName);
            if (file.exists()) {
                if (file.isFile()) {
                    result = readFile();
                } else
                    logger.severe("The file name is not file. File name = " + fileName);
            } else
                logger.severe("The file name is invalid. File name = " + fileName);
        } else
            logger.severe("The file name is empty.");
        return result;
    }
    
    private String readFile() throws Exception{
        FileInputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader buff = null;
        StringBuilder builder = new StringBuilder();
        try {
            inputStream = new FileInputStream(fileName);
            reader = new InputStreamReader(inputStream, charset);
            buff = new BufferedReader(reader);
            String line = null;
            while ((line = buff.readLine()) != null) {
                builder.append(line);
                builder.append("\r\n");
            }
            buff.close();
            buff = null;
            reader.close();
            reader = null;
            inputStream.close();
            inputStream = null;
            logger.info("Finished to read file. File name=" + fileName);
        } catch (Exception e) {
        	logger.severe("Failed to execute ReadFileAction. Message=" + e.getMessage());
            throw e;
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return builder.toString();
    }
}
