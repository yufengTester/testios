package jar.commands;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jar.common.AppiumDriver;
import jar.common.Utils;

import java.util.ArrayList;
import java.util.Iterator;


public class Element {
    private AppiumDriver driver;
    private Utils utils;
    private Boolean globalTap;

    public Element(AppiumDriver driver) {
        this.driver = driver;
        this.globalTap = Boolean.valueOf(false);
        this.utils = new Utils(driver);
    }

    public void sendKeys(String keys) throws Exception {
        JSONObject jsonObject = new JSONObject();
        ArrayList values = new ArrayList();
        values.add(keys);
        jsonObject.put("value", values);
        this.setValue(jsonObject);
    }

    public void setValue(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        this.utils.request("POST", "session/:sessionId/element/:elementId/value", jsonObject);
    }

    public void click() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        if(!this.globalTap.booleanValue()) {
            jsonObject.put("elementId", this.driver.getElementId());
            this.utils.request("POST", "session/:sessionId/element/:elementId/click", jsonObject);
        } else {
            this.utils.request("POST", "session/:sessionId/click", jsonObject);
            this.globalTap = Boolean.valueOf(false);
        }

    }

    public boolean hasElement(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.driver.getSessionId());
        JSONObject response = (JSONObject)this.utils.request("POST", "session/:sessionId/element", jsonObject);
        JSONObject element = (JSONObject)response.get("value");
        Object elementId = element.get("ELEMENT");
        return elementId != null;
    }

    public String getText() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        String text = (String)this.utils.request("GET", "session/:sessionId/element/:elementId/text", jsonObject);
        return text;
    }


    public void clearText() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        this.utils.request("POST", "session/:sessionId/element/:elementId/clear", jsonObject);
    }

//    public void clearText1() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("elementName", this.driver.getElementName());
//        this.utils.request("POST", "session/:sessionId/element/:elementName/clear", jsonObject);
//    }
//    public void clearTextByXpath() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("elementName", this.driver.getElementName());
//        this.utils.request("POST", "session/:sessionId/element/:elementName/clear", jsonObject);
//    }
    public void back() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        this.utils.request("POST", "session/:sessionId/back", jsonObject);
    }

    public Object getProperty(String name) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        jsonObject.put("name", name);
        Object response = this.utils.request("GET", "session/:sessionId/element/:elementId/property/:name", jsonObject);
        return response;
    }

    public Object getRect() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        Object response = this.utils.request("GET", "session/:sessionId/element/:elementId/rect", jsonObject);
        return response;
    }

    public String getComputedCss(String propertyName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        jsonObject.put("propertyName", propertyName);
        String computedCss = (String)this.utils.request("GET", "session/:sessionId/element/:elementId/css/:propertyName", jsonObject);
        return computedCss;
    }

    public boolean isDisplayed() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        jsonObject.put("elementId", this.driver.getElementId());
        boolean displayed = ((Boolean)this.utils.request("GET", "session/:sessionId/element/:elementId/displayed", jsonObject)).booleanValue();
        return displayed;
    }

    public void touch(String action, JSONObject args) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.driver.getSessionId());
        JSONArray array = new JSONArray();
        JSONObject actionObject = new JSONObject();
        actionObject.put("element", this.driver.getElementId());
        actionObject.put("type", action);
        Iterator var6 = args.keySet().iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            String value = args.getString(key);
            actionObject.put(key, value);
        }

        array.add(actionObject);
        jsonObject.put("actions", array);
        this.utils.request("POST", "session/:sessionId/actions", jsonObject);
    }
}

