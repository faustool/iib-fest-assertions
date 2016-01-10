package com.faustool.iib.assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

class XPathAssertNamespaceContext implements NamespaceContext {

	private final Map<String, String> prefixMap = new HashMap<>();
	private final Map<String, List<String>> uriMap = new HashMap<>();

	public XPathAssertNamespaceContext() {
		declare(XMLConstants.XML_NS_PREFIX, XMLConstants.XML_NS_URI);
		declare(XMLConstants.XMLNS_ATTRIBUTE, XMLConstants.XMLNS_ATTRIBUTE_NS_URI);
		clearDefaultNamespaceURI();
	}

	public void clearDefaultNamespaceURI() {
		declare(XMLConstants.DEFAULT_NS_PREFIX, XMLConstants.NULL_NS_URI);
	}

	public void setDefaultNamespaceURI(String defaultNamespaceURI) {
		declare(XMLConstants.DEFAULT_NS_PREFIX, defaultNamespaceURI);
	}

	public void declare(String prefix, String namespaceURI) {
		List<String> list = uriMap.get(namespaceURI);
		if (list == null) {
			list = new ArrayList<>();
			uriMap.put(namespaceURI, list);
		}
		list.add(prefix);
		prefixMap.put(prefix, namespaceURI);
	}

	public void reset() {
		uriMap.clear();
		prefixMap.clear();
		clearDefaultNamespaceURI();
	}

	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("The prefix cannot be null");
		}
		
		String namespaceURI = prefixMap.get(prefix);
		if (namespaceURI == null)
			return XMLConstants.NULL_NS_URI;
		else
			return namespaceURI;
	}

	@Override
	public String getPrefix(String namespaceURI) {
		if (namespaceURI == null) {
			throw new IllegalArgumentException("The namespaceURI cannot be null");
		}
		
		List<String> list = uriMap.get(namespaceURI);
		if (list != null && !list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator getPrefixes(String namespaceURI) {
		if (namespaceURI == null) {
			throw new IllegalArgumentException("The namespaceURI cannot be null");
		}
		
		List<String> list = uriMap.get(namespaceURI);
		if (list != null)
			return Collections.unmodifiableList(list).iterator();
		else
			return Collections.emptyIterator();
	}

}
