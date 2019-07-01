package com.kfit.mybatis.test;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketTest {

    private String deviceId;

    private Session session;

    public WebSocketTest () {
    }

    public WebSocketTest (String deviceId) {
        this.deviceId = deviceId;
    }

    /*protected*/public boolean start() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        //ws://localhost:80/websocket
//        String uri = "ws://192.168.12.161/orderapp/recommend?role=1&group=recommend&dldm="+ deviceId;
        String uri = "ws://localhost:80/websocket/"+ deviceId;
        System.out.println("Connecting to " + uri);
        try {
            session = container.connectToServer(WebSocketTest.class, URI.create(uri));
            System.out.println("count: " + deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
      * 计算两个时间点之间的天数
      */
    public static long getDateDays(String date1,String date2){
        long days = 0;
        try {
            DateTimeFormatter df2 =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate1 = LocalDate.parse(date1);
            LocalDate localDate2 = LocalDate.parse(date2);
            // LocalDate now = LocalDate.now();
            long fa = localDate2.toEpochDay();
            days = localDate2.toEpochDay() - localDate1.toEpochDay();
            System.out.println(days);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println(getDateDays("1970-01-02","2019-02-28"));
//        for (int i = 1; i< 5; i++) {
//            WebSocketTest wSocketTest = new WebSocketTest(String.valueOf(i));
//            if (!wSocketTest.start()) {
//                System.out.println("测试结束！");
//                break;
//            }
//        }
    }
}