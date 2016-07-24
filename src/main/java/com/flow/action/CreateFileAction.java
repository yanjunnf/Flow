package com.flow.action;

import java.io.File;

import org.apache.log4j.Logger;

public class CreateFileAction extends AbstractAction {
    private String fileName;
    private boolean overwrite;
    private Logger logger = Logger.getLogger(CreateFileAction.class);
    
    public CreateFileAction(String name, boolean asyncAction, String fileName, boolean overwrite) {
        super(name, asyncAction);
        this.fileName = fileName;
        this.overwrite = overwrite;
    }

    @Override
    public Object execute() {
        boolean result = false;
        if (fileName.isEmpty())
            logger.error("File name cannot be empty");
        else {
            try {
                File file = new File(fileName);
                if (file.exists()) {
                    if (overwrite) {
                        file.createNewFile();
                        result = true;
                    } else {
                        logger.error("The file exists already. File name = " + fileName);
                        result = false;
                    }
                } else {
                    file.createNewFile();
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }

}
