package org.ertuo.linliqin.util;

import java.security.SecureRandom;
import java.util.logging.Logger;

public class MyUID {
	private static final Logger m_log = Logger.getLogger(MyUID.class.getName());

	public MyUID() {
	}

	public String getUID() {
		String strRetVal = "";
		String strTemp = "";
		try {
			// Get IPAddress Segment
			/*
			 * InetAddress addr = InetAddress.getLocalHost();
			 * 
			 * byte[] ipaddr = addr.getAddress(); for (int i = 0; i <
			 * ipaddr.length; i++) { Byte b = new Byte(ipaddr[i]);
			 * 
			 * strTemp = Integer.toHexString(b.intValue() & 0x000000ff); while
			 * (strTemp.length() < 2) { strTemp = '0' + strTemp; } strRetVal +=
			 * strTemp; }
			 */

			strRetVal += ':';

			// Get CurrentTimeMillis() segment
			strTemp = Long.toHexString(System.currentTimeMillis());
			while (strTemp.length() < 12) {
				strTemp = '0' + strTemp;
			}
			strRetVal += strTemp + ':';

			// Get Random Segment
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

			strTemp = Integer.toHexString(prng.nextInt());
			while (strTemp.length() < 8) {
				strTemp = '0' + strTemp;
			}

			strRetVal += strTemp.substring(4) + ':';

			// Get IdentityHash() segment
			strTemp = Long.toHexString(System.identityHashCode((Object) this));
			while (strTemp.length() < 8) {
				strTemp = '0' + strTemp;
			}
			strRetVal += strTemp;

		} /*
		 * catch (java.net.UnknownHostException ex) {
		 * m_log.info("Unknown Host Exception Caught: " + ex.getMessage()); }
		 */catch (java.security.NoSuchAlgorithmException ex) {
			m_log
					.info("No Such Algorithm Exception Caught: "
							+ ex.getMessage());
		}

		return strRetVal.toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			long lngStart = System.currentTimeMillis();
			MyUID objUID = new MyUID();

			m_log.info(objUID.getUID());
			m_log.info("Elapsed time: "
					+ (System.currentTimeMillis() - lngStart));
		}
	}
}
