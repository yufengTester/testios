//package biz;
//
///**
// * Created by test_ge on 2017/6/15.
// */
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import jar.AppiumClient;
//import jar.commands.Element;
//import java.io.File;
//
//
//public class BaseAppiumClient extends AppiumClient {
//    public BaseAppiumClient.PlatformType curPlatform;
//    public static final String BEFORE_IMAGE_NAME = "before.png";
//    public static final String AFTER_IMAGE_NAME = "after.png";
//
//    public BaseAppiumClient() {
//    }
//
//    public BaseAppiumClient.PlatformType getCurPlatform() {
//        return this.curPlatform;
//    }
//
//    public void setCurPlatform(BaseAppiumClient.PlatformType curPlatform) {
//        this.curPlatform = curPlatform;
//    }
//
//    public Element findElement(CommonUIBean bean) throws Exception {
//        return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.getElement(bean.getIosBy(), bean.getIosValue()):this.getElement(bean.getAndroidBy(), bean.getAndroidValue());
//    }
//
//    public Element findElementByIndex(CommonUIBean bean, int index) throws Exception {
//        return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.getElement(bean.getIosBy(), bean.getIosValue(), index):this.getElement(bean.getAndroidBy(), bean.getAndroidValue(), index);
//    }
//
//    public int countOfElment(CommonUIBean bean) throws Exception {
//        return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.countOfElements(bean.getIosBy(), bean.getIosValue()):this.countOfElements(bean.getAndroidBy(), bean.getAndroidValue());
//    }
//
//    public Element waitForElement(CommonUIBean bean) throws Exception {
//        return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.waitForElement(bean.getIosBy(), bean.getIosValue()):this.waitForElement(bean.getAndroidBy(), bean.getAndroidValue());
//    }
//
//    public Element waitForElement(CommonUIBean bean, int index) throws Exception {
//        return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.waitForElement(bean.getIosBy(), bean.getIosValue(), index):this.waitForElement(bean.getAndroidBy(), bean.getAndroidValue(), index);
//    }
//
//    public boolean isElementExist(CommonUIBean bean) {
//        try {
//            return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.isElementExist(bean.getIosBy(), bean.getIosValue()):this.isElementExist(bean.getAndroidBy(), bean.getAndroidValue());
//        } catch (Exception var3) {
//            return false;
//        }
//    }
//
//    public boolean isElementExist(CommonUIBean bean, int index) {
//        try {
//            return this.curPlatform == BaseAppiumClient.PlatformType.IOS?this.isElementExist(bean.getIosBy(), bean.getIosValue(), index):this.isElementExist(bean.getAndroidBy(), bean.getAndroidValue(), index);
//        } catch (Exception var4) {
//            ResultGenerator.catchedException(var4);
//            return false;
//        }
//    }
//
//    public boolean isElementExistAfterWaiting(CommonUIBean bean) {
//        try {
//            if(this.isElementExist(bean)) {
//                return true;
//            } else {
//                this.waitForElement(bean);
//                return this.isElementExist(bean);
//            }
//        } catch (Exception var3) {
//            ResultGenerator.catchedException(var3);
//            return false;
//        }
//    }
//
//    public boolean isElementExistAfterWaiting(CommonUIBean bean, int index) {
//        try {
//            if(this.isElementExist(bean, index)) {
//                return true;
//            } else {
//                this.waitForElement(bean, index);
//                return this.isElementExist(bean, index);
//            }
//        } catch (Exception var4) {
//            ResultGenerator.catchedException(var4);
//            return false;
//        }
//    }
//
////    public void customBack() throws Exception {
////        if(this.curPlatform == BaseMacacaClient.PlatformType.IOS) {
////            try {
////                this.drag(0.0D, 100.0D, 300.0D, 100.0D, 0.5D, 10);
////            } catch (Exception var2) {
////                ResultGenerator.catchedException(var2);
////            }
////        } else {
////            super.back();
////        }
////
////    }
//
//    public void onclickBean(CommonUIBean bean) {
//        try {
//            if(this.isElementExistAfterWaiting(bean)) {
//                Element e = this.findElement(bean);
//                e.click();
//                ResultGenerator.success("点击:" + bean.elementDesc, "");
//            } else {
//                ResultGenerator.findElementFail(bean);
//            }
//        } catch (Exception var3) {
//            ResultGenerator.catchedException(var3);
//        }
//
//    }
//
//    public void onclickBeanAtIndex(CommonUIBean bean, int index) {
//        try {
//            if(this.isElementExistAfterWaiting(bean, index)) {
//                Element e = this.findElementByIndex(bean, index);
//                e.click();
//                ResultGenerator.success("点击:" + bean.elementDesc + "[" + index + "]", "");
//            } else {
//                ResultGenerator.findElementFail(bean);
//            }
//        } catch (Exception var4) {
//            ResultGenerator.catchedException(var4);
//        }
//
//    }
//
//    public void inputBean(CommonUIBean bean, String input) {
//        try {
//            if(this.isElementExistAfterWaiting(bean)) {
//                Element e = this.findElement(bean);
//                e.sendKeys(input);
//                ResultGenerator.success("输入: " + bean.elementDesc + ";value:" + input, "");
//            } else {
//                ResultGenerator.findElementFail(bean);
//            }
//        } catch (Exception var4) {
//            ResultGenerator.catchedException(var4);
//        }
//
//    }
//
//    public void clearText(CommonUIBean bean) {
//        try {
//            Element e = this.findElement(bean);
//            e.clearText();
//        } catch (Exception var3) {
//            ResultGenerator.catchedException(var3);
//        }
//
//    }
//
//    public String getText(CommonUIBean bean) {
//        try {
//            Element e = this.findElement(bean);
//            return e.getText();
//        } catch (Exception var3) {
//            ResultGenerator.catchedException(var3);
//            return null;
//        }
//    }
//
//    public Object getProperty(CommonUIBean bean, String name) {
//        try {
//            Element e = this.findElement(bean);
//            return e.getProperty(name);
//        } catch (Exception var4) {
//            ResultGenerator.catchedException(var4);
//            return null;
//        }
//    }
//
//    public Object getRect(CommonUIBean bean) {
//        try {
//            Element e = this.findElement(bean);
//            return e.getRect();
//        } catch (Exception var3) {
//            ResultGenerator.catchedException(var3);
//            return null;
//        }
//    }
//
//    public boolean isDisplayed(CommonUIBean bean) {
//        try {
//            Element e = this.findElement(bean);
//            return e.isDisplayed();
//        } catch (Exception var3) {
//            ResultGenerator.catchedException(var3);
//            return false;
//        }
//    }
//
////    public boolean scrollToElement(GetElementWay wayToFind, String value) {
////        try {
////            JSONObject windowSize = this.getWindowSize();
////            int e = windowSize.getIntValue("width");
////            int windowHeight = windowSize.getIntValue("height");
////            int startX = e - 20;
////            int endX = startX;
////            int startY = windowHeight * 3 / 5;
////            int endY = windowHeight * 2 / 5;
////            String beforeScreenShot = null;
////            String afterScreenShot = null;
////            String beforePng = "before.png";
////            String afterPng = "after.png";
////
////            while(!this.isElementExist(wayToFind, value)) {
////                File shotOne = new File(beforePng);
////                File shotTwo = new File(afterPng);
////                beforeScreenShot = BaseUtils.getFileMD5(shotOne);
////                afterScreenShot = BaseUtils.getFileMD5(shotTwo);
////                if(beforeScreenShot != null && beforeScreenShot.length() > 0 && beforeScreenShot.equals(afterScreenShot)) {
////                    System.out.println("the given element does not exist");
////                    this.deleteDiffImages();
////                    return false;
////                }
////
////                this.saveScreenshot(beforePng);
////                System.out.println("scroll: (" + startX + "," + startY + "," + endX + "," + endY + ")");
////                this.drag((double)startX, (double)startY, (double)endX, (double)endY, 0.05D, 10);
////                Thread.sleep(1000L);
////                this.saveScreenshot(afterPng);
////            }
////
////            this.deleteDiffImages();
////            return true;
////        } catch (Exception var16) {
////            this.deleteDiffImages();
////            var16.printStackTrace();
////            this.deleteDiffImages();
////            return false;
////        }
////    }
//
////    public void scrollToBottom() {
////        try {
////            JSONObject windowSize = this.getWindowSize();
////            int e = windowSize.getIntValue("width");
////            int windowHeight = windowSize.getIntValue("height");
////            int startX = e - 20;
////            int endX = startX;
////            int startY = windowHeight * 3 / 5;
////            int endY = windowHeight * 2 / 5;
////            String beforeScreenShot = null;
////            String afterScreenShot = null;
////            String beforePng = "before_bottom.png";
////            String afterPng = "after_bottom.png";
////
////            for(int flag = 10; flag > 0; --flag) {
////                File shotOne = new File(beforePng);
////                File shotTwo = new File(afterPng);
////                beforeScreenShot = BaseUtils.getFileMD5(shotOne);
////                afterScreenShot = BaseUtils.getFileMD5(shotTwo);
////                if(beforeScreenShot != null && beforeScreenShot.length() > 0 && beforeScreenShot.equals(afterScreenShot)) {
////                    System.out.println("scroollToBottom");
////                    this.deleteDiffImages();
////                    return;
////                }
////
////                this.saveScreenshot(beforePng);
////                System.out.println("scroll: (" + startX + "," + startY + "," + endX + "," + endY + ")");
////                this.drag((double)startX, (double)startY, (double)endX, (double)endY, 0.05D, 10);
////                Thread.sleep(1000L);
////                this.saveScreenshot(afterPng);
////            }
////
////            this.deleteDiffImages();
////        } catch (Exception var15) {
////            this.deleteDiffImages();
////            var15.printStackTrace();
////        }
////
////        this.deleteDiffImages();
////    }
//
////    public void scrollToTop() {
////        try {
////            JSONObject windowSize = this.getWindowSize();
////            int e = windowSize.getIntValue("width");
////            int windowHeight = windowSize.getIntValue("height");
////            int startX = e - 20;
////            int endX = startX;
////            int startY = windowHeight * 2 / 5;
////            int endY = windowHeight * 3 / 5;
////            String beforeScreenShot = null;
////            String afterScreenShot = null;
////            String beforePng = "before_top.png";
////            String afterPng = "after_top.png";
////
////            for(int flag = 10; flag > 0; --flag) {
////                File shotOne = new File(beforePng);
////                File shotTwo = new File(afterPng);
////                beforeScreenShot = BaseUtils.getFileMD5(shotOne);
////                afterScreenShot = BaseUtils.getFileMD5(shotTwo);
////                if(beforeScreenShot != null && beforeScreenShot.length() > 0 && beforeScreenShot.equals(afterScreenShot)) {
////                    System.out.println("scroollToBottom");
////                    this.deleteDiffImages();
////                    return;
////                }
////
////                this.saveScreenshot(beforePng);
////                System.out.println("scroll: (" + startX + "," + startY + "," + endX + "," + endY + ")");
////                this.drag((double)startX, (double)startY, (double)endX, (double)endY, 0.05D, 10);
////                Thread.sleep(1000L);
////                this.saveScreenshot(afterPng);
////            }
////
////            this.deleteDiffImages();
////        } catch (Exception var15) {
////            this.deleteDiffImages();
////            var15.printStackTrace();
////        }
////
////        this.deleteDiffImages();
////    }
//
//    private void deleteDiffImages() {
//        try {
//            File e = new File("before.png");
//            File shotTwo = new File("after.png");
//            if(e.exists()) {
//                e.delete();
//            }
//
//            if(shotTwo.exists()) {
//                shotTwo.delete();
//            }
//        } catch (Exception var3) {
//            var3.printStackTrace();
//        }
//
//    }
//
//    public void startApp() {
//        JSONObject capabilities = this.contexts.getCapabilities();
//        JSONObject value = capabilities.getJSONObject("value");
//        String deviceId = value.getString("udid");
//        String packageName = value.getString("package");
//        String activityName = value.getString("activity");
//        BaseUtils.startApp(deviceId, packageName, activityName);
//    }
//
//    public void clearApp() {
//        JSONObject capabilities = this.contexts.getCapabilities();
//        JSONObject value = capabilities.getJSONObject("value");
//        String deviceId = value.getString("udid");
//        String packageName = value.getString("package");
//        BaseUtils.clearApp(deviceId, packageName);
//    }
//
//    public void forceStopApp() {
//        JSONObject capabilities = this.contexts.getCapabilities();
//        JSONObject value = capabilities.getJSONObject("value");
//        String deviceId = value.getString("udid");
//        String packageName = value.getString("package");
//        BaseUtils.forceStopApp(deviceId, packageName);
//    }
//
//    public void switchFromNativeToWebView() throws Exception {
//        JSONArray contexts = this.contexts();
//        this.context(contexts.get(contexts.size() - 1).toString());
//    }
//
//    public void switchFromeWebviewToNative() throws Exception {
//        JSONArray contexts = this.contexts();
//        this.context(contexts.get(0).toString());
//    }
//
//    public static enum GesturePinchType {
//        PINCH_IN,
//        PINCH_OUT;
//
//        private GesturePinchType() {
//        }
//    }
//
//    public static enum PlatformType {
//        IOS,
//        ANDROID;
//
//        private PlatformType() {
//        }
//    }
//}
