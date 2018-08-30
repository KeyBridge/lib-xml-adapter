/*
 * Copyright 2018 Key Bridge. All rights reserved. Use is subject to license
 * terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any. Key Bridge reserves all rights in and to
 * Copyrights and no license is granted under Copyrights in this Software
 * License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request by sending an email to info@keybridgewireless.com.
 *
 * All information contained herein is the property of Key Bridge and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary.
 */
package ch.keybridge.xml.adapter.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import javax.xml.bind.annotation.*;

/**
 * A basic Xml compatible entry set DTO to support the [String, String] map Xml
 * adapter.
 *
 * @author Key Bridge
 * @since v1.6.0 added 08/30/18
 */
@XmlRootElement(name = "EntrySet")
@XmlType(name = "EntrySet")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntrySet implements Serializable {

  /**
   * The (sorted) list of entries.
   */
  @XmlElement(name = "Entry")
  private Collection<SimpleEntry> entries;

  public EntrySet() {
    entries = new TreeSet<>();
  }

  public EntrySet(Collection<SimpleEntry> entries) {
    this.entries = entries != null ? new TreeSet<>(entries) : new TreeSet<>();
  }

  public Collection<SimpleEntry> getEntries() {
    if (entries == null) {
      entries = new TreeSet<>();
    }
    return entries;
  }

  public void setEntries(List<SimpleEntry> entries) {
    this.entries = entries;
  }

  public void put(String key, String value) {
    entries.add(new SimpleEntry(key, value));
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 67 * hash + Objects.hashCode(this.entries);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final EntrySet other = (EntrySet) obj;
    if (!Objects.equals(this.entries, other.entries)) {
      return false;
    }
    return true;
  }

  /**
   * An Entry maintaining a key and a value. The value may be changed using the
   * {@code setValue} method. This class facilitates the process of building
   * custom map implementations. For example, it may be convenient to return
   * arrays of {@code SimpleEntry} instances in method
   * {@code Map.entrySet().toArray}.
   *
   * @since 1.6
   */
  @XmlType(name = "SimpleEntry")
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class SimpleEntry implements Serializable, Comparable<SimpleEntry> {

    private static final long serialVersionUID = -8499721149061103585L;
    /**
     * The entry key.
     */
    @XmlAttribute
    private String key;
    /**
     * The entry value.
     */
    @XmlValue
    private String value;

    public SimpleEntry(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    @Override
    public int compareTo(SimpleEntry o) {
      return key.compareTo(o.getKey());
    }

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 89 * hash + Objects.hashCode(this.key);
      return hash;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final SimpleEntry other = (SimpleEntry) obj;
      return Objects.equals(this.key, other.key);
    }

  }
}
