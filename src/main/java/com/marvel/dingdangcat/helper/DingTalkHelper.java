package com.marvel.dingdangcat.helper;

import com.google.gson.Gson;
import com.marvel.dingdangcat.domain.dingtalk.DingTalkSendRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Marvel on 2019/10/10.
 */
public class DingTalkHelper {

    private static final Logger logger = LoggerFactory.getLogger(DingTalkHelper.class);
    private static final Gson gson = new Gson();

    private static String getText(String message, Boolean isAtAll) {
        return getText(message, null, isAtAll);
    }

    private static String getText(String message, List<String> atMobiles, Boolean isAtAll) {
        DingTalkSendRequest dingTalkSendRequest = new DingTalkSendRequest();

        DingTalkSendRequest.DingTalkText dingTalkText = dingTalkSendRequest.new DingTalkText();
        dingTalkText.setContent(message);

        DingTalkSendRequest.DingTalkAt dingTalkAt = dingTalkSendRequest.new DingTalkAt();
        dingTalkAt.setAtMobiles(atMobiles);
        dingTalkAt.setIsAtAll(isAtAll);

        dingTalkSendRequest.setMsgtype("text");
        dingTalkSendRequest.setText(dingTalkText);
        dingTalkSendRequest.setAt(dingTalkAt);
        return gson.toJson(dingTalkSendRequest);
    }

    private static void post(String url, String message) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        // 消息实体
        StringEntity entity = new StringEntity(message, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        logger.info("url: " + url);
        logger.info("message: " + message);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            logger.info("statusCode: " + statusCode);
            HttpEntity responseEntity = httpResponse.getEntity();
            logger.info("responseEntity: " + EntityUtils.toString(responseEntity));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpPost.releaseConnection();
        }
    }

    public static void sendText(String url, String message, Boolean isAtAll) {
        post(url, getText(message, isAtAll));
    }

    public static void main(String[] args) {
        String url = "";
        String message = "嘿";
        sendText(url, message, false);
    }
}
