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

import ch.keybridge.lib.xml.adapter.XmlMapDoubleAdapter.EntryList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal MAP instances of DOUBLE
 * value pairs. This produces a Sorted list of Entries. e.g.
 * <pre>
 * &lt;Entry id="0.2630339401223166"&gt;0.6184834691764691&lt;/Entry&gt;
 * &lt;Entry id="0.25640031536717633"&gt;0.13034743218391254&lt;/Entry&gt;
 * &lt;Entry id="0.14305560811040707"&gt;0.22700202552762183&lt;/Entry&gt;
 * &lt;Entry id="0.5152167837622237"&gt;0.007199533422655224&lt;/Entry&gt;
 * &lt;Entry id="0.10235824434661589"&gt;0.9195694909559805&lt;/Entry&gt;
 * &lt;Entry id="0.6330812449273229"&gt;0.00863123333069149&lt;/Entry&gt;
 * &lt;Entry id="0.19679123117283004"&gt;0.8488302839325784&lt;/Entry&gt;
 * </pre>
 *
 * @author Key Bridge LLC
 * @since 1.3.1 - created 06/02/16; Update 07/28/17 to ensure non-null
 * ArrayList.
 */
@XmlTransient
public class XmlMapDoubleAdapter extends XmlAdapter<EntryList, Map<Double, Double>> {

  @Override
  public Map<Double, Double> unmarshal(EntryList v) throws Exception {
    Map<Double, Double> hashMap = new TreeMap<>();
    for (EntryType myEntryType : v.getEntry()) {
      hashMap.put(myEntryType.id, myEntryType.value);
    }
    return hashMap;
  }

  @Override
  public EntryList marshal(Map<Double, Double> v) throws Exception {
    EntryList myMapType = new EntryList();
    for (Map.Entry<Double, Double> entry : v.entrySet()) {
      EntryType myMapEntryType = new EntryType();
      myMapEntryType.id = entry.getKey();
      myMapEntryType.value = entry.getValue();
      myMapType.getEntry().add(myMapEntryType);
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
    public Double id;

    @XmlValue
    public Double value;
  }
}
