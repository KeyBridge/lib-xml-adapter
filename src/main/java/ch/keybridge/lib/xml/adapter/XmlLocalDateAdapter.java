/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.time.LocalDate instance
 * and the ISO 8601 Date format.
 * <p>
 * The International Standard for the representation of dates and times is ISO
 * 8601. ISO 8601 describes an internationally accepted way to represent dates
 * and times using numbers.
 * <p>
 * ISO 8601 describes a large number of date/time formats. For example it
 * defines Basic Format, without punctuation, and Extended Format, with
 * punctuation, and it allows elements to be omitted. Different standards may
 * need different levels of granularity in the date and time.
 * <p>
 * This profile defines a complete date format according to the pattern
 * <code>YYYY-MM-DD</code>. For example, September 27, 2012 is represented as
 * 2012-09-27.
 *
 * @see
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/DateFormat.html">DateFormat</a>
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v1.1.1 added 10/22/15
 */
public class XmlLocalDateAdapter extends XmlAdapter<String, LocalDate> {

  /**
   * The ISO date formatter that formats or parses a date without an offset,
   * such as '2011-12-03'. This returns an immutable formatter capable of
   * formatting and parsing the ISO-8601 extended local date format. The format
   * consists of:
   * <ul>
   * <li>Four digits or more for the year. Years in the range 0000 to 9999 will
   * be pre-padded by zero to ensure four digits. Years outside that range will
   * have a prefixed positive or negative symbol.</li>
   * <li>A dash</li>
   * <li>Two digits for the month-of-year. This is pre-padded by zero to ensure
   * two digits.</li>
   * <li>A dash</li>
   * <li>Two digits for the day-of-month. This is pre-padded by zero to ensure
   * two digits.</li></ul>
   */
  private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

  /**
   * {@inheritDoc}
   */
  @Override
  public LocalDate unmarshal(String v) throws ParseException {
    return v != null ? LocalDate.parse(v, dateTimeFormatter) : null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * An example output value would be, for example: "2001-10-26"
   */
  @Override
  public String marshal(LocalDate v) {
    return v != null ? v.format(dateTimeFormatter) : null;
  }
}
