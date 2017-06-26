package jar.common;

/**
 * Created by test_ge on 2017/6/15.
 */

public enum Keycode implements CharSequence {
    NULL('\ue000'),
    CANCEL('\ue001'),
    HELP('\ue002'),
    BACK_SPACE('\ue003'),
    TAB('\ue004'),
    CLEAR('\ue005'),
    RETURN('\ue006'),
    ENTER('\ue007'),
    SHIFT('\ue008'),
    CONTROL('\ue009'),
    ALT('\ue00a'),
    PAUSE('\ue00b'),
    ESCAPE('\ue00c'),
    SPACE('\ue00d'),
    PAGE_UP('\ue00e'),
    PAGE_DOWN('\ue00f'),
    END('\ue010'),
    HOME('\ue011'),
    ARROW_LEFT('\ue012'),
    ARROW_UP('\ue013'),
    ARROW_RIGHT('\ue014'),
    ARROW_DOWN('\ue015'),
    INSERT('\ue016'),
    DELETE('\ue017'),
    SEMICOLON('\ue018'),
    EQUALS('\ue019'),
    NUMPAD0('\ue01a'),
    NUMPAD1('\ue01b'),
    NUMPAD2('\ue01c'),
    NUMPAD3('\ue01d'),
    NUMPAD4('\ue01e'),
    NUMPAD5('\ue01f'),
    NUMPAD6('\ue020'),
    NUMPAD7('\ue021'),
    NUMPAD8('\ue022'),
    NUMPAD9('\ue023'),
    MULTIPLY('\ue024'),
    ADD('\ue025'),
    SEPARATOR('\ue026'),
    SUBTRACT('\ue027'),
    DECIMAL('\ue028'),
    DIVIDE('\ue029'),
    F1('\ue031'),
    F2('\ue032'),
    F3('\ue033'),
    F4('\ue034'),
    F5('\ue035'),
    F6('\ue036'),
    F7('\ue037'),
    F8('\ue038'),
    F9('\ue039'),
    F10('\ue03a'),
    F11('\ue03b'),
    F12('\ue03c'),
    META('\ue03d'),
    COMMAND('\ue03e'),
    ZENKAKU_HANKAKU('\ue03f');
    private final char keyCode;

    private Keycode(jar.common.Keycode key) {
        this(key.charAt(0));
    }

    private Keycode(char keyCode) {
        this.keyCode = keyCode;
    }

    public char charAt(int index) {
        return index == 0?this.keyCode:'\u0000';
    }

    public int length() {
        return 1;
    }

    public CharSequence subSequence(int start, int end) {
        if(start == 0 && end == 1) {
            return String.valueOf(this.keyCode);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString() {
        return String.valueOf(this.keyCode);
    }

    public static Keycode getKeyFromUnicode(char key) {
        Keycode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Keycode unicodeKey = var1[var3];
            if(unicodeKey.charAt(0) == key) {
                return unicodeKey;
            }
        }

        return null;
    }

}