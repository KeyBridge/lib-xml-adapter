/*
 * Copyright 2016 Key Bridge.
 *
 * All rights reserved. Use is subject to license terms.
 * This software is protected by copyright.
 *
 * See the License for specific language governing permissions and
 * limitations under the License.
 */
package ch.keybridge.xml.adapter;

import ch.keybridge.xml.adapter.XmlDouble02PrecisionAdapter;
import ch.keybridge.xml.adapter.XmlDouble06PrecisionAdapter;
import junit.framework.TestCase;

/**
 *
 * @author Key Bridge LLC
 */
public class XmlPrecisionAdapter02Test extends TestCase {

  public XmlPrecisionAdapter02Test(String testName) {
    super(testName);
  }

  public void testAdapter02() throws Exception {

    XmlDouble02PrecisionAdapter adapter = new XmlDouble02PrecisionAdapter();

    Double d = 1219.0570068359375;
    String result = adapter.marshal(d);
    System.out.println(d + " rounded to two places " + result + " and back again " + adapter.unmarshal(result));

  }

  public void testAdapter06() throws Exception {

    XmlDouble06PrecisionAdapter adapter = new XmlDouble06PrecisionAdapter();

    Double d = 1219.0570068359375;
    String result = adapter.marshal(d);
    System.out.println(d + " rounded to six places " + result + " and back again " + adapter.unmarshal(result));

  }

}
