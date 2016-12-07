/*
 * Copyright 2015 Caulfield IP Holdings (Caulfield) and affiliates. All rights
 * reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Caulfield hereby reserves all rights
 * and copyrights and no license is granted under said copyrights in this
 * Software License Agreement. Caulfield generally licenses software for
 * commercialization pursuant to the terms of either a Standard Software Source
 * Code License Agreement or a Standard Product License Agreement. A copy of
 * these agreements may be obtained by sending a request via email to
 * info@caufield.org.
 */
package ch.keybridge.lib.xml.adapter;

import java.security.KeyFactory;
import java.security.PrivateKey;
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

  @Override
  public PrivateKey unmarshal(String v) throws Exception {
    KeyFactory kf = KeyFactory.getInstance("DSA"); // or "EC" or whatever
    PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(v)));
    return privateKey;
  }

  @Override
  public String marshal(PrivateKey v) throws Exception {
    return Base64.getEncoder().encodeToString(v.getEncoded());
  }

}
