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
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.*;

/**
 * A List of entries. Each entry is labeled "Entry".
 */
@XmlRootElement(name = "MapEntrySet")
@XmlType(name = "MapEntrySet")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapEntrySet implements Serializable {

  /**
   * A simple arrayList of MapEntryType containers.
   */
  @XmlElement(name = "Entry")
  private Collection<MapEntryType> entry;

  public MapEntrySet() {
    this.entry = new ArrayList<>();
  }

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