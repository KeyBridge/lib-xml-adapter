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
package ch.keybridge.lib.xml.adapter.map;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
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

  public EntrySet(Map<String, String> map) {
    entries = new TreeSet(
      map.entrySet().stream()
        .filter(e -> e.getKey() != null)
        .filter(e -> !e.getKey().isEmpty())
        .map(e -> new SimpleEntry(String.valueOf(e.getKey()), String.valueOf(e.getValue())))
        .collect(Collectors.toList()));
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

  public Map<String, String> asMap() {
    return new TreeMap<>(entries.stream()
      .filter(e -> e.getKey() != null)
      .filter(e -> !e.getKey().isEmpty())
      .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
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
    return this.entries.containsAll(other.entries) && other.entries.containsAll(entries);
  }

}
