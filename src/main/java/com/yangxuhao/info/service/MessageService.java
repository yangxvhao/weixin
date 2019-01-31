package com.yangxuhao.info.service;

import com.yangxuhao.info.bean.Message;
import com.yangxuhao.info.until.Constant;
import jodd.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.yangxuhao.info.until.Constant.SPLIT_FLAG;
import static com.yangxuhao.info.until.Constant.SPLIT_LENGTH;

/**
 * @author yangxuhao
 * @date 2019-01-31 11:27.
 */
@Slf4j
@Service
public class MessageService {
    @Value("${yang.data.file}")
    private String fileName;

    public Integer saveToFile(Message message){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getContactName()).append(SPLIT_FLAG);
        stringBuilder.append(message.getContactEmail()).append(SPLIT_FLAG);
        stringBuilder.append(message.getContactMessage()).append("\n");
        try {
            String filePath = getFilePath(fileName);
            if(StringUtils.isNotBlank(filePath)) {
                FileUtil.appendString(filePath, stringBuilder.toString());
                return 1;
            }
        } catch (IOException e) {
            log.error("saveToFile error, message = {}, error : ", message.toString(), e);
        }
        return 0;
    }
    
    public List<Message> readFromFile(){
        List<Message> messageList = Lists.newArrayList();
        try {
            String filePath = getFilePath(fileName);
            if(StringUtils.isNotBlank(filePath)) {
                messageList = Stream.of(FileUtil.readLines(filePath))
                        .map(this::splitToMessage)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
        }catch (Exception e){
            log.error("readFromFile error, error:", e);
        }
        return messageList.stream().skip(messageList.size() - 3).collect(Collectors.toList());
    }

    private Message splitToMessage(String line) {
        if(StringUtils.isNotBlank(line.replaceAll(SPLIT_FLAG, ""))){
            String[] strings = line.split(SPLIT_FLAG);
            if(strings.length == SPLIT_LENGTH){
                return Message.builder()
                        .contactName(strings[0])
                        .contactEmail(strings[1])
                        .contactMessage(strings[2])
                        .build();
            }
        }
        return null;
    }

    private String getFilePath(String fileName){
        return Optional.of(ClassLoader.getSystemResource(fileName).getPath()).orElse("");
    }
}
