package com.andreasfink.utils.xml.writer;

/**
 * TODO howto handle internal declarations?
 *
 * © 2010 Andreas Fink
 * @author af@andreasfink.com
 *
 */
public class DTDeclaration {

	public enum Type {
		SYSTEM, PUBLIC
	}

	private final String _root;
	private final Type _type;
	private final String _uri;
	private final String _identifier;

	public DTDeclaration(final String rootElement, final Type type, final String uri) {
		this(rootElement, type, uri, null);
	}

	public DTDeclaration(final String rootElement, final Type type, final String uri, final String identifier) {
		_root = rootElement;
		_type = type;
		_uri = uri;
		_identifier = identifier;
	}

	String getUri() {
		return _uri;
	}

//	/* (non-Javadoc)
//	 * @see com.andreasfink.utils.xml.writer.Node#toXml()
//	 */
//	@Override
	public String toXml() {
		final StringBuilder buffer = new StringBuilder();

		buffer.append("<!DOCTYPE ")
			.append(_root)
			.append(" "+_type);

		if (_type.equals(Type.PUBLIC)) {
			buffer.append(" \"").append(_identifier).append("\"");
		}

		buffer.append(" \"").append(_uri).append("\"");
		buffer.append(">");

		return buffer.toString();
	}

}
