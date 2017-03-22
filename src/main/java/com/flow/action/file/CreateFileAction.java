package com.flow.action.file;

import java.io.File;
import java.util.logging.Logger;

import com.flow.action.AbstractAction;


public class CreateFileAction extends AbstractAction {
    private String fileName;
    private boolean overwrite;
    private Logger logger = Logger.getLogger(CreateFileAction.class.toString());
    
    public CreateFileAction(String name, boolean asyncAction, String fileName, boolean overwrite) {
        super(name, asyncAction);
        this.fileName = fileName;
        this.overwrite = overwrite;
    }

    @Override
    public Object execute() throws Exception {
        boolean result = false;
        if (fileName.isEmpty())
            logger.severe("File name cannot be empty");
        else {
            try {
                File file = new File(fileName);
                if (file.exists()) {
                    if (overwrite) {
                        file.createNewFile();
                        result = true;
                    } else {
                        logger.severe("The file exists already. File name = " + fileName);
                        result = false;
                    }
                } else {
                    file.createNewFile();
                    result = true;
                }
            } catch (Exception e) {
                logger.severe("Failed to execute action. Message=" + e.getMessage());
                throw e;
            }
        }
        
        return result;
    }

}
