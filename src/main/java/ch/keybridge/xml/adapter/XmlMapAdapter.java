/*
 * Copyright 2015 Caulfield IP Holdings (Caulfield) and affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Caulfield hereby
 * reserves all rights and copyrights and no license is
 * granted under said copyrights in this Software License Agreement.
 * Caulfield generally licenses software for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of these agreements may be obtained by sending a request
 * via email to info@caufield.org.
 */
package ch.keybridge.xml.adapter;

import ch.keybridge.xml.adapter.map.EntrySet;
import java.util.Map;
import java.util.stream.Collectors;
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
    return v == null
           ? null
           : v.getEntries().stream()
        .collect(Collectors.toMap(e -> (String) e.getKey(),
                                  e -> (String) e.getValue()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntrySet marshal(Map<String, String> v) throws Exception {
    if (v == null) {
      return null;
    }
    return new EntrySet(v.entrySet().stream()
      .map(e -> new EntrySet.SimpleEntry(e.getKey(), e.getValue()))
      .collect(Collectors.toList()));
  }

}
