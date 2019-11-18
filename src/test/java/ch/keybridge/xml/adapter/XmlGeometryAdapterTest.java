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
package ch.keybridge.xml.adapter;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class XmlGeometryAdapterTest {

  public XmlGeometryAdapterTest() {
  }

  @Before
  public void setUp() {
  }

  @Test
  public void testSomeMethod() throws Exception {

    Point p = new Point(new Coordinate(123.321, 456.654), new PrecisionModel(), 0);

    XmlGeometryAdapter adapter = new XmlGeometryAdapter();

    System.out.println("  Point " + adapter.marshal(p));

    Assert.assertEquals(p.toText(), adapter.marshal(p));

  }

}
