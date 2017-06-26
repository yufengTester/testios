package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Context {
    private AppiumDriver driver;
    private Utils utils;

    public Context(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public String getContext() {
        return this.driver.getContext();
    }

    public JSONArray getContexts() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        JSONArray contexts = (JSONArray)this.utils.request("GET", "session/:sessionId/contexts", jsonObject);
        return contexts;
    }

    public void setContext(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.driver.setContext(jsonObject.getString("name"));
        this.utils.request("POST", "session/:sessionId/context", jsonObject);
    }
}
