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

import java.util.Map;

/**
 * Represents the eventAssignment XML element of a SBML file.
 * 
 * @author Andreas Dr&auml;ger
 * @author marine
 * @since 0.8
 * @version $Rev$
 */
public class EventAssignment extends AbstractMathContainer implements Assignment {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -263409745456083049L;
	/**
	 * Represents the 'variable' XML attribute of an eventAssignment element.
	 */
	private String variableID;

	/**
	 * Creates an EventAssignment instance. By default, the variableID is null.
	 */
	public EventAssignment() {
		super();
		this.variableID = null;
	}

	/**
	 * Creates an EventAssignment instance from a given EventAssignment.
	 * 
	 * @param eventAssignment
	 */
	public EventAssignment(EventAssignment eventAssignment) {
		super(eventAssignment);
		if (isSetVariable()) {
			this.variableID = new String(eventAssignment.getVariable());
		} else {
			this.variableID = null;
		}
	}

	/**
	 * Creates an EventAssignment instance from a level and version. By default,
	 * the variableID is null.
	 */
	public EventAssignment(int level, int version) {
		super(level, version);
		this.variableID = null;
		if (isSetLevel() && (getLevel() < 2)) {
			throw new IllegalAccessError("Cannot create an EventAssignment with Level < 2.");
		}
	}

	/**
	 * Sets the variableID of this EventAssignment to 'variable'. If 'variable'
	 * doesn't match any id of Compartment , Species, SpeciesReference or
	 * Parameter in Model, an {@link IllegalArgumentException} is thrown.
	 * 
	 * @param variable
	 * @throws IllegalArgumentException
	 */
	public void checkAndSetVariable(String variable) {
		Model m = getModel();
		if (m != null) {
			Variable nsb = getModel().findVariable(variable);
			if (nsb == null) {
				throw new IllegalArgumentException(String.format(
										NO_SUCH_VARIABLE_EXCEPTION_MSG,
										m.getId(), variable));
			}
			setVariable(nsb);
		} else {
			throw new NullPointerException(JSBML.UNDEFINED_MODEL_MSG);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#clone()
	 */
	public EventAssignment clone() {
		return new EventAssignment(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof EventAssignment) {
			EventAssignment ea = (EventAssignment) o;
			if ((!ea.isSetVariable() && isSetVariable())
					|| (ea.isSetVariable() && !isSetVariable())) {
				return false;
			}
			boolean equal = super.equals(o);
			if (ea.isSetVariable() && isSetVariable()) {
				equal &= ea.getVariable().equals(getVariable());
			}
			return equal;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#getParent()
	 */
	@Override
	public Event getParent() {
		return (Event) super.getParent();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#getVariable()
	 */
	public String getVariable() {
		return isSetVariable() ? this.variableID : "";
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#getVariableInstance()
	 */
	public Variable getVariableInstance() {
		Model m = getModel();
		return m != null ? m.findVariable(this.variableID) : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#isSetVariable()
	 */
	public boolean isSetVariable() {
		return variableID != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#isSetVariableInstance()
	 */
	public boolean isSetVariableInstance() {
		Model m = getModel();
		return m != null ? m.findVariable(this.variableID) != null : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see readAttribute(String attributeName, String prefix, String value)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);
		if (!isAttributeRead) {
			if (attributeName.equals("variable")) {
				setVariable(value);
				return true;
			}
		}
		return isAttributeRead;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#setVariable(java.lang.String)
	 */
	public void setVariable(String variable) {
		String oldVariable = this.variableID;
		this.variableID = variable;
		firePropertyChange(SBaseChangedEvent.variable, oldVariable, variable);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#setVariable(org.sbml.jsbml.Variable)
	 */
	public void setVariable(Variable variable) {
		if (!variable.isConstant()) {
			if ((getLevel() < 3) && (variable instanceof SpeciesReference)) {
				throw new IllegalArgumentException(String.format(
						ILLEGAL_VARIABLE_EXCEPTION_MSG, variable.getId(),
						getElementName()));
			}
			if (variable.isSetId()) {
				setVariable(variable.getId());
			} else {
				unsetVariable();
			}
		} else {
			throw new IllegalArgumentException(String.format(
					ILLEGAL_CONSTANT_VARIABLE_MSG, variable.getId(),
					getElementName()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#toString()
	 */
	@Override
	public String toString() {
		if (getMath() != null && getVariable() != null) {
			return getVariable() + " = " + getMath().toString();
		} else if (isSetMath()) {
			return getMath().toString();
		} else if (isSetVariable()) {
			return getVariable() + " = 0";
		}
		return getElementName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Assignment#unsetVariable()
	 */
	public void unsetVariable() {
		setVariable((String) null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();
		if (isSetVariable() && getLevel() >= 2) {
			attributes.put("variable", getVariable());
		}
		return attributes;
	}
}
