package com.yangxuhao.info.controller;

import com.yangxuhao.info.until.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yangxuhao
 * @date 2019-01-31 11:57.
 */
@Slf4j
@Controller
public class IndexController {
    @RequestMapping("/health")
    @ResponseBody
    public String health(){
        log.info("keep-alive");
        return "keep-alive";

    }

    @RequestMapping("")
    public String index(HttpServletRequest request){
        String requestIp = null;
        try {
            requestIp = IpUtil.getRealIP(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(requestIp);
        return "index";
    }
}
