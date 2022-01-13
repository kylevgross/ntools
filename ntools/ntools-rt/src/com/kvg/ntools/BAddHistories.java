package com.kvg.ntools;

import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.query.BQueryResult;
import javax.baja.sys.*;
import java.util.Date;
import javax.baja.history.ext.*;
import javax.baja.history.*;
import javax.baja.tag.*;
import javax.baja.util.BFormat;
import javax.baja.util.CloseableIterator;

@NiagaraType

@NiagaraProperty(
        name = "Status",
        type = "String",
        flags = Flags.SUMMARY | Flags.READONLY,
        defaultValue = "not running"
)
@NiagaraProperty(
        name = "historiesLastAdded",
        type = "String",
        flags = Flags.SUMMARY | Flags.READONLY,
        defaultValue = "never"
)
@NiagaraProperty(
        name = "NumberOfHistoriesGenerated",
        type = "int",
        flags = Flags.SUMMARY | Flags.READONLY,
        defaultValue = "0"
)


@NiagaraProperty(
        name = "HistoryNameFormat",
        type = "BFormat",
        flags = Flags.SUMMARY,
        defaultValue = "BFormat.make(\"%parent.parent.parent.name%_%parent.name%\")"
)
@NiagaraProperty(
        name = "COVHistoryNameFormat",
        type = "BFormat",
        flags = Flags.SUMMARY,
        defaultValue = "BFormat.make(\"%parent.parent.parent.name%_%parent.name%_COV\")"
)
@NiagaraProperty(
        name = "NumberOfNumericIntervalHistoriesForSensorsAndOutputs",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "500"
)
@NiagaraProperty(
        name = "CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs",
        type = "BRelTime",
        flags = Flags.SUMMARY,
        defaultValue = "BRelTime.make(0,0,15,0)"
)

@NiagaraProperty(
        name = "NumberOfNumericCOVHistoriesForOutputs",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "100"
)
@NiagaraProperty(
        name = "ChangeToleranceNumericCOVHistoriesForOutputs",
        type = "double",
        flags = Flags.SUMMARY,
        defaultValue = "1.0"
)

@NiagaraProperty(
        name = "NumberOfNumericIntervalHistoriesForSps",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "10"
)
@NiagaraProperty(
        name = "CollectionIntervalNumericIntervalHistoriesForSps",
        type = "BRelTime",
        flags = Flags.SUMMARY,
        defaultValue = "BRelTime.make(0,24,0,0)"
)

@NiagaraProperty(
        name = "NumberOfNumericCOVHistoriesForSps",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "100"
)
@NiagaraProperty(
        name = "ChangeToleranceNumericCOVHistoriesForSps",
        type = "double",
        flags = Flags.SUMMARY,
        defaultValue = "1.0"
)

@NiagaraProperty(
        name = "NumberOfBooleanCOVHistories",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "500"
)
@NiagaraProperty(
        name = "NumberOfBooleanIntervalHistories",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "10"
)

@NiagaraProperty(
        name = "CollectionIntervalBooleanIntervalHistories",
        type = "BRelTime",
        flags = Flags.SUMMARY,
        defaultValue = "BRelTime.make(0,24,0,0)"
)

@NiagaraProperty(
        name = "NumberOfEnumCOVHistories",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "500"
)
@NiagaraProperty(
        name = "NumberOfEnumIntervalHistories",
        type = "int",
        flags = Flags.SUMMARY,
        defaultValue = "10"
)
@NiagaraProperty(
        name = "CollectionIntervalEnumIntervalHistories",
        type = "BRelTime",
        flags = Flags.SUMMARY,
        defaultValue = "BRelTime.make(0,24,0,0)"
)

@NiagaraAction(
        name = "addHistoryExtensions"
)


public class BAddHistories extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.kvg.nTools.BAddHistories(1678063969)1.0$ @*/
/* Generated Mon Apr 13 11:42:47 BOT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "Status"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code Status} property.
   * @see #getStatus
   * @see #setStatus
   */
  public static final Property Status = newProperty(Flags.SUMMARY | Flags.READONLY, "not running", null);
  
  /**
   * Get the {@code Status} property.
   * @see #Status
   */
  public String getStatus() { return getString(Status); }
  
  /**
   * Set the {@code Status} property.
   * @see #Status
   */
  public void setStatus(String v) { setString(Status, v, null); }

////////////////////////////////////////////////////////////////
// Property "historiesLastAdded"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historiesLastAdded} property.
   * @see #getHistoriesLastAdded
   * @see #setHistoriesLastAdded
   */
  public static final Property historiesLastAdded = newProperty(Flags.SUMMARY | Flags.READONLY, "never", null);
  
  /**
   * Get the {@code historiesLastAdded} property.
   * @see #historiesLastAdded
   */
  public String getHistoriesLastAdded() { return getString(historiesLastAdded); }
  
  /**
   * Set the {@code historiesLastAdded} property.
   * @see #historiesLastAdded
   */
  public void setHistoriesLastAdded(String v) { setString(historiesLastAdded, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfHistoriesGenerated"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfHistoriesGenerated} property.
   * @see #getNumberOfHistoriesGenerated
   * @see #setNumberOfHistoriesGenerated
   */
  public static final Property NumberOfHistoriesGenerated = newProperty(Flags.SUMMARY | Flags.READONLY, 0, null);
  
  /**
   * Get the {@code NumberOfHistoriesGenerated} property.
   * @see #NumberOfHistoriesGenerated
   */
  public int getNumberOfHistoriesGenerated() { return getInt(NumberOfHistoriesGenerated); }
  
  /**
   * Set the {@code NumberOfHistoriesGenerated} property.
   * @see #NumberOfHistoriesGenerated
   */
  public void setNumberOfHistoriesGenerated(int v) { setInt(NumberOfHistoriesGenerated, v, null); }

////////////////////////////////////////////////////////////////
// Property "HistoryNameFormat"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code HistoryNameFormat} property.
   * @see #getHistoryNameFormat
   * @see #setHistoryNameFormat
   */
  public static final Property HistoryNameFormat = newProperty(Flags.SUMMARY, BFormat.make("%parent.parent.parent.name%_%parent.name%"), null);
  
  /**
   * Get the {@code HistoryNameFormat} property.
   * @see #HistoryNameFormat
   */
  public BFormat getHistoryNameFormat() { return (BFormat)get(HistoryNameFormat); }
  
  /**
   * Set the {@code HistoryNameFormat} property.
   * @see #HistoryNameFormat
   */
  public void setHistoryNameFormat(BFormat v) { set(HistoryNameFormat, v, null); }

////////////////////////////////////////////////////////////////
// Property "COVHistoryNameFormat"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code COVHistoryNameFormat} property.
   * @see #getCOVHistoryNameFormat
   * @see #setCOVHistoryNameFormat
   */
  public static final Property COVHistoryNameFormat = newProperty(Flags.SUMMARY, BFormat.make("%parent.parent.parent.name%_%parent.name%_COV"), null);
  
  /**
   * Get the {@code COVHistoryNameFormat} property.
   * @see #COVHistoryNameFormat
   */
  public BFormat getCOVHistoryNameFormat() { return (BFormat)get(COVHistoryNameFormat); }
  
  /**
   * Set the {@code COVHistoryNameFormat} property.
   * @see #COVHistoryNameFormat
   */
  public void setCOVHistoryNameFormat(BFormat v) { set(COVHistoryNameFormat, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfNumericIntervalHistoriesForSensorsAndOutputs"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfNumericIntervalHistoriesForSensorsAndOutputs} property.
   * @see #getNumberOfNumericIntervalHistoriesForSensorsAndOutputs
   * @see #setNumberOfNumericIntervalHistoriesForSensorsAndOutputs
   */
  public static final Property NumberOfNumericIntervalHistoriesForSensorsAndOutputs = newProperty(Flags.SUMMARY, 500, null);
  
  /**
   * Get the {@code NumberOfNumericIntervalHistoriesForSensorsAndOutputs} property.
   * @see #NumberOfNumericIntervalHistoriesForSensorsAndOutputs
   */
  public int getNumberOfNumericIntervalHistoriesForSensorsAndOutputs() { return getInt(NumberOfNumericIntervalHistoriesForSensorsAndOutputs); }
  
  /**
   * Set the {@code NumberOfNumericIntervalHistoriesForSensorsAndOutputs} property.
   * @see #NumberOfNumericIntervalHistoriesForSensorsAndOutputs
   */
  public void setNumberOfNumericIntervalHistoriesForSensorsAndOutputs(int v) { setInt(NumberOfNumericIntervalHistoriesForSensorsAndOutputs, v, null); }

////////////////////////////////////////////////////////////////
// Property "CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs} property.
   * @see #getCollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs
   * @see #setCollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs
   */
  public static final Property CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs = newProperty(Flags.SUMMARY, BRelTime.make(0,0,15,0), null);
  
  /**
   * Get the {@code CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs} property.
   * @see #CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs
   */
  public BRelTime getCollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs() { return (BRelTime)get(CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs); }
  
  /**
   * Set the {@code CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs} property.
   * @see #CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs
   */
  public void setCollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs(BRelTime v) { set(CollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfNumericCOVHistoriesForOutputs"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfNumericCOVHistoriesForOutputs} property.
   * @see #getNumberOfNumericCOVHistoriesForOutputs
   * @see #setNumberOfNumericCOVHistoriesForOutputs
   */
  public static final Property NumberOfNumericCOVHistoriesForOutputs = newProperty(Flags.SUMMARY, 100, null);
  
  /**
   * Get the {@code NumberOfNumericCOVHistoriesForOutputs} property.
   * @see #NumberOfNumericCOVHistoriesForOutputs
   */
  public int getNumberOfNumericCOVHistoriesForOutputs() { return getInt(NumberOfNumericCOVHistoriesForOutputs); }
  
  /**
   * Set the {@code NumberOfNumericCOVHistoriesForOutputs} property.
   * @see #NumberOfNumericCOVHistoriesForOutputs
   */
  public void setNumberOfNumericCOVHistoriesForOutputs(int v) { setInt(NumberOfNumericCOVHistoriesForOutputs, v, null); }

////////////////////////////////////////////////////////////////
// Property "ChangeToleranceNumericCOVHistoriesForOutputs"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ChangeToleranceNumericCOVHistoriesForOutputs} property.
   * @see #getChangeToleranceNumericCOVHistoriesForOutputs
   * @see #setChangeToleranceNumericCOVHistoriesForOutputs
   */
  public static final Property ChangeToleranceNumericCOVHistoriesForOutputs = newProperty(Flags.SUMMARY, 1.0, null);
  
  /**
   * Get the {@code ChangeToleranceNumericCOVHistoriesForOutputs} property.
   * @see #ChangeToleranceNumericCOVHistoriesForOutputs
   */
  public double getChangeToleranceNumericCOVHistoriesForOutputs() { return getDouble(ChangeToleranceNumericCOVHistoriesForOutputs); }
  
  /**
   * Set the {@code ChangeToleranceNumericCOVHistoriesForOutputs} property.
   * @see #ChangeToleranceNumericCOVHistoriesForOutputs
   */
  public void setChangeToleranceNumericCOVHistoriesForOutputs(double v) { setDouble(ChangeToleranceNumericCOVHistoriesForOutputs, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfNumericIntervalHistoriesForSps"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfNumericIntervalHistoriesForSps} property.
   * @see #getNumberOfNumericIntervalHistoriesForSps
   * @see #setNumberOfNumericIntervalHistoriesForSps
   */
  public static final Property NumberOfNumericIntervalHistoriesForSps = newProperty(Flags.SUMMARY, 10, null);
  
  /**
   * Get the {@code NumberOfNumericIntervalHistoriesForSps} property.
   * @see #NumberOfNumericIntervalHistoriesForSps
   */
  public int getNumberOfNumericIntervalHistoriesForSps() { return getInt(NumberOfNumericIntervalHistoriesForSps); }
  
  /**
   * Set the {@code NumberOfNumericIntervalHistoriesForSps} property.
   * @see #NumberOfNumericIntervalHistoriesForSps
   */
  public void setNumberOfNumericIntervalHistoriesForSps(int v) { setInt(NumberOfNumericIntervalHistoriesForSps, v, null); }

////////////////////////////////////////////////////////////////
// Property "CollectionIntervalNumericIntervalHistoriesForSps"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code CollectionIntervalNumericIntervalHistoriesForSps} property.
   * @see #getCollectionIntervalNumericIntervalHistoriesForSps
   * @see #setCollectionIntervalNumericIntervalHistoriesForSps
   */
  public static final Property CollectionIntervalNumericIntervalHistoriesForSps = newProperty(Flags.SUMMARY, BRelTime.make(0,24,0,0), null);
  
  /**
   * Get the {@code CollectionIntervalNumericIntervalHistoriesForSps} property.
   * @see #CollectionIntervalNumericIntervalHistoriesForSps
   */
  public BRelTime getCollectionIntervalNumericIntervalHistoriesForSps() { return (BRelTime)get(CollectionIntervalNumericIntervalHistoriesForSps); }
  
  /**
   * Set the {@code CollectionIntervalNumericIntervalHistoriesForSps} property.
   * @see #CollectionIntervalNumericIntervalHistoriesForSps
   */
  public void setCollectionIntervalNumericIntervalHistoriesForSps(BRelTime v) { set(CollectionIntervalNumericIntervalHistoriesForSps, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfNumericCOVHistoriesForSps"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfNumericCOVHistoriesForSps} property.
   * @see #getNumberOfNumericCOVHistoriesForSps
   * @see #setNumberOfNumericCOVHistoriesForSps
   */
  public static final Property NumberOfNumericCOVHistoriesForSps = newProperty(Flags.SUMMARY, 100, null);
  
  /**
   * Get the {@code NumberOfNumericCOVHistoriesForSps} property.
   * @see #NumberOfNumericCOVHistoriesForSps
   */
  public int getNumberOfNumericCOVHistoriesForSps() { return getInt(NumberOfNumericCOVHistoriesForSps); }
  
  /**
   * Set the {@code NumberOfNumericCOVHistoriesForSps} property.
   * @see #NumberOfNumericCOVHistoriesForSps
   */
  public void setNumberOfNumericCOVHistoriesForSps(int v) { setInt(NumberOfNumericCOVHistoriesForSps, v, null); }

////////////////////////////////////////////////////////////////
// Property "ChangeToleranceNumericCOVHistoriesForSps"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ChangeToleranceNumericCOVHistoriesForSps} property.
   * @see #getChangeToleranceNumericCOVHistoriesForSps
   * @see #setChangeToleranceNumericCOVHistoriesForSps
   */
  public static final Property ChangeToleranceNumericCOVHistoriesForSps = newProperty(Flags.SUMMARY, 1.0, null);
  
  /**
   * Get the {@code ChangeToleranceNumericCOVHistoriesForSps} property.
   * @see #ChangeToleranceNumericCOVHistoriesForSps
   */
  public double getChangeToleranceNumericCOVHistoriesForSps() { return getDouble(ChangeToleranceNumericCOVHistoriesForSps); }
  
  /**
   * Set the {@code ChangeToleranceNumericCOVHistoriesForSps} property.
   * @see #ChangeToleranceNumericCOVHistoriesForSps
   */
  public void setChangeToleranceNumericCOVHistoriesForSps(double v) { setDouble(ChangeToleranceNumericCOVHistoriesForSps, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfBooleanCOVHistories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfBooleanCOVHistories} property.
   * @see #getNumberOfBooleanCOVHistories
   * @see #setNumberOfBooleanCOVHistories
   */
  public static final Property NumberOfBooleanCOVHistories = newProperty(Flags.SUMMARY, 500, null);
  
  /**
   * Get the {@code NumberOfBooleanCOVHistories} property.
   * @see #NumberOfBooleanCOVHistories
   */
  public int getNumberOfBooleanCOVHistories() { return getInt(NumberOfBooleanCOVHistories); }
  
  /**
   * Set the {@code NumberOfBooleanCOVHistories} property.
   * @see #NumberOfBooleanCOVHistories
   */
  public void setNumberOfBooleanCOVHistories(int v) { setInt(NumberOfBooleanCOVHistories, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfBooleanIntervalHistories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfBooleanIntervalHistories} property.
   * @see #getNumberOfBooleanIntervalHistories
   * @see #setNumberOfBooleanIntervalHistories
   */
  public static final Property NumberOfBooleanIntervalHistories = newProperty(Flags.SUMMARY, 10, null);
  
  /**
   * Get the {@code NumberOfBooleanIntervalHistories} property.
   * @see #NumberOfBooleanIntervalHistories
   */
  public int getNumberOfBooleanIntervalHistories() { return getInt(NumberOfBooleanIntervalHistories); }
  
  /**
   * Set the {@code NumberOfBooleanIntervalHistories} property.
   * @see #NumberOfBooleanIntervalHistories
   */
  public void setNumberOfBooleanIntervalHistories(int v) { setInt(NumberOfBooleanIntervalHistories, v, null); }

////////////////////////////////////////////////////////////////
// Property "CollectionIntervalBooleanIntervalHistories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code CollectionIntervalBooleanIntervalHistories} property.
   * @see #getCollectionIntervalBooleanIntervalHistories
   * @see #setCollectionIntervalBooleanIntervalHistories
   */
  public static final Property CollectionIntervalBooleanIntervalHistories = newProperty(Flags.SUMMARY, BRelTime.make(0,24,0,0), null);
  
  /**
   * Get the {@code CollectionIntervalBooleanIntervalHistories} property.
   * @see #CollectionIntervalBooleanIntervalHistories
   */
  public BRelTime getCollectionIntervalBooleanIntervalHistories() { return (BRelTime)get(CollectionIntervalBooleanIntervalHistories); }
  
  /**
   * Set the {@code CollectionIntervalBooleanIntervalHistories} property.
   * @see #CollectionIntervalBooleanIntervalHistories
   */
  public void setCollectionIntervalBooleanIntervalHistories(BRelTime v) { set(CollectionIntervalBooleanIntervalHistories, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfEnumCOVHistories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfEnumCOVHistories} property.
   * @see #getNumberOfEnumCOVHistories
   * @see #setNumberOfEnumCOVHistories
   */
  public static final Property NumberOfEnumCOVHistories = newProperty(Flags.SUMMARY, 500, null);
  
  /**
   * Get the {@code NumberOfEnumCOVHistories} property.
   * @see #NumberOfEnumCOVHistories
   */
  public int getNumberOfEnumCOVHistories() { return getInt(NumberOfEnumCOVHistories); }
  
  /**
   * Set the {@code NumberOfEnumCOVHistories} property.
   * @see #NumberOfEnumCOVHistories
   */
  public void setNumberOfEnumCOVHistories(int v) { setInt(NumberOfEnumCOVHistories, v, null); }

////////////////////////////////////////////////////////////////
// Property "NumberOfEnumIntervalHistories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code NumberOfEnumIntervalHistories} property.
   * @see #getNumberOfEnumIntervalHistories
   * @see #setNumberOfEnumIntervalHistories
   */
  public static final Property NumberOfEnumIntervalHistories = newProperty(Flags.SUMMARY, 10, null);
  
  /**
   * Get the {@code NumberOfEnumIntervalHistories} property.
   * @see #NumberOfEnumIntervalHistories
   */
  public int getNumberOfEnumIntervalHistories() { return getInt(NumberOfEnumIntervalHistories); }
  
  /**
   * Set the {@code NumberOfEnumIntervalHistories} property.
   * @see #NumberOfEnumIntervalHistories
   */
  public void setNumberOfEnumIntervalHistories(int v) { setInt(NumberOfEnumIntervalHistories, v, null); }

////////////////////////////////////////////////////////////////
// Property "CollectionIntervalEnumIntervalHistories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code CollectionIntervalEnumIntervalHistories} property.
   * @see #getCollectionIntervalEnumIntervalHistories
   * @see #setCollectionIntervalEnumIntervalHistories
   */
  public static final Property CollectionIntervalEnumIntervalHistories = newProperty(Flags.SUMMARY, BRelTime.make(0,24,0,0), null);
  
  /**
   * Get the {@code CollectionIntervalEnumIntervalHistories} property.
   * @see #CollectionIntervalEnumIntervalHistories
   */
  public BRelTime getCollectionIntervalEnumIntervalHistories() { return (BRelTime)get(CollectionIntervalEnumIntervalHistories); }
  
  /**
   * Set the {@code CollectionIntervalEnumIntervalHistories} property.
   * @see #CollectionIntervalEnumIntervalHistories
   */
  public void setCollectionIntervalEnumIntervalHistories(BRelTime v) { set(CollectionIntervalEnumIntervalHistories, v, null); }

////////////////////////////////////////////////////////////////
// Action "addHistoryExtensions"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code addHistoryExtensions} action.
   * @see #addHistoryExtensions()
   */
  public static final Action addHistoryExtensions = newAction(0, null);
  
  /**
   * Invoke the {@code addHistoryExtensions} action.
   * @see #addHistoryExtensions
   */
  public void addHistoryExtensions() { invoke(addHistoryExtensions, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAddHistories.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/



    public void doAddHistoryExtensions() {

        setStatus("Running");

        BComponent comp;
        BHistoryConfig histConfig;

        int numberOfHistories = 0;
        BOrd neqlQuery = BOrd.make("neql: (n:type='control:NumericPoint' and sbs:sensor) or (n:type='control:NumericPoint' and sbs:cmd) or (n:type='control:NumericPoint' and hs:sensor) or (n:type='control:NumericPoint' and hs:cmd)  or (n:type='control:NumericWritable' and sbs:sensor) or (n:type='control:NumericWritable' and sbs:cmd) or (n:type='control:NumericWritable' and hs:sensor) or (n:type='control:NumericWritable' and hs:cmd)");
        BQueryResult result = (BQueryResult)neqlQuery.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results = result.getResults();
        setStatus("Generating Numeric Interval Histories For Sensors and Outputs");

        while(results.hasNext())


        {
        // found entities with the neql query
            comp = (BComponent)results.next();
            if (comp.getSlot("NumericInterval") == null)
            {
                BNumericIntervalHistoryExt hist = new BNumericIntervalHistoryExt();
                comp.add("NumericInterval", hist);
                hist.setHistoryName(getHistoryNameFormat());
                hist.setEnabled(true);
                hist.setInterval(getCollectionIntervalNumericIntervalHistoriesForSensorsAndOutputs());
                histConfig = (BHistoryConfig)hist.getHistoryConfig();
                histConfig.setCapacity(BCapacity.makeByRecordCount(getNumberOfNumericIntervalHistoriesForSensorsAndOutputs()));


                numberOfHistories++;
            }


        }
        setStatus("Numeric Histories for Sensors and Outputs Generated");

        BComponent comp6;
        BHistoryConfig histConfig6;
        BOrd neqlQuery6 = BOrd.make("neql: (n:type='control:NumericPoint' and sbs:sp) or (n:type='control:NumericPoint' and hs:sp) or (n:type='control:NumericWritable' and sbs:sp) or (n:type='control:NumericWritable' and hs:sp)");
        BQueryResult result6 = (BQueryResult)neqlQuery6.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results6 = result6.getResults();
        setStatus("Generating Numeric Interval Histories for SPs");

        while(results6.hasNext())


        {
            // found entities with the neql query
            comp6 = (BComponent)results6.next();
            if (comp6.getSlot("NumericInterval") == null)
            {
                BNumericIntervalHistoryExt hist6 = new BNumericIntervalHistoryExt();
                comp6.add("NumericInterval", hist6);
                hist6.setHistoryName(getHistoryNameFormat());
                hist6.setEnabled(true);
                hist6.setInterval(getCollectionIntervalNumericIntervalHistoriesForSps());
                histConfig6 = (BHistoryConfig)hist6.getHistoryConfig();
                histConfig6.setCapacity(BCapacity.makeByRecordCount(getNumberOfNumericIntervalHistoriesForSps()));


                numberOfHistories++;
            }


        }
        setStatus("Numeric Histories for SPs Generated");


        BComponent comp1;
        BHistoryConfig histConfig1;
        BOrd neqlQuery1 = BOrd.make("neql: (n:type='control:Boolean.*' and sbs:sensor) or (n:type='control:BooleanPoint' and sbs:cmd) or (n:type='control:BooleanPoint' and sbs:sp) or (n:type='control:BooleanPoint' and hs:sensor) or (n:type='control:BooleanPoint' and hs:cmd) or (n:type='control:BooleanPoint' and hs:sp) or (n:type='control:BooleanWritable' and sbs:sensor) or (n:type='control:BooleanWritable' and sbs:cmd) or (n:type='control:BooleanWritable' and sbs:sp) or (n:type='control:BooleanWritable' and hs:sensor) or (n:type='control:BooleanWritable' and hs:cmd) or (n:type='control:BooleanWritable' and hs:sp)");
        BQueryResult result1 = (BQueryResult)neqlQuery1.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results1 = result1.getResults();
        setStatus("Generating Boolean COV Histories");

        while(results1.hasNext())

        {
            // found entities with the neql query
            comp1 = (BComponent)results1.next();
            if (comp1.getSlot("BooleanCov") == null)
            {
                BBooleanCovHistoryExt hist1 = new BBooleanCovHistoryExt();
                comp1.add("BooleanCov", hist1);
                hist1.setHistoryName(getHistoryNameFormat());
                hist1.setEnabled(true);
                histConfig1 = (BHistoryConfig)hist1.getHistoryConfig();
                histConfig1.setCapacity(BCapacity.makeByRecordCount(getNumberOfBooleanCOVHistories()));
                numberOfHistories++;
            }


        }

        setStatus("Boolean COV Histories Generated");

        BComponent comp4;
        BHistoryConfig histConfig4;
        BOrd neqlQuery4 = BOrd.make("neql: (n:type='control:Boolean.*' and sbs:sensor) or (n:type='control:BooleanPoint' and sbs:cmd) or (n:type='control:BooleanPoint' and sbs:sp) or (n:type='control:BooleanPoint' and hs:sensor) or (n:type='control:BooleanPoint' and hs:cmd) or (n:type='control:BooleanPoint' and hs:sp) or (n:type='control:BooleanWritable' and sbs:sensor) or (n:type='control:BooleanWritable' and sbs:cmd) or (n:type='control:BooleanWritable' and sbs:sp) or (n:type='control:BooleanWritable' and hs:sensor) or (n:type='control:BooleanWritable' and hs:cmd) or (n:type='control:BooleanWritable' and hs:sp)");
        BQueryResult result4 = (BQueryResult)neqlQuery4.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results4 = result4.getResults();
        setStatus("Generating Boolean Interval Histories");

        while(results4.hasNext())

        {
            // found entities with the neql query
            comp4 = (BComponent)results4.next();

            if (comp4.getSlot("BooleanInterval") == null)
            {
                BBooleanIntervalHistoryExt hist4 = new BBooleanIntervalHistoryExt();
                comp4.add("BooleanInterval", hist4);
                hist4.setHistoryName(getHistoryNameFormat());
                hist4.setEnabled(true);
                hist4.setInterval(getCollectionIntervalBooleanIntervalHistories());
                histConfig4 = (BHistoryConfig)hist4.getHistoryConfig();
                histConfig4.setCapacity(BCapacity.makeByRecordCount(getNumberOfBooleanIntervalHistories()));
                numberOfHistories++;
            }


        }

        setStatus("Boolean Interval Histories Generated");

        BComponent comp2;
        BHistoryConfig histConfig2;
        BOrd neqlQuery2 = BOrd.make("neql: (n:type='control:EnumPoint' and sbs:sensor) or (n:type='control:EnumPoint' and sbs:cmd) or (n:type='control:EnumPoint' and sbs:sp) or (n:type='control:EnumPoint' and hs:sensor) or (n:type='control:EnumPoint' and hs:cmd) or (n:type='control:EnumPoint' and hs:sp) or (n:type='control:EnumWritable' and sbs:sensor) or (n:type='control:EnumWritable' and sbs:cmd) or (n:type='control:EnumWritable' and sbs:sp) or (n:type='control:EnumWritable' and hs:sensor) or (n:type='control:EnumWritable' and hs:cmd) or (n:type='control:EnumWritable' and hs:sp)");
        BQueryResult result2 = (BQueryResult)neqlQuery2.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results2 = result2.getResults();
        setStatus("Generating Enum COV Histories");

        while(results2.hasNext())
        {

        // found entities with the neql query
            comp2 = (BComponent)results2.next();
            if (comp2.getSlot("EnumCov") == null)
            {
                BEnumCovHistoryExt hist2 = new BEnumCovHistoryExt();
                comp2.add("EnumCov", hist2);
                hist2.setHistoryName(getCOVHistoryNameFormat());
                hist2.setEnabled(true);
                histConfig2 = (BHistoryConfig)hist2.getHistoryConfig();
                histConfig2.setCapacity(BCapacity.makeByRecordCount(getNumberOfEnumCOVHistories()));
                numberOfHistories++;
            }

        }
        setStatus("Enum COV Histories Generated");

        BComponent comp5;
        BHistoryConfig histConfig5;
        BOrd neqlQuery5 = BOrd.make("neql: (n:type='control:EnumPoint' and sbs:sensor) or (n:type='control:EnumPoint' and sbs:cmd) or (n:type='control:EnumPoint' and sbs:sp) or (n:type='control:EnumPoint' and hs:sensor) or (n:type='control:EnumPoint' and hs:cmd) or (n:type='control:EnumPoint' and hs:sp) or (n:type='control:EnumWritable' and sbs:sensor) or (n:type='control:EnumWritable' and sbs:cmd) or (n:type='control:EnumWritable' and sbs:sp) or (n:type='control:EnumWritable' and hs:sensor) or (n:type='control:EnumWritable' and hs:cmd) or (n:type='control:EnumWritable' and hs:sp)");
        BQueryResult result5 = (BQueryResult)neqlQuery5.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results5= result2.getResults();
        setStatus("Generating Enum Interval Histories");

        while(results5.hasNext())
        {

            // found entities with the neql query
            comp5 = (BComponent)results5.next();
            if (comp5.getSlot("EnumInterval") == null)
            {
                BEnumIntervalHistoryExt hist5 = new BEnumIntervalHistoryExt();
                comp5.add("EnumInterval", hist5);
                hist5.setHistoryName(getHistoryNameFormat());
                hist5.setEnabled(true);
                hist5.setInterval(getCollectionIntervalEnumIntervalHistories());
                histConfig5 = (BHistoryConfig)hist5.getHistoryConfig();
                histConfig5.setCapacity(BCapacity.makeByRecordCount(getNumberOfEnumIntervalHistories()));
                numberOfHistories++;
            }

        }
        setStatus("Enum Interval Histories Generated");

        BComponent comp3;
        BHistoryConfig histConfig3;
        BOrd neqlQuery3 = BOrd.make("neql: ((n:type='control:NumericPoint' or n:type='control:NumericWritable') and sbs:cmd) or ((n:type='control:NumericPoint' or n:type='control:NumericWritable') and hs:cmd)");
        BQueryResult result3 = (BQueryResult)neqlQuery3.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results3 = result3.getResults();
        setStatus("Generating Numeric COV Histories For Outputs");

        while(results3.hasNext())
        {

        // found entities with the neql query
            comp3 = (BComponent)results3.next();
            if (comp3.getSlot("NumericCov") == null)
            {
                BNumericCovHistoryExt hist3 = new BNumericCovHistoryExt();
                comp3.add("NumericCov", hist3);
                hist3.setHistoryName(getCOVHistoryNameFormat());
                hist3.setEnabled(true);
                hist3.setChangeTolerance(getChangeToleranceNumericCOVHistoriesForOutputs());
                histConfig3 = (BHistoryConfig)hist3.getHistoryConfig();
                histConfig3.setCapacity(BCapacity.makeByRecordCount(getNumberOfNumericCOVHistoriesForOutputs()));
                numberOfHistories++;
            }


        }
        setStatus("Nuermic COV Histories for Outputs Generated");

        BComponent comp7;
        BHistoryConfig histConfig7;
        BOrd neqlQuery7 = BOrd.make("neql: ((n:type='control:NumericPoint' or n:type='control:NumericWritable') and sbs:sp) or ((n:type='control:NumericPoint' or n:type='control:NumericWritable') and hs:sp)");
        BQueryResult result7 = (BQueryResult)neqlQuery7.resolve(Sys.getStation()).get();
        CloseableIterator<Entity> results7 = result7.getResults();
        setStatus("Generating Numeric COV Histories for SPs");

        while(results7.hasNext())
        {

            // found entities with the neql query
            comp7 = (BComponent)results7.next();
            if (comp7.getSlot("NumericCov") == null)
            {
                BNumericCovHistoryExt hist7 = new BNumericCovHistoryExt();
                comp7.add("NumericCov", hist7);
                hist7.setHistoryName(getCOVHistoryNameFormat());
                hist7.setEnabled(true);
                hist7.setChangeTolerance(getChangeToleranceNumericCOVHistoriesForSps());
                histConfig7 = (BHistoryConfig)hist7.getHistoryConfig();
                histConfig7.setCapacity(BCapacity.makeByRecordCount(getNumberOfNumericCOVHistoriesForSps()));
                numberOfHistories++;
            }


        }
        setStatus("Numeric COV Histories for SPs Generated");

        //wrap up by updating date and number of histories
        Date date = new Date();
        String currentDate = date.toString();
        setHistoriesLastAdded(currentDate);
        setNumberOfHistoriesGenerated(numberOfHistories);
        setStatus("Completed");

    }
    @Override
    public BIcon getIcon() {
        return BIcon.std("history.png");
    }

        }





