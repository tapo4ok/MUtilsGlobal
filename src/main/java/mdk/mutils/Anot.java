package mdk.mutils;

import mdk.mutils.config.SimpleConfig;
import mdk.mutils.lang.LangUtls;

public class Anot {
    public static void init(Object obj) {
        init(obj, Context.newWith(obj));
    }
    public static void init(Object obj, Context context) {
        SimpleConfig.init(obj, context);
        LangUtls.init(obj, context);
        Static.init(obj, context);
    }
}
