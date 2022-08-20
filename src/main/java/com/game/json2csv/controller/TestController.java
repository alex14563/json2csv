package com.game.json2csv.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * @author zhangjqx 20211110
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testApp(){
        return "hello";
    }

    /**
     * 原样输出
     * @param response
     */
    @RequestMapping(value = "/csv", method = RequestMethod.GET)
    @ResponseBody
    public void exportCsv(HttpServletResponse response){
        try {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=data.csv");
            OutputStream os = response.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "GBK");

            JSONArray jsonArray = this.getJSON();
            Set<String> keySets = new LinkedHashSet<String>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject o = (JSONObject) jsonArray.get(i);
                keySets.addAll(o.keySet());
            }
            List<String> keys = new ArrayList<>(keySets);
            String[] strings = keys.toArray(new String[keys.size()]);
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(strings);
            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);

            Iterator<Object> it = jsonArray.iterator();
            while (it.hasNext()){
                JSONObject next = (JSONObject) it.next();
                StringBuilder str = new StringBuilder();
                for(String key : keys) {
                    csvPrinter.print(next.getStr(key));
                }
                csvPrinter.printRecord();
            }
            csvPrinter.flush();
            csvPrinter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public JSONArray getJSON(){
        String test = "13456";
        String test2 = "tag1";
        String test4 = "1";
        String test3 = "1";
        String t082002 = "08200201";
//        String jsonString = "[{\"name\":\"张三\",\"code\":\"123\"},{\"name\":\"李四\",\"code\":\"123\"}]";
//        String jsonString = "[{\"name\":\"张三\",\"code\":\"123\"},{\"name\":\"李四\",\"code\":\"123\",\"age\":\"100\"}]";
        String jsonString = "[{\"__name\":\"张三\",\"__code\":\"123\",\"__json\":\"[{\\\"__language\\\":\\\"zh\\\",\\\"__event_name\\\":\\\"applaunch\\\",\\\"__event_time\\\":1622722556,\\\"__fid\\\":\\\"1400426014731014144\\\",\\\"__oaid\\\":\\\"\\\",\\\"__useragent\\\":\\\"Mozilla/5.0 (Linux; Android 9; SM-N960U Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.210 Mobile Safari/537.36\\\",\\\"sign\\\":\\\"c720e3bffd0b972035e9777b5c3a9b53\\\",\\\"__lifetime_session_id\\\":\\\"2\\\",\\\"__appid\\\":\\\"v6jk3jdgf4zavy26fpkglwje\\\",\\\"__network_type\\\":\\\"wifi\\\",\\\"__gaid\\\":\\\"\\\",\\\"__reg\\\":\\\"cn\\\",\\\"__current_version\\\":\\\"2.0\\\",\\\"__sdk_send_time\\\":1622722576,\\\"__first_start_time\\\":\\\"1622722555\\\",\\\"__zone\\\":\\\"GMT+8\\\",\\\"__session_id\\\":\\\"2\\\",\\\"__network_name\\\":\\\"\\\",\\\"__device_vender\\\":\\\"samsung\\\",\\\"__environment\\\":\\\"0\\\",\\\"__dpi_h\\\":\\\"2094\\\",\\\"__device_name\\\":\\\"SM-N960U\\\",\\\"__pkg_name\\\":\\\"com.wuyr.catchpiggy\\\",\\\"__none_id\\\":\\\"9d201321-39dc-4e83-98d1-208f6c4bc3ef\\\",\\\"props\\\":{\\\"launch_count\\\":1,\\\"continuous_days\\\":1,\\\"day_login_count\\\":1,\\\"cur_life\\\":5},\\\"__platform\\\":\\\"1\\\",\\\"__activite_days\\\":\\\"1\\\",\\\"__device_type\\\":\\\"phone\\\",\\\"__dpi_w\\\":\\\"1080\\\",\\\"__did\\\":\\\"bbb4ae34-4904-4819-90a7-0a09f1941220\\\",\\\"__os_version\\\":\\\"9\\\",\\\"__third_party_id\\\":\\\"\\\",\\\"__bid\\\":\\\"\\\"}]\"},{\"__name\":\"李四\",\"__code\":\"123\",\"__age\":\"100\"}]";
//        String jsonString = "[{\"__language\":\"zh\",\"__event_name\":\"applaunch\",\"__event_time\":1622722556,\"__fid\":\"1400426014731014144\",\"__oaid\":\"\",\"__useragent\":\"Mozilla/5.0 (Linux; Android 9; SM-N960U Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.210 Mobile Safari/537.36\",\"sign\":\"c720e3bffd0b972035e9777b5c3a9b53\",\"__lifetime_session_id\":\"2\",\"__appid\":\"v6jk3jdgf4zavy26fpkglwje\",\"__network_type\":\"wifi\",\"__gaid\":\"\",\"__reg\":\"cn\",\"__current_version\":\"2.0\",\"__sdk_send_time\":1622722576,\"__first_start_time\":\"1622722555\",\"__zone\":\"GMT+8\",\"__session_id\":\"2\",\"__network_name\":\"\",\"__device_vender\":\"samsung\",\"__environment\":\"0\",\"__dpi_h\":\"2094\",\"__device_name\":\"SM-N960U\",\"__pkg_name\":\"com.wuyr.catchpiggy\",\"__none_id\":\"9d201321-39dc-4e83-98d1-208f6c4bc3ef\",\"props\":{\"launch_count\":1,\"continuous_days\":1,\"day_login_count\":1,\"cur_life\":5},\"__platform\":\"1\",\"__activite_days\":\"1\",\"__device_type\":\"phone\",\"__dpi_w\":\"1080\",\"__did\":\"bbb4ae34-4904-4819-90a7-0a09f1941220\",\"__os_version\":\"9\",\"__third_party_id\":\"\",\"__bid\":\"\"}]";
        JSONArray jsonArray = new JSONArray(jsonString);

        return jsonArray;
    }
}
