package jar.common;

/**
 * Created by test_ge on 2017/6/15.
 */
public class DriverCommand {
    public static final String CREATE_SESSION = "session";
    public static final String GET_SESSIONS = "sessions";
    public static final String SESSION = "session/:sessionId";
    public static final String STATUS = "status";
    public static final String IMPLICITLY_WAIT = "session/:sessionId/timeouts/implicit_wait";
    public static final String SCREENSHOT = "session/:sessionId/screenshot";
    public static final String GET_SOURCE = "session/:sessionId/source";
    public static final String CONTEXTS = "session/:sessionId/contexts";
    public static final String CONTEXT = "session/:sessionId/context";
    public static final String CLICK = "session/:sessionId/click";
    public static final String KEYS = "session/:sessionId/keys";
    public static final String ACTIONS = "session/:sessionId/actions";
    public static final String FIND_CHILD_ELEMENT = "session/:sessionId/element/:elementId/element";
    public static final String FIND_CHILD_ELEMENTS = "session/:sessionId/element/:elementId/elements";
    public static final String FIND_ELEMENT = "session/:sessionId/element";
    public static final String FIND_ELEMENTS = "session/:sessionId/elements";
    public static final String ELEMENT_VALUE = "session/:sessionId/element/:elementId/value";
    public static final String CLICK_ELEMENT = "session/:sessionId/element/:elementId/click";
    public static final String GET_ELEMENT_TEXT = "session/:sessionId/element/:elementId/text";
    public static final String IS_ELEMENT_DISPLAYED = "session/:sessionId/element/:elementId/displayed";
    public static final String CLEAR_ELEMENT = "session/:sessionId/element/:elementId/clear";
    public static final String GET_ELEMENT_ATTRIBUTE = "session/:sessionId/element/:elementId/attribute/:name";
    public static final String GET_ELEMENT_PROPERTY = "session/:sessionId/element/:elementId/property/:name";
    public static final String GET_ELEMENT_RECT = "session/:sessionId/element/:elementId/rect";
    public static final String GET_ELEMENT_VALUE_OF_CSS_PROPERTY = "session/:sessionId/element/:elementId/css/:propertyName";
    public static final String EXECUTE_SCRIPT = "session/:sessionId/execute";
    public static final String GET_TITLE = "session/:sessionId/title";
    public static final String ACCEPT_ALERT = "session/:sessionId/accept_alert";
    public static final String DISMISS_ALERT = "session/:sessionId/dismiss_alert";
    public static final String ALERT_TEXT = "session/:sessionId/alert_text";
    public static final String URL = "session/:sessionId/url";
    public static final String BACK = "session/:sessionId/back";
    public static final String FORWARD = "session/:sessionId/forward";
    public static final String REFRESH = "session/:sessionId/refresh";
    public static final String WINDOW_HANDLE = "session/:sessionId/window_handle";
    public static final String WINDOW_SIZE = "session/:sessionId/window/:windowHandle/size";
    public static final String WINDOW_HANDLES = "session/:sessionId/window_handles";
    public static final String WINDOW = "/window";
    public static final String SET_WINDOW_SIZE = "session/:sessionId/setWindowSize";
    public static final String MAXIMIZE_WINDOW = "session/:sessionId/window/:windowHandle/maximize";
    public static final String FRAME = "session/:sessionId/frame";

    public DriverCommand() {
    }
}

