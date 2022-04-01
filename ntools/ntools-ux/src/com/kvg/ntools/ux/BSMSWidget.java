package com.kvg.ntools.ux;

import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BSingleton;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.BIFormFactorMax;
import javax.baja.web.js.BIJavaScript;
import javax.baja.web.js.JsInfo;

@NiagaraType(
        agent = {@AgentOn(
                types = {"ntools:SMSRecipient"}
        )}
)
public class BSMSWidget extends BSingleton implements BIJavaScript, BIFormFactorMax {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.kvg.ntools.ux.BSMSWidget(3736156076)1.0$ @*/
/* Generated Thu Mar 31 12:15:08 BOT 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public static final BSMSWidget INSTANCE = new BSMSWidget();
    public static final Type TYPE = Sys.loadType(BSMSWidget.class);
    private static final JsInfo jsInfo = JsInfo.make(BOrd.make("module://ntools/rc/smswidget.js"));

    private BSMSWidget() {
    }

    public Type getType() {
        return TYPE;
    }

    public JsInfo getJsInfo(Context cx) {
        return jsInfo;
    }
}
