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
package ch.keybridge.xml.adapter.map;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * An Entry maintaining a Double key and a Double value. The value may be
 * changed using the {@code setValue} method. This class facilitates the process
 * of building custom map implementations. For example, it may be convenient to
 * return arrays of {@code SimpleEntry} instances in method
 * {@code Map.entrySet().toArray}.
 *
 * @since 3.0.1 created 03/12/19
 */
@XmlRootElement(name = "DoubleEntry")
@XmlType(name = "DoubleEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoubleEntry implements Serializable, Comparable<DoubleEntry> {

  /**
   * The entry key.
   */
  @XmlAttribute
  private Double key;
  /**
   * The entry value.
   */
  @XmlValue
  private Double value;

  public DoubleEntry() {
  }

  public DoubleEntry(Double key, Double value) {
    this.key = key;
    this.value = value;
  }

  public Double getKey() {
    return key;
  }

  public void setKey(Double key) {
    this.key = key;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  @Override
  public int compareTo(DoubleEntry o) {
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
    final DoubleEntry other = (DoubleEntry) obj;
    return Objects.equals(this.key, other.key);
  }

}
