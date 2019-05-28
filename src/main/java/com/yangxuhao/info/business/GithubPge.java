//package com.yangxuhao.info.business;
//
///**
// * Created by yangxvhao on 17-4-25.
// */
//import com.yangxuhao.info.until.UserAgentEnum;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.processor.PageProcessor;
//
//public class GithubPge implements PageProcessor {
//
//    private Site site = Site.me()
//            .setRetryTimes(3)
//            .setSleepTime(100)
//            .setUserAgent(UserAgentEnum.USER_AGENT_ENUM.getValue());
//
//    @Override
//    public void process(Page page) {
////        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
////        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
////        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
////        if (page.getResultItems().get("name")==null){
//            //skip this page
////            page.setSkip(true);
////        }
////        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//        page.getHtml();
//    }
//
//    @Override
//    public Site getSite() {
//        return site;
//    }
//
////    public static void main(String[] args) {
////
////        String url="https://mobile.qzone.qq.com/profile?sid=&hostuin=1152042991&no_topbar=1&stat=#mine?res_uin=1152042991&ticket=";
////
////        Spider.create(new GithubPge()).addUrl(url).thread(5).run();
////    }
//}