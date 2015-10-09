/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2015 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.spatial;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.ResourceManager;
import org.sbml.jsbml.util.StringTools;

/**
 * @author Alex Thomas
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev$
 */
public class Boundary extends AbstractSpatialNamedSBase {

  
  /**
   * A {@link Logger} for this class.
   */
  private Logger logger = Logger.getLogger(Boundary.class);
  
  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -5283759970799753982L;

  /**
   * 
   */
  private Double value;

  /**
   * 
   */
  private static final ResourceBundle bundle = ResourceManager.getBundle("org.sbml.jsbml.ext.spatial.Messages");


  /**
   * 
   */
  public Boundary() {
    super();
  }

  /**
   * 
   * @param boundary
   */
  public Boundary(Boundary boundary) {
    super(boundary);
    if (boundary.isSetValue()) {
      value = Double.valueOf(value);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.AbstractTreeNode#clone()
   */
  @Override
  public Boundary clone() {
    return new Boundary(this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      Boundary b = (Boundary) object;
      equal &= b.isSetValue() == isSetValue();
      if (equal && isSetValue()) {
        equal &= b.getValue() == getValue();
      }
    }
    return equal;
  }


  /**
   * Returns the value of value
   *
   * @return the value of value
   */
  public double getValue() {
    if (isSetValue()) {
      return value;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.value, this);
  }


  /**
   * Returns whether value is set
   *
   * @return whether value is set
   */
  public boolean isSetValue() {
    return value != null;
  }


  /**
   * Sets the value of value
   * @param value
   */
  public void setValue(double value) {
    Double oldValue = this.value;
    this.value = value;
    firePropertyChange(SpatialConstants.value, oldValue, this.value);
  }


  /**
   * Unsets the variable value
   *
   * @return {@code true}, if value was set before,
   *         otherwise {@code false}
   */
  public boolean unsetValue() {
    if (isSetValue()) {
      Double oldValue = value;
      value = null;
      firePropertyChange(SpatialConstants.value, oldValue, value);
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.spatial.NamedSpatialElement#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 983;
    int hashCode = super.hashCode();
    if (isSetValue()) {
      hashCode += prime * getValue();
    }
    return hashCode;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Boundary [value=");
    builder.append(value);
    builder.append("]");
    return builder.toString();
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetValue()) {
      attributes.remove("value");
      attributes.put(SpatialConstants.shortLabel + ":value", String.valueOf(getValue()));
    }
    return attributes;
  }


  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix, value) && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.value)) {
        try{
          setValue(StringTools.parseSBMLDouble(value));
        } catch (Exception e) {
          logger.warn(MessageFormat.format(
            SpatialConstants.bundle.getString("COULD_NOT_READ_ATTRIBUTE"), value, SpatialConstants.value, getElementName()));
        }
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }


}
