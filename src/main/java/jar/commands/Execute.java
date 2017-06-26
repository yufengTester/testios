package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Execute {
    private AppiumDriver driver;
    private Utils utils;

    public Execute(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public JSONObject execute(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        JSONObject result = (JSONObject)this.utils.request("POST", "session/:sessionId/execute", jsonObject);
        return result;
    }
}
