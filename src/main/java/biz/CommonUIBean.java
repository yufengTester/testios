package biz;

/**
 * Created by test_ge on 2017/6/15.
 */
import jar.common.GetElementWay;

public class CommonUIBean {
    protected GetElementWay androidBy;
    protected String androidValue;
    protected GetElementWay iosBy;
    protected String iosValue;
    public String elementDesc;

    public CommonUIBean(GetElementWay androidBy, String androidValue, GetElementWay iosBy, String iosValue, String elementDesc) {
        this.androidBy = androidBy;
        this.androidValue = androidValue;
        this.iosBy = iosBy;
        this.iosValue = iosValue;
        this.elementDesc = elementDesc;
    }

    public CommonUIBean(GetElementWay commonBy, String commonValue, String elementDesc) {
        this.androidBy = commonBy;
        this.androidValue = commonValue;
        this.iosBy = commonBy;
        this.iosValue = commonValue;
        this.elementDesc = elementDesc;
    }

    public GetElementWay getAndroidBy() {
        return this.androidBy;
    }

    public void setAndroidBy(GetElementWay androidBy) {
        this.androidBy = androidBy;
    }

    public String getAndroidValue() {
        return this.androidValue;
    }

    public void setAndroidValue(String androidValue) {
        this.androidValue = androidValue;
    }

    public GetElementWay getIosBy() {
        return this.iosBy;
    }

    public void setIosBy(GetElementWay iosBy) {
        this.iosBy = iosBy;
    }

    public String getIosValue() {
        return this.iosValue;
    }

    public void setIosValue(String iosValue) {
        this.iosValue = iosValue;
    }

    public String getElementDesc() {
        return this.elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }
}
