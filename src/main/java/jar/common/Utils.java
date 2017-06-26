package jar.common;

/**
 * Created by test_ge on 2017/6/15.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jar.model.JsonWireStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Utils {
    private final Log log = LogFactory.getLog(this.getClass());
    private HttpGet httpget = null;
    private HttpPost httppost = null;
    private HttpDelete httpdelete = null;
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private CloseableHttpResponse response = null;
    private HttpEntity entity = null;
    private StringEntity stringEntity = null;
    private JSONObject jsonResponse = null;
    private String stringResponse = "";
    private AppiumDriver driver;

    public Utils(AppiumDriver driver) {
        this.driver = driver;
    }

    private void printResponse(String stringResponse) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(stringResponse.length() > 400) {
            System.out.println(df.format(new Date()) + " Response:" + stringResponse.substring(0, 400) + "...more response is ignored..");
        } else {
            System.out.println(df.format(new Date()) + " Response:" + stringResponse);
        }

    }

    private void printRequest(String stringRequest) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()) + " Request:" + stringRequest);
    }



    public Object getRequest(String method, JSONObject jsonBody) throws Exception {
        String key;
        String value;
        for(Iterator e = jsonBody.keySet().iterator(); e.hasNext(); method = method.replace(":" + key, value)) {
            key = (String)e.next();
            value = jsonBody.get(key).toString();
        }

        try {
            String e1 = "http://${host}:${port}/wd/hub/".replace("${host}", this.driver.getHost()).replace("${port}", this.driver.getPort()) + method;
            this.printRequest(e1);
            this.httpget = new HttpGet(e1);
            this.response = this.httpclient.execute(this.httpget);
            this.entity = this.response.getEntity();
            if(this.entity != null) {
                this.stringResponse = EntityUtils.toString(this.entity);
                this.printResponse(this.stringResponse);
                this.jsonResponse = JSON.parseObject(this.stringResponse);
                this.handleStatus(this.jsonResponse.getInteger("status").intValue());
                return this.jsonResponse.get("value");
            }
        } catch (ClientProtocolException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return null;
    }

    public Object postRequest(String method, JSONObject jsonBody) throws Exception {
        JSONObject tempObj = new JSONObject();
        Iterator e = jsonBody.keySet().iterator();

        while(e.hasNext()) {
            String key = (String)e.next();
            String value = jsonBody.get(key).toString();
            if(method.contains(":" + key)) {
                method = method.replace(":" + key, value);
            } else {
                tempObj.put(key, jsonBody.get(key));
            }
        }

        try {
            String e1 = "http://${host}:${port}/wd/hub/".replace("${host}", this.driver.getHost()).replace("${port}", this.driver.getPort()) + method;
            this.httppost = new HttpPost(e1);
            if(jsonBody != null) {
                this.stringEntity = new StringEntity(tempObj.toString(), "utf-8");
                this.stringEntity.setContentEncoding("utf-8");
                this.stringEntity.setContentType("application/json");
                this.httppost.setEntity(this.stringEntity);
            }

            this.printRequest(e1 + ":" + tempObj.toString());
            this.response = this.httpclient.execute(this.httppost);
            this.entity = this.response.getEntity();
            if(this.entity != null) {
                this.stringResponse = EntityUtils.toString(this.entity);
                this.printResponse(this.stringResponse);
                this.jsonResponse = JSON.parseObject(this.stringResponse);
                this.handleStatus(this.jsonResponse.getInteger("status").intValue());
                return this.jsonResponse;
            }
        } catch (ClientProtocolException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public Object deleteRequest(String method, JSONObject jsonBody) throws Exception {
        String stringResponse;
        String value;
        for(Iterator url = jsonBody.keySet().iterator(); url.hasNext(); method = method.replace(":" + stringResponse, value)) {
            stringResponse = (String)url.next();
            value = jsonBody.get(stringResponse).toString();
        }

        String url1 = "http://${host}:${port}/wd/hub/".replace("${host}", this.driver.getHost()).replace("${port}", this.driver.getPort()) + method;
        this.httpdelete = new HttpDelete(url1);
        this.response = this.httpclient.execute(this.httpdelete);
        this.entity = this.response.getEntity();
        System.out.println(this.response.getStatusLine().getStatusCode());
        if(this.entity != null) {
            stringResponse = EntityUtils.toString(this.entity);
            this.printResponse(stringResponse);
            this.jsonResponse = JSON.parseObject(stringResponse);
            this.handleStatus(this.jsonResponse.getInteger("status").intValue());
            return this.jsonResponse;
        } else {
            return null;
        }
    }

    public Object request(String method, String url, JSONObject jsonObj) throws Exception {
        return "GET".equals(method.toUpperCase())?this.getRequest(url, jsonObj):("POST".equals(method.toUpperCase())?this.postRequest(url, jsonObj):("DELETE".equals(method.toUpperCase())?this.deleteRequest(url, jsonObj):null));
    }

    public void handleStatus(int statusCode) throws Exception {
        JsonWireStatus status = JsonWireStatus.findByStatus(statusCode);
        if(status != JsonWireStatus.Success && status != JsonWireStatus.Default) {
            throw new Exception(status.message());
        }
    }

    public String getStatus(String method) throws Exception {
        try {
            String e = "http://${host}:${port}/wd/hub/".replace("${host}", this.driver.getHost()).replace("${port}", this.driver.getPort()) + method;
            this.httpget = new HttpGet(e);
            this.response = this.httpclient.execute(this.httpget);
            this.entity = this.response.getEntity();
            return String.valueOf(this.response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return "get server status error";
    }
}

