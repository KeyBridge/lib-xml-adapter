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

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.WKTReader;

import java.util.*;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal MAP instances of DOUBLE
 * value pairs to and from a "PATTERN" labeled WKT-encoded MULTIPOINT.
 *
 * @author Key Bridge LLC
 * @since WSIF 7.5.0 - created 11/28/15
 */
@XmlTransient
public class XmlRadiationPatternAdapter extends XmlAdapter<String, Map<Double, Double>> {

  /**
   * Unmarshal a text-encoded MULTIPOINT geometry into a Map of Double pairs.
   *
   * @param v a text-encoded MULTIPOINT geometry
   * @return a Map of Double pairs
   * @throws Exception if the geometry fails to parse
   */
  @Override
  public Map<Double, Double> unmarshal(String v) throws Exception {
    Geometry geometry = new WKTReader().read(v.replace("PATTERN", "MULTIPOINT"));
    TreeMap<Double, Double> treeMap = new TreeMap<>();
    if (geometry instanceof MultiPoint) {
      for (Coordinate coordinate : geometry.getCoordinates()) {
        treeMap.put(coordinate.x, coordinate.y);
      }
    }
    return treeMap;
  }

  /**
   * Marshal a Map of Double pairs into a JTS MULTIPOINT geometry WKT.
   *
   * @param v the antenna pattern
   * @return a text-encoded MULTIPOINT geometry
   * @throws Exception if the geometry fails to convert
   */
  @Override
  public String marshal(Map<Double, Double> v) throws Exception {
    /**
     * If the provided map is not sorted, enforce the natural ordering of keys
     */
    if (! (v instanceof SortedMap)) v = new TreeMap<>(v);

    Coordinate[] coordinates = new Coordinate[v.size()];
    int idx = 0;
    for (Map.Entry<Double, Double> entry : v.entrySet()) {
      coordinates[idx++] = new Coordinate(entry.getKey(), entry.getValue());
    }
    /**
     * Convert a Map of Double values into a JTS MULTIPOINT geometry. Applying
     * the Precision Model will trim the numbers (key and value) to 4 decimal
     * places. (Scale of 10^3 = 10000, where scale is the amount by which to
     * multiply a coordinate after subtracting the offset, to obtain a precise
     * coordinate).
     */
    return new GeometryFactory(new PrecisionModel(Math.pow(10, 3)))
            .createMultiPoint(coordinates)
            .toText()
            .replace("MULTIPOINT", "PATTERN");
  }
}
