package com.flow.action;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class ReadFileActionTest {
    @Test
    public void testReadFileAction() {
        //Create file
        String fileName = "test.txt";
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
            writer.write("Test1");
            writer.write("\r\n");
            writer.write("Test2");
            writer.flush();
            writer.close();
            outputStream.close();
            
            //Run action
            ReadFileAction action = new ReadFileAction("actionName", false, fileName, "UTF-8");
            String result = (String)action.execute();
            assertEquals(result.contains("Test1"), true);
            assertEquals(result.contains("Test2"), true);
            
            //Delete file
            File file = new File(fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(true, false);            
        }
    }
}
