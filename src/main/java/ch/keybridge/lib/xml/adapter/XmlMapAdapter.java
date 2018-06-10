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
package ch.keybridge.lib.xml.adapter;

import ch.keybridge.lib.xml.adapter.XmlMapAdapter.EntryList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal a generic MAP instance.
 * The MARSHAL function calls {@code toString} on the key/value entries. The
 * UNMARSHAL function returns a map of [String, String] values.
 *
 * @author Key Bridge LLC
 * @since v1.2.0 - added 06/10/18
 */
@XmlTransient
public class XmlMapAdapter extends XmlAdapter<EntryList, Map<Object, Object>> {

  @Override
  public Map<Object, Object> unmarshal(EntryList v) throws Exception {
    if (v == null) {
      return null;
    }
    HashMap<Object, Object> hashMap = new HashMap<>();
    for (EntryType myEntryType : v.getEntry()) {
      hashMap.put(myEntryType.key, myEntryType.value);
    }
    return hashMap;
  }

  @Override
  public EntryList marshal(Map<Object, Object> v) throws Exception {
    if (v == null) {
      return null;
    }
    EntryList myMapType = new EntryList();
    for (Entry<Object, Object> entry : v.entrySet()) {
      EntryType entryType = new EntryType();
      entryType.key = entry.getKey().toString();
      entryType.value = entry.getValue().toString();
      myMapType.getEntry().add(entryType);
    }
    return myMapType;
  }

  /**
   * A List of entries. Each entry is labeled "Entry".
   */
  public static class EntryList {

    @XmlElement(name = "Entry")
    List<EntryType> entry = new ArrayList<>();

    public List<EntryType> getEntry() {
      if (entry == null) {
        entry = new ArrayList<>();
      }
      return entry;
    }

  }

  /**
   * A entry type containing a String ID and a String value.
   */
  public static class EntryType {

    @XmlAttribute
    public String key;

    @XmlValue
    public String value;
  }
}
