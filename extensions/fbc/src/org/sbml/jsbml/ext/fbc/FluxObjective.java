/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.fbc;

import java.util.Locale;
import java.util.Map;

import org.sbml.jsbml.AbstractNamedSBase;
import org.sbml.jsbml.LevelVersionError;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.UniqueNamedSBase;
import org.sbml.jsbml.util.StringTools;

/**
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 * @date 27.10.2011
 */
public class FluxObjective extends AbstractNamedSBase implements UniqueNamedSBase {

  /**
   * 
   */
  private static final long serialVersionUID = 246449689493121713L;

  /**
   * 
   */
  private double coefficient;
  /**
   * 
   */
  private boolean isSetCoefficient = false;
  /**
   * 
   */
  private String reaction;

  /**
   * Creates an FluxObjective instance 
   */
  public FluxObjective() {
    super();
    initDefaults();
  }

  /**
   * Creates a FluxObjective instance with a level and version. 
   * 
   * @param level
   * @param version
   */
  public FluxObjective(int level, int version) {
    this(null, null, level, version);
  }

  /**
   * Creates a FluxObjective instance with an id. 
   * 
   * @param id
   */
  public FluxObjective(String id) {
    super(id);
    initDefaults();
  }

  /**
   * Creates a FluxObjective instance with an id, level, and version. 
   * 
   * @param id
   * @param level
   * @param version
   */
  public FluxObjective(String id, int level, int version) {
    this(id, null, level, version);
  }

  /**
   * Creates a FluxObjective instance with an id, name, level, and version. 
   * 
   * @param id
   * @param name
   * @param level
   * @param version
   */
  public FluxObjective(String id, String name, int level, int version) {
    super(id, name, level, version);
    if (getLevelAndVersion().compareTo(
      Integer.valueOf(FBCConstants.MIN_SBML_LEVEL),
      Integer.valueOf(FBCConstants.MIN_SBML_VERSION)) < 0) {
      throw new LevelVersionError(getElementName(), level, version);
    }
    initDefaults();
  }

  /**
   * Clone constructor
   */
  public FluxObjective(FluxObjective obj) {
    super(obj);

    if (obj.isSetReaction()) {
    	setReaction(obj.getReaction());
    }
    if (obj.isSetCoefficient()) {
    	setCoefficient(obj.getCoefficient());
    }
  }

  /**
   * clones this class
   */
  public FluxObjective clone() {
    return new FluxObjective(this);
  }

  /**
   * Returns the value of coefficient
   *
   * @return the value of coefficient
   */
  public double getCoefficient() {
	  if (isSetCoefficient()) {
		  return coefficient;
	  }
	  // This is necessary if we cannot return null here.
	  throw new PropertyUndefinedError(FBCConstants.coefficient, this);
  }

  /**
   * Returns the value of reaction
   *
   * @return the value of reaction
   */
  public String getReaction() {
	  return reaction;
  }

  /**
   * Returns whether reaction is set 
   *
   * @return whether reaction is set 
   */
  public boolean isSetReaction() {
	  return this.reaction != null;
  }

  /**
   * Initializes the default values using the namespace.
   */
  public void initDefaults() {
    addNamespace(FBCConstants.namespaceURI);
  }

/* (non-Javadoc)
 * @see org.sbml.jsbml.NamedSBase#isIdMandatory()
*/
  public boolean isIdMandatory() {
    return true;
  }

  /**
   * Returns whether coefficient is set 
   *
   * @return whether coefficient is set 
   */
  public boolean isSetCoefficient() {
	  return isSetCoefficient;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.element.SBase#readAttribute(String attributeName, String prefix, String value)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix,
      value);

    if (!isAttributeRead) {
      isAttributeRead = true;

      if (attributeName.equals("reaction")) {
        setReaction(value);
      } else if (attributeName.equals("coefficient")) {
        setCoefficient(StringTools.parseSBMLDouble(value));
      } else {
        isAttributeRead = false;
      }

    }

    return isAttributeRead;
  }

  /**
   * Sets the value of coefficient
   * 
   * @param coefficient the coefficient to set
   */
  public void setCoefficient(double coefficient) {
	  double oldCoefficient = this.coefficient;
	  this.coefficient = coefficient;
	  isSetCoefficient = true;
	  firePropertyChange(FBCConstants.coefficient, oldCoefficient, this.coefficient);
  }


  /**
   * 
   * @param reaction
   */
  public void setReaction(Reaction reaction) {
    setReaction(reaction.getId());
  }

  /**
   * @param reaction the reaction to set
   */
  public void setReaction(String reaction) {
    this.reaction = reaction;
  }

  /**
   * Unsets the variable coefficient 
   *
   * @return {@code true}, if coefficient was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetCoefficient() {
	  if (isSetCoefficient()) {
		  double oldCoefficient = this.coefficient;
		  this.coefficient = Double.NaN;
		  isSetCoefficient = false;
		  firePropertyChange(FBCConstants.coefficient, oldCoefficient, this.coefficient);
		  return true;
	  }
	  return false;
  }

  /**
   * Unsets the variable reaction 
   *
   * @return {@code true}, if reaction was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetReaction() {
	  if (isSetReaction()) {
		  String oldReaction = this.reaction;
		  this.reaction = null;
		  firePropertyChange(FBCConstants.reaction, oldReaction, this.reaction);
		  return true;
	  }
	  return false;
  }
  

  /* (non-Javadoc)
   * @see org.sbml.jsbml.element.SBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (isSetReaction()) {
      attributes.put(FBCConstants.shortLabel+ ":reaction", getReaction());			
    }
    if (isSetCoefficient()) {
      attributes.put(FBCConstants.shortLabel+ ":" + FBCConstants.coefficient,
        StringTools.toString(Locale.ENGLISH, getCoefficient()));
    }
	if (isSetId()) {
		attributes.remove("id");
		attributes.put(FBCConstants.shortLabel + ":id", getId());
	}
	if (isSetName())
	{
		attributes.remove("name");
		attributes.put(FBCConstants.shortLabel + ":name", getName());
	}

    return attributes;
  }

}
