package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */
import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Source {
    private AppiumDriver driver;
    private Utils utils;

    public Source(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public String getSource() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        return this.utils.request("GET", "session/:sessionId/source", jsonObject).toString();
    }
}

