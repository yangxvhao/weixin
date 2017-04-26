package business;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pipeline.SingleFilePipeline;
import until.UserAgentEnum;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.compile;
import static us.codecraft.webmagic.selector.Selectors.xpath;

/**
 * Created by yangxvhao on 17-4-25.
 */
public class ZipCodePageProcessor implements PageProcessor {

    private Logger logger= LoggerFactory.getLogger(ZipCodePageProcessor.class);

    private Site site = Site.me()
//            .setCharset("gb2312")
            .setSleepTime(100)
            .setUserAgent(UserAgentEnum.USER_AGENT_ENUM.getValue())
            .setRetryTimes(3)
            .setSleepTime(200);

    @Override
    public void process(Page page) {
        if (page.getUrl().toString().equals("http://www.ip138.com/post/")) {
            logger.info("processCountry");
            processCountry(page);
        } else if (page.getUrl().regex("http://www\\.ip138\\.com/\\d{3,6}[/]?$").toString() != null) {
            logger.info("processDistrict");
            processDistrict(page);
        } else {
            logger.info("processProvince");
            processProvince(page);
        }

    }

    private void processCountry(Page page) {
        List<Selectable> provinces = page.getHtml().xpath("//*[@id=\"newAlexa\"]/table/tbody/tr/td").nodes();
        for (Selectable province : provinces) {
            String link = province.xpath("//a/@href").toString();
            String title = province.xpath("//a/text()").toString();
            Request request = new Request(link).setPriority(0).putExtra("province", title);
            page.addTargetRequest(request);
        }
    }

    private void processProvince(Page page) {
        //这里仅靠xpath没法精准定位，所以使用正则作为筛选，不符合正则的会被过滤掉
        List<String> districts = page.getHtml().xpath("//body/table/tbody/tr[@bgcolor=\"#ffffff\"]").all();
        Pattern pattern = compile("<td>([^<>]+)</td>.*?href=\"(.*?)\"", DOTALL);
        for (String district : districts) {
            Matcher matcher = pattern.matcher(district);
            while (matcher.find()) {
                String title = matcher.group(1);
                String link = matcher.group(2);
                Request request = new Request(link).setPriority(1).putExtra("province", page.getRequest().getExtra("province")).putExtra("district", title);
                page.addTargetRequest(request);
            }
        }
    }

    private void processDistrict(Page page) {
        String province = page.getRequest().getExtra("province").toString();
        String district = page.getRequest().getExtra("district").toString();
        List<String> codes=page.getHtml().xpath("//body/table/tbody/tr[@bgcolor=\"#ffffff\"]").all();
        String zipCode = "";
        String areaCOde ="";
        for (String code:codes) {
            zipCode = xpath("//a[1]/text()").select(code);
            areaCOde = xpath("//a[2]/text()").select(code);
        }
        page.putField("result", StringUtils.join(new String[]{province, district, zipCode,areaCOde,page.getUrl().toString()}, "\t"));
        List<String> links = page.getHtml().links().regex("http://www\\.ip138\\.com/\\d{3,6}[/]?$").all();
        for (String link : links) {
            page.addTargetRequest(new Request(link).setPriority(2).putExtra("province", province).putExtra("district", district));
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ZipCodePageProcessor())
                .addUrl("http://www.ip138.com/post/")
                .addPipeline(new SingleFilePipeline("/home/yangxvhao/webmgic/ip138.txt"))
//                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
    }
}
