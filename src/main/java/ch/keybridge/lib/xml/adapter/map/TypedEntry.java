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
import javax.xml.bind.annotation.*;

/**
 * A entry type containing a String ID and a String value.
 */
@XmlType(name = "TypedEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypedEntry implements Serializable {

  /**
   * The Map entry key index.
   */
  @XmlAttribute
  public String key;
  /**
   * The value class name.
   */
  @XmlAttribute(name = "class")
  public String clazz;
  /**
   * The Map entry value.
   */
  @XmlValue
  public String value;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
