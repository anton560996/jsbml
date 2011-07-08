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

package org.sbml.jsbml;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.util.StringTools;

/**
 * Contains all the information about a creator of a {@link Model} (or other {@link SBase} in level
 * 3).
 * 
 * @author marine3
 * @author Andreas Dr&auml;ger
 * @since 0.8
 * @version $Rev$
 */
public class Creator extends AnnotationElement {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -3403463908044292946L;
	
	/**
	 * URI for the RDF syntax name space definition for VCards.
	 */
	public static final transient String URI_RDF_VCARD_NS = "http://www.w3.org/2001/vcard-rdf/3.0#";
	
	/**
	 * email of the creator
	 */
	private String email;
	/**
	 * Family name of the creator
	 */
	private String familyName;
	/**
	 * Given name of the creator
	 */
	private String givenName;

	/**
	 * Organisation name of the creator.
	 */
	private String organisation;

	// private String otherXMLInformation;

	/**
	 * Creates a {@link Creator} instance. By default, the email, familyName,
	 * givenName, organisation are null.
	 */
	public Creator() {
		this(null, null, null, null);
	}
	
	/**
	 * Creates a {@link Creator} instance. 
	 * 
	 * @param givenName
	 * @param familyName
	 * @param organization
	 * @param email
	 */
	public Creator(String givenName, String familyName, String organization, String email) {
		this.givenName = givenName;
		this.familyName = familyName;
		this.organisation = organization;
		this.email = email;
		// this.otherXMLInformation = null;
	}

	/**
	 * Creates a {@link Creator} instance from a given {@link Creator}.
	 * 
	 * @param modelCreator
	 */
	public Creator(Creator modelCreator) {
		if (modelCreator.isSetEmail()) {
			this.email = new String(modelCreator.getEmail());
		} else {
			this.email = null;
		}
		if (modelCreator.isSetFamilyName()) {
			this.familyName = new String(modelCreator.getFamilyName());
		} else {
			this.familyName = null;
		}
		if (modelCreator.isSetGivenName()) {
			this.givenName = new String(modelCreator.getGivenName());
		} else {
			this.givenName = null;
		}
		if (modelCreator.isSetOrganisation()) {
			this.organisation = new String(modelCreator.getOrganisation());
		} else {
			this.organisation = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Creator clone() {
		return new Creator(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object sb) {
		if (sb instanceof Creator) {
			Creator m = (Creator) sb;
			boolean equal = isSetEmail() == m.isSetEmail();
			if (equal && isSetEmail()) {
				equal &= getEmail().equals(m.getEmail());
			}
			equal &= isSetFamilyName() == m.isSetFamilyName();
			if (equal && isSetFamilyName()) {
				equal &= getFamilyName().equals(m.getFamilyName());
			}
			equal &= isSetGivenName() == m.isSetGivenName();
			if (equal && isSetGivenName()) {
				equal &= getGivenName().equals(m.getGivenName());
			}
			equal &= isSetOrganisation() == m.isSetOrganisation();
			if (equal && isSetOrganisation()) {
				equal &= getOrganisation().equals(m.getOrganisation());
			}
			return equal;
		}
		return false;
	}

	/**
	 * Returns the email from the {@link Creator}. Returns an empty String if it is
	 *         not set.
	 * 
	 * @return the email from the {@link Creator}. Returns an empty String if it is
	 *         not set.
	 */
	public String getEmail() {
		return isSetEmail() ? email : "";
	}

	/**
	 * Returns the familyName from the {@link Creator}. Returns an empty String if
	 *         it is not set.
	 * 
	 * @return the familyName from the {@link Creator}. Returns an empty String if
	 *         it is not set.
	 */
	public String getFamilyName() {
		return isSetFamilyName() ? familyName : "";
	}

	/**
	 * Returns the givenName from the {@link Creator}. Returns an empty String if
	 * it is not set.
	 * 
	 * @return the givenName from the {@link Creator}. Returns an empty String if
	 * it is not set.
	 */
	public String getGivenName() {
		return isSetGivenName() ? givenName : "";
	}

	/**
	 * Returns the organisation from the {@link Creator}. Returns an empty String
	 * if it is not set.
	 * 
	 * @return the organisation from the {@link Creator}. Returns an empty String
	 * if it is not set.
	 */
	public String getOrganisation() {
		return isSetOrganisation() ? organisation : "";
	}

	/**
	 * Returns the organisation from the {@link Creator}. Returns an empty String
	 * if it is not set.
	 * <p>
	 * Equal to {@link #getOrganisation()}
	 * 
	 * @return the organisation from the {@link Creator}. Returns an empty String
	 * if it is not set.
	 */
	public String getOrganization() {
		return getOrganisation();
	}

	/**
	 * Returns true or false depending on whether this
	 * {@link Creator}'s email has been set.
	 * 
	 * @return true if the email of this {@link Creator} is not null.
	 */
	public boolean isSetEmail() {
		return email != null;
	}

	/**
	 * Returns true or false depending on whether this
	 * {@link Creator}'s familyName has been set.
	 * 
	 * @return true if the familyName of this {@link Creator} is not null.
	 */
	public boolean isSetFamilyName() {
		return familyName != null;
	}

	/**
	 * Returns true or false depending on whether this
	 * {@link Creator}'s givenName has been set.
	 * 
	 * @return true if the givenName of this {@link Creator} is not null.
	 */
	public boolean isSetGivenName() {
		return givenName != null;
	}

	/**
	 * Returns true or false depending on whether this
	 * {@link Creator}'s organisation has been set.
	 * 
	 * @return true if the organisation of this {@link Creator} is not null.
	 */
	public boolean isSetOrganisation() {
		return organisation != null;
	}

	/**
	 * Returns true or false depending on whether this
	 * {@link Creator}'s organisation has been set.
	 * <p>Equal to {@link #isSetOrganisation()}
	 * 
	 * @return true or false depending on whether this
	 * {@link Creator}'s organisation has been set.
	 */
	public boolean isSetOrganization() {
		return isSetOrganisation();
	}

	/**
	 * Returns true if the XML attribute is known by this {@link Creator}.
	 * 
	 * @return true if the XML attribute is known by this {@link Creator}.
	 */
	public boolean readAttribute(String elementName, String attributeName,
			String prefix, String value) {

		if (elementName.equals("li") || elementName.equals("N")
				|| elementName.equals("ORG")) {
			if (attributeName.equals("parseType") && value.equals("Resource")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the email
	 * 
	 * @param email
	 * @return {@link JSBML#OPERATION_SUCCESS}
	 */
	public int setEmail(String email) {
		this.email = email;
		return JSBML.OPERATION_SUCCESS;
	}

	/**
	 * Sets the family name
	 * 
	 * @param familyName
	 * @return {@link JSBML#OPERATION_SUCCESS}
	 */
	public int setFamilyName(String familyName) {
		this.familyName = familyName;
		return JSBML.OPERATION_SUCCESS;
	}

	/**
	 * Sets the given name
	 * 
	 * @param givenName
	 * @return {@link JSBML#OPERATION_SUCCESS}
	 */
	public int setGivenName(String givenName) {
		this.givenName = givenName;
		return JSBML.OPERATION_SUCCESS;
	}

	/**
	 * Sets the organisation
	 * 
	 * @param organisation
	 */
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	/**
	 * Sets the organisation
	 * <p>Equal to {@link #setOrganisation(String)}.
	 * 
	 * @param organization
	 */
	public void setOrganization(String organization) {
		setOrganisation(organization);
	}

	/**
	 * Returns the information about the creator as a String.
	 * 
	 * @return the information about the creator as a String.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (isSetGivenName()) {
			sb.append(getGivenName());
			if (isSetFamilyName() || isSetEmail() || isSetOrganisation()) {
				sb.append(' ');
			}
		}
		if (isSetFamilyName()) {
			sb.append(getFamilyName());
			if (isSetEmail() || isSetOrganisation()) {
				sb.append(", ");
			}
		}
		if (isSetEmail()) {
			sb.append(getEmail());
			if (isSetOrganisation()) {
				sb.append(", ");
			}
		}
		if (isSetOrganisation()) {
			sb.append(getOrganisation());
		}

		return sb.toString();
	}

	/**
	 * Converts the {@link Creator} into XML
	 * 
	 * @param indent
	 * @param buffer
	 */
	public void toXML(String indent, StringBuffer buffer) {
		StringTools.append(buffer, indent, "<rdf:li rdf:parseType=", '"',
				"Resource", '"', ">", StringTools.newLine());
		createNElement(indent + "  ", buffer);
		createEMAILElement(indent + "  ", buffer);
		createOrGElement(indent + "  ", buffer);
		// createOtherElement(indent, buffer);
		StringTools.append(buffer, indent, "</rdf:li>", StringTools.newLine());
	}

	/**
	 * Unsets the email of this {@link Creator}.
	 * 
	 * @return {@link JSBML#OPERATION_SUCCESS}
	 */
	public int unsetEmail() {
		email = null;
		return JSBML.OPERATION_SUCCESS;
	}

	/**
	 * Unsets the familyName of this {@link Creator}.
	 * 
	 * @return {@link JSBML#OPERATION_SUCCESS}
	 */
	public int unsetFamilyName() {
		familyName = null;
		return JSBML.OPERATION_SUCCESS;
	}

	/**
	 * Unsets the givenName of this {@link Creator}.
	 * 
	 * @return {@link JSBML#OPERATION_SUCCESS}
	 */
	public int unsetGivenName() {
		givenName = null;
		return JSBML.OPERATION_SUCCESS;
	}

	/**
	 * Unsets the organisation of this {@link Creator}.
	 * 
	 */
	public void unsetOrganization() {
		organisation = null;
	}

	/**
	 * Writes the EMAIL element of the {@link Creator} in 'buffer'
	 * 
	 * @param indent
	 * @param buffer
	 */
	private void createEMAILElement(String indent, StringBuffer buffer) {
		if (isSetEmail()) {
			buffer.append(indent).append("<vCard:EMAIL>").append(getEmail())
					.append("</vCard:EMAIL> \n");
		}
	}

	/**
	 * Writes the N element of the {@link Creator} in 'buffer'
	 * 
	 * @param indent
	 * @param buffer
	 */
	private void createNElement(String indent, StringBuffer buffer) {

		if (isSetFamilyName() || isSetGivenName()) {
			buffer.append(indent).append("<vCard:N rdf:parseType=").append('"')
					.append("Resource").append('"').append("> \n");
			if (isSetFamilyName()) {
				buffer.append(indent).append("  <vCard:Family>").append(
						getFamilyName()).append("</vCard:Family> \n");
			}

			if (isSetGivenName()) {
				buffer.append(indent).append("  <vCard:Given>").append(
						getGivenName()).append("</vCard:Given> \n");
			}
			buffer.append(indent).append("</vCard:N> \n");
		}

	}

	/**
	 * Writes the ORG element of the {@link Creator} in 'buffer'
	 * 
	 * @param indent
	 * @param buffer
	 */
	private void createOrGElement(String indent, StringBuffer buffer) {
		if (isSetOrganisation()) {
			buffer.append(indent).append("<vCard:OrG> \n");
			buffer.append(indent).append("  <vCard:Orgname>").append(
					getOrganisation()).append("</vCard:Orgname> \n");
			buffer.append(indent).append("</vCard:OrG> \n");
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		throw new IndexOutOfBoundsException(Integer.toString(childIndex));
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		return 0;
	}
}
