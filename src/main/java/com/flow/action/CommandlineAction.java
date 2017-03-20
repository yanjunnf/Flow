package com.flow.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.flow.job.JobManager;

public class CommandlineAction extends AbstractAction {
    private String commandLine;
    private String charset;
    private Logger logger = Logger.getLogger(JobManager.class.toString());
    
    public CommandlineAction(String name, String commandLine, boolean asyncAction) {
        super(name, asyncAction);
        this.commandLine = commandLine;
        charset = "UTF-8";
    }
    
    public CommandlineAction(String name, String commandLine, boolean asyncAction, String charset) {
        super(name, asyncAction);
        this.commandLine = commandLine;
        this.charset = charset;
    }

    @Override
    public Object execute() throws Exception {
        String response = null;
        if (commandLine != null && !commandLine.isEmpty()) {
            BufferedReader br = null;
            try {
                Process process = Runtime.getRuntime().exec(commandLine);
                br = new BufferedReader(new InputStreamReader(process.getInputStream(), charset));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                br = null;
                response = sb.toString();
            } catch (Exception e) {
            	logger.severe("Failed to execute action. Message =" + e.getMessage());
                throw e;
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return response;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
