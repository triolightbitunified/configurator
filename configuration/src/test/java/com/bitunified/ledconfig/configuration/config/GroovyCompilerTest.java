package com.bitunified.ledconfig.configuration.config;

import com.bitunified.ledconfig.configuration.parser.steps.ParserDataResult;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;


public class GroovyCompilerTest {

    @Test
    public void test() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        GroovyCompiler compiler = new GroovyCompiler();
        InputStream inputStream = GroovyCompilerTest.class.getResourceAsStream("/ParserData.groovy");

        ParserDataResult dataResult = compiler.compile(convertToOutputStream(inputStream));
        assertTrue(dataResult.getModels().size() > 100);
    }

    private ByteArrayOutputStream convertToOutputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = inputStream.read(buffer);
        while (len != -1) {
            outputStream.write(buffer, 0, len);
            len = inputStream.read(buffer);
        }
        return outputStream;
    }
}