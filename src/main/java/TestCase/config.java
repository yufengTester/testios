package TestCase;

import java.io.File;


public class config {

    // 根目录
    public static final String RootPath = System.getProperty("user.dir");
    //截图保存目录
    public static final String ScreenshotPath = RootPath + File.separator + "screenshot";
    //结果日志保存文件
    public static final String ResultLogPath = RootPath + File.separator + "result.log";

    // 用户名
    public static final String USER_NAME = "18668192462";
    // 密码
    public static final String PASSWORD = "1234";
    // 目标平台- ios android
    public static final String PLATFORM = "ios";
    // 是否覆盖安装 3-覆盖安装
    public static final String REUSE = "3";

    // ios平台相关信息 各参数含义参考 https://macacajs.github.io/macaca/desired-caps.html
    public static final String IOS_PLATFORM_NAME = "iOS";
    public static final String IOS_DEVICE_NAME = "iPhone 5s";
    public static final String IOS_bundleId = "com.tqmall.Motormaster";
    public static final String IOS_platformVersion = "10.3";
//    public static final String IOS_APP = RootPath + File.separator + "app/ios-app-bootstrap.zip";
    public static final String IOS_UDID = "2b01b1d98986abcb703c2fd5916618bda25531e4";

    // 安卓平台相关信息
    public static final String ADR_PLATFORM_NAME = "Android";
    public static final String ADR_APP = RootPath + File.separator + "app/android-app-bootstrap.zip";
    // 多台设备时，如果指定某一台设备可以在这里指定udid
    public static final String ADR_UDID = "";
}



