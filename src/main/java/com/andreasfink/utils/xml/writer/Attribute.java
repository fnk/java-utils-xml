package com.andreasfink.utils.xml.writer;

import com.andreasfink.utils.xml.writer.util.PCDataCodec;

/**
 * TODO handle namespaces
 * TODO also escape control-characters (only LF/NL?) and quotes
 *
 * © 2010 Andreas Fink
 * @author af@andreasfink.com
 *
 */
public class Attribute {

	private final String _name;
	private final String _value;

	public Attribute(final String name, final String value) {
		_name = name;
		_value = PCDataCodec.encodeAttribute(value);
	}

//	/* (non-Javadoc)
//	 * @see com.andreasfink.utils.xml.writer.Node#toXml()
//	 */
//	@Override
	public String toXml() {
		return _name.concat("=\"").concat(_value).concat("\"");
	}

}
