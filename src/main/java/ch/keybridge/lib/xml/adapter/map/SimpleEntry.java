/*
 * Copyright 2019 Key Bridge. All rights reserved. Use is subject to license
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
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * An Entry maintaining a key and a value. The value may be changed using the
 * {@code setValue} method. This class facilitates the process of building
 * custom map implementations. For example, it may be convenient to return
 * arrays of {@code SimpleEntry} instances in method
 * {@code Map.entrySet().toArray}.
 *
 * @since 1.6
 */
@XmlRootElement(name = "SimpleEntry")
@XmlType(name = "SimpleEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleEntry implements Serializable, Comparable<SimpleEntry> {

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

  public SimpleEntry() {
  }

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
