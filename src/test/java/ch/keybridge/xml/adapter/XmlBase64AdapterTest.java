/*
 * Copyright 2017 Key Bridge. All rights reserved.
 * Use is subject to license terms.
 *
 * Software Code is protected by Copyrights. Author hereby reserves all rights
 * in and to Copyrights and no license is granted under Copyrights in this
 * Software License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request from: info@keybridgewireless.com
 */
package ch.keybridge.xml.adapter;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Key Bridge LLC
 */
public class XmlBase64AdapterTest {

  public XmlBase64AdapterTest() {
  }

  @Test
  public void testBasic() throws Exception {

    XmlBase64Adapter adapter = new XmlBase64Adapter();

    String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
    String base64 = adapter.marshal(loremIpsum.getBytes());

    Assert.assertNotNull(base64);
    Assert.assertTrue(base64.length() > 10);

    System.out.println("Original size " + loremIpsum.length());

    System.out.println("Base64S  size " + base64.length());
    System.out.println("Base64: " + base64);

    byte[] bytes = adapter.unmarshal(base64);

    Assert.assertNotNull(bytes);
    Assert.assertTrue(bytes.length > 10);

    String reconstituted = new String(bytes);

    Assert.assertTrue(loremIpsum.equals(reconstituted));

    System.out.println("XmlBase64AdapterTest OK");
  }

}
