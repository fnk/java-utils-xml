package com.andreasfink.utils.xml.writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.andreasfink.utils.xml.writer.util.ElementsCallback;
import com.andreasfink.utils.xml.writer.util.PCDataCodec;

/**
 *
 * © 2010 Andreas Fink
 * @author af@andreasfink.com
 *
 */
public class Element {

	private final StringBuilder _buffer;
	private final ArrayList<String> _attrBuffer;
	private final StringBuilder _childBuffer;

	private final String _name;

	public Element(final String name) {
		_name = name;
		_buffer = new StringBuilder();
		_attrBuffer = new ArrayList<String>(0);
		_childBuffer = new StringBuilder();
	}

	public Element addAttribute(final Attribute attribute) {
		_attrBuffer.add(attribute.toXml());

		return this;
	}

	public Element addChild(final Element element) {
		_childBuffer.append(element.toXml());

		return this;
	}

	// --
//	public Element add(final Node node) {
//		add(3f);
//		return this;
//	}
//	private void add(final int node) {
//
//	}
//	private void add(final float node) {
//
//	}
//	private void add(final Object node) {
//
//	}

	// --

	public <T> Element addChildren(final Collection<T> objects, final ElementsCallback<T> ecb) {
		for (final T object : objects) {
			final Element newElement = ecb.createElement(object);

			if (null!=newElement) {
				addChild(newElement);
			}
		}

		return this;
	}

	public <T> Element addChildren(final T[] objects,  final ElementsCallback<T> ecb) {
		final ArrayList<T> list = new ArrayList<T>(Arrays.asList(objects));

		return addChildren(list, ecb);
	}

	public Element addText(final String pcdata) {
		final String encoded = PCDataCodec.encode(pcdata);

		_childBuffer.append(encoded);

		return this;
	}

	public Element addCdata(final String cdata) {
		_childBuffer.append("<![CDATA[").append(cdata).append("]]>");

		return this;
	}

	public Element addChild(final Comment comment) {
		_childBuffer.append(comment.toXml());

		return this;
	}

//	/* (non-Javadoc)
//	 * @see com.andreasfink.utils.xml.writer.Node#toXml()
//	 */
//	@Override
	public String toXml() {
//		_buffer.append("<").append(_name);
		_buffer.append("\r\n<").append(_name);

		if (_attrBuffer.size()>0) {
			for (final String attribute : _attrBuffer) {
				_buffer.append(" ").append(attribute);
			}
		}

		if (_childBuffer.length() == 0) {
			_buffer.append("/>");
		} else {
			_buffer.append(">");
			_buffer.append(_childBuffer.toString());
			_buffer.append("</").append(_name).append(">");
		}

		return _buffer.toString();
	}

}
