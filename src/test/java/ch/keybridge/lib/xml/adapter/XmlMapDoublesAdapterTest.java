/*
 * Copyright 2016 Key Bridge.
 *
 * All rights reserved. Use is subject to license terms.
 * This software is protected by copyright.
 *
 * See the License for specific language governing permissions and
 * limitations under the License.
 */
package ch.keybridge.lib.xml.adapter;

import ch.keybridge.lib.xml.JaxbUtility;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.junit.Test;

/**
 *
 * @author Key Bridge LLC
 */
public class XmlMapDoublesAdapterTest {

  @Test
  public void testSomeMethod() throws Exception {
    // TODO review the generated test code and remove the default call to fail.

    TestContainer c = new TestContainer();
//    Map<Double, Double> dmap = new HashMap<>();

    Random r = new Random();

    for (int i = 0; i < 10; i++) {
      c.dmap.put(r.nextDouble(), r.nextDouble());
    }

    System.out.println("DMAP: " + JaxbUtility.marshal(c));

    XmlMapDoublesAdapter adapter = new XmlMapDoublesAdapter();
    String marshal = adapter.marshal(c.dmap);
    System.out.println("Marshal: " + marshal);
  }

  @XmlRootElement
  public static class TestContainer {

    @XmlJavaTypeAdapter(XmlMapDoublesAdapter.class)
    Map<Double, Double> dmap = new HashMap<>();

  }

}
