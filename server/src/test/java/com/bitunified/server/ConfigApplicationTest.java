package com.bitunified.server;

import com.bitunified.server.google.Download;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ConfigApplicationTest {


    @Ignore
    @Test
    public void testRules() throws IOException {
        ConfigApplication configApplication = new ConfigApplication();
        File file = getTempFile("LedConfig.drl");
        if (file.exists()){
            file.delete();
        }
        ServletContext servletContext = new ServletContext() {
            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public ServletContext getContext(String s) {
                return null;
            }

            @Override
            public int getMajorVersion() {
                return 0;
            }

            @Override
            public int getMinorVersion() {
                return 0;
            }

            @Override
            public String getMimeType(String s) {
                return null;
            }

            @Override
            public Set getResourcePaths(String s) {
                return null;
            }

            @Override
            public URL getResource(String s) throws MalformedURLException {
                return null;
            }

            @Override
            public InputStream getResourceAsStream(String s) {
                return null;
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String s) {
                return null;
            }

            @Override
            public RequestDispatcher getNamedDispatcher(String s) {
                return null;
            }

            @Override
            public Servlet getServlet(String s) throws ServletException {
                return null;
            }

            @Override
            public Enumeration getServlets() {
                return null;
            }

            @Override
            public Enumeration getServletNames() {
                return null;
            }

            @Override
            public void log(String s) {

            }

            @Override
            public void log(Exception e, String s) {

            }

            @Override
            public void log(String s, Throwable throwable) {

            }

            @Override
            public String getRealPath(String s) {
                return null;
            }

            @Override
            public String getServerInfo() {
                return null;
            }

            @Override
            public String getInitParameter(String s) {
                return null;
            }

            @Override
            public Enumeration getInitParameterNames() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Enumeration getAttributeNames() {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public String getServletContextName() {
                return null;
            }
        };


        String result=configApplication.updateRules(servletContext);

        assertEquals(result,"Rules updates have been successfully applied!");

        assertTrue(file.exists());


    }

    private File getTempFile(String name) throws IOException {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));

        File tempFile = new File(baseDir, name);
        return tempFile;
    }
}