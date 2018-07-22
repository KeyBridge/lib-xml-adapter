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

import ch.keybridge.xml.adapter.XmlMapAdapter.EntryList;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal a generic MAP instance.
 * The MARSHAL function calls {@code String.valueOf()} on the key/value entries.
 * The UNMARSHAL function returns a map of [String, String] values.
 *
 * @author Key Bridge LLC
 * @since v1.2.0 - added 06/10/18
 */
@XmlTransient
public class XmlMapAdapter extends XmlAdapter<EntryList, Map<String, Serializable>> {

  private static final Logger LOGGER = Logger.getLogger(XmlMapAdapter.class.getName());

  /**
   * A map of XML adapters.
   */
  private static final Map<Class<?>, Class<?>> ADAPTERS;

  static {
    ADAPTERS = new HashMap<>();

    // java.lang
    ADAPTERS.put(Boolean.class, XmlBooleanAdapter.class);
    ADAPTERS.put(Double.class, XmlDoubleAdapter.class);
    ADAPTERS.put(Float.class, XmlFloatAdapter.class);
    ADAPTERS.put(Integer.class, XmlIntegerAdapter.class);
    ADAPTERS.put(Long.class, XmlLongAdapter.class);
    ADAPTERS.put(Short.class, XmlShortAdapter.class);

    // jts
    ADAPTERS.put(Envelope.class, XmlEnvelopeAdapter.class);
    ADAPTERS.put(Geometry.class, XmlGeometryAdapter.class);

    // java.util
    ADAPTERS.put(Date.class, XmlDateTimeAdapter.class);
    ADAPTERS.put(GregorianCalendar.class, XmlCalendarAdapter.class);
    ADAPTERS.put(Locale.class, XmlLocaleAdapter.class);
    ADAPTERS.put(TimeZone.class, XmlTimeZoneAdapter.class);

    // java.time
    ADAPTERS.put(ChronoUnit.class, XmlChronoUnitAdapter.class);
    ADAPTERS.put(Duration.class, XmlDurationAdapter.class);
    ADAPTERS.put(LocalDate.class, XmlLocalDateAdapter.class);
    ADAPTERS.put(LocalDateTime.class, XmlLocalDateTimeAdapter.class);
    ADAPTERS.put(Period.class, XmlPeriodAdapter.class);
    ADAPTERS.put(ZoneId.class, XmlZoneIdAdapter.class);
    ADAPTERS.put(ZonedDateTime.class, XmlZonedDateTimeAdapter.class);
  }

  /**
   * Internal method to scan the set of configured adapters for an
   * implementation that can handle the indicated class type.
   *
   * @param clazz the class type to transform
   * @return an adapter instance; null if none exist
   * @throws Exception if the adapter instance cannot be created
   */
  private XmlAdapter findAdapter(Class<?> clazz) throws Exception {
    for (Map.Entry<Class<?>, Class<?>> entry : ADAPTERS.entrySet()) {
      if (entry.getKey().isAssignableFrom(clazz)) {
        return (XmlAdapter) entry.getValue().newInstance();
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Parses a sequence of Entries into a HashMap.
   */
  @Override
  public Map<String, Serializable> unmarshal(EntryList entryList) throws Exception {
    if (entryList == null) {
      return null;
    }
    HashMap<String, Serializable> hashMap = new HashMap<>();
    entryList.getEntry().forEach((myEntryType) -> {
      try {
        /**
         * Try to find and use the appropriate adapter.
         */
        XmlAdapter adapter = findAdapter(Class.forName(myEntryType.clazz));
        if (adapter != null) {
          hashMap.put(myEntryType.key, (Serializable) adapter.unmarshal(myEntryType.value));
        } else {
          LOGGER.log(Level.FINE, "Unrecognized class type. No adapter for {0}", myEntryType.clazz);
          hashMap.put(myEntryType.key, myEntryType.value);
        }
      } catch (Exception ex) {
        LOGGER.log(Level.WARNING, "Unrecognized class type. Unmarshal from String failed for {0}", myEntryType.clazz);
      }
    });
    return hashMap;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Parses a HashMap into an ArrayList of Entries.
   */
  @Override
  public EntryList marshal(Map<String, Serializable> entryMap) throws Exception {
    if (entryMap == null) {
      return null;
    }
    EntryList myMapType = new EntryList();
    entryMap.entrySet().stream().map((entry) -> {
      /**
       * Transform the Entry to an EntryType.
       */
      MapEntryType entryType = new MapEntryType();
      entryType.key = entry.getKey();
      entryType.clazz = entry.getValue().getClass().getName();
      /**
       * If an adapter exists for the type then use it. Otherwise convert to
       * String.
       */
      try {
        XmlAdapter adapter = findAdapter(entry.getValue().getClass());
        if (adapter != null) {
          entryType.value = (String) adapter.marshal(entry.getValue());
        } else {
          LOGGER.log(Level.FINE, "Unrecognized class type. No adapter for {0}", entry.getValue().getClass());
          entryType.value = String.valueOf(entry.getValue());
        }
      } catch (Exception exception) {
        LOGGER.log(Level.WARNING, "Unexpected error unmarshaling {0} for value {1}.  {2}", new Object[]{entry.getValue().getClass(), entry.getValue(), exception.getMessage()});
        entryType.value = String.valueOf(entry.getValue());
      }

      return entryType;

    }).forEach((entryType) -> {
      /**
       * Add the new EntryType to the EntryList.
       */
      myMapType.getEntry().add(entryType);
    });

    return myMapType;
  }

  /**
   * A List of entries. Each entry is labeled "Entry".
   */
  @XmlRootElement(name = "MapEntrySet")
  @XmlType(name = "MapEntrySet")
  public static class EntryList implements Serializable {

    /**
     * A simple arrayList of MapEntryType containers.
     */
    @XmlElement(name = "Entry")
    Collection<MapEntryType> entry = new ArrayList<>();

    /**
     * Get the MapEntryType list.
     *
     * @return a non-null ArrayList
     */
    public Collection<MapEntryType> getEntry() {
      if (entry == null) {
        entry = new ArrayList<>();
      }
      return entry;
    }

  }

  /**
   * A entry type containing a String ID and a String value.
   */
  @XmlType(name = "MapEntryType")
  public static class MapEntryType {

    /**
     * The Map entry key index.
     */
    @XmlAttribute
    public String key;
    /**
     * The value class name.
     */
    @XmlAttribute
    public String clazz;
    /**
     * The Map entry value.
     */
    @XmlValue
    public String value;
  }
}
