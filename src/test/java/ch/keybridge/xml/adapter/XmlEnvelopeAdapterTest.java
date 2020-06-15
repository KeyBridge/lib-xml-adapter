/*
 * Copyright 2020 Key Bridge. All rights reserved. Use is subject to license
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

import org.junit.*;
import org.locationtech.jts.geom.*;

/**
 *
 * @author Key Bridge
 */
public class XmlEnvelopeAdapterTest {

  public XmlEnvelopeAdapterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testMarshal() throws Exception {

    Coordinate c = new Coordinate(-87.6540, 4.1230);
    Point p = new GeometryFactory(new PrecisionModel(Math.pow(10, 6))).createPoint(c);
    Geometry geometry = p.buffer(10);

    XmlEnvelopeAdapter adapter = new XmlEnvelopeAdapter();
    String env = adapter.marshal(geometry.getEnvelopeInternal());
    Envelope envelope = adapter.unmarshal(env);

    Assert.assertEquals(geometry.getEnvelopeInternal(), envelope);

    System.out.println("testMarshal OK ");

  }

}
