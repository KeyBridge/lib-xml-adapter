/*
 * Copyright 2015 - 2018 Key Bridge. All rights reserved. Use is subject to license
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

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML Adapter to exchange PRIVATE KEYs. Requires that keys use the {@code DSA}
 * algorithm.
 *
 * @author Key Bridge LLC
 * @since 1.2.2 - added 11/30/15 from lib-wsdba
 */
@XmlTransient
public class XmlPrivateKeyAdapter extends XmlAdapter<String, PrivateKey> {

  /**
   * {@inheritDoc} Try to parse the public key. Cycle through the most common /
   * expected key types: {"DSA", "RSA", "EC"}
   */
  @Override
  public PrivateKey unmarshal(String v) throws Exception {
    for (String algorithm : new String[]{"DSA", "RSA", "EC"}) {
      PrivateKey privateKey = parsePrivateKey(algorithm, v);
      if (privateKey != null) {
        return privateKey;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(PrivateKey v) throws Exception {
    return Base64.getEncoder().encodeToString(v.getEncoded());
  }

  /**
   * Internal method to safely try to parse a serialized private key.
   *
   * @param keyType        the key type. Expect one of {"DSA", "RSA", "EC"}
   * @param encodedKeySpec the encoded key
   * @return the parsed key or null on error
   */
  private PrivateKey parsePrivateKey(String keyType, String encodedKeySpec) {
    try {
      return KeyFactory.getInstance(keyType).generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedKeySpec)));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException noSuchAlgorithmException) {
      return null;
    }

  }

}
