/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.xml.adapter;

import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.util.Locale instance
 * and the language tag.
 *
 * @author Key Bridge LLC
 * @since 1.0.2 added 10/06/17
 *
 * @author jesse
 */
public class XmlLocaleAdapter extends XmlAdapter<String, Locale> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Locale unmarshal(String v) throws Exception {
    return v == null || v.trim().isEmpty() ? null : Locale.forLanguageTag(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Locale v) throws Exception {
    return v == null ? null : v.toLanguageTag();
  }

}
