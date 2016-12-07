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

import ch.keybridge.lib.xml.adapter.XmlMapStringAdapter.EntryList;
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
 * XmlAdapter implementation to marshal and unmarshal MAP instances of STRING
 * value pairs.
 *
 * @author Key Bridge LLC
 * @since WSIF 7.4.2 - created 11/17/15 supporting AExtensible
 * @since 1.3.1 - updated 06/02/16 to produce a list of Entries instead of
 * URL-encoded text. Update 07/28/17 to ensure non-null ArrayList.
 */
@XmlTransient
public class XmlMapStringAdapter extends XmlAdapter<EntryList, Map<String, String>> {

  @Override
  public Map<String, String> unmarshal(EntryList v) throws Exception {
    if (v == null) {
      return null;
    }
    HashMap<String, String> hashMap = new HashMap<>();
    for (EntryType myEntryType : v.getEntry()) {
      hashMap.put(myEntryType.key, myEntryType.value);
    }
    return hashMap;
  }

  @Override
  public EntryList marshal(Map<String, String> v) throws Exception {
    if (v == null) {
      return null;
    }
    EntryList myMapType = new EntryList();
    for (Entry<String, String> entry : v.entrySet()) {
      EntryType entryType = new EntryType();
      entryType.key = entry.getKey();
      entryType.value = entry.getValue();
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
