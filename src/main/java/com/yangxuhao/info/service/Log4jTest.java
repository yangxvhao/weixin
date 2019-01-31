package com.yangxuhao.info.service;


import java.util.logging.Logger;

/**
 * Created by yangxvhao on 17-5-1.
 */
public class Log4jTest {
    static Logger logger= Logger.getLogger(String.valueOf(Log4jTest.class));
    public static void main(String[] args) {
        String temp="fheifhe";
        logger.info(temp);
    }
}
