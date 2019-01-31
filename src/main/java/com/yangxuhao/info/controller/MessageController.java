package com.yangxuhao.info.controller;

import com.yangxuhao.info.bean.Message;
import com.yangxuhao.info.service.MessageService;
import com.yangxuhao.info.until.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yangxuhao
 * @date 2019-01-31 12:05.
 */
@Slf4j
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("dev")
    @ResponseBody
    public List<Message> dev(@RequestParam("contact_name") String contactName,
                             @RequestParam("contact_email") String contactEmail,
                             @RequestParam("contact_message") String contactMessage,
                             HttpServletRequest request){
        String realIp = IpUtil.getRealIP(request);
        log.info("realIp = {}, name = {}, email = {}, message = {}", realIp, contactName, contactEmail, contactMessage);
        Message message = Message.builder()
                .contactName(contactName)
                .contactEmail(contactEmail)
                .contactMessage(contactMessage)
                .build();
        if(messageService.saveToFile(message) == 0){
            return null;
        }
        return messageService.readFromFile();
    }
}
