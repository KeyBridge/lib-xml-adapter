/*
 * Copyright 2018 Key Bridge.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.keybridge.lib.xml;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * A local copy of the ubiquitous TextUtility class of various static TEXT
 * Encoding and Decoding Utilities. Renamed to avoid conflict and reduced to
 * bare minimum needed methods.
 *
 * @author Jesse Caulfield
 * @since v1.0.0 2012 Caulfield
 * @since v1.6.0 copied to lib-xml-adapter 08/05/18
 */
public final class TextEncodingUtility {

  /**
   * The constructor is PRIVATE to ensure only STATIC methods are called from
   * this utility class.
   */
  private TextEncodingUtility() {
  }

  /**
   * Trim the input string to the maximum length.
   * <p>
   * Includes null and length check to prevent error. If the input string is
   * shorter than the maxLength value the original, unaltered string is
   * returned.
   *
   * @param string    The input string.
   * @param maxLength The desired maximum length. Must be a positive integer
   *                  greater than one.
   * @return the input string, trimmed to the desired value if required.
   */
  public static String trim(String string, int maxLength) {
    if (string == null) {
      return string;
    }
    return string.length() > maxLength ? string.substring(0, maxLength).trim() : string.trim();
  }

  /**
   * Encode a raw text string into URL-encoded text.
   * <p>
   * Encoding is a best-effort try so this method will ignore any text parameter
   * encoding errors.
   *
   * @param rawTextString the original, un-encoded string
   * @return a URL-encoded string
   */
  private static String encode(String rawTextString) {
    try {
      return URLEncoder.encode(rawTextString, "UTF-8");
    } catch (UnsupportedEncodingException | NullPointerException exception) {
      return "";
    }
  }

  /**
   * Decode URL-encoded text into a raw text string.
   * <p>
   * This method will silently ignore illegal hex characters in escape pattern,
   * such as (%). Decoding is a best-effort try so this method will ignore any
   * text parameter decoding errors; specifically if the string cannot be
   * decoded from the UTF8 character set.
   *
   * @param urlEncodedText the URL-encoded string
   * @return the original, un-encoded string
   */
  private static String decode(String urlEncodedText) {
    try {
      return URLDecoder.decode(urlEncodedText, "UTF-8");
    } catch (UnsupportedEncodingException | NullPointerException exception) {
      return "";
    }
  }

  /**
   * Encode a key / value map into a URI-encoded string.
   * <p>
   * Both Keys and Values must be generic, serializable objects.
   * <p>
   * During encoding the Key and Value {@code toString()} method is called on
   * each entry. Null values are replaced with an empty string.
   *
   * @param keyValueMap A map of {@code Serializable} key/value pairs.
   * @return null if the keyValueMap is null or empty.
   */
  public static String encodeKVMap(Map<? extends Object, ? extends Object> keyValueMap) {
    StringBuilder sb = new StringBuilder();
    if (keyValueMap != null && !keyValueMap.isEmpty()) {
      keyValueMap.entrySet().stream().forEach((entry) -> {
        /**
         * Append an ampersand if stringBuilder is not empty. This method is SQL
         * injection safe and will fail if the value contains a '%' character.
         */
        sb.append(sb.length() == 0 ? "" : "&")
          .append(encode(entry.getKey() != null ? entry.getKey().toString() : ""))
          .append("=")
          .append(encode(entry.getValue() != null ? entry.getValue().toString() : ""));
      });
      /**
       * The SubString method below strips an automatically prepended '?'
       * character from the URIBuilder query string
       */
      return sb.toString();
    } else {
      return null;
    }
  }

  /**
   * Decode a URI-encoded key value map into its constituents.
   * <p>
   * This method uses an internal TreeMap to ensure the key/value pairs are
   * always sorted by KEY.
   *
   * @param encodedMap a URL-encoded set of key/value pairs
   * @return non-null Map (TreeMap implementation)
   */
  public static Map<String, String> decodeKVMap(final String encodedMap) {
    /**
     * Clean the string of all non-essential characters.
     */
    Map<String, String> stringMap = new TreeMap<>();
    /**
     * Only process if the keyValueString is not empty
     */
    if (encodedMap != null && !encodedMap.isEmpty()) {
      /**
       * Strip the leading '?' if present. '?' is prepended by some URI
       * builders.
       */
      String trimmedString = encodedMap.startsWith("?")
                             ? encodedMap.substring(1)
                             : encodedMap;
      /**
       * Parse the URI-encoded string into a set of key/value pairs on '&', then
       * decode each key/value pair into a map entry.
       * <p>
       * Strip all non-word characters, including the leading '?' if present.
       * '?' is prepended by some URI builders. To accommodate numbers to not
       * strip the characters '+', '-', and '.'.
       */
      for (String keyValuePair : trimmedString.split("&")) {
        try {
          /**
           * Split the Key/Value pair on '=', then populate the map with the
           * (decoded) key and (decoded) value pairs (decoding again here is
           * important!)
           */
          String[] kv = decode(keyValuePair).split("=");
          stringMap.put(decode(kv[0]), decode(kv[1]));
        } catch (Exception ex) {
          //        Logger.getLogger(URIEncodeDecodeFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return stringMap;
  }

  /**
   * Encode a list of Strings into a standardized text string.
   * <p>
   * This method encodes each string into a UTF-8 character set to ensure it is
   * properly stored in the database.
   *
   * @param stringCollection a collection of strings.
   * @return An encoded string
   */
  public static String encodeCollection(Collection<String> stringCollection) {
    if (stringCollection == null || stringCollection.isEmpty()) {
      return null;
    }
    Set<String> stringSetUtf8 = new HashSet<>();
    stringCollection.stream().forEach((string) -> {
      stringSetUtf8.add(encode(string));
    });
    /**
     * Strip the first and last characters, which are open/close brackets "["
     * and "]". Strip space characters.
     */
    String string = stringSetUtf8.toString().replaceAll(",\\s+", ",").trim();
    return string.substring(1, string.length() - 1);
  }

  /**
   * Decode an encoded String into a list of Strings.
   * <p>
   * This method decodes each string from the UTF-8 character set to ensure it
   * is properly returned exactly as it was entered prior to persistence in the
   * database.
   *
   * @param encodedCollection The encoded string.
   * @return A non-null (possibly empty) ArrayList of Strings.
   */
  public static Collection<String> decodeCollection(final String encodedCollection) {
    Collection<String> stringSet = new TreeSet<>();
    if (encodedCollection == null || encodedCollection.isEmpty()) {
      return stringSet;
    }
    /**
     * Strip brackets off the input String.
     */
    String trimmedString = encodedCollection.startsWith("[") && encodedCollection.endsWith("]")
                           ? encodedCollection.substring(1, encodedCollection.length() - 1)
                           : encodedCollection;
    for (String token : trimmedString.split(",")) {
      stringSet.add(decode(token).trim());
    }
    return stringSet;
  }

}
