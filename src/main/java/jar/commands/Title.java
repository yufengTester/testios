package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */
import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Title {
    private AppiumDriver driver;
    private Utils utils;

    public Title(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public String title() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        String title = (String)this.utils.request("GET", "session/:sessionId/title", jsonObject);
        return title;
    }
}

