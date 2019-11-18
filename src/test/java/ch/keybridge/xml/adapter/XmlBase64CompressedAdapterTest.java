/*
 * Copyright 2019 Key Bridge. All rights reserved. Use is subject to license
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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class XmlBase64CompressedAdapterTest {

  public XmlBase64CompressedAdapterTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testCompressed() throws Exception {
    XmlBase64CompressedAdapter adapter = new XmlBase64CompressedAdapter();

    String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
    String base64 = adapter.marshal(loremIpsum.getBytes());

    Assert.assertNotNull(base64);
    Assert.assertTrue(base64.length() > 10);

    System.out.println("Original    size " + loremIpsum.length());

    System.out.println("Compressed  size " + base64.length());
    System.out.println("Base64: \n" + base64);

    byte[] bytes = adapter.unmarshal(base64);

    Assert.assertNotNull(bytes);
    Assert.assertTrue(bytes.length > 10);

    String reconstituted = new String(bytes);

    Assert.assertEquals(loremIpsum, reconstituted);
    System.out.println("XmlBase64CompressedAdapterTest OK");
  }

}
