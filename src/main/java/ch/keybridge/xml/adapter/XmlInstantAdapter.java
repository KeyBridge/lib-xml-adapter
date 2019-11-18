/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.xml.adapter;

import java.text.ParseException;
import java.time.Instant;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.time.Instant instance
 * and the ISO 8601 Date format. The ISO instant formatter that formats or
 * parses an instant in UTC, such as '2011-12-03T10:15:30Z'.
 * <p>
 * This ISO date-time formatter that formats or parses a date-time without an
 * offset, such as '2011-12-03T10:15:30'. This returns an immutable formatter
 * capable of formatting and parsing the ISO-8601 extended offset date-time
 * format.
 *
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v3.0.2 created 04/26/19
 */
public class XmlInstantAdapter extends XmlAdapter<String, Instant> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Instant unmarshal(String v) throws ParseException {
    return v != null ? Instant.parse(v) : null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * An example output value would be, for example: "2001-10-26"
   */
  @Override
  public String marshal(Instant v) {
    return v != null ? v.toString() : null;
  }
}
