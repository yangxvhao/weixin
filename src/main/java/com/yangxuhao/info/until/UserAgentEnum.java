package com.yangxuhao.info.until;

/**
 * Created by yangxvhao on 17-4-25.
 */
public enum UserAgentEnum {
    
    USER_AGENT_ENUM("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
    
    private final String value;
    
    UserAgentEnum(String value){
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
