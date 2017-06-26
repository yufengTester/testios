package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */
import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Session {
    private AppiumDriver driver;
    private Utils utils;

    public Session(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public void createSession(JSONObject jsonObj) throws Exception {
        if(jsonObj.get("host") != null && jsonObj.get("port") != null) {
            String response = (String)jsonObj.get("host");
            int sessionId = ((Integer)jsonObj.get("port")).intValue();
            this.driver.setRemote(response, sessionId);
        }

        if(System.getenv("Appium_UDID") != null) {
            jsonObj.put("udid", System.getenv("Appium_UDID"));
        }

        if(System.getenv("Appium_APP_NAME") != null) {
            jsonObj.put("package", System.getenv("Appium_APP_NAME"));
        }
        if(System.getenv("Appium_bundle_Id") != null) {
            jsonObj.put("bundleId", System.getenv("Appium_bundle_Id"));
        }

        System.out.print(jsonObj.toString());
        JSONObject response1 = (JSONObject)this.utils.request("POST", "session", jsonObj);
        String sessionId1 = (String)response1.get("sessionId");
        this.driver.setSessionId(sessionId1);
        this.driver.setCapabilities(response1);
    }

    public void delSession() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("DELETE", "session/:sessionId", jsonObject);
    }

    public JSONObject sessionAvailable() throws Exception {
        return this.driver.getCapabilities();
    }
}

