package com.andreasfink.utils.xml.writer;

import org.custommonkey.xmlunit.Validator;

/**
 * TODO also outputstreams instead of stringbuilders/lists or nio filechannel's? look at woodstox/stax2
 * TODO pretty-printing?
 * TODO custom encoding does nothing right now. fix!
 * TODO currently, changes to nodes (Element, Attribute, ...) do not get reflected affer getting added to the parent
 *      use jgrapht in the background or jdbm+hashmap(google mapmaker)?
 * TODO maybe we can replace the whole thing with: http://javolution.org/target/site/apidocs/index.html it looks really good!
 *
 * This could be the start for a whole xml/html5/css infrastructure:
 * - Document-util that extends and deals with streams/buffers/encoding... also deals with the fluent interface
 * - integrate my html5-canvas
 * - Pi's
 * - getElementById() -> UUID
 * - typconverter for even more lazyness. steal from Jackson or Jaxb
 * - Make Element, Comment, Pi and Text implement Node?
 * - in the future also handle relax-ng or other schemas for validation
 *
 * © 2010 Andreas Fink
 * @author af@andreasfink.com
 *
 */
public class Document {

	private final StringBuilder _buffer;
	private final String _encoding;
	private final DTDeclaration _dtd;

	public Document() {
		this(null);
	}

	public Document(final DTDeclaration dtd) {
		this(dtd, System.getProperty("file.encoding"));
	}

	// TODO something jdk-builtin for encodings, better then strings?
	public Document(final DTDeclaration dtd, final String encoding) {
		_dtd = dtd;
		_buffer = new StringBuilder();
		_encoding = encoding;

		_buffer.append("<?xml version=\"1.0\" encoding=\"".concat(_encoding).concat("\"?>"));
		if (null != _dtd) {
			_buffer.append(_dtd.toXml());
		}
	}

	public Document addNode(final Element element) {
		_buffer.append(element.toXml());

		return this;
	}

	public Document addComment(final Comment comment) {
		_buffer.append(comment.toXml());

		return this;
	}

//	public Document add(final Node element) {
//		if (element instanceof Attribute) {
//			throw new IllegalArgumentException("You cannot add attributes to a Document-node");
//		}
//
//		_buffer.append(element.toXml());
//
//		return this;
//	}

//	/* (non-Javadoc)
//	 * @see com.andreasfink.utils.xml.writer.Node#toXml()
//	 */
//	@Override
	public String toXml() {
		return _buffer.toString();
	}

	// see: http://www.infoq.com/articles/xml-unit-test
	public boolean isValid() {
		final String dtdUri = Document.class.getResource("/dtd/".concat(_dtd.getUri())).toString();
		final Validator validator;
		try {
			validator = new Validator(toXml(), dtdUri);
		} catch (final Exception e) {
			throw new RuntimeException("could not validate", e);
		}

		return validator.isValid();
	}

}
