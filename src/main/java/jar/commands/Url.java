package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Url {
    private AppiumDriver driver;
    private Utils utils;

    public Url(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public String url() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        String url = (String)this.utils.request("GET", "session/:sessionId/url", jsonObject);
        return url;
    }

    public void getUrl(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/url", jsonObject);
    }

    public void forward(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/url", jsonObject);
    }

    public void back(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/url", jsonObject);
    }

    public void refresh(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/url", jsonObject);
    }
}
