package cn.bugstack.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author 小傅哥，微信：fustack
 * @description 单元测试
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ApiTest {

    @Test
    public void base64(){
        String cronExpression = new String(Base64.getDecoder().decode("MC81MCAqICogKiAqID8="), StandardCharsets.UTF_8);
        System.out.println(cronExpression);
    }

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88888255114512/topics?scope=unanswered_questions&count=20");

        //如果失败了需要随时更新cookie
        get.addHeader("cookie", "abtest_env=product; zsxqsessionid=7a88d50b5eb3e5d9d753c565cd92ce18; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415485512584528%22%2C%22first_id%22%3A%2218d30ea766b582-037f55315fba542-26001951-2073600-18d30ea766cd93%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkMzBlYTc2NmI1ODItMDM3ZjU1MzE1ZmJhNTQyLTI2MDAxOTUxLTIwNzM2MDAtMThkMzBlYTc2NmNkOTMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU0ODU1MTI1ODQ1MjgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415485512584528%22%7D%2C%22%24device_id%22%3A%2218d30ea766b582-037f55315fba542-26001951-2073600-18d30ea766cd93%22%7D; zsxq_access_token=8673600C-2539-A6ED-61E2-726C3F810E9F_4675DF4ECF5D1908");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //"https://api.zsxq.com/v2/topics/{topic_id}/answer"
        //根据get获取未回答列表，然后更新topic_id进行回答
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/5122581121282884/answer");
        post.addHeader("cookie", "abtest_env=product; zsxqsessionid=7a88d50b5eb3e5d9d753c565cd92ce18; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415485512584528%22%2C%22first_id%22%3A%2218d30ea766b582-037f55315fba542-26001951-2073600-18d30ea766cd93%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkMzBlYTc2NmI1ODItMDM3ZjU1MzE1ZmJhNTQyLTI2MDAxOTUxLTIwNzM2MDAtMThkMzBlYTc2NmNkOTMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU0ODU1MTI1ODQ1MjgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415485512584528%22%7D%2C%22%24device_id%22%3A%2218d30ea766b582-037f55315fba542-26001951-2073600-18d30ea766cd93%22%7D; zsxq_access_token=8673600C-2539-A6ED-61E2-726C3F810E9F_4675DF4ECF5D1908");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": true\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
