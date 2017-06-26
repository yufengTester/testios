package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;

public class ScreenShot {
    private AppiumDriver driver;
    private Utils utils;

    public ScreenShot(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public Object takeScreenshot() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        return this.utils.request("GET", "session/:sessionId/screenshot", jsonObject);
    }

    public void saveScreenshot(String filename) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();

        try {
            byte[] e = decoder.decodeBuffer(this.takeScreenshot().toString());

            for(int out = 0; out < e.length; ++out) {
                if(e[out] < 0) {
                    e[out] = (byte)(e[out] + 256);
                }
            }

            FileOutputStream var6 = new FileOutputStream(filename);
            var6.write(e);
            var6.flush();
            var6.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
