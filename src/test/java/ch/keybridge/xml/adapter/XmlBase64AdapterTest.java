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

import ch.keybridge.xml.adapter.XmlBase64CompressedAdapter;
import ch.keybridge.xml.adapter.XmlBase64Adapter;
import junit.framework.TestCase;
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

    TestCase.assertNotNull(base64);
    TestCase.assertTrue(base64.length() > 10);

    System.out.println("Original size " + loremIpsum.length());

    System.out.println("Base64S  size " + base64.length());
    System.out.println("Base64: " + base64);

    byte[] bytes = adapter.unmarshal(base64);

    TestCase.assertNotNull(bytes);
    TestCase.assertTrue(bytes.length > 10);

    String reconstituted = new String(bytes);

    TestCase.assertTrue(loremIpsum.equals(reconstituted));

  }

  @Test
  public void testCompressed() throws Exception {

    XmlBase64CompressedAdapter adapter = new XmlBase64CompressedAdapter();

    String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
    String base64 = adapter.marshal(loremIpsum.getBytes());

    TestCase.assertNotNull(base64);
    TestCase.assertTrue(base64.length() > 10);

    System.out.println("Original    size " + loremIpsum.length());

    System.out.println("Compressed  size " + base64.length());
    System.out.println("Base64: \n" + base64);

    byte[] bytes = adapter.unmarshal(base64);

    TestCase.assertNotNull(bytes);
    TestCase.assertTrue(bytes.length > 10);

    String reconstituted = new String(bytes);

    TestCase.assertTrue(loremIpsum.equals(reconstituted));

  }

}
