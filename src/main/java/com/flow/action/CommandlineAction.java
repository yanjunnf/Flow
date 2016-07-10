package com.flow.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandlineAction extends AbstractAction implements Action{
    private String commandLine;
    private String charset;
    
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
    public Object execute() {
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
