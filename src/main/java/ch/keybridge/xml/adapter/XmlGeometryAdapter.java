/*
 *  Copyright (C) 2014 Caulfield IP Holdings (Caulfield) and/or its affiliates.
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
package ch.keybridge.xml.adapter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal Geometry class types.
 *
 * @author Jesse Caulfield
 */
public class XmlGeometryAdapter extends XmlAdapter<String, Geometry> {

  @Override
  public String marshal(Geometry v) throws Exception {
    /**
     * 09/01/2016 - Important. Must specify a 3D WKT Writer to output the
     * Z-component of the geometry. "toString()" and the default WKTWriter are
     * 2-dimensional and do not output the Z-component.
     */
    return v != null ? new WKTWriter(3).write(v) : null;
  }

  @Override
  public Geometry unmarshal(String v) throws Exception {
    return v != null && !v.isEmpty() ? new WKTReader().read(v) : null;
  }
}
