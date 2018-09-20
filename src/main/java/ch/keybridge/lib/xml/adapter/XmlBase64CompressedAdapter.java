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
package ch.keybridge.lib.xml.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Base64 Text encoder and decoder using GZIP compression.
 * <p>
 * This XML adapter leverages the Java8 Base64 utility and supports the
 * following types of Base64 as specified in RFC 4648 and RFC 2045.
 * <p>
 * Developer note: This XML Adapter is NOT a transparent encoder and decoder.
 * This adapter adds a compression step when marshaling and a decompression step
 * when unmarshaling the data.
 * <p>
 * The employed encoding strategy is Basic: Uses "The Base64 Alphabet" as
 * specified in Table 1 of RFC 4648 and RFC 2045 for encoding and decoding
 * operation. The encoder does not add any line feed (line separator) character.
 * The decoder rejects data that contains characters outside the base64
 * alphabet.
 * <p>
 * Use the {@code XmlBaseAdapter} or {@code XmlBase64MimeAdapter} for
 * uncompressed Base64 conversion.
 *
 * @author Key Bridge LLC
 * @since 1.0.0 created 02/26/17
 */
public class XmlBase64CompressedAdapter extends XmlAdapter<String, byte[]> {

  /**
   * {@inheritDoc}
   * <p>
   * The encoded string sequence MUST represent a GZIP compressed byte array.
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
    byte[] byteArray = Base64.getMimeDecoder().decode(v);
    ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
    GZIPInputStream gzipStream = new GZIPInputStream(byteInputStream);
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    int numBytesRead;
    byte[] tempBytes = new byte[6000];
    while ((numBytesRead = gzipStream.read(tempBytes, 0, tempBytes.length)) != -1) {
      byteOutputStream.write(tempBytes, 0, numBytesRead);
    }
    return byteOutputStream.toByteArray();
  }

  /**
   * {@inheritDoc}
   * <p>
   * The out string sequence represents a GZIP compressed byte array.
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
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream);
    gzipStream.write(v);
    byteStream.close();
    gzipStream.close();
    return Base64.getMimeEncoder().encodeToString(byteStream.toByteArray());
  }

}
