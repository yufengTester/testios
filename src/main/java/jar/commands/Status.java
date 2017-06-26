package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Status {
    private AppiumDriver driver;
    private Utils utils;

    public Status(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public String getStatus() throws Exception {
        new JSONObject();
        return this.utils.getStatus("status");
    }
}

