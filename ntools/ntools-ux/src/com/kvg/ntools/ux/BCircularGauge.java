package com.kvg.ntools.ux;

import javax.baja.naming.BOrd;
import javax.baja.sys.BSingleton;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.BIFormFactorMax;
import javax.baja.web.js.BIJavaScript;
import javax.baja.web.js.JsInfo;


public final class BCircularGauge extends BSingleton implements BIJavaScript, BIFormFactorMax {
    public static final BCircularGauge INSTANCE = new BCircularGauge();
    public static final Type TYPE = Sys.loadType(BCircularGauge.class);
    private static final JsInfo jsInfo = JsInfo.make(BOrd.make("module://ntools/rc/BCircularGauge.js"));

    private BCircularGauge() {
    }

    public Type getType() {
        return TYPE;
    }

    public JsInfo getJsInfo(Context cx) {
        return jsInfo;
    }
}