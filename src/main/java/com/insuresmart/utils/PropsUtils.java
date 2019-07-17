package com.insuresmart.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author:shixianqing
 * @Date:2019/7/17 15:46
 * @Description:
 **/
public class PropsUtils {

    private static Properties props = new Properties();

    static {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getString(String key){
        return props.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getString("acks"));
    }
}
