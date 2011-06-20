package com.andreasfink.utils.xml.writer.util;

/**
 * © 2010 Andreas Fink
 * @author af@andreasfink.com
 *
 */
public class PCDataCodec {

	private PCDataCodec() {}

	public static String encode(final String pcdata) {
		return pcdata
			.replace("&", "&amp;")
			.replace("<", "&lt;")
			.replace(">", "&gt;"); // this one is not strictly necessary but good style
	}

	public static String encodeAttribute(final String pcdata) {
		return pcdata
			.replace("&", "&amp;")
			.replace("<", "&lt;")
			.replace(">", "&gt;")
			.replace("\"", "&quot;"); // ' -> &apos; TODO control characters
	}

}
