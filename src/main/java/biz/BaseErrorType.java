package biz;

/**
 * Created by test_ge on 2017/6/15.
 */
public enum BaseErrorType {
    FUNCTION_FAILED(3001, "其他异常--"),
    ELEMENT_NOT_FOUND(3004, "未找到控件"),
    PAGE_NOT_LOAD(3006, "页面未加载"),
    INSTALL_FAIL(1011, "安装失败");

    private int id;
    private String desc;

    private BaseErrorType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }
}

