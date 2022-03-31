/*
 * Copyright 2018 Tridium, Inc. All Rights Reserved.
 */
package com.kvg.ntools.ux;

import javax.baja.naming.BOrd;
import javax.baja.sys.BIcon;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.js.BJsBuild;

/**
 * The JavaScript Build for the GMaps Widget.
 *
 * @author Gareth Johnson
 */
public final class BGMapsWidgetBuild
    extends BJsBuild
{
  @SuppressWarnings("unused")
  public static final BGMapsWidgetBuild INSTANCE = new BGMapsWidgetBuild(
    "gmaps",
    new BOrd[] { BOrd.make("module://ntools/rc/gmaps.built.min.js") }
  );

  public static final Type TYPE = Sys.loadType(BGMapsWidgetBuild.class);

  @Override
  public Type getType() { return TYPE; }
  public BIcon getIcon() {
    return BIcon.std("web.png");

  }

  private BGMapsWidgetBuild(String id, BOrd[] files)
  {
    super(id, files);
  }
}
