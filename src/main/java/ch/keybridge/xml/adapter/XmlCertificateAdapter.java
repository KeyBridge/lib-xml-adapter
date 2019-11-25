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

import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML Adapter to exchange X.509 Certificates.
 *
 * @author Key Bridge LLC
 * @since 1.2.2 - added 11/30/15 from lib-wsdba
 */
@XmlTransient
public class XmlCertificateAdapter extends XmlAdapter<String, Certificate> {

  /**
   * {@inheritDoc}
   * <p>
   * Generates a certificate object and initializes it with the data read from
   * the input stream inStream. In order to take advantage of the specialized
   * certificate format supported by this certificate factory, the returned
   * certificate object can be typecast to the corresponding certificate class.
   * For example, if this certificate factory implements X.509 certificates, the
   * returned certificate object can be typecast to the X509Certificate class.
   * In the case of a certificate factory for X.509 certificates, the
   * certificate provided in inStream must be DER-encoded and may be supplied in
   * binary or printable (Base64) encoding. If the certificate is provided in
   * Base64 encoding, it must be bounded at the beginning by -----BEGIN
   * CERTIFICATE-----, and must be bounded at the end by -----END
   * CERTIFICATE-----.
   */
  @Override
  public Certificate unmarshal(String v) throws Exception {
    CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    return certFactory.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(v)));
  }

  /**
   * {@inheritDoc}
   * <p>
   * Returns the Base64 encoded form of this certificate. It is assumed that
   * each certificate type would have only a single form of encoding; for
   * example, X.509 certificates would be encoded as ASN.1 DER.
   */
  @Override
  public String marshal(Certificate v) throws Exception {
    return Base64.getEncoder().encodeToString(v.getEncoded());
  }
}
