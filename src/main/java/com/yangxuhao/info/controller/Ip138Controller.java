//package com.yangxuhao.info.controller;
//
//import com.yangxuhao.info.business.GetIpProcessor;
//import com.yangxuhao.info.until.RedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * @author yangxuhao
// * @date 2019-01-31 12:02.
// */
//@Slf4j
////@Controller
//public class Ip138Controller {
//    @RequestMapping(value = "/query/{ip}", method = {RequestMethod.GET})
//    @ResponseBody
//    public String getIp(@PathVariable("ip") String message){
//        log.info("-------"+message);
//        GetIpProcessor.main(new String[] {message});
//        String ip= RedisUtil.get(message);
//        return "ip所在地："+ip;
//    }
//
//    @RequestMapping(value = "/q", method = {RequestMethod.POST})
//    public String getIp(@ModelAttribute("message") String message, Model model){
//        log.info("-------"+message);
//        String requestIp=null;
//        try {
//            requestIp= InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        log.info("requestIp:"+requestIp);
//        String ip= RedisUtil.get(message);
//        if(StringUtils.isEmpty(ip)) {
//            GetIpProcessor.main(new String[]{message});
//            ip= RedisUtil.get(message);
//        }
//        model.addAttribute("result",ip);
//        model.addAttribute("request",message);
//        return "result";
//    }
//}
