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

import java.util.Locale;
import java.util.Map;

import org.sbml.jsbml.util.StringTools;

/**
 * A local parameter can only be used to specify a constant within a
 * {@link KineticLaw}.
 * 
 * @author Andreas Dr&auml;ger
 * @author Nicolas Rodriguez
 * @date 2010-04-20
 * @since 0.8
 * @version $Rev$
 */
public class LocalParameter extends QuantityWithUnit {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 57994535283502018L;

	/**
	 * 
	 */
	public LocalParameter() {
		super();
	}
	
	/**
	 * @param level
	 * @param version
	 */
	public LocalParameter(int level, int version) {
		super(level, version);
	}

	/**
	 * @param lp
	 */
	public LocalParameter(LocalParameter lp) {
		super(lp);
	}

	/**
	 * Creates a new local parameter that will have the same properties than the
	 * given global parameter. However, the value of the constant attribute will
	 * be ignored because a local parameter can only be a constant quantity.
	 * 
	 * @param parameter
	 */
	public LocalParameter(Parameter parameter) {
		super(parameter);
	}

	/**
	 * 
	 * @param id
	 */
	public LocalParameter(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param level
	 * @param version
	 */
	public LocalParameter(String id, int level, int version) {
		super(id, level, version);
	}

	/**
	 * @param id
	 * @param name
	 * @param level
	 * @param version
	 */
	public LocalParameter(String id, String name, int level, int version) {
		super(id, name, level, version);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.QuantityWithUnit#clone()
	 */
	public LocalParameter clone() {
		return new LocalParameter(this);
	}


	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#getElementName()
	 */
	public String getElementName() {
		if (getLevel() < 3) {
			return "parameter";
		}
		return super.getElementName();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractNamedSBaseWithUnit#getPredefinedUnitID()
	 */
	public String getPredefinedUnitID() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Symbol#readAttribute(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);
		if (attributeName.equals("value")) {
			this.setValue(StringTools.parseSBMLDouble(value));
			return true;
		} else if (attributeName.equals("units")) {
			this.setUnits(value);
			return true;
		}
		
		return isAttributeRead;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Symbol#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();

		if (isSetValue()) {
			attributes.put("value", StringTools.toString(Locale.ENGLISH,
					getValue()));
		}
		if (isSetUnits()) {
			attributes.put("units", getUnits());
		}

		return attributes;
	}

}
