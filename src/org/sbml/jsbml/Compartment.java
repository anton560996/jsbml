/*
 * $Id$
 * $URL$
 *
 * 
 *==================================================================================
 * Copyright (c) 2009 The jsbml team.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */

package org.sbml.jsbml;

import java.util.Map;

import org.sbml.jsbml.util.StringTools;

/**
 * Represents the compartment in a model, i.e., a variable element with name,
 * identifier, unit, and value that may change during a simulation. This is the
 * container for reacting species.
 * 
 * @author Andreas Dr&auml;ger
 * @author Nicolas Rodriguez
 * @author marine
 * 
 * @opt attributes
 * @opt types
 * @opt visibility
 * @has 0..1 outside 0..* Compartment
 * @has 0..1 type 1..* CompartmentType
 */
public class Compartment extends Symbol {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -1117854029388326636L;
	/**
	 * Represents the compartmentType XML attribute of a compartment element. It
	 * matches a compartmentType id in the model.
	 */
	@Deprecated
	private String compartmentTypeID;
	/**
	 * Represents the outside XML attribute of a compartment element. It matches
	 * a compartment id in the model instance.
	 */
	@Deprecated
	private String outsideID;
	/**
	 * Represents the spatialDimensions XML attribute of a compartment element.
	 */
	private Short spatialDimensions;
	/**
	 * Helper variable to check if spatial dimensions has been set by the user.
	 */
	private boolean isSetSpatialDimensions = false;
	/**
	 * This message will be displayed if the user tries to set the spatial
	 * dimensions of this element to a value other than 0, 1, 2, or 3.
	 */
	private static final String ERROR_MESSAGE_INVALID_DIM = "Spatial dimensions must be within {0, 3}, but %f was given.";

	/**
	 * This is the error message to be displayed if an application tries to set
	 * units or size attribute for this compartment but the spatial dimensions
	 * have been set to zero.
	 */
	private static final String ERROR_MESSAGE_ZERO_DIM = "Cannot set %s for compartment %s if the spatial dimensions are zero.";

	/**
	 * Creates a Compartment instance. By default, if the level is set and is
	 * superior or equal to 3, sets the compartmentType, outsideID and
	 * spatialDimension to null.
	 */
	public Compartment() {
		super();
		initDefaults();
	}

	/**
	 * Creates a Compartment instance from a given compartment.
	 * 
	 * @param compartment
	 */
	public Compartment(Compartment compartment) {
		super(compartment);
		initDefaults();
		if (compartment.isSetCompartmentType()) {
			this.compartmentTypeID = new String(compartment
					.getCompartmentType());
		}
		if (compartment.isSetSpatialDimensions()) {
			this.spatialDimensions = Short.valueOf(compartment
					.getSpatialDimensions());
		}
		if (compartment.isSetOutside()) {
			this.outsideID = new String(compartment.getOutside());
		}
		if (compartment.isSetSize()) {
			setValue(compartment.getSize());
		}
	}

	/**
	 * Creates a Compartment instance from a level and version. By default, if
	 * the level is set and is superior or equal to 3, sets the compartmentType,
	 * outsideID and spatialDimension to null.
	 * 
	 * @param level
	 * @param version
	 */
	public Compartment(int level, int version) {
		this(null, null, level, version);
	}
	
	/**
	 * 
	 * @param id
	 */
	public Compartment(String id) {
		this();
		setId(id);
	}

	/**
	 * Creates a Compartment instance from an id, level and version. By default,
	 * sets the compartmentType, outsideID and spatialDimension to null.
	 * 
	 * @param id
	 * @param level
	 * @param version
	 */
	public Compartment(String id, int level, int version) {
		this(id, null, level, version);
	}

	/**
	 * Creates a Compartment instance from an id, name, level and version. By
	 * default, if the level is set and is superior or equal to 3, sets the
	 * compartmentType, outsideID and spatialDimension to null.
	 * 
	 * @param id
	 * @param name
	 * @param level
	 * @param version
	 */
	public Compartment(String id, String name, int level, int version) {
		super(id, name, level, version);
		initDefaults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Symbol#clone()
	 */
	@Override
	public Compartment clone() {
		return new Compartment(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.Symbol#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Compartment) {
			boolean equal = super.equals(o);
			Compartment c = (Compartment) o;
			equal &= c.getConstant() == getConstant();
			equal &= c.isSetOutside() == isSetOutside();
			if (c.isSetOutside() && isSetOutside()) {
				equal &= c.getOutside().equals(getOutside());
			}
			equal &= c.isSetCompartmentType() == isSetCompartmentType();
			if (c.isSetCompartmentType() && isSetCompartmentType()) {
				equal &= c.getCompartmentType().equals(getCompartmentType());
			}
			equal &= c.getSize() == getSize();
			equal &= c.getSpatialDimensions() == getSpatialDimensions();
			return equal;
		}
		return false;
	}

	/**
	 * 
	 * @return the compartmentType id of this compartment. Return an empty
	 *         String if it is not set.
	 */
	public String getCompartmentType() {
		return isSetCompartmentType() ? this.compartmentTypeID : "";
	}

	/**
	 * 
	 * @return the compartmentType instance in Model for this compartment
	 *         compartmentTypeID. Return null if there is no compartmentType
	 *         instance which matches this id.
	 */
	@Deprecated
	public CompartmentType getCompartmentTypeInstance() {
		Model m = getModel();
		return m != null ? m.getCompartmentType(this.compartmentTypeID) : null;
	}

	/**
	 * 
	 * @return the outside id of this compartment. Return "" if it is not set.
	 */
	public String getOutside() {
		return isSetOutside() ? outsideID : "";
	}

	/**
	 * 
	 * @return the compartment instance which matches the outside id in Model.
	 *         Return null if no compartment instance matches the outside id.
	 */
	public Compartment getOutsideInstance() {
		Model m = getModel();
		return m != null ? m.getCompartment(outsideID) : null;
	}

	/**
	 * 
	 * @return the size of this compartment.
	 */
	public double getSize() {
		return getValue();
	}

	/**
	 * Returns the spatialDimensions of this compartment. If it is not set,
	 * returns the default SBML level 2 value, which is 3.
	 * 
	 * @return the spatialDimensions of this compartment or 3 if
	 *         spatialdimensions is not set.
	 */
	public short getSpatialDimensions() {
		return isSetSpatialDimensions() && (spatialDimensions != null) ? spatialDimensions
				: 3;
	}

	/**
	 *(For SBML Level 1) Get the volume of this Compartment
	 * 
	 * This method is identical to getSize(). In SBML Level 1, compartments are
	 * always three-dimensional constructs and only have volumes, whereas in
	 * SBML Level 2, compartments may be other than three-dimensional and
	 * therefore the 'volume' attribute is named 'size' in Level 2. LibSBML
	 * provides both getSize() and getVolume() for easier compatibility between
	 * SBML Levels.
	 * 
	 * @return
	 */
	public double getVolume() {
		return getSize();
	}

	/**
	 * initializes the default values.
	 */
	public void initDefaults() {
		compartmentTypeID = null;
		outsideID = null;
		if (getLevel() < 3) {
			spatialDimensions = 3;
			constant = new Boolean(true);
		} else {
			spatialDimensions = null;
			constant = null;
		}
		// TODO : There is no default value for compartment size (only for level 1 apparently) ??
		value = 1.;
	}

	/**
	 * 
	 * @return true if the compartmentID of this compartment is not null.
	 */
	public boolean isSetCompartmentType() {
		return compartmentTypeID != null;
	}

	/**
	 * 
	 * @return true if the compartmentType instance which matches the
	 *         compartmentTypeID of this compartment is not null.
	 */
	@Deprecated
	public boolean isSetCompartmentTypeInstance() {
		Model m = getModel();
		return m != null ? m.getCompartmentType(this.compartmentTypeID) != null
				: false;
	}

	/**
	 * 
	 * @return true if the outsideID of this compartment is not null.
	 */
	public boolean isSetOutside() {
		return outsideID != null;
	}

	/**
	 * 
	 * @return true if the compartment instance which matches the outsideID of
	 *         this compartment is not null.
	 */
	public boolean isSetOutsideInstance() {
		Model m = getModel();
		return m != null ? m.getCompartment(this.outsideID) != null : false;
	}

	/**
	 * 
	 * @return true if the size of this compartment is not null.
	 */
	public boolean isSetSize() {
		return isSetValue();
	}

	/**
	 * 
	 * @return true if the spatialDimensions of this compartment is not null.
	 */
	public boolean isSetSpatialDimensions() {
		return isSetSpatialDimensions;
	}

	/**
	 * <p>
	 * (For SBML Level 1) Predicate returning true or false depending on whether
	 * this Compartment's 'volume' attribute has been set.
	 * </p>
	 * <p>
	 * Some words of explanation about the set/unset/isSet methods: SBML Levels
	 * 1 and 2 define certain attributes on some classes of objects as optional.
	 * This requires an application to be careful about the distinction between
	 * two cases: (1) a given attribute has never been set to a value, and
	 * therefore should be assumed to have the SBML-defined default value, and
	 * (2) a given attribute has been set to a value, but the value happens to
	 * be an empty string. LibSBML supports these distinctions by providing
	 * methods to set, unset, and query the status of attributes that are
	 * optional. The methods have names of the form setAttribute(...),
	 * unsetAttribute(), and isSetAttribute(), where Attribute is the the name
	 * of the optional attribute in question.
	 * </p>
	 * <p>
	 * This method is similar but not identical to isSetSize(). The latter
	 * should not be used in the context of SBML Level 1 models because this
	 * method (isSetVolume()) performs extra processing to take into account the
	 * difference in default values between SBML Levels 1 and 2.
	 * </p>
	 * 
	 * @return true if the 'volume' attribute ('size' in L2) of this Compartment
	 *         has been set, false otherwise.
	 * @see isSetSize
	 * @note In SBML Level 1, a compartment's volume has a default value ( 1.0)
	 *       and therefore this method will always return true. In Level 2, a
	 *       compartment's size (the equivalent of SBML Level 1's 'volume') is
	 *       optional and has no default value, and therefore may or may not be
	 *       set.
	 */
	public boolean isSetVolume() {
		return isSetSize();
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
			if (attributeName.equals("spatialDimensions") && getLevel() > 1) {
				this.setSpatialDimensions(Short.parseShort(value));
				return true;
			} else if (attributeName.equals("units")) {
				this.setUnits(value);
				return true;
			} else if (attributeName.equals("size") && getLevel() > 1) {
				this.setSize(StringTools.parseSBMLDouble(value));
				return true;
			} else if (attributeName.equals("volume") && getLevel() == 1) {
				this.setSize(StringTools.parseSBMLDouble(value));
				return true;
			} else if (attributeName.equals("compartmentType")
					&& getLevel() == 2) {
				this.setCompartmentType(value);
				return true;
			} else if (attributeName.equals("outside") && getLevel() < 3) {
				this.setOutside(value);
			} else if (attributeName.equals("constant") && getLevel() > 1) {
					this.setConstant(StringTools.parseSBMLBoolean(value));
			}
		}
		return isAttributeRead;
	}

	/**
	 * Sets the compartmentTypeID value of this compartment to the id of
	 * 'compartmentType'
	 * 
	 * @param compartmentType
	 */
	@Deprecated
	public void setCompartmentType(CompartmentType compartmentType) {
		this.compartmentTypeID = compartmentType != null ? compartmentType
				.getId() : null;
		stateChanged();
	}

	/**
	 * Sets the compartmentTypeID of this compartment to 'compartmentTypeID'
	 * 
	 * @param compartmentTypeID
	 */
	public void setCompartmentType(String compartmentTypeID) {
		this.compartmentTypeID = compartmentTypeID;
		stateChanged();
	}

	/**
	 * Sets the outsideID of this compartment to the id of 'outside'.
	 * 
	 * @param outside
	 */
	public void setOutside(Compartment outside) {
		this.outsideID = outside != null ? outside.getId() : null;
		stateChanged();
	}

	/**
	 * Sets the outsideID of this compartment to 'outside'.
	 * 
	 * @param outside
	 */
	public void setOutside(String outside) {
		if (outside != null && outside.trim().length() == 0) {
			this.outsideID = null;
		} else {
			this.outsideID = outside;
		}
		stateChanged();
	}

	/**
	 * Sets the size of this compartment to 'size'.
	 * 
	 * @param size
	 */
	public void setSize(double size) {
		setValue(size);
	}

	/**
	 * Sets the spatialDimensions of this compartment to 'i'.
	 * 
	 * @param spatialDimension
	 * @throws IllegalArgumentException
	 *             if spatialDimension < 0 or if spatialDimension > 3
	 */
	public void setSpatialDimensions(int spatialDimension) {
		if ((spatialDimension >= 0) && (spatialDimension <= 3)) {
			isSetSpatialDimensions = true;
			this.spatialDimensions = Short.valueOf((short) spatialDimension);
		} else {
			throw new IllegalArgumentException(String.format(
					ERROR_MESSAGE_INVALID_DIM, spatialDimension));
		}
	}

	/**
	 * Sets the spatialDimensions of this compartment to 'spatialDimensiosn'.
	 * 
	 * @param spatialDimensions
	 * @throws IllegalArgumentException
	 *             if spatialDimension < 0 or if spatialDimension > 3
	 */
	public void setSpatialDimensions(short spatialDimensions) {
		setSpatialDimensions((int) spatialDimensions);
	}

	/**
	 * Sets the unitsID of this {@link QuantityWithDefinedUnit}. Only valid unit
	 * kind names or identifiers of already existing {@link UnitDefinition}s are
	 * allowed arguments of this function.
	 * 
	 * @param units
	 *            the identifier of an already existing {@link UnitDefinition}
	 *            or an {@link Unit.Kind} identifier for the current
	 *            level/version combination of this unit. Passing a null value
	 *            to this method is equivalent to calling {@link #unsetUnits()}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the unit is not valid or if spatialDimensions = 0.
	 */
	public void setUnits(String units) {
		if (getSpatialDimensions() > 0) {
			super.setUnits(units);
		} else {
			throw new IllegalArgumentException(String.format(
					ERROR_MESSAGE_ZERO_DIM, "units", getId()));
		}
	}

	/**
	 * Sets the Unit of this Compartment.
	 * 
	 * @param unit
	 * 
	 * @throws IllegalArgumentException
	 *             if spatialDimensions = 0.
	 */
	public void setUnits(Unit unit) {
		if (getSpatialDimensions() > 0) {
			super.setUnits(unit);
		} else {
			throw new IllegalArgumentException(String.format(
					ERROR_MESSAGE_ZERO_DIM, "unit", getId()));
		}
	}

	/**
	 * Sets the unit of this Compartment.
	 * 
	 * A new unit will be created base on this kind.
	 * 
	 * @param unitKind
	 * 
	 * @throws IllegalArgumentException
	 *             if spatialDimensions = 0.
	 * 
	 */
	public void setUnits(Unit.Kind unitKind) {
		if (getSpatialDimensions() > 0) {
			super.setUnits(unitKind);
		} else {
			throw new IllegalArgumentException(String.format(
					ERROR_MESSAGE_ZERO_DIM, "unit kind", getId()));
		}
	}

	/**
	 * Set the unit attribute of this Compartment to the given unit definition.
	 * 
	 * @param units
	 * 
	 * @throws IllegalArgumentException
	 *             if spatialDimensions = 0.
	 * 
	 */
	public void setUnits(UnitDefinition unitDefinition) {
		if (getSpatialDimensions() > 0) {
			super.setUnits(unitDefinition);
		} else {
			throw new IllegalArgumentException(String.format(
					ERROR_MESSAGE_ZERO_DIM, "unit definition", getId()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.QuantityWithDefinedUnit#setValue(double)
	 */
	@Override
	public void setValue(double value) {
		if (getSpatialDimensions() > 0) {
			super.setValue(value);
		} else {
			throw new IllegalArgumentException(String.format(
					ERROR_MESSAGE_ZERO_DIM, "size", getId()));
		}
	}

	/**
	 * <p>
	 * Sets the 'volume' attribute (or 'size' in SBML Level 2) of this
	 * Compartment.
	 * </p>
	 * <p>
	 * Some words of explanation about the set/unset/isSet methods: SBML Levels
	 * 1 and 2 define certain attributes on some classes of objects as optional.
	 * This requires an application to be careful about the distinction between
	 * two cases: (1) a given attribute has never been set to a value, and
	 * therefore should be assumed to have the SBML-defined default value, and
	 * (2) a given attribute has been set to a value, but the value happens to
	 * be an empty string. LibSBML supports these distinctions by providing
	 * methods to set, unset, and query the status of attributes that are
	 * optional. The methods have names of the form setAttribute(...),
	 * unsetAttribute(), and isSetAttribute(), where Attribute is the the name
	 * of the optional attribute in question.
	 * </p>
	 * <p>
	 * This method is identical to setVolume() and is provided for compatibility
	 * between SBML Level 1 and Level 2.
	 * </p>
	 * 
	 * @param value
	 *            a double representing the volume of this compartment instance
	 *            in whatever units are in effect for the compartment.
	 */
	public void setVolume(double value) {
		setSize(value);
	}

	/**
	 * Sets the compartmentTypeID of this compartment to null.
	 */
	public void unsetCompartmentType() {
		compartmentTypeID = null;
	}

	/**
	 * Sets the outsideID of this compartment to null.
	 */
	public void unsetOutside() {
		this.outsideID = null;
		stateChanged();
	}

	/**
	 * <p>
	 * Unsets the value of the 'size' attribute of this Compartment.
	 * </p>
	 * <p>
	 * Some words of explanation about the set/unset/isSet methods: SBML Levels
	 * 1 and 2 define certain attributes on some classes of objects as optional.
	 * This requires an application to be careful about the distinction between
	 * two cases: (1) a given attribute has never been set to a value, and
	 * therefore should be assumed to have the SBML-defined default value, and
	 * (2) a given attribute has been set to a value, but the value happens to
	 * be an empty string. LibSBML supports these distinctions by providing
	 * methods to set, unset, and query the status of attributes that are
	 * optional. The methods have names of the form setAttribute(...),
	 * unsetAttribute(), and isSetAttribute(), where Attribute is the the name
	 * of the optional attribute in question.
	 * </p>
	 */
	public void unsetSize() {
		unsetValue();
	}

	/**
	 * sets the spatialDimensions of this compartment to null.
	 */
	public void unsetSpatialDimensions() {
		this.spatialDimensions = null;
		isSetSpatialDimensions = false;
		stateChanged();
	}

	/**
	 * <p>
	 * (For SBML Level 1) Unsets the value of the 'volume' attribute of this
	 * Compartment.
	 * </p>
	 * <p>
	 * Some words of explanation about the set/unset/isSet methods: SBML Levels
	 * 1 and 2 define certain attributes on some classes of objects as optional.
	 * This requires an application to be careful about the distinction between
	 * two cases: (1) a given attribute has never been set to a value, and
	 * therefore should be assumed to have the SBML-defined default value, and
	 * (2) a given attribute has been set to a value, but the value happens to
	 * be an empty string. LibSBML supports these distinctions by providing
	 * methods to set, unset, and query the status of attributes that are
	 * optional. The methods have names of the form setAttribute(...),
	 * unsetAttribute(), and isSetAttribute(), where Attribute is the the name
	 * of the optional attribute in question.
	 * </p>
	 * <p>
	 * In SBML Level 1, a Compartment volume has a default value (1.0) and
	 * therefore should always be set. In Level 2, 'size' is optional with no
	 * default value and as such may or may not be set.
	 * </p>
	 */
	public void unsetVolume() {
		unsetSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.SBase#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();

		if (getLevel() == 1) {
			if (isSetVolume()) {
				attributes.put("volume", Double.toString(getVolume()));
			}
		} else if (1 < getLevel()) {
			if (isSetSpatialDimensions()) {
				attributes.put("spatialDimensions", Short
						.toString(getSpatialDimensions()));
			}
			if (isSetSize()) {
				attributes.put("size", Double.toString(getSize()));
			}
			if (isSetConstant()) {
				attributes.put("constant", Boolean.toString(getConstant()));
			}
			if (isSetUnits()) {
				attributes.put("units", getUnits());
			}
		}
		if (getLevel() == 2) {
			if (isSetCompartmentType()) {
				attributes.put("compartmentType", getCompartmentType());
			}
		}
		if (getLevel() < 3) {
			if (isSetOutside()) {
				attributes.put("outside", outsideID);
			}
		}
		return attributes;
	}

}
