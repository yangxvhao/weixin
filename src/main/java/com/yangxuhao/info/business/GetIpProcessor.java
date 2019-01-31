package com.yangxuhao.info.business;


import com.yangxuhao.info.pipeline.RedisPipeline;
import com.yangxuhao.info.until.UserAgentEnum;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.logging.Logger;

/**
 * Created by yangxvhao on 17-4-27.
 */
public class GetIpProcessor implements PageProcessor {

    private Logger logger= Logger.getLogger(String.valueOf(GetIpProcessor.class));

    private Site site=Site.me()
            .setUserAgent(UserAgentEnum.USER_AGENT_ENUM.getValue())
            .setRetryTimes(3)
            .setSleepTime(30);

    @Override
    public void process(Page page) {
        String ip=page.getHtml().regex("您查询的IP:(.*?)<").toString();
        String result=page.getHtml().regex("本站数据：(.*?)<").toString();
        logger.info("requestUrl"+page.getUrl());
        logger.info("本次查询结果为："+ip+result);
        page.putField(ip,result);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GetIpProcessor())
                .thread(5)
                .addUrl("http://www.ip138.com/ips1388.asp?ip="+args[0]+"&action=2")
                .addPipeline(new RedisPipeline())
                .run();
    }
}
