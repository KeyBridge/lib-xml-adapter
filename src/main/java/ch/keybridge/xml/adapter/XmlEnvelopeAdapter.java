/*
 *  Copyright (C) 2014 Key Bridge and/or its affiliates.
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

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.locationtech.jts.geom.Envelope;

/**
 * XmlAdapter implementation to marshal and unmarshal Envelope class types.
 * <p>
 * Note that JTS {@code Envelope} class marshals as [Xmin, Xmax, Ymin, Ymax].
 * This marshals the format [Xmin, Ymin, Xmax, Ymax], which is used by GML and
 * WFS.
 *
 * @author Jesse Caulfield
 * @since 1.3.4 added 09/17/16
 */
public class XmlEnvelopeAdapter extends XmlAdapter<String, Envelope> {

  private static final DecimalFormat DF = new DecimalFormat("0.000000");

  /**
   * {@inheritDoc}
   * <p>
   * This marshals the format [Xmin, Ymin, Xmax, Ymax], which is used by GML and
   * WFS.
   */
  @Override
  public String marshal(Envelope v) throws Exception {
    return "ENV(" + DF.format(v.getMinX())
      + ", " + DF.format(v.getMinY())
      + ", " + DF.format(v.getMaxX())
      + ", " + DF.format(v.getMaxY()) + ")";
  }

  /**
   * {@inheritDoc}
   * <p>
   * Unmarshal the format [Xmin, Ymin, Xmax, Ymax], which is used by GML and
   * WFS.
   */
  @Override
  public Envelope unmarshal(String v) throws Exception {
    Pattern p = Pattern.compile("ENV\\((-?\\d+\\.\\d+)\\s*,\\s*(-?\\d+\\.\\d+)\\s*,\\s*(-?\\d+\\.\\d+)\\s*,\\s*(-?\\d+\\.\\d+)\\)");
//    Pattern p = Pattern.compile("ENV\\((-?\\d+\\.\\d+)\\s+,\\s+(-?\\d+\\.\\d+)\\s+,\\s+(-?\\d+\\.\\d+)\\s+,\\s+(-?\\d+\\.\\d+)\\s+,\\s+\\)");
    Matcher m = p.matcher(v);
    if (m.find()) {
      return new Envelope(Double.valueOf(m.group(1)),
                          Double.valueOf(m.group(3)),
                          Double.valueOf(m.group(2)),
                          Double.valueOf(m.group(4)));
    }
    System.out.println("ERROR - not found");
//    return v != null && !v.isEmpty() ? new WKTReader().read(v) : null;
    return null;
  }
}
