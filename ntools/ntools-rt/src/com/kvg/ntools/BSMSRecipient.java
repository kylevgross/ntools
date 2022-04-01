package com.kvg.ntools;

// 3/31/2022 by Kyle Gross
// SMS Recipient using twilio

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
@NiagaraProperty(
        name = "apiKey",
        type = "String",
        defaultValue = ""

)

@NiagaraProperty(
        name = "apiSecret",
        type = "BPassword",
        defaultValue = "BPassword.DEFAULT"

)


public class BSMSRecipient extends BAlarmRecipient implements BIAlarmSource {


    //setup some slots without slotomatic
    public static final Property textFormat = newProperty(0, makeDefaultTextFormat(), BFacets.make("multiLine", BBoolean.make(true)));
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.kvg.ntools.BSMSRecipient(676336021)1.0$ @*/
/* Generated Thu Mar 31 12:27:03 BOT 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Property "apiKey"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code apiKey} property.
   * @see #getApiKey
   * @see #setApiKey
   */
  public static final Property apiKey = newProperty(0, "", null);
  
  /**
   * Get the {@code apiKey} property.
   * @see #apiKey
   */
  public String getApiKey() { return getString(apiKey); }
  
  /**
   * Set the {@code apiKey} property.
   * @see #apiKey
   */
  public void setApiKey(String v) { setString(apiKey, v, null); }

////////////////////////////////////////////////////////////////
// Property "apiSecret"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code apiSecret} property.
   * @see #getApiSecret
   * @see #setApiSecret
   */
  public static final Property apiSecret = newProperty(0, BPassword.DEFAULT, null);
  
  /**
   * Get the {@code apiSecret} property.
   * @see #apiSecret
   */
  public BPassword getApiSecret() { return (BPassword)get(apiSecret); }
  
  /**
   * Set the {@code apiSecret} property.
   * @see #apiSecret
   */
  public void setApiSecret(BPassword v) { set(apiSecret, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSMSRecipient.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private AlarmSupport support;




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
    //setup default message
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

   //do stuff with the alarm
    public void handleAlarm(BAlarmRecord alarm) {
        //get the alarm and turn it into the text were going to send out
        String body = BFormat.make(this.getTextFormat().format(alarm)).format(alarm);
        //get the list of people
        String to = this.getTo();
        //split into recipients
        String[] recipients = to.split(",", 0);
        //iterate through each recipient
        for(String toNumber : recipients) {
            //get rid of everything that's not a number (Name, -, ., :, (), etc.)
            String recipientNumber = toNumber.replaceAll("[^\\d.]", "");
            // do the "privileged" stuff
            PrivilegedAction action = new PrivilegedAction<Void>() {
                public Void run() {
                    //get the SID and auth token
                    //API_KEY, API_SECRET, ACCOUNT_SID
                    Twilio.init(getApiKey(), getApiSecret().getValue(), getAccountSid());
                    //System.out.println("sending to " + Arrays.toString(recipients));
                    // create the message
                    Message message = Message.creator(
                                    // get recipient number and apped +1
                                    new com.twilio.type.PhoneNumber("+1" + recipientNumber),
                                    // hard coded from number (this is our number purchased from tilwio)
                                    new com.twilio.type.PhoneNumber("+18336161505"),
                                    // pass in the body text
                                    body)
                            .create();
                    //print recipients in app director
                    System.out.println("SMS message sent to " + recipientNumber);
                    return null; // nothing to return
                }
            };
            AccessController.doPrivileged(action);
        }
    }
    // send acks?
    public BBoolean ackAlarm(BAlarmRecord alert) {
        try {
            return BBoolean.make(this.support.ackAlarm(alert));
        } catch (Exception var3) {
            return BBoolean.FALSE;
        }
    }

    @Override
    // set mobile icon
    public BIcon getIcon() {
        return BIcon.std("mobile.png");

    }
}

