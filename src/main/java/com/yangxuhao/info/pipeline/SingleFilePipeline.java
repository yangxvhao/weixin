//package com.yangxuhao.info.pipeline;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import us.codecraft.webmagic.ResultItems;
//import us.codecraft.webmagic.Task;
//import us.codecraft.webmagic.pipeline.Pipeline;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
///**
// * Created by yangxvhao on 17-4-26.
// */
//public class SingleFilePipeline implements Pipeline {
//
//    private final String path;
//
//    public SingleFilePipeline(String path){
//        this.path=path;
//    }
//
//    Logger logger= LoggerFactory.getLogger(SingleFilePipeline.class);
//    @Override
//    public void process(ResultItems resultItems, Task task) {
//        try {
//            FileWriter fw=new FileWriter(path,true);
//            fw.write(resultItems.get("result").toString()+"\n");
//            logger.info("保存到文件....."+resultItems.get("result").toString());
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
