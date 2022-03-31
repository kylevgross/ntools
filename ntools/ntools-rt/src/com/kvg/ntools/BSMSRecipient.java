package com.kvg.ntools;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.security.BPassword;
import java.security.*;
import javax.baja.alarm.*;
import com.tridium.alarm.user.BIUserAlarmRecipient;
import javax.baja.alarm.BAlarmRecipient;
import javax.baja.alarm.BAlarmRecord;
import javax.baja.util.BFormat;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.*;
import java.util.logging.Logger;
import javax.baja.alarm.AlarmSupport;
import javax.baja.alarm.BAlarmRecipient;
import javax.baja.alarm.BAlarmRecord;
import javax.baja.alarm.BAlarmSourceInfo;
import javax.baja.alarm.BIAlarmSource;
import javax.baja.gx.BFont;
import javax.baja.naming.BOrd;
import javax.baja.naming.BOrdList;
import javax.baja.sys.Action;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BFacets;
import javax.baja.sys.BIcon;
import javax.baja.sys.BString;
import javax.baja.sys.BValue;
import javax.baja.sys.BVector;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFormat;
import javax.baja.util.Queue;
import javax.baja.util.Worker;

@NiagaraType

@NiagaraProperty(
        name = "to",
        type = "BString",
        defaultValue = "personName:1234567890"

)
@NiagaraProperty(
        name = "accountSid",
        type = "String",
        defaultValue = ""

)




public class BSMSRecipient extends BAlarmRecipient implements BIAlarmSource {

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.kvg.ntools.BSMSRecipient(2420554016)1.0$ @*/
/* Generated Tue Mar 29 15:28:10 BOT 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "to"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code to} property.
   * @see #getTo
   * @see #setTo
   */
  public static final Property to = newProperty(0, "personName:1234567890", null);
  
  /**
   * Get the {@code to} property.
   * @see #to
   */
  public String getTo() { return getString(to); }
  
  /**
   * Set the {@code to} property.
   * @see #to
   */
  public void setTo(String v) { setString(to, v, null); }

////////////////////////////////////////////////////////////////
// Property "accountSid"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code accountSid} property.
   * @see #getAccountSid
   * @see #setAccountSid
   */
  public static final Property accountSid = newProperty(0, "", null);
  
  /**
   * Get the {@code accountSid} property.
   * @see #accountSid
   */
  public String getAccountSid() { return getString(accountSid); }
  
  /**
   * Set the {@code accountSid} property.
   * @see #accountSid
   */
  public void setAccountSid(String v) { setString(accountSid, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
public static final Property authToken = newProperty(0, BPassword.DEFAULT, (BFacets)null);
    public static final Property textFormat = newProperty(0, makeDefaultTextFormat(), BFacets.make("multiLine", BBoolean.make(true)));

    @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSMSRecipient.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    



    private AlarmSupport support;



    public BPassword getAuthToken() {
        return (BPassword)this.get(authToken);
    }

    public void setAuthToken(BPassword v) {
        this.set(authToken, v, (Context)null);
    }


    public BFormat getTextFormat() {
        return (BFormat)this.get(textFormat);
    }

    public void setTextFormat(BFormat v) {
        this.set(textFormat, v, (Context)null);
    }


    public void started() throws Exception {
        System.out.println("started");
    }

    public void stopped() throws Exception {
        System.out.println("stopped");

    }

    public static BFormat makeDefaultTextFormat() {
        StringBuilder buf = new StringBuilder();
        buf.append("Site:           %sys().station.stationName%\n");
        buf.append("Source:         %alarmData.sourceName%\n");
        buf.append("Timestamp:      %timestamp%\n");
        buf.append("State:          %sourceState% / %ackState%\n");
        buf.append("Present Value:  %alarmData.presentValue%\n");
        buf.append("Priority:       %priority%\n");
        buf.append("Alarm Class:    %alarmClass%\n");
        buf.append("Text:           %alarmData.msgText%\n");
        buf.append("\n");
        return BFormat.make(buf.toString());
    }



    public void handleAlarm(BAlarmRecord alarm) {
        String body = BFormat.make(this.getTextFormat().format(alarm)).format(alarm);
        String to = this.getTo();
        String[] recipients = to.split(",", 0);
        for(String toNumber : recipients) {
            String recipientNumber = toNumber.replaceAll("[^\\d.]", "");
            PrivilegedAction action = new PrivilegedAction<Void>() {
                public Void run() {

                    // privileged code goes here, for example:
                    Twilio.init(getAccountSid(), getAuthToken().getValue());
                    //System.out.println("sending to " + Arrays.toString(recipients));
                    Message message = Message.creator(
                                    new com.twilio.type.PhoneNumber("+1" + recipientNumber),
                                    new com.twilio.type.PhoneNumber("+18336161505"),
                                    body)
                            .create();
                    System.out.println("Message sent to " + recipientNumber);
                    return null; // nothing to return
                }


            };
            AccessController.doPrivileged(action);
        }
    }


    public BBoolean ackAlarm(BAlarmRecord alert) {
        try {
            return BBoolean.make(this.support.ackAlarm(alert));
        } catch (Exception var3) {
            return BBoolean.FALSE;
        }
    }

    @Override
    public BIcon getIcon() {
        return BIcon.std("mobile.png");

    }
}

