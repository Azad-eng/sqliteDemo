package com.azad.messageNotifer;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DingTalkNotify {
//    private static final String WEBHOOK_URL = "https://oapi.dingtalk.com/robot/send?access_token=f013acb2c4478025cd07280680bf67df7c666eeeeafee84699da54c30c4709fd"; // 在钉钉后台获取Webhook地址，并替换XXX为access_token

//    public static void main(String[] args) throws Exception {
////        String webhook = "https://oapi.dingtalk.com/robot/send?access_token=f013acb2c4478025cd07280680bf67df7c666eeeeafee84699da54c30c4709fd";
////        String mobile = "13617122902";
////        String message = "New commit pushed to the repository.";
////        String atMobiles = URLEncoder.encode(mobile, "UTF-8");
////        String content = URLEncoder.encode(message, "UTF-8");
////        String url = webhook + "&text=" + content + "&atMobiles=" + atMobiles;
////        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
////        conn.setRequestMethod("POST");
////        conn.setRequestProperty("Content-Type", "application/json");
////        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////        String inputLine;
////        StringBuffer response = new StringBuffer();
////        while ((inputLine = in.readLine()) != null) {
////            response.append(inputLine);
////        }
////        in.close();
////        System.out.println(response.toString());
//
//        String message = "Hello, DingTalk!"; // 要发送的消息内容
//
//        try {
//            URL url = new URL(WEBHOOK_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//
//            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//            out.write("{\"msgtype\":\"text\",\"text\":{\"content\":\"" + message + "\"}}"); // 消息内容
//            out.flush();
//            out.close();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line); // 打印返回结果
//            }
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) throws Exception {

        Long timestamp = System.currentTimeMillis();
        String secret = "SEC3555d163261161ab73c788853e3ba6bc3e2e7fe32e5fa3df65ac4753c8d8eacd";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        System.out.println(sign);
        String webhook = "https://oapi.dingtalk.com/robot/send?access_token=f013acb2c4478025cd07280680bf67df7c666eeeeafee84699da54c30c4709fd&timestamp="
                + timestamp + "&sign=" + sign;
        System.out.println("webhook: " + webhook);

        String message = "IT'S A TEST. NEW PUSH IN 6601 !!!"; // 要发送的消息内容

        try {
            URL url = new URL(webhook);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write("{\"msgtype\":\"text\",\"text\":{\"content\":\"" + message + "\"}}"); // 消息内容
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line); // 打印返回结果
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
