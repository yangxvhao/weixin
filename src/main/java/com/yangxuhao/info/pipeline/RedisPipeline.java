//package com.yangxuhao.info.pipeline;
//
//import com.yangxuhao.info.until.RedisUtil;
//import us.codecraft.webmagic.ResultItems;
//import us.codecraft.webmagic.Task;
//import us.codecraft.webmagic.pipeline.Pipeline;
//
//import java.util.Map;
//
///**
// * Created by yangxvhao on 17-4-27.
// */
//public class RedisPipeline implements Pipeline {
//
//    @Override
//    public void process(ResultItems resultItems, Task task) {
//        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
//            RedisUtil.set(entry.getKey(),entry.getValue().toString());
//        }
//    }
//}
