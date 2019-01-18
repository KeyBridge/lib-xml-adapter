/*
 * Copyright (C) 2019 Key Bridge
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.keybridge.lib.xml.adapter;

import java.util.Currency;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML converter for the java.util.Currency class.
 *
 * @author Key Bridge
 * @since v2.1.0 created 01/17/19
 */
public class XmlCurrencyConverter extends XmlAdapter<String, Currency> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Currency unmarshal(String v) throws Exception {
    return v == null ? null : Currency.getInstance(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Currency v) throws Exception {
    return v == null ? null : v.getCurrencyCode();
  }

}
