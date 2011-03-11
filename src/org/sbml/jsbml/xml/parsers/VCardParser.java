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

package org.sbml.jsbml.xml.parsers;

import org.sbml.jsbml.Annotation;
import org.sbml.jsbml.Creator;
import org.sbml.jsbml.SBMLDocument;

/**
 * A VCardParser is used to parser the subNodes of an annotation which have this
 * namespace URI : "http://www.w3.org/2001/vcard-rdf/3.0#".
 * 
 * @author marine
 * @since 0.8
 * @version $Rev$
 */
public class VCardParser implements ReadingParser {

	/**
	 * Boolean value to know if the 'N' node has been read.
	 */
	private boolean hasReadNNode = false;
	/**
	 * Boolean value to know if the 'Family' node has been read.
	 */
	private boolean hasReadFamilyName = false;
	/**
	 * Boolean value to know if the 'Given' node has been read.
	 */
	private boolean hasReadGivenName = false;
	/**
	 * Boolean value to know if the 'Orgname' node has been read.
	 */
	private boolean hasReadOrgName = false;
	/**
	 * Boolean value to know if the 'EMAIL' node has been read.
	 */
	private boolean hasReadEMAIL = false;

	/**
	 * Boolean value to know if the 'ORG' node has been read.
	 */
	private boolean hasReadORGNode = false;

	/**
	 * @return the namespaceURI
	 */
	public static String getNamespaceURI() {
		return Creator.URI_RDF_VCARD_NS;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.ReadingParser#processAttribute(String
	 * elementName, String attributeName, String value, String prefix, boolean
	 * isLastAttribute, Object contextObject)
	 */
	public void processAttribute(String elementName, String attributeName,
			String value, String prefix, boolean isLastAttribute,
			Object contextObject) {
		// TODO : There is no attribute with a namespace
		// "http://www.w3.org/2001/vcard-rdf/3.0#", SBML syntax error.
		// Throw an exception?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.ReadingParser#processCharactersOf(String
	 * elementName, String characters, Object contextObject)
	 */
	public void processCharactersOf(String elementName, String characters,
			Object contextObject) {

		// An elementName can be null if the text appears after a ending element
		// tag.
		if (elementName != null) {
			// A VCardParser can only modify a contextObject which is a
			// ModelCreator instance.
			if (contextObject instanceof Creator) {
				Creator modelCreator = (Creator) contextObject;

				// Sets the familyName String of modelCreator.
				if (elementName.equals("Family") && hasReadFamilyName) {
					modelCreator.setFamilyName(characters);
				}
				// Sets the givenName String of modelCreator.
				else if (elementName.equals("Given") && hasReadGivenName) {
					modelCreator.setGivenName(characters);
				}
				// Sets the email String of modelCreator.
				else if (elementName.equals("EMAIL") && hasReadEMAIL) {
					modelCreator.setEmail(characters);
				}
				// Sets the orgname String of modelCreator.
				else if (elementName.equals("Orgname") && hasReadOrgName) {
					modelCreator.setOrganisation(characters);
				} else {
					// TODO : SBML syntax error, throw an exception?
				}
			} else {
				// TODO : SBML syntax error, throw an exception?
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.ReadingParser#processEndDocument(SBMLDocument
	 * sbmlDocument)
	 */
	public void processEndDocument(SBMLDocument sbmlDocument) {
		hasReadEMAIL = false;
		hasReadFamilyName = false;
		hasReadGivenName = false;
		hasReadNNode = false;
		hasReadORGNode = false;
		hasReadOrgName = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.ReadingParser#processEndElement(String
	 * elementName, String prefix, boolean isNested, Object contextObject)
	 */
	public boolean processEndElement(String elementName, String prefix,
			boolean isNested, Object contextObject) {

		// A VCardParser can only modify a contextObject which is a ModelCreator
		// instance.
		if (contextObject instanceof Creator) {
			// End of a 'N' node, sets hasReadNNode, hasReadFamilyName and
			// hasReadGivenName to false.
			if (elementName.equals("N")) {
				hasReadNNode = false;
				hasReadFamilyName = false;
				hasReadGivenName = false;
			}
			// End of a 'EMAIL' node, sets hasReadEMAIL to false.
			else if (elementName.equals("EMAIL")) {
				hasReadEMAIL = false;
			}
			// End of a 'ORG' node, sets hasReadORGNode, hasReadOrgName.
			else if (elementName.equals("ORG")) {
				hasReadORGNode = false;
				hasReadOrgName = false;

			} else {
				// TODO : SBML syntax error, throw an exception?
			}
		} else {
			// TODO : SBML syntax error, throw an exception?
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.ReadingParser#processNamespace(String
	 * elementName, String URI, String prefix, String localName, boolean
	 * hasAttributes, boolean isLastNamespace, Object contextObject)
	 */
	public void processNamespace(String elementName, String URI, String prefix,
			String localName, boolean hasAttributes, boolean isLastNamespace,
			Object contextObject) {

		// The namespace of this parser should be declared in a 'RDF' subnode of
		// annotation.
		// Adds the namespace to RDFAnnotationNamespaces HashMap of annotation.
		if (elementName.equals("RDF") && contextObject instanceof Annotation) {
			Annotation annotation = (Annotation) contextObject;

			annotation.addRDFAnnotationNamespace(localName, prefix, URI);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.ReadingParser#processStartElement(String
	 * elementName, String prefix, boolean hasAttribute, boolean hasNamespaces,
	 * Object contextObject)
	 */
	public Object processStartElement(String elementName, String prefix,
			boolean hasAttribute, boolean hasNamespaces, Object contextObject) {

		// A VCardParser can only modify a contextObject which is a ModelCreator
		// instance.
		if (contextObject instanceof Creator) {
			// Reads the 'N' node.
			if (elementName.equals("N") && !hasReadNNode) {
				hasReadNNode = true;
			}
			// Reads the 'Family' node.
			else if (elementName.equals("Family") && hasReadNNode
					&& !hasReadFamilyName && !hasReadGivenName) {
				hasReadFamilyName = true;
			}
			// Reads the 'Given' node.
			else if (elementName.equals("Given") && hasReadNNode
					&& hasReadFamilyName && !hasReadGivenName) {
				hasReadGivenName = true;
			}
			// Reads the 'EMAIL' node.
			else if (elementName.equals("EMAIL") && !hasReadEMAIL) {
				hasReadEMAIL = true;
			}
			// Reads the 'ORG' node.
			else if (elementName.equals("ORG") && !hasReadORGNode) {
				hasReadORGNode = true;
			}
			// Reads the 'Orgname' node.
			else if (elementName.equals("Orgname") && hasReadORGNode
					&& !hasReadOrgName) {
				hasReadOrgName = true;
			} else {
				// TODO : SBML syntax error, throw an exception?
			}
		} else {
			// TODO : SBML syntax error, throw an exception?
		}
		return contextObject;
	}
}
