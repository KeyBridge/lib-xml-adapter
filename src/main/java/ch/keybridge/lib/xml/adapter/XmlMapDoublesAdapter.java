/*
 * Copyright 2015 Key Bridge and affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Key Bridge hereby
 * reserves all rights and copyrights and no license is
 * granted under said copyrights in this Software License Agreement.
 * Caulfield generally licenses software for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of these agreements may be obtained by sending a request
 * via email to information@keybridgewireless.com.
 */
package ch.keybridge.lib.xml.adapter;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.WKTReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @deprecated v1.4.0 - use XmlMapAdapter
 *
 * XmlAdapter implementation to marshal and unmarshal MAP instances of DOUBLE
 * value pairs.
 * <p>
 * This produces a MULTIPOINT encoded String representation of the values. e.g.  <pre>
 * DOUBLES ((0.2630339 0.6184835), (0.2564003 0.1303474), (0.1430556 0.227002), (0.5152168 0.0071995))
 * </pre>
 *
 * @author Key Bridge LLC
 * @since WSIF 7.4.0 - created 11/10/15, updated 06/02/16 replaces MULTIPOINT
 * label with DOUBLES
 */
@Deprecated
public class XmlMapDoublesAdapter extends XmlAdapter<String, Map<Double, Double>> {

  /**
   * Unmarshal a text-encoded MULTIPOINT geometry into a Map of Double pairs.
   *
   * @param v a text-encoded MULTIPOINT geometry
   * @return a Map of Double pairs
   * @throws Exception if the geometry fails to parse
   */
  @Override
  public Map<Double, Double> unmarshal(String v) throws Exception {
    Geometry geometry = new WKTReader().read(v.replace("DOUBLE", "MULTIPOINT"));
    Map<Double, Double> treeMap = new TreeMap<>();
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
    List<Coordinate> coordinates = new ArrayList<>();
    for (Map.Entry<Double, Double> entry : v.entrySet()) {
      coordinates.add(new Coordinate(entry.getKey(), entry.getValue()));
    }
    /**
     * Convert a Map of Double values into a JTS MULTIPOINT geometry. Applying
     * the Precision Model will trim the numbers (key and value) to 4 decimal
     * places. (Scale of 10^3 = 10000, where scale is the amount by which to
     * multiply a coordinate after subtracting the offset, to obtain a precise
     * coordinate).
     */
    return new GeometryFactory(new PrecisionModel(Math.pow(10, 6)))
      .createMultiPoint(coordinates.toArray(new Coordinate[coordinates.size()]))
      .toText().replace("MULTIPOINT", "DOUBLE");
  }
}
