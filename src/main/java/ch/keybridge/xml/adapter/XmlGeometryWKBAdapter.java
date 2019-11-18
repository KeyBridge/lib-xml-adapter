/*
 *  Copyright (C) 2015 Key Bridge and/or its affiliates.
 *  All rights reserved. Use is subject to license terms.
 *
 *  Software Code is protected by Copyrights. Key Bridge hereby reserves
 *  all rights in and to Copyrights and no license is granted under
 *  Copyrights in this Software License Agreement. Caulfield generally
 *  licenses Copyrights for commercialization pursuant to the terms of
 *  either Caulfield's Standard Software Source Code License Agreement or
 *  Caulfield's Standard Product License Agreement.
 *
 *  A copy of either License Agreement can be obtained on request by email from:
 *  information@keybridgewireless.com.
 */
package ch.keybridge.xml.adapter;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal Geometry class types
 * to/from a binary array to a HEX binary data format.
 *
 * @author Jesse Caulfield
 * @since 02/01/15
 */
public class XmlGeometryWKBAdapter extends XmlAdapter<String, Geometry> {

  /**
   * Convert the MySQL proprietary WKB format to normal WKB format, then print
   * as a hexadecimal binary string.
   *
   * @param v the MySQL proprietary WKB format
   * @return normal WKB format as a hexadecimal binary string
   * @throws Exception never thrown, returns null on exception
   */
  @Override
  public String marshal(Geometry v) throws Exception {
    try {
      return v != null
             ? DatatypeConverter.printHexBinary(new WKBWriter(3).write(v))
             : null;
    } catch (Exception exception) {
      Logger.getLogger(XmlGeometryWKBAdapter.class.getSimpleName()).log(Level.SEVERE, "Error converting binary to geometry {0}", exception.getMessage());
      return null;
    }
  }

  /**
   * Convert a hexadecimal binary string to MySQL proprietary WKB format
   *
   * @param v a hexadecimal binary string
   * @return MySQL proprietary WKB format
   * @throws Exception Exception never thrown, returns null on exception
   */
  @Override
  public Geometry unmarshal(String v) throws Exception {
    try {
      return v != null
             ? new WKBReader().read(DatatypeConverter.parseHexBinary(v))
             : null;
    } catch (Exception exception) {
      Logger.getLogger(XmlGeometryWKBAdapter.class.getSimpleName()).log(Level.SEVERE, "Error converting geometry to binary {0}", exception.getMessage());
      return null;
    }
  }
}
