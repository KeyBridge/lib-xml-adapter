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

import java.time.ZoneId;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.time.ZoneId instance
 * and the time zone ID in the public tz database, otherwise known as the Olson
 * database.
 *
 * @author Key Bridge LLC
 * @since 1.0.2 added 10/06/17
 */
public class XmlZoneIdAdapter extends XmlAdapter<String, ZoneId> {

  /**
   * {@inheritDoc}
   */
  @Override
  public ZoneId unmarshal(String v) throws Exception {
    return ZoneId.of(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(ZoneId v) throws Exception {
    return v.getId();
  }

}
