package com.bitunified.server;

import com.bitunified.ledconfig.configuration.config.GroovyCompiler;
import com.bitunified.ledconfig.configuration.parser.steps.Data;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.configuration.parser.steps.ParserDataResult;
import com.bitunified.server.google.Download;

import javax.servlet.ServletContext;
import java.io.*;


public class ConfigApplication {

    public String updateData(ServletContext context) {
        String fileNameStart = "ParserData";
        String message;
        StringBuilder sb = new StringBuilder();

        Download download = new Download(context);
        GroovyCompiler groovyCompiler = new GroovyCompiler();

        try {
            ParserDataResult dataResult = groovyCompiler.compile(download.getParserDataFile(fileNameStart));
            if (dataResult != null) {
                Data.parser = new Parser();
                Data.parser.createParts(dataResult);
            }
        } catch (Exception e) {
            System.out.print(e);
            sb.append(e);
        }
        if (sb.length() == 0) {
            sb.append("Data updates have been successfully applied!");
        }
        message = sb.toString();

        return message;
    }

    public String updateRules(ServletContext context) {
        String message;
        StringBuilder sb = new StringBuilder();
        Download download = new Download(context);
        try {
            ByteArrayOutputStream outputStream = download.getParserDataFile("LedConfig");
            createTempDir("LedConfig.drl", outputStream);
        } catch (Exception e) {
            System.out.print(e);
            sb.append(e);
        }
        if (sb.length() == 0) {
            sb.append("Rules updates have been successfully applied!");
        }
        message = sb.toString();

        return message;
    }

    private void createTempDir(String name, ByteArrayOutputStream outputStream) throws IOException {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));

        File tempFile = new File(baseDir, name);

        try {
            FileWriter f2 = new FileWriter(tempFile, false);
            f2.write(outputStream.toString("UTF-8"));
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
