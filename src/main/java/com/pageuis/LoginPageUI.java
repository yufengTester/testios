package com.pageuis;

import jar.common.GetElementWay;
import biz.BasePageUI;
import biz.CommonUIBean;

public class LoginPageUI extends BasePageUI{
	public static final CommonUIBean USER_NAME = new CommonUIBean(GetElementWay.ID, "com.github.android_app_bootstrap:id/mobileNoEditText",GetElementWay.XPATH,"//XCUIElementTypeApplication[@name=\"淘汽云修\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField", "登录用户名输入框");
	public static final CommonUIBean PASSWORD = new CommonUIBean(GetElementWay.ID, "com.github.android_app_bootstrap:id/codeEditText",GetElementWay.XPATH,"//XCUIElementTypeApplication[@name=\"淘汽云修\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSecureTextField","登录密码输入框");
	public static final CommonUIBean LOGIN_BTN = new CommonUIBean(GetElementWay.ID, "com.github.android_app_bootstrap:id/login_button",GetElementWay.NAME,"登  录", "登录按钮");
//	public static final CommonUIBean KEY_BOARD = new CommonUIBean(GetElementWay.NAME, "Done", "键盘完成按钮");
}
