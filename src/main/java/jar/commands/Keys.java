package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Keys {
    private AppiumDriver driver;
    private Utils utils;

    public Keys(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public void keys(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/keys", jsonObject);
    }
}
