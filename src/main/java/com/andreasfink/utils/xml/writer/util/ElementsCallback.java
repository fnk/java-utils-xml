package com.andreasfink.utils.xml.writer.util;

import com.andreasfink.utils.xml.writer.Element;

/**
 * TODO shall we also have Element[] createElements(final T object)? then we could add comments.
 * this would require an abstract class on top of it
 * TODO use inflector (https://inflector.dev.java.net/) and abstract class to simplify callback
 * <p/>
 * <dependency>
 * <groupId>net.java.dev.inflector</groupId>
 * <artifactId>inflector</artifactId>
 * <version>0.7.0</version>
 * </dependency>
 * <p/>
 * © 2010 Andreas Fink
 *
 * @author af@andreasfink.com
 */
public interface ElementsCallback<T>
{

    public Element createElement(final T object);

}
