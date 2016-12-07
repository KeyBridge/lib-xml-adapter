/*
 * Copyright 2015 Caulfield IP Holdings (Caulfield) and affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Caulfield hereby
 * reserves all rights and copyrights and no license is
 * granted under said copyrights in this Software License Agreement.
 * Caulfield generally licenses software for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of these agreements may be obtained by sending a request
 * via email to info@caufield.org.
 */
package ch.keybridge.lib.xml.adapter;

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

  @Override
  public Certificate unmarshal(String v) throws Exception {
    CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    return certFactory.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(v)));
  }

  @Override
  public String marshal(Certificate v) throws Exception {
    return Base64.getEncoder().encodeToString(v.getEncoded());
  }
}
