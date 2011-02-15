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

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.util.StringTools;

/**
 * Represents the speciesReference XML element of a SBML file.
 * 
 * @author Andreas Dr&auml;ger
 * @author marine
 * @author Nicolas Rodriguez
 * 
 * @opt attributes
 * @opt types
 * @opt visibility
 */
public class SpeciesReference extends SimpleSpeciesReference implements
		Variable {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 4400834403773787677L;
	/**
	 * Message to be displayed in case that an illegal stoichiometric value has been set.
	 */
	private static final String ILLEGAL_STOCHIOMETRY_VALUE = "Only positive integer values can be set as %s. Invalid value %d.";
	/**
	 * Represents the 'constant' XML attribute of this SpeciesReference.
	 */
	private Boolean constant;
	/**
	 * Represents the 'denominator' XML attribute of this SpeciesReference.
	 */
	private Integer denominator;
	/**
	 * 
	 */
	private boolean isSetConstant;
	/**
	 * Boolean value to know if the SpeciesReference denominator has been set.
	 */
	private boolean isSetDenominator;
	/**
	 * 
	 */
	private boolean isSetStoichiometry;
	/**
	 * Represents the 'stoichiometry' XML attribute of this SpeciesReference.
	 */
	private Double stoichiometry;
	/**
	 * Contains the MathML expression for the stoichiometry of this
	 * SpeciesReference.
	 * 
	 * @deprecated
	 */
	private StoichiometryMath stoichiometryMath;

	/**
	 * Creates a SpeciesReference instance. By default, if the level is superior
	 * or equal to 3, the constant, stoichiometryMath and stoichiometry are
	 * null.
	 * 
	 * @param spec
	 */
	public SpeciesReference() {
		super();
		initDefaults();
	}

	/**
	 * 
	 * @param level
	 * @param version
	 */
	public SpeciesReference(int level, int version) {
		this(null, level, version);
	}
	
	/**
	 * Creates a SpeciesReference instance from a Species. By default, if the
	 * level is superior or equal to 3, the constant, stoichiometryMath and
	 * stoichiometry are null.
	 * 
	 * @param speciesReference
	 */
	public SpeciesReference(Species species) {
		super(species);
		initDefaults();
	}

	/**
	 * Creates a SpeciesReference instance from a given SpeciesReference.
	 * 
	 * @param speciesReference
	 */
	@SuppressWarnings("deprecation")
	public SpeciesReference(SpeciesReference speciesReference) {
		super(speciesReference);
		if (speciesReference.isSetStoichiometryMath()) {
			setStoichiometryMath(speciesReference.getStoichiometryMath()
					.clone());
		}
		if (speciesReference.isSetStoichiometry()) {
			setStoichiometry(new Double(speciesReference.getStoichiometry()));
		}
		if (speciesReference.isSetConstant()) {
			setConstant(new Boolean(speciesReference.getConstant()));
		}
		if (speciesReference.isSetDenominator) {
			setDenominator(new Integer(speciesReference.getDenominator()));
		}
	}

	/**
	 * 
	 * @param id
	 */
	public SpeciesReference(String id) {
		super(id);
		initDefaults();
	}

	/**
	 * 
	 * @param id
	 * @param level
	 * @param version
	 */
	public SpeciesReference(String id, int level, int version) {
		this(id, null, level, version);
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param level
	 * @param version
	 */
	public SpeciesReference(String id, String name, int level, int version) {
		super(id, name, level, version);
		initDefaults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.SBase#clone()
	 */
	@Override
	public SpeciesReference clone() {
		return new SpeciesReference(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.SBaseWithDerivedUnit#containsUndeclaredUnits()
	 */
	public boolean containsUndeclaredUnits() {
		if (isSetStoichiometryMath()) {
			return getStoichiometryMath().containsUndeclaredUnits();
		}
		return isSetStoichiometry() ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.SBase#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof SpeciesReference) {
			SpeciesReference sr = (SpeciesReference) o;
			boolean equal = super.equals(o);
			if ((sr.isSetStoichiometryMath() && !isSetStoichiometryMath())
					|| (!sr.isSetStoichiometryMath() && isSetStoichiometryMath())) {
				return false;
			} else if (sr.isSetStoichiometryMath() && isSetStoichiometryMath()) {
				equal &= sr.getStoichiometryMath().equals(stoichiometryMath);
			}
			if ((sr.isSetStoichiometry() && !isSetStoichiometry())
					|| (!sr.isSetStoichiometry() && isSetStoichiometry())) {
				return false;
			} else if (sr.isSetStoichiometry() && isSetStoichiometry()) {
				equal &= sr.getStoichiometry() == stoichiometry;
			}
			if ((sr.isSetConstant() && !isSetConstant())
					|| (!sr.isSetConstant() && isSetConstant())) {
				return false;
			} else if (sr.isSetConstant() && isSetConstant()) {
				equal &= sr.getConstant() == constant;
			}
			if ((sr.isSetDenominator() && !isSetDenominator())
					|| (!sr.isSetDenominator() && isSetDenominator())) {
				return false;
			} else if (sr.isSetDenominator() && isSetDenominator()) {
				equal &= sr.getDenominator() == denominator;
			}
			return equal;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.AbstractSBase#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.AbstractSBase#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int index) {
		int children = getChildCount();
		if (index >= children) {
			throw new IndexOutOfBoundsException(index + " >= " + children);
		}
		int pos = 0;
		if (isSetStoichiometryMath()) {
			if (pos == index) {
				return getStoichiometryMath();
			}
			pos++;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.AbstractSBase#getChildCount()
	 */
	@Override
	public int getChildCount() {
		return isSetStoichiometryMath() ? 1 : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.State#getConstant()
	 */
	public boolean getConstant() {
		return constant != null ? constant : false;
	}

	/**
	 * 
	 * @return the denominator value if it is set, 1 otherwise
	 * @deprecated Use for Level 1 only.
	 */
	@Deprecated
	public int getDenominator() {
		return isSetDenominator ? denominator : 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Quantity#getDerivedUnitInstance()
	 */
	public UnitDefinition getDerivedUnitDefinition() {
		if (isSetStoichiometryMath()) {
			return stoichiometryMath.getDerivedUnitDefinition();
		}
		UnitDefinition ud = new UnitDefinition(getLevel(), getVersion());
		ud.addUnit(Unit.Kind.DIMENSIONLESS);
		return ud;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Quantity#getDerivedUnit()
	 */
	public String getDerivedUnits() {
		if (isSetStoichiometryMath()) {
			return stoichiometryMath.getDerivedUnits();
		}
		return Unit.Kind.DIMENSIONLESS.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.AbstractSBase#getElementName()
	 */
	@Override
	public String getElementName() {
		if ((getLevel() == 1) && (getVersion() == 1)) {
			return "specieReference";
		}
		return super.getElementName();
	}

	/**
	 * 
	 * @return the stoichiometry value of this SpeciesReference if it is set, 1
	 *         otherwise.
	 */
	public double getStoichiometry() {
		return isSetStoichiometry() ? stoichiometry : 1;
	}

	/**
	 * 
	 * @return the stoichiometryMath of this SpeciesReference. Can be null if
	 *         the stoichiometryMath is not set.
	 * @deprecated
	 */
	public StoichiometryMath getStoichiometryMath() {
		return stoichiometryMath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Quantity#getValue()
	 */
	public double getValue() {
		return getStoichiometry();
	}

	/**
	 * Initializes the default values of this SpeciesReference.
	 */
	public void initDefaults() {
		// See
		// http://sbml.org/Community/Wiki/SBML_Level_3_Core/Reaction_changes/Changes_to_stoichiometry
		if (getLevel() <= 2) {
			constant = true;
			stoichiometry = 1d;
			denominator = 1;
		} else {
			isSetConstant = false;
			isSetDenominator = false;
			isSetStoichiometry = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.State#isConstant()
	 */
	public boolean isConstant() {
		return constant != null ? constant : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.State#isSetConstant()
	 */
	public boolean isSetConstant() {
		return isSetConstant;
	}

	/**
	 * 
	 * @return true if the denominator is not null.
	 */
	public boolean isSetDenominator() {
		return denominator != null;
	}

	/**
	 * 
	 * @return true if the stoichiometry of this SpeciesReference is not null.
	 */
	public boolean isSetStoichiometry() {
		return isSetStoichiometry;
	}

	/**
	 * 
	 * @return true if the stoichiometryMath of this SpeciesReference is not
	 *         null.
	 */
	public boolean isSetStoichiometryMath() {
		return stoichiometryMath != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Quantity#isSetValue()
	 */
	public boolean isSetValue() {
		return isSetStoichiometry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.SBase#readAttribute(String attributeName,
	 * String prefix, String value)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);

		if (!isAttributeRead) {
			isAttributeRead = true;
			
			if (attributeName.equals("stoichiometry")) {
				setStoichiometry(StringTools.parseSBMLDouble(value));
			} else if (attributeName.equals("constant")) {
				setConstant(StringTools.parseSBMLBoolean(value));
			} else if (attributeName.equals("denominator")) {
				setDenominator(denominator);
			} else {
				isAttributeRead = false;
			}
		}
		return isAttributeRead;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.State#setConstant(boolean)
	 */
	public void setConstant(boolean constant) {
		if (getLevel() < 3) {
			throw new PropertyNotAvailableError(SBaseChangedEvent.constant,
					this);
		}
		Boolean oldConstant = this.constant;
		this.constant = Boolean.valueOf(constant);
		isSetConstant = true;
		firePropertyChange(SBaseChangedEvent.constant, oldConstant,
				this.constant);
	}

	/**
	 * Sets the denominator of this {@link SpeciesReference}.
	 * 
	 * @param denominator
	 * @deprecated
	 */
	@Deprecated
	public void setDenominator(int denominator) {
		if ((getLevel() == 1) && (getVersion() == 2)) {
			if (denominator < 0) {
				throw new IllegalArgumentException(String.format(
										ILLEGAL_STOCHIOMETRY_VALUE,
										"denominator", stoichiometry));
			}
		}
		Integer oldDenominator = this.denominator;
		this.denominator = denominator;
		isSetDenominator = true;
		firePropertyChange(SBaseChangedEvent.denominator, oldDenominator, this.denominator);
	}

	/**
	 * Sets the stoichiometry of this {@link SpeciesReference}.
	 * 
	 * @param stoichiometry
	 */
	public void setStoichiometry(double stoichiometry) {
		if ((getLevel() == 1) && (getVersion() == 2)) {
			int stoch = (int) stoichiometry;
			if ((stoch < 0) || (stoch - stoichiometry != 0d)) {
				throw new IllegalArgumentException(String.format(
						ILLEGAL_STOCHIOMETRY_VALUE, "stoichiometry",
						stoichiometry));
			}
		}
		Double oldStoichiometry = this.stoichiometry;
		this.stoichiometry = Double.valueOf(stoichiometry);
		if (isSetStoichiometryMath()) {
			stoichiometryMath = null;
		}
		if (Double.isNaN(stoichiometry)) {
			isSetStoichiometry = false;
		} else {
			isSetStoichiometry = true;
		}
		firePropertyChange(SBaseChangedEvent.stoichiometry, oldStoichiometry,
				this.stoichiometry);
	}

	/**
	 * Sets the {@link StoichiometryMath} of this {@link SpeciesReference).
	 * 
	 * @param math
	 * @deprecated
	 */
	@Deprecated
	public void setStoichiometryMath(StoichiometryMath math) {
		unsetStoichiometryMath();
		this.stoichiometryMath = math;
		setThisAsParentSBMLObject(this.stoichiometryMath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Quantity#setValue(double)
	 */
	public void setValue(double value) {
		setStoichiometry(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Variable#unsetConstant()
	 */
	public void unsetConstant() {
		this.constant = null;
	}

	/**
	 * Unsets the stoichiometry property of this element.
	 */
	public void unsetStoichiometry() {
		this.stoichiometry = null;
	}

	/**
	 * 
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public boolean unsetStoichiometryMath() {
		if (this.stoichiometryMath != null) {
			StoichiometryMath oldStoichiometryMath = this.stoichiometryMath;
			this.stoichiometryMath = null;
			oldStoichiometryMath.fireSBaseRemovedEvent();
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Quantity#unsetValue()
	 */
	public void unsetValue() {
		unsetStoichiometry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.SBase#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();

		if (isSetStoichiometry()) {
			attributes.put("stoichiometry", StringTools.toString(
					Locale.ENGLISH, getStoichiometry()));
		}
		if (isSetConstant()) {
			attributes.put("constant", Boolean.toString(getConstant()));
		}
		if (isSetDenominator() && (getLevel() == 1)) {
			attributes.put("denominator", Integer.toString(getDenominator()));
		}

		return attributes;
	}
}
