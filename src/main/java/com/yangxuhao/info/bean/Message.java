package com.yangxuhao.info.bean;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author yangxuhao
 * @date 2019-01-31 11:04.
 */
@Data
@ToString
@Builder
public class Message {
    private String contactName;
    private String contactEmail;
    private String contactMessage;
}
