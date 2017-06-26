package biz;

import jar.AppiumClient;

/**
 * Created by test_ge on 2017/6/15.
 */



public class BasePage {
    public AppiumClient driver;
    public String pageDesc;

    public AppiumClient getDriver() {
        return this.driver;
    }

    public void setDriver(AppiumClient driver) {
        this.driver = driver;
    }

    public BasePage(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    public BasePage(String pageDesc, AppiumClient driver) {
        this.pageDesc = pageDesc;
        this.driver = driver;
    }

//    public boolean hasPageShown(CommonUIBean bean) {
//        return this.driver.isElementExistAfterWaiting(bean);
//    }
}
