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

import java.util.Base64;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Base64 Text encoder and decoder. This XML adapter leverages the Java8 Base64
 * utility and supports the following types of Base64 as specified in RFC 4648
 * and RFC 2045.
 * <p>
 * The employed strategy is Basic: Uses "The Base64 Alphabet" as specified in
 * Table 1 of RFC 4648 and RFC 2045 for encoding and decoding operation. The
 * encoder does not add any line feed (line separator) character. The decoder
 * rejects data that contains characters outside the base64 alphabet.
 * <p>
 * Use the {@code XmlBase64MimeAdapter} for pretty-formatted Base64 output.
 *
 * @author Key Bridge LLC
 * @since 1.0.0 created 02/26/17
 */
public class XmlBase64Adapter extends XmlAdapter<String, byte[]> {

  /**
   * {@inheritDoc}
   *
   * @param v a Base64 encoded string sequence to decode
   * @return A newly-allocated byte array containing the decoded bytes.
   * @throws Exception on error
   */
  @Override
  public byte[] unmarshal(String v) throws Exception {
    /**
     * Decodes a Base64 encoded String into a newly-allocated byte array using
     * the Base64 encoding scheme. An invocation of this method has exactly the
     * same effect as invoking decode(src.getBytes(StandardCharsets.ISO_8859_1))
     */
    return Base64.getDecoder().decode(v);
  }

  /**
   * {@inheritDoc}
   *
   * @param v the byte array to encode
   * @return A String containing the resulting Base64 encoded characters
   * @throws Exception on error
   */
  @Override
  public String marshal(byte[] v) throws Exception {
    /**
     * Encodes the specified byte array into a String using the Base64 encoding
     * scheme.
     * <p>
     * This method first encodes all input bytes into a base64 encoded byte
     * array and then constructs a new String by using the encoded byte array
     * and the ISO-8859-1 charset. In other words, an invocation of this method
     * has exactly the same effect as invoking new String(encode(src),
     * StandardCharsets.ISO_8859_1).
     */
    return Base64.getEncoder().encodeToString(v);
  }

}
