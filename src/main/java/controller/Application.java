package controller;

import business.GetIpProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import until.RedisUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Created by yangxvhao on 17-4-27.
 */
@SpringBootApplication
@Controller
//@RestController
//@RequestMapping("/")
public class Application{
    Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @RequestMapping("/health")
    @ResponseBody
    public String health(){
        logger.info("keep-alive");
        return "keep-alive";

    }

    @RequestMapping(value = "/query/{ip}", method = {RequestMethod.GET})
    @ResponseBody
    public String getIp(@PathVariable("ip") String message){
        logger.info("-------"+message);
        GetIpProcessor.main(new String[] {message});
        String ip= RedisUtil.get(message);
        return "ip所在地："+ip;
    }

    @RequestMapping(value = "/q", method = {RequestMethod.POST})
    public String getIp(@ModelAttribute("message") String message, Model model){
        logger.info("-------"+message);
        String requestIp=null;
        try {
            requestIp=InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//        String requestIp=request.getHeaders().toString();
        logger.info("requestIp:"+requestIp);
        String ip= RedisUtil.get(message);
        if(StringUtils.isEmpty(ip)) {
            GetIpProcessor.main(new String[]{message});
            ip= RedisUtil.get(message);
        }
        model.addAttribute("result",ip);
        model.addAttribute("request",message);
        return "result";
    }
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name","yang");
        return "submit";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
