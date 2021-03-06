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
package ch.keybridge.xml;

import java.util.Base64;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class TestBase64 {

  @Test
  public void testBase64Test() {

    String token = "foo:bar";
    System.out.println("testBase64Test");
    String base64 = Base64.getEncoder().encodeToString(token.getBytes());
    System.out.println("  Token : " + token);
    System.out.println("  Base64: " + base64);
  }

}
