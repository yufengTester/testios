package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

public class Window {
    private AppiumDriver driver;
    private Utils utils;

    public Window(AppiumDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public void getWindow() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        String windowHandle = (String)this.utils.request("GET", "session/:sessionId/window_handle", jsonObject);
        this.driver.setWindowHandle(windowHandle);
    }

    public void getWindows() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("GET", "session/:sessionId/window_handles", jsonObject);
    }

    public JSONObject getWindowSize() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("windowHandle", "current");
        return (JSONObject)this.utils.request("GET", "session/:sessionId/window/:windowHandle/size", jsonObject);
    }

    public void setWindowSize(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("windowHandle", "current");
        this.utils.request("POST", "session/:sessionId/window/:windowHandle/size", jsonObject);
    }

    public void maximize() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("windowHandle", "current");
        this.utils.request("POST", "session/:sessionId/window/:windowHandle/maximize", jsonObject);
    }

    public String setWindow(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        String name = (String)this.utils.request("POST", "/window", jsonObject);
        return name;
    }

    public void deleteWindow() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("DELETE", "/window", jsonObject);
    }

    public void setFrame(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        String name = (String)this.utils.request("POST", "session/:sessionId/frame", jsonObject);
    }
}
