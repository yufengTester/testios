package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Alert {
    private AppiumDriver driver;
    private Utils utils;

    public Alert(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public void acceptAlert() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/accept_alert", jsonObject);
    }

    public void dismissAlert() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/dismiss_alert", jsonObject);
    }

    public String alertText() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        String alert = (String)this.utils.request("GET", "session/:sessionId/alert_text", jsonObject);
        return alert;
    }

    public void alertKeys(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/alert_text", jsonObject);
    }
}

