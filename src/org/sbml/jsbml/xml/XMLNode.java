/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2011 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.xml;

import static org.sbml.jsbml.JSBML.OPERATION_FAILED;
import static org.sbml.jsbml.JSBML.OPERATION_SUCCESS;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.xml.parsers.XMLNodeWriter;
import org.sbml.jsbml.xml.stax.SBMLReader;



/** 
 * Representation of a node in an XML document tree.
 * <p>
 * Beginning with version 1.0, jsbml implements an XML abstraction
 * layer, like libSBML. The basic data object in the XML abstraction is a
 * <em>node</em>, represented by {@link XMLNode}.
 * <p>
 * An {@link XMLNode} can contain any number of children.  Each child is another
 * {@link XMLNode}, thereby forming a tree.  The methods XMLNode.getNumChildren()
 * and {@link XMLNode#getChild(long n)}
 * can be used to access the tree structure starting from a given node.
 * <p>
 * <p>
 * Each {@link XMLNode} is subclassed from {@link XMLToken}, and thus has the same methods
 * available as {@link XMLToken}.  These methods include XMLToken.getNamespaces(),
 * XMLToken.getPrefix(), XMLToken.getName(), XMLToken.getURI(), and
 * XMLToken.getAttributes().
 * <p>
 * <h2>Conversion between an XML string and an {@link XMLNode}</h2>
 * <p>
 * jsbml provides the following utility functions for converting an XML string 
 * (e.g., &lt;annotation&gt;...&lt;/annotation&gt;) to/from an {@link XMLNode} object.
 * <ul>
 * <li> XMLNode.toXMLString() returns a string representation of the {@link XMLNode} object. 
 * <p>
 * <li> XMLNode.convertXMLNodeToString() (static function) returns a string representation 
 * of the given {@link XMLNode} object.
 * <p>
 * <li> XMLNode.convertStringToXMLNode() (static function) returns an {@link XMLNode} object converted 
 * from the given XML string.
 * </ul>
 * <p>
 * The returned {@link XMLNode} object by XMLNode.convertStringToXMLNode() is a dummy root 
 * (container) {@link XMLNode} if the given XML string has two or more top-level elements 
 * (e.g., '&lt;p&gt;...&lt;/p&gt;&lt;p&gt;...&lt;/p&gt;'). In the dummy root node, each top-level element in 
 * the given XML string is contained as a child {@link XMLNode}. XMLToken.isEOF() can be used 
 * to identify if the returned {@link XMLNode} object is a dummy node or not. 
 * Here is an example:
 * <div class='fragment'><pre>
// Checks if the returned {@link XMLNode} object by XMLNode.convertStringToXMLNode() is a dummy root node:

String str = '...';
{@link XMLNode} xn = {@link XMLNode}.convertStringToXMLNode(str);
if (xn == null)
{
  // returned value is null (error)
  ...
}
else if ( xn.isEOF() )
{
  // root node is a dummy node
  for (int i=0; i &lt; xn.getNumChildren(); i++)
  {
    // access to each child node of the dummy node.
    {@link XMLNode} xnChild = xn.getChild(i);
    ...
  }
}
else
{
  // root node is NOT a dummy node
  ...
}
</pre></div> 
 * <p>
 */
public class XMLNode extends XMLToken {

	// TODO : check that @jsbml.note is supported in java 1.6, otherwise use the command : javadoc -tag note:a:"Note:" ...

	private ArrayList<XMLNode> childrenElements = new ArrayList<XMLNode>();



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		XMLNode other = (XMLNode) obj;
		if (childrenElements == null) {
			if (other.childrenElements != null) {
				return false;
			}
		} else if (!childrenElements.equals(other.childrenElements)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
		* result
		+ ((childrenElements == null) ? 0 : childrenElements.hashCode());
		return result;
	}


	/**
	 * Creates a new empty {@link XMLNode} with no children.
	 */
	public XMLNode() {

	}


	/**
	 * Creates a new {@link XMLNode} by copying token.
	 * <p>
	 * @param token {@link XMLToken} to be copied to {@link XMLNode}
	 */
	public XMLNode(XMLToken token) {

		// TODO
	}


	/**
	 * Creates a new start element {@link XMLNode} with the given set of attributes and
	 * namespace declarations.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param attributes {@link XMLAttributes}, the attributes to set.
	 * @param namespaces {@link XMLNamespaces}, the namespaces to set.
	 * @param line a long integer, the line number (default = 0).
	 * @param column a long integer, the column number (default = 0).
	 */
	public XMLNode(XMLTriple triple, XMLAttributes attributes, XMLNamespaces namespaces, long line, long column) {		 
		super(triple, attributes, namespaces, line, column);
	}


	/**
	 * Creates a new start element {@link XMLNode} with the given set of attributes and
	 * namespace declarations.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param attributes {@link XMLAttributes}, the attributes to set.
	 * @param namespaces {@link XMLNamespaces}, the namespaces to set.
	 * @param line a long integer, the line number (default = 0).
	 * 
	 */
	public XMLNode(XMLTriple triple, XMLAttributes attributes, XMLNamespaces namespaces, long line) {
		super(triple, attributes, namespaces, line);	  }


	/**
	 * Creates a new start element {@link XMLNode} with the given set of attributes and
	 * namespace declarations.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param attributes {@link XMLAttributes}, the attributes to set.
	 * @param namespaces {@link XMLNamespaces}, the namespaces to set.
	 * 
	 */
	public XMLNode(XMLTriple triple, XMLAttributes attributes, XMLNamespaces namespaces) {
		super(triple, attributes, namespaces);
	}


	/**
	 * Creates a start element {@link XMLNode} with the given set of attributes.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param attributes {@link XMLAttributes}, the attributes to set.
	 * @param line a long integer, the line number (default = 0).
	 * @param column a long integer, the column number (default = 0).
	 * 
	 */
	public XMLNode(XMLTriple triple, XMLAttributes attributes, long line, long column) {
		super(triple, attributes, line, column);
	}


	/**
	 * Creates a start element {@link XMLNode} with the given set of attributes.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param attributes {@link XMLAttributes}, the attributes to set.
	 * @param line a long integer, the line number (default = 0).
	 * 
	 */
	public XMLNode(XMLTriple triple, XMLAttributes attributes, long line) {
		super(triple, attributes, line, 0);

	}


	/**
	 * Creates a start element {@link XMLNode} with the given set of attributes.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param attributes {@link XMLAttributes}, the attributes to set.
	 * 
	 */
	public XMLNode(XMLTriple triple, XMLAttributes attributes) {
		super(triple, attributes);
	}


	/**
	 * Creates an end element {@link XMLNode}.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param line a long integer, the line number (default = 0).
	 * @param column a long integer, the column number (default = 0).
	 * 
	 */
	public XMLNode(XMLTriple triple, long line, long column) {
		super(triple, line, column);
	}


	/**
	 * Creates an end element {@link XMLNode}.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * @param line a long integer, the line number (default = 0).
	 * 
	 */
	public XMLNode(XMLTriple triple, long line) {
		super(triple, line);
	}


	/**
	 * Creates an end element {@link XMLNode}.
	 * <p>
	 * @param triple {@link XMLTriple}.
	 * 
	 */
	public XMLNode(XMLTriple triple) {
		super(triple);
	}


	/**
	 * Creates a text {@link XMLNode}.
	 * <p>
	 * @param chars a string, the text to be added to the {@link XMLToken}
	 * @param line a long integer, the line number (default = 0).
	 * @param column a long integer, the column number (default = 0).
	 * 
	 */
	public XMLNode(String chars, long line, long column) {
		super(chars, line, column);
	}


	/**
	 * Creates a text {@link XMLNode}.
	 * <p>
	 * @param chars a string, the text to be added to the {@link XMLToken}
	 * @param line a long integer, the line number (default = 0).
	 * 
	 */
	public XMLNode(String chars, long line) {
		super(chars, line, 0);
	}


	/**
	 * Creates a text {@link XMLNode}.
	 * <p>
	 * @param chars a string, the text to be added to the {@link XMLToken}
	 * 
	 */
	public XMLNode(String chars) {
		super(chars);
	}


	/**
	 * Creates a copy of this {@link XMLNode}.
	 * <p>
	 * @param orig the {@link XMLNode} instance to copy.
	 */
	public XMLNode(XMLNode orig) {

		// TODO
	}


	/**
	 * Creates and returns a deep copy of this {@link XMLNode}.
	 * <p>
	 * @return a (deep) copy of this {@link XMLNode}.
	 */
	public XMLNode clone() {
		return new XMLNode(this);
	}


	/**
	 * Adds a child to this {@link XMLNode}.
	 * <p>
	 * The given <code>node</code> is added at the end of the list of children.
	 * <p>
	 * @param node the {@link XMLNode} to be added as child.
	 * <p>
	 * @return integer value indicating success/failure of the
	 * function.   The possible values
	 * returned by this function are:
	 * <li> OPERATION_SUCCESS
	 * <li> OPERATION_FAILED
	 * <p>
	 * @jsbml.note The given node is added at the end of the children list.
	 */
	public int addChild(XMLNode node) {

		if (node == null) {
			return OPERATION_FAILED;
		}
		
		if (isEnd()) {
			unsetEnd();
		}

		// TODO : there are more tests in libsbml XMLNode.cpp, check if we need them, like isEOF()

		childrenElements.add(node);

		return OPERATION_SUCCESS;
	}


	/**
	 * Inserts a node as the <code>n</code>th child of this
	 * {@link XMLNode}.
	 * <p>
	 * If the given index <code>n</code> is out of range for this {@link XMLNode} instance,
	 * the <code>node</code> is added at the end of the list of children.  Even in
	 * that situation, this method does not throw an error.
	 * <p>
	 * @param n an integer, the index at which the given node is inserted
	 * @param node an {@link XMLNode} to be inserted as <code>n</code>th child.
	 * <p>
	 * @return a reference to the newly-inserted child <code>node</code>
	 */
	public XMLNode insertChild(long n, XMLNode node) {
		
		if (node == null) {
			return node;
		}
		
		if (isEnd()) {
			unsetEnd();
		}
		if (n > getNumChildren() || n <= 0) {
		
			childrenElements.add(node);

		} else {
		
			childrenElements.add((int) n, node);
		}
		
		return node;
	}


	/**
	 * Removes the <code>n</code>th child of this {@link XMLNode} and returns the
	 * removed node.
	 * <p>
	 * It is important to keep in mind that a given {@link XMLNode} may have more
	 * than one child.  Calling this method erases all existing references to
	 * child nodes <em>after</em> the given position <code>n</code>.  If the index <code>n</code> is
	 * greater than the number of child nodes in this {@link XMLNode}, this method
	 * takes no action (and returns NULL).
	 * <p>
	 * @param n an integer, the index of the node to be removed
	 * <p>
	 * @return the removed child, or NULL if <code>n</code> is greater than the number
	 * of children in this node
	 * <p>
	 * @jsbml.note The caller owns the returned node and is responsible for deleting it.
	 */
	public XMLNode removeChild(long n) {
		
		if (n > getNumChildren() || n < 0) {
			return null;
		}
		
		return childrenElements.remove((int) n);
	}


	/**
	 * Removes all children from this node.
	 * @return integer value indicating success/failure of the
	 * function.   The possible values
	 * returned by this function are:
	 * <li> OPERATION_SUCCESS
	 */
	public int removeChildren() {

		childrenElements.clear();
		
		return OPERATION_SUCCESS;
	}


	/**
	 * Returns the <code>n</code>th child of this {@link XMLNode}.
	 * <p>
	 * If the index <code>n</code> is greater than the number of child nodes, or it is
	 * 0 or less, this method returns an empty node.
	 * <p>
	 * @param n the index of the node to return
	 * <p>
	 * @return the  <code>n</code>th child of this {@link XMLNode}.
	 */
	public XMLNode getChild(long n) {

		if (n > getNumChildren() || n < 0) {
			return new XMLNode();
		}

		return childrenElements.get((int) n);
	}


	/**
	 * Returns the number of children for this {@link XMLNode}.
	 * <p>
	 * @return the number of children for this {@link XMLNode}.
	 */
	public long getNumChildren() {
		return childrenElements.size();
	}


	/**
	 * Returns a string representation of this {@link XMLNode}. 
	 * <p>
	 * @return a string derived from this {@link XMLNode}.
	 */
	public String toXMLString() {
		return XMLNodeWriter.toXML(this);
	}


	/**
	 * Returns a string representation of a given {@link XMLNode}. 
	 * <p>
	 * @param node the {@link XMLNode} to be represented as a string
	 * <p>
	 * @return a string-form representation of <code>node</code>
	 */
	public static String convertXMLNodeToString(XMLNode node) {
		
		return node.toXMLString();
	}


	/**
	 * Returns an {@link XMLNode} which is derived from a string containing XML
	 * content.
	 * <p>
	 * The XML namespace must be defined using argument <code>xmlns</code> if the
	 * corresponding XML namespace attribute is not part of the string of the
	 * first argument.
	 * <p>
	 * @param xmlstr string to be converted to a XML node.
	 * @param xmlns {@link XMLNamespaces} the namespaces to set (default value is NULL). This argument is ignored at the moment.
	 * <p>
	 * @jsbml.note The caller owns the returned {@link XMLNode} and is reponsible for deleting it.
	 * The returned {@link XMLNode} object is a dummy root (container) {@link XMLNode} if the top-level 
	 * element in the given XML string is NOT <code>html</code>, <code>body</code>, <code>annotation</code>, <code>notes</code>.
	 * In the dummy root node, each top-level element in the given XML string is contained
	 * as a child {@link XMLNode}. XMLToken.isEOF() can be used to identify if the returned {@link XMLNode} 
	 * object is a dummy node.
	 * <p>
	 * @return a {@link XMLNode} which is converted from string <code>xmlstr</code>. NULL is returned
	 * if the conversion failed. 
	 * 
	 */
	public static XMLNode convertStringToXMLNode(String xmlstr, XMLNamespaces xmlns) {
		
		// TODO : check how to use the xmlns arguments inside the SBMLReader.readNotes.
		
		try {
			return new SBMLReader().readNotes(xmlstr);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * Returns an {@link XMLNode} which is derived from a string containing XML
	 * content.
	 * <p>
	 * The XML namespace must be defined using argument <code>xmlns</code> if the
	 * corresponding XML namespace attribute is not part of the string of the
	 * first argument.
	 * <p>
	 * @param xmlstr string to be converted to a XML node.
	 * @param xmlns {@link XMLNamespaces} the namespaces to set (default value is NULL).
	 * <p>
	 * @jsbml.note The caller owns the returned {@link XMLNode} and is reponsible for deleting it.
	 * The returned {@link XMLNode} object is a dummy root (container) {@link XMLNode} if the top-level 
	 * element in the given XML string is NOT <code>html</code>, <code>body</code>, <code>annotation</code>, <code>notes</code>.
	 * In the dummy root node, each top-level element in the given XML string is contained
	 * as a child {@link XMLNode}. XMLToken.isEOF() can be used to identify if the returned {@link XMLNode} 
	 * object is a dummy node.
	 * <p>
	 * @return a {@link XMLNode} which is converted from string <code>xmlstr</code>. NULL is returned
	 * if the conversion failed. 
	 * 
	 */
	public static XMLNode convertStringToXMLNode(String xmlstr) {
		try {
			return new SBMLReader().readNotes(xmlstr);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return null;
	}



}
