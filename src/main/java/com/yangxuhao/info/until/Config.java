//package com.yangxuhao.info.until;
//
//import org.apache.commons.configuration.CompositeConfiguration;
//import org.apache.commons.configuration.Configuration;
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.log4j.Logger;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.util.HashSet;
///**
// * Created by yangxvhao on 17-4-27.
// */
//public class Config {
//    // Valid log names
//    public static final String DEV = "dev";
//    public static final String STAGING = "staging";
//    public static final String PROD = "prod";
//    public static final String TEST = "test";
//    public static final String root_dir = System.getProperty("user.dir");
//    private static Logger logger = Logger.getLogger(Config.class);
//    // Singleton.
//    private static Config instance = new Config();
//    private String activeConfig;
//    private CompositeConfiguration configuration;
//
//    // Loads log files. Panics if anything goes wrong.
//    private Config() {
//        // Find active configuration from environment variable.
//        activeConfig = System.getenv("CRAWLER_ACTIVE_CONFIG");
//        if (activeConfig == null) {
//            activeConfig = DEV;
//        }
//        loadActiveConfig();
//    }
//
//    // Get active configuration.
//    public static String getActiveConfig() {
//        return instance.activeConfig;
//    }
//
//    public static void loadTestConfig() {
//        instance.activeConfig = TEST;
//        instance.loadActiveConfig();
////        ConnectionProvider.initDBSource();
//    }
//
//    // Get a string type log. Returns null if not found.
//    public static String getString(String key) {
//        return instance.configuration.getString(key);
//    }
//
//    // Get a string type log. Returns defaultValue if not found.
//    public static String getString(String key, String defaultValue) {
//        return instance.configuration.getString(key, defaultValue);
//    }
//
//    // Get a string type log. Panic and exit if not found.
//    public static String getCriticalString(String key) {
//        String value = instance.configuration.getString(key);
//        if (value == null) {
//            logger.error("Cannot find configuration: " + key, new Exception());
//            System.exit(-1);
//        }
//        return value;
//    }
//
//    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
//        return instance.configuration.getBigDecimal(key, defaultValue);
//    }
//
//    // Get a int type log. Returns defaultValue if not found.
//    public static int getInt(String key, int defaultValue) {
//        return instance.configuration.getInt(key, defaultValue);
//    }
//
//    // Get a int type log. Returns defaultValue if not found.
//    public static Long getLong(String key) {
//        return instance.configuration.getLong(key);
//    }
//
//    private void loadActiveConfig() {
//        // Verify active configuration. Panic if active configuration is invalid.
//        HashSet<String> validConfigs = new HashSet<String>();
//        validConfigs.add(DEV);
//        validConfigs.add(STAGING);
//        validConfigs.add(PROD);
//        validConfigs.add(TEST);
//        if (!validConfigs.contains(activeConfig)) {
//            logger.error("Invalid configuration name: " + activeConfig, new Exception());
//            System.exit(-1);
//        }
//
//        logger.info("start com.yangxuhao.info.config file");
//        // Load common configuration and active configuration.
//        configuration = new CompositeConfiguration();
//        configuration.addConfiguration(loadConf("common"));
//        configuration.addConfiguration(loadConf("strings"));
//        configuration.addConfiguration(loadConf(activeConfig));
//        logger.info("end com.yangxuhao.info.config file");
//    }
//
//    // Loads classpath:/conf/<configName>.conf
//    // Panics if loading failed.
//    private Configuration loadConf(String configName){
//        PropertiesConfiguration conf = new PropertiesConfiguration();
//        InputStream is = null;
//        try {
//            logger.info("/conf/" + configName + ".conf");
//            is = Config.class.getResourceAsStream("/conf/" + configName + ".conf");
//            conf.load(is);
//        } catch (ConfigurationException e) {
//            logger.error("Cannot load configuration " + configName, e);
//            System.exit(-1);
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return conf;
//    }
//
//}
