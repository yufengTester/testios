package biz;

/**
 * Created by test_ge on 2017/6/15.
 */

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


public class BaseUtils {
    public BaseUtils() {
    }

    public static List<String> exec2(String cmd) {
        ResultGenerator.customLog("执行系统命令", cmd);
        Runtime run = Runtime.getRuntime();
        ArrayList lines = new ArrayList();

        try {
            Process e = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(e.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = inBr.readLine()) != null) {
                lines.add(line);
            }

            inBr.close();
            in.close();
        } catch (Exception var7) {
            ResultGenerator.customLog("命令执行异常", var7.getMessage());
        }

        return lines;
    }

    public static void installApp(String deviceId, String apkPath) {
        if(isStringNotNull(deviceId)) {
            exec2("adb -s " + deviceId + "install " + apkPath);
        } else {
            exec2("adb install" + apkPath);
        }

    }

    public static void startApp(String deviceId, String packageName, String activityName) {
        if(isStringNotNull(deviceId)) {
            exec2("adb -s " + deviceId + " shell am start -S -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -f 0x10200000 -n " + packageName + "/" + activityName);
        } else {
            exec2("adb shell am start -S -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -f 0x10200000 -n " + packageName + "/" + activityName);
        }

    }

    public static void clearApp(String deviceId, String packageName) {
        if(isStringNotNull(deviceId)) {
            exec2("adb -s " + deviceId + " shell pm clear " + packageName);
        } else {
            exec2("adb shell pm clear " + packageName);
        }

    }

    public static void forceStopApp(String deviceId, String packageName) {
        if(isStringNotNull(deviceId)) {
            exec2("adb -s " + deviceId + " shell am force-stop " + packageName);
        } else {
            exec2("adb shell am force-stop " + packageName);
        }

    }

    public static String getFileMD5(File file) {
        if(!file.isFile()) {
            return null;
        } else {
            MessageDigest digest = null;
            FileInputStream in = null;
            byte[] buffer = new byte[1024];

            try {
                digest = MessageDigest.getInstance("MD5");
                in = new FileInputStream(file);

                while(true) {
                    int len;
                    if((len = in.read(buffer, 0, 1024)) == -1) {
                        in.close();
                        break;
                    }

                    digest.update(buffer, 0, len);
                }
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }

            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        }
    }

    public static boolean isStringNotNull(String str) {
        return str != null && str.length() != 0;
    }
}
