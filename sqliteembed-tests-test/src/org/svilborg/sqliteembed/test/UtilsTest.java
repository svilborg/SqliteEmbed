/**
 * 
 */
package org.svilborg.sqliteembed.test;

import org.svilborg.sqliteembed.utils.Utils;
import junit.framework.TestCase;

/**
 * Utils Tests
 */
public class UtilsTest extends TestCase {


	/**
	 * Test method for
	 * {@link org.svilborg.sqliteembed.utils.Utils#getNumerics(java.lang.String)}
	 * .
	 */
	public void testGetNumerics() {
		assertEquals("Digits", "01", Utils.getNumerics("update_0.1.sql"));
		assertEquals("Digits", "02", Utils.getNumerics("update_0.2.sql"));
		assertEquals("Digits", "12", Utils.getNumerics("update_1.2.sql"));
		assertEquals("Digits", "1290", Utils.getNumerics("12#@$%%&*CFB CXV`-=][p';lkjhgfds\\//.,mnbvcxSAS<>>{}{90"));
	}

}
