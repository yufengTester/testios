package TestCase;

//import biz.BaseAppiumClient;
import biz.BaseErrorType;

//import biz.BaseAppiumClient.PlatformType;
import biz.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import jar.AppiumClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by test_ge on 2017/6/15.
 */
public class Basecase {

    // 屏幕截图的数目，为了实现递增的顺序
    private int screenNum = 1;

    public AppiumClient driver= new AppiumClient();

//    @Before
    @Test
    public void setUp() throws Exception {

//        // 清除日志记录
//        ResultGenerator.clearOldData();
//        //清理截图重新记录
//        File file = new File(config.ScreenshotPath);
//        deleteOldScreen(file);

        // 初始化应用基础信息
        JSONObject props = new JSONObject();
        if (config.PLATFORM.equals("ios")) {

            // 创建ios实例
//            props.put("app", config.IOS_APP);
			props.put("bundleId",config.IOS_bundleId);
            props.put("platformName", config.IOS_PLATFORM_NAME);
            props.put("deviceName", config.IOS_DEVICE_NAME);
            props.put("udid", config.IOS_UDID);
            props.put("autoAcceptAlerts", true);
            props.put("waitForAppScript","$.delay(5000); $.acceptAlert(); true;");
            props.put("resetKeyboard", "True");
//            props.put("platformVersion",config.IOS_platformVersion);
//            driver.setCurPlatform(PlatformType.IOS);

        } else {

            //创建安卓实例
            props.put("app", config.ADR_APP);
            props.put("platformName", config.ADR_PLATFORM_NAME);
//            driver.setCurPlatform(PlatformType.ANDROID);
        }

        // 覆盖安装
        props.put("reuse", config.REUSE);

        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", props);
        driver.initDriver(desiredCapabilities);

    }

//
//    @After
//    public void tearDown() throws Exception {
//
//        try {
//            driver.quit();
//        } catch (Exception e) {
//            // TODO: handle exception
//            ResultGenerator.fail("quit fail", "", BaseErrorType.FUNCTION_FAILED);
//        }
//
//    }

//    /**
//     * 保存当前屏幕截图-生成的截图会按照截图的先后顺序生成有序的名称
//     * @param fileName 图片名称，默认为.png格式,图片默认保存在screenShot目录下
//     */
//    public void saveScreen(String fileName) {
//        try {
//            // 判断是否存在对应目录，不存在的话则创建
//            File file = new File(config.ScreenshotPath);
//            if (!file.exists() || !file.isDirectory()) {
//                // 没有目录 创建截屏目录
//                System.out.println("没有screenshot目录，创建目录");
//                boolean isMkdirSucc = file.mkdir();
//                if (isMkdirSucc) {
//                    System.out.println("创建screenshot目录成功");
//                } else {
//                    System.out.println("创建screenshot目录失败");
//                }
//            } else {
//                System.out.println("存在screenshot目录");
//            }
//
//            driver.saveScreenshot(
//                    config.ScreenshotPath + File.separator + screenNum + "_" + fileName + ".png");
//            screenNum++;
//        } catch (Exception e) {
//            // TODO: handle exception
//            ResultGenerator.fail("截屏异常", "", BaseErrorType.FUNCTION_FAILED);
//        }
//    }
//
//    //删除screenshot目录下旧的截图
//    public void deleteOldScreen(File oldScreen) {
//        if (oldScreen.exists() && oldScreen.isDirectory()) {
//            File[] files = oldScreen.listFiles();
//            for (File file : files) {
//                deleteOldScreen(file);
//            }
//        } else {
//            oldScreen.delete();
//        }
//
//    }
}

