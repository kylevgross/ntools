package com.kvg.ntools.ux;

import javax.baja.naming.BOrd;
import javax.baja.sys.BSingleton;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.BIFormFactorMax;
import javax.baja.web.js.BIJavaScript;
import javax.baja.web.js.JsInfo;


public final class BStaticIconWidget extends BSingleton implements BIJavaScript, BIFormFactorMax {
    public static final BStaticIconWidget INSTANCE = new BStaticIconWidget();
    public static final Type TYPE = Sys.loadType(BStaticIconWidget.class);
    private static final JsInfo jsInfo = JsInfo.make(BOrd.make("module://ntools/rc/BStaticIconWidget.js"));

    private BStaticIconWidget() {
    }

    public Type getType() {
        return TYPE;
    }

    public JsInfo getJsInfo(Context cx) {
        return jsInfo;
    }
}