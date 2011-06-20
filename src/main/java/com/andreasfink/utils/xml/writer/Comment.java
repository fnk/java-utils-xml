package com.andreasfink.utils.xml.writer;

/**
 * © 2010 Andreas Fink
 * @author af@andreasfink.com
 *
 */
public class Comment {

	private final String _comment;

	public Comment(final String comment) {
		_comment = comment;
	}

//	/* (non-Javadoc)
//	 * @see com.andreasfink.utils.xml.writer.Node#toXml()
//	 */
//	@Override
	public String toXml() {
		return "<!-- ".concat(_comment).concat(" -->");
	}

}
