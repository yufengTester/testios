package biz;

/**
 * Created by test_ge on 2017/6/15.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ResultGenerator {
    public static final String NAME = "result.log";
    public static final String CUSTOM_LOG = "custom.log";
    public static final String SEPARATOR = "|";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public ResultGenerator() {
    }

    public static void write2File(String name, boolean append, String content) {
        System.out.println(content);
        FileOutputStream fos = null;

        try {
            try {
                fos = new FileOutputStream(name, append);
                fos.write(content.getBytes());
            } catch (FileNotFoundException var9) {
                var9.printStackTrace();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        } finally {
            ;
        }
    }

    public static void clearOldData() {
        File resultFile = new File("result.log");
        if(resultFile.exists() && resultFile.isFile()) {
            resultFile.delete();
        }

        File customLogFile = new File("custom.log");
        if(customLogFile.exists() && customLogFile.isFile()) {
            customLogFile.delete();
        }

    }

    public static void success(String action, String desc) {
        write2File("result.log", true, getStringDate() + "," + action + "|" + true + "|" + desc + LINE_SEPARATOR);
        write2File("custom.log", true, getStringDate() + "," + action + "|" + true + "|" + desc + LINE_SEPARATOR);
    }

    public static void loadPageSucc(BasePage page) {
        success(page.pageDesc, "页面加载成功");
    }

    public static void loadPageFail(BasePage page) {
        fail(page.pageDesc, "页面加载失败", BaseErrorType.PAGE_NOT_LOAD);
    }

    public static void findElementFail(CommonUIBean targetElement) {
        customLog(targetElement.elementDesc, "控件不存在");
    }

    public static void catchedException(Exception e) {
        customLog("异常捕捉", e.getMessage());
    }

    public static void fail(String action, String desc, BaseErrorType type) {
        write2File("result.log", true, getStringDate() + "," + action + "|" + false + "|" + desc + "|" + type.getId() + LINE_SEPARATOR);
        write2File("custom.log", true, getStringDate() + "," + action + "|" + false + "|" + desc + "|" + type.getId() + LINE_SEPARATOR);
    }

    public static void customLog(String action, String desc) {
        write2File("custom.log", true, getStringDate() + "," + action + "|" + desc + LINE_SEPARATOR);
    }

    public static void main(String[] args) {
    }

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
