package jar;

/**
 * Created by test_ge on 2017/6/15.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jar.commands.*;
import jar.common.AppiumDriver;
import jar.common.ElementSelector;
import jar.common.GetElementWay;
import jar.common.Utils;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.Iterator;

public class AppiumClient {
    public AppiumDriver contexts = new AppiumDriver();
    private Alert alert;
    private Context context;
    public Element element;
    private Execute execute;
    private Keys _keys;
    private ScreenShot screenshot;
    private Session session;
    private Source source;
    private Status status;
    private Title title;
    private Url _url;
    private Utils utils;
    private Window window;
    public int waitElementTimeout;
    public int waitElementTimeInterval;

    public AppiumClient() {
        this.alert = new Alert(this.contexts);
        this.context = new Context(this.contexts);
        this.element = new Element(this.contexts);
        this.execute = new Execute(this.contexts);
        this._keys = new Keys(this.contexts);
        this.screenshot = new ScreenShot(this.contexts);
        this.session = new Session(this.contexts);
        this.source = new Source(this.contexts);
        this.status = new Status(this.contexts);
        this.title = new Title(this.contexts);
        this._url = new Url(this.contexts);
        this.utils = new Utils(this.contexts);
        this.window = new Window(this.contexts);
        this.waitElementTimeout = 1000;
        this.waitElementTimeInterval = 200;
    }

    public int getWaitElementTimeout() {
        return this.waitElementTimeout;
    }

    public void setWaitElementTimeout(int waitElementTimeout) {
        this.waitElementTimeout = waitElementTimeout;
    }

    public int getWaitElementTimeInterval() {
        return this.waitElementTimeInterval;
    }

    public void setWaitElementTimeInterval(int waitElementTimeInterval) {
        this.waitElementTimeInterval = waitElementTimeInterval;
    }

    public AppiumClient acceptAlert() throws Exception {
        this.alert.acceptAlert();
        return this;
    }

    public AppiumClient dismissAlert() throws Exception {
        this.alert.dismissAlert();
        return this;
    }

    public String alertText() throws Exception {
        return this.alert.alertText();
    }

    public AppiumClient alertKeys(String keys) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", keys);
        this.alert.alertKeys(jsonObject);
        return this;
    }

    public JSONArray contexts() throws Exception {
        return this.context.getContexts();
    }

    public AppiumClient context(String contextRef) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", contextRef);
        this.context.setContext(jsonObject);
        return this;
    }

    public String currentContext() throws Exception {
        return this.context.getContext();
    }

    private boolean findElement(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.contexts.getSessionId());
        JSONObject response = (JSONObject)this.utils.request("POST", "session/:sessionId/element", jsonObject);
        JSONObject element = (JSONObject)response.get("value");
        Object elementId = element.get("ELEMENT");
        if(elementId != null) {
            this.contexts.setElementId(elementId);
            return true;
        } else {
            return false;
        }
    }

    private JSONArray findElements(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", this.contexts.getSessionId());
        JSONObject response = (JSONObject)this.utils.request("POST", "session/:sessionId/elements", jsonObject);
        JSONArray elements = (JSONArray)response.get("value");
        return elements;
    }

    public Element element(String name, String value) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", name);
        jsonObject.put("using", value);
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementById(String elementId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", elementId);
        jsonObject.put("using", "id");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementByCss(String selector) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", selector);
        jsonObject.put("using", "css");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementByXPath(String xpath) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", xpath);
        jsonObject.put("using", "xpath");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementByName(String name) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", name);
        jsonObject.put("using", "name");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element getElement(GetElementWay wayToFind, String value, int index) throws Exception {
        ElementSelector elementSelector;
//        switch(null.$SwitchMap$macaca$client$common$GetElementWay[wayToFind.ordinal()]) {
        switch(wayToFind.ordinal()) {
            case 1:
                elementSelector = this.elementsById(value);
                break;
            case 2:
                elementSelector = this.elementsByCss(value);
                break;
            case 3:
                elementSelector = this.elementsByName(value);
                break;
            case 4:
                elementSelector = this.elementsByXPath(value);
                break;
            case 5:
                elementSelector = this.elementsByClassName(value);
                break;
            case 6:
                elementSelector = this.elementsByLinkText(value);
                break;
            case 7:
                elementSelector = this.elementsByPartialLinkText(value);
                break;
            case 8:
                elementSelector = this.elementsByTagName(value);
                break;
            default:
                elementSelector = null;
        }

        if(elementSelector != null) {
            elementSelector.getIndex(index);
            return this.element;
        } else {
            System.out.println("can\'t find the element:" + value + "[" + index + "]");
            return null;
        }
    }

    public Element getElement(GetElementWay wayToFind, String value) throws Exception {
//        switch(null.$SwitchMap$macaca$client$common$GetElementWay[wayToFind.ordinal()]) {
        switch(wayToFind.ordinal()) {
            case 1:
                return this.elementById(value);
            case 2:
                return this.elementByCss(value);
            case 3:
                return this.elementByName(value);
            case 4:
                return this.elementByXPath(value);
            case 5:
                return this.elementByClassName(value);
            case 6:
                return this.elementByLinkText(value);
            case 7:
                return this.elementByPartialLinkText(value);
            case 8:
                return this.elementByTagName(value);
            default:
                return null;
        }
    }

    public int countOfElements(GetElementWay wayToFind, String value) throws Exception {
        ElementSelector elementSelector = this.getElementSelector(wayToFind, value);
        return elementSelector != null?elementSelector.size():0;
    }

    private ElementSelector getElementSelector(GetElementWay wayToFind, String value) throws Exception {
        ElementSelector elementSelector;
//        switch(null.$SwitchMap$macaca$client$common$GetElementWay[wayToFind.ordinal()]) {
        switch(wayToFind.ordinal()) {
            case 1:
                elementSelector = this.elementsById(value);
                break;
            case 2:
                elementSelector = this.elementsByCss(value);
                break;
            case 3:
                elementSelector = this.elementsByName(value);
                break;
            case 4:
                elementSelector = this.elementsByXPath(value);
                break;
            case 5:
                elementSelector = this.elementsByClassName(value);
                break;
            case 6:
                elementSelector = this.elementsByLinkText(value);
                break;
            case 7:
                elementSelector = this.elementsByPartialLinkText(value);
                break;
            case 8:
                elementSelector = this.elementsByTagName(value);
                break;
            default:
                elementSelector = null;
        }

        return elementSelector;
    }

    public Element waitForElement(GetElementWay wayToFind, String value, int index) throws Exception {
        int count = 0;
        int timeLeft = this.waitElementTimeout;

        boolean satisfied;
        for(satisfied = false; timeLeft > 0; timeLeft -= this.waitElementTimeInterval) {
            boolean elementExist = false;
            System.out.println(String.format("attempt to search the element for %d times", new Object[]{Integer.valueOf(count++)}));
            elementExist = this.isElementExist(wayToFind, value, index);
            if(elementExist) {
                satisfied = true;
                this.getElement(wayToFind, value, index);
                break;
            }

            this.sleep(this.waitElementTimeInterval);
        }

        if(!satisfied) {
            System.out.println("can\'t find the element:" + value);
            return null;
        } else {
            return this.element;
        }
    }

    public Element waitForElement(GetElementWay wayToFind, String value) throws Exception {
        int count = 0;
        int timeLeft = this.waitElementTimeout;

        boolean satisfied;
        for(satisfied = false; timeLeft > 0; timeLeft -= this.waitElementTimeInterval) {
            boolean elementExist = false;
            System.out.println(String.format("attempt to search the element for %d times", new Object[]{Integer.valueOf(count++)}));
            elementExist = this.isElementExist(wayToFind, value);
            if(elementExist) {
                satisfied = true;
                this.getElement(wayToFind, value);
                break;
            }

            this.sleep(this.waitElementTimeInterval);
        }

        if(!satisfied) {
            System.out.println("can\'t find the element:" + value);
            return null;
        } else {
            return this.element;
        }
    }

    public Element elementByClassName(String className) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", className);
        jsonObject.put("using", "class name");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementByLinkText(String linkText) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", linkText);
        jsonObject.put("using", "link text");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementByTagName(String tagName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", tagName);
        jsonObject.put("using", "tag name");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public Element elementByPartialLinkText(String partialLinkText) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", partialLinkText);
        jsonObject.put("using", "partial link text");
        boolean isExist = this.findElement(jsonObject);
        return isExist?this.element:null;
    }

    public ElementSelector elementsByXPath(String xpath) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", xpath);
        jsonObject.put("using", "xpath");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsByName(String name) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", name);
        jsonObject.put("using", "name");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsById(String elementId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", elementId);
        jsonObject.put("using", "id");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsByClassName(String className) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", className);
        jsonObject.put("using", "class name");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsByCss(String css) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", css);
        jsonObject.put("using", "selector");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsByLinkText(String linkText) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", linkText);
        jsonObject.put("using", "link text");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsByPartialLinkText(String partialLinkText) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", partialLinkText);
        jsonObject.put("using", "partial link text");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public ElementSelector elementsByTagName(String tagName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", tagName);
        jsonObject.put("using", "tag name");
        JSONArray jsonArray = this.findElements(jsonObject);
        return new ElementSelector(this.contexts, this, jsonArray);
    }

    public Element waitForElement(String using, String value, int timeout, int interval) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", value);
        jsonObject.put("using", using);
        int count = 1;
        int timeLeft = timeout;

        boolean satisfied;
        for(satisfied = false; timeLeft > 0; timeLeft -= interval) {
            boolean elementExist = false;
            System.out.println(String.format("attempt to search the element for %d times", new Object[]{Integer.valueOf(count++)}));
            elementExist = this.isElementExist(using, value);
            if(elementExist) {
                satisfied = true;
                this.findElement(jsonObject);
                break;
            }

            this.sleep(interval);
        }

        if(!satisfied) {
            System.out.println("can\'t find the element: " + using + ":" + value);
            return null;
        } else {
            return this.element;
        }
    }

    public Element waitForElement(String using, String value) throws Exception {
        return this.waitForElement(using, value, this.waitElementTimeout, this.waitElementTimeInterval);
    }

    public Element waitForElementById(String elementId) throws Exception {
        return this.waitForElement(GetElementWay.ID, elementId);
    }

    public Element waitForElementByCss(String selector) throws Exception {
        return this.waitForElement(GetElementWay.CSS, selector);
    }

    public Element waitForElementByXPath(String xpath) throws Exception {
        return this.waitForElement(GetElementWay.XPATH, xpath);
    }

    public Element waitForElementByTagName(String tagname) throws Exception {
        return this.waitForElement(GetElementWay.TAG_NAME, tagname);
    }

    public Element waitForElementByName(String name) throws Exception {
        return this.waitForElement(GetElementWay.NAME, name);
    }

    public Element waitForElementByLinkText(String text) throws Exception {
        return this.waitForElement(GetElementWay.LINK_TEXT, text);
    }

    public Element waitForElementByPartialLinkText(String text) throws Exception {
        return this.waitForElement("partial link text", text);
    }

    public boolean isElementExist(String using, String value) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", value);
        jsonObject.put("using", using);

        try {
            this.findElement(jsonObject);
        } catch (Exception var5) {
            return false;
        }

        return this.element.isDisplayed();
    }

    public boolean isElementExist(GetElementWay wayToFind, String value, int index) throws Exception {
        try {
            this.getElement(wayToFind, value, index);
        } catch (Exception var5) {
            return false;
        }

        return this.element.isDisplayed();
    }

    public boolean isElementExist(GetElementWay wayToFind, String value) throws Exception {
        try {
            this.getElement(wayToFind, value);
        } catch (Exception var4) {
            return false;
        }

        return this.element.isDisplayed();
    }

    /** @deprecated */
    @Deprecated
    public AppiumClient sendKeys(String keys) throws Exception {
        JSONObject jsonObject = new JSONObject();
        ArrayList values = new ArrayList();
        values.add(keys);
        jsonObject.put("value", values);
        this.element.setValue(jsonObject);
        return this;
    }

    /** @deprecated */
    @Deprecated
    public AppiumClient click() throws Exception {
        this.element.click();
        return this;
    }

    /** @deprecated */
    @Deprecated
    public AppiumClient clear() throws Exception {
        this.element.clearText();
        return this;
    }

    public AppiumClient back() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.contexts.getSessionId());
        this.utils.request("POST", "session/:sessionId/back", jsonObject);
        return this;
    }

    /** @deprecated */
    @Deprecated
    public Object getProperty(String name) throws Exception {
        return this.element.getProperty(name);
    }

    /** @deprecated */
    @Deprecated
    public Object getRect() throws Exception {
        return this.element.getRect();
    }

    public JSONObject execute(String code) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("script", code);
        jsonObject.put("args", new JSONArray());
        return this.execute.execute(jsonObject);
    }

    public AppiumClient keys(String keys) throws Exception {
        JSONObject jsonObject = new JSONObject();
        ArrayList values = new ArrayList();
        values.add(keys);
        jsonObject.put("value", values);
        this._keys.keys(jsonObject);
        return this;
    }

    public AppiumClient takeScreenshot() throws Exception {
        this.screenshot.takeScreenshot();
        return this;
    }

    public AppiumClient saveScreenshot(String fileName) throws Exception {
        this.screenshot.saveScreenshot(fileName);
        return this;
    }

    public AppiumClient initDriver(JSONObject jsonObject) throws Exception {
        this.session.createSession(jsonObject);
        return this;
    }

    public String sessionId() throws Exception {
        return this.contexts.getSessionId();
    }

    public void quit() throws Exception {
        this.session.delSession();
    }

    public String source() throws Exception {
        return this.source.getSource();
    }

    public String status() throws Exception {
        return this.status.getStatus();
    }

    public AppiumClient sleep(int ms) throws Exception {
        Thread.sleep((long)ms);
        return this;
    }

    public String title() throws Exception {
        return this.title.title();
    }

    public String url() throws Exception {
        return this._url.url();
    }

    public AppiumClient get(String url) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        this._url.getUrl(jsonObject);
        return this;
    }

    public JSONObject getWindowSize() throws Exception {
        return this.window.getWindowSize();
    }

    public AppiumClient setWindowSize(int width, int height) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("width", Integer.valueOf(width));
        jsonObject.put("height", Integer.valueOf(height));
        this.window.setWindowSize(jsonObject);
        return this;
    }

    public AppiumClient maximize() throws Exception {
        this.window.maximize();
        return this;
    }

    /** @deprecated */
    @Deprecated
    public String text() throws Exception {
        return this.element.getText();
    }

    public AppiumClient touch(String action, JSONObject args) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", this.contexts.getSessionId());
        JSONArray array = new JSONArray();
        JSONObject actionObject = new JSONObject();
        actionObject.put("type", action);
        Iterator utils = args.keySet().iterator();

        while(utils.hasNext()) {
            String key = (String)utils.next();
            int value = args.getIntValue(key);
            actionObject.put(key, Integer.valueOf(value));
        }

        array.add(actionObject);
        jsonObject.put("actions", array);
        Utils utils1 = new Utils(this.contexts);
        utils1.request("POST", "session/:sessionId/actions", jsonObject);
        return this;
    }

    public void tap(double x, double y) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", Double.valueOf(x));
        jsonObject.put("y", Double.valueOf(y));
        this.touch("tap", jsonObject);
    }

    public void doubleTap(double x, double y) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", Double.valueOf(x));
        jsonObject.put("y", Double.valueOf(y));
        this.touch("doubleTap", jsonObject);
    }

    public void press(double x, double y, double duration) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", Double.valueOf(x));
        jsonObject.put("y", Double.valueOf(y));
        jsonObject.put("duration", Double.valueOf(duration));
        this.touch("press", jsonObject);
    }

    public void pinch(double x, double y, double scale, double velocity, AppiumClient.GesturePinchType direction, double percent, int steps) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", Double.valueOf(x));
        jsonObject.put("y", Double.valueOf(y));
        jsonObject.put("scale", Double.valueOf(scale));
        jsonObject.put("velocity", Double.valueOf(velocity));
        jsonObject.put("percent", Double.valueOf(percent));
        jsonObject.put("steps", Integer.valueOf(steps));
        if(direction == AppiumClient.GesturePinchType.PINCH_IN) {
            jsonObject.put("direction", "in");
        } else {
            jsonObject.put("direction", "out");
        }

        this.touch("pinch", jsonObject);
    }

    public void rotate(double rotation, double velocity) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rotation", Double.valueOf(rotation));
        jsonObject.put("velocity", Double.valueOf(velocity));
        this.touch("rotate", jsonObject);
    }

    public void drag(double fromX, double fromY, double toX, double toY, double duration) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromX", Double.valueOf(fromX));
        jsonObject.put("fromY", Double.valueOf(fromY));
        jsonObject.put("toX", Double.valueOf(toX));
        jsonObject.put("toY", Double.valueOf(toY));
        jsonObject.put("duration", Double.valueOf(duration));
        this.touch("drag", jsonObject);
    }

    public static enum GesturePinchType {
        PINCH_IN,
        PINCH_OUT;

        private GesturePinchType() {
        }

    }
    public static enum PlatformType {
        IOS,
        ANDROID;

        private PlatformType() {
        }
    }
}

