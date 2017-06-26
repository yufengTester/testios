//package TestCase.LoginCase;
//
//import TestCase.Basecase;
//import biz.CommonUIBean;
//import biz.ResultGenerator;
//import com.alibaba.fastjson.JSONObject;
//import com.pages.LoginPage;
//import com.pageuis.LoginPageUI;
//import jar.common.AppiumDriver;
//import org.junit.Before;
//import jar.AppiumClient;
//import org.junit.Test;
//import org.openqa.selenium.By;
//
///**
// * Created by test_ge on 2017/6/16.
// */
//public class IosSampleTest extends Basecase {
//    @Test
//    public void test_case_1() throws Exception {
//        LoginPage loginPage = new LoginPage("登录页");
//
//        loginPage.setDriver(driver);
////        driver.findElementByIndex(LoginPageUI.USER_NAME,4).clearText();
//        if (loginPage.hasPageShown(LoginPageUI.LOGIN_BTN)) {
////            saveScreen(loginPage.pageDesc);
//            ResultGenerator.loadPageSucc(loginPage);
//            loginPage.login("~!@#$%^&", "1234");
//        } else {
//            ResultGenerator.loadPageFail(loginPage);
//
//        }
//    }
//}
