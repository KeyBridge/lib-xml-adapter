/*
 * Copyright 2015 Key Bridge and affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Key Bridge hereby
 * reserves all rights and copyrights and no license is
 * granted under said copyrights in this Software License Agreement.
 * Caulfield generally licenses software for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of these agreements may be obtained by sending a request
 * via email to information@keybridgewireless.com.
 */
package ch.keybridge.xml.adapter;

import java.util.TimeZone;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.util.TimeZone instance
 * and the time zone ID in the public tz database, otherwise known as the Olson
 * database.
 *
 * @author Key Bridge LLC
 * @since 1.2.2 created 11/18/15
 */
public class XmlTimeZoneAdapter extends XmlAdapter<String, TimeZone> {

  @Override
  public TimeZone unmarshal(String v) throws Exception {
    return TimeZone.getTimeZone(v);
  }

  @Override
  public String marshal(TimeZone v) throws Exception {
    return v.getID();
  }

}
