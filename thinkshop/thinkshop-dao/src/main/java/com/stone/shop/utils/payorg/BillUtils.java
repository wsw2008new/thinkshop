package com.stone.shop.utils.payorg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import com.stone.shop.common.exception.SystemException;
import com.stone.shop.common.http.HttpClientUtil;
import com.stone.shop.common.utils.EncryptUtil;
import com.stone.shop.utils.logger.FuwuLogger;

public class BillUtils {

	private static Properties properties = new Properties();

	static {
		String tmpPath = TvPayUtils.class.getResource("/").getPath();
		FuwuLogger.info("-------------path="+tmpPath, TvPayUtils.class);
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(tmpPath
					+ "config/bill99.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getMerchantId() {
		return properties.getProperty("merchantId");
	}

	public static String getVersion() {
		return properties.getProperty("version");
	}

	public static String getPayUrl() {
		return properties.getProperty("pay_url");
	}

	public static String getTxnUrl() {
		return properties.getProperty("txnUrl");
	}

	public static String getQuertCardUrl() {
		return properties.getProperty("quertCardUrl");
	}

	public static String getBatchTxnUrl() {
		return properties.getProperty("batchtxnUrl");
	}

	public static String getRevocationUrl() {
		return properties.getProperty("revocation_url");
	}

	public static String getRefundUrl() {
		return properties.getProperty("refund_url");
	}

	public static String getQrySettlementUrl() {
		return properties.getProperty("qry_settlement_url");
	}

	public static String getQryConfirmUrl() {
		return properties.getProperty("qry_confirm_url");
	}

	public static String getMerchantLoginKey() {
		return properties.getProperty("merchantLoginKey");
	}

	public static String getCertFileName() {
		return properties.getProperty("certFileName");
	}

	public static String getCertPassword() {
		return properties.getProperty("certPassword");
	}

	public static String getDomainName() {
		return properties.getProperty("domainName");
	}

	public static int getSslPort() {
		String sslPort = properties.getProperty("sslPort");
		return Integer.parseInt(sslPort);
	}

	public static String getPublickey() {
		return properties.getProperty("publickey");
	}

	public static String getSettleMerchantId() {
		return properties.getProperty("settleMerchantId");
	}

	public static String getTerminalId() {
		return properties.getProperty("terminalId");
	}

	public static String getTr3Url() {
		String host = properties.getProperty("payReturnHost");
		return host + properties.getProperty("tr3_url");
	}

	public static String getKeyType() {
		return properties.getProperty("keytype");
	}

	public static String sendSslPost(String url, String xml)
			throws UnsupportedEncodingException, SystemException {
		String keyType = getKeyType();
		String keyPath = getCertFileName();
		String keyPassword = getCertPassword();
		int sslport = BillUtils.getSslPort();
		String authString = BillUtils.getMerchantId() + ":"
				+ BillUtils.getMerchantLoginKey();
		authString = "Basic " + EncryptUtil.base64Encode(authString);
		return HttpClientUtil.sslPostXml(url, xml, keyType, keyPath,
				keyPassword, sslport, authString);
	}

	public static boolean encodeByCert(String val, String msg) {
		boolean flag = false;
		InputStream is = null;
		try {
			is = new FileInputStream(getPublickey());
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
			PublicKey pk = cert.getPublicKey();
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(pk);
			sig.update(val.getBytes());
			Base64 decoder = new Base64();
			flag = sig.verify((byte[]) decoder.decode(msg));
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return flag;
	}

	public static boolean veriSign(byte[] data, byte[] signData)
			throws RuntimeException {
		InputStream is = null;
		try {
			is = new FileInputStream(getPublickey());
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Certificate cert = cf.generateCertificate(is);
			PublicKey pk = cert.getPublicKey();
			Signature sig = Signature.getInstance("SHA1WithRSA");
			byte[] signed = EncryptUtil.base64Decode(signData);
			sig.initVerify(pk);
			sig.update(data);
			return sig.verify(signed);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean veriSignForXml(String tr3Xml) throws RuntimeException {
		String dataBeforeSign = tr3Xml.replaceAll("<signature>.*</signature>",
				"");
		int beginIndex = tr3Xml.indexOf("<signature>");
		int endIndex = tr3Xml.indexOf("</signature>");
		String signData = tr3Xml.substring(beginIndex + 11, endIndex);
		try {
			return encodeByCert(dataBeforeSign, signData);
			// return veriSign(dataBeforeSign.getBytes("UTF-8"),
			// signData.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
