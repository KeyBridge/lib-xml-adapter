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

import ch.keybridge.xml.adapter.map.EntrySet;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal a [String, String] MAP
 * instance. T
 *
 * @author Key Bridge LLC
 * @since v1.2.0 - added 06/10/18
 */
public class XmlMapAdapter extends XmlAdapter<EntrySet, Map<String, String>> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, String> unmarshal(EntrySet v) throws Exception {
    return v == null ? null : v.asMap();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntrySet marshal(Map<String, String> v) throws Exception {
    return v == null ? null : new EntrySet(v);
  }

}
