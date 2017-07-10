/*
 *  Copyright (C) 2015 Caulfield IP Holdings (Caulfield) and/or its affiliates.
 *  All rights reserved. Use is subject to license terms.
 *
 *  Software Code is protected by Caulfield Copyrights. Caulfield hereby reserves
 *  all rights in and to Caulfield Copyrights and no license is granted under
 *  Caulfield Copyrights in this Software License Agreement. Caulfield generally
 *  licenses Caulfield Copyrights for commercialization pursuant to the terms of
 *  either Caulfield's Standard Software Source Code License Agreement or
 *  Caulfield's Standard Product License Agreement.
 *
 *  A copy of either License Agreement can be obtained on request by email from:
 *  info@caufield.org.
 */
package ch.keybridge.lib.xml.adapter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ByteOrderValues;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal Geometry class types
 * to/from a binary array to a HEX binary data format.
 *
 * @author Jesse Caulfield
 * @since 02/01/15
 */
@XmlTransient
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
             ? DatatypeConverter.printHexBinary(new WKBWriter(2, ByteOrderValues.LITTLE_ENDIAN).write(v))
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
