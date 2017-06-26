package jar.common;

/**
 * Created by test_ge on 2017/6/15.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jar.commands.Element;
import jar.AppiumClient;


public class ElementSelector {
    private JSONArray jsonArray;
    private AppiumClient client;
    private AppiumDriver driver;

    public ElementSelector(AppiumDriver driver, AppiumClient client, JSONArray jsonArray) {
        this.client = client;
        this.driver = driver;
        this.jsonArray = jsonArray;
    }

    public int size() {
        return this.jsonArray.size();
    }

    public Element getIndex(int index) {
        this.setElementByIndex(index);
        return this.client.element;
    }

    private void setElementByIndex(int index) {
        this.driver.setElementId(((JSONObject)this.jsonArray.get(index)).getString("ELEMENT"));
    }
}
