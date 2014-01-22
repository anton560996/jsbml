/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2014  jointly by the following organizations:
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
package org.sbml.jsbml.ext.spatial;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;

import org.sbml.jsbml.PropertyUndefinedError;

import de.zbit.util.ResourceManager;





/**
 * @author Alex Thomas
 * @version $Rev$
 * @since 1.0
 * @date Jan 20, 2014
 */
public class CSGPseudoPrimitive extends CSGNode{

  /**
   * 
   */
  private static final long serialVersionUID = 303742063326104808L;

  private String csgObjectRef;

  private static final ResourceBundle bundle = ResourceManager.getBundle("org.sbml.jsbml.ext.spatial.Messages");


  public CSGPseudoPrimitive() {
    super();
  }


  /**
   * @param node
   */
  public CSGPseudoPrimitive(CSGPseudoPrimitive csgp) {
    super(csgp);


    if (csgp.isSetCsgObjectRef()) {
      csgObjectRef = new String(csgp.getCsgObjectRef());
    }

  }


  /**
   * @param level
   * @param version
   */
  public CSGPseudoPrimitive(int level, int version) {
    super(level, version);
  }


  /**
   * 
   * @param id
   * @param level
   * @param version
   */
  public CSGPseudoPrimitive(String id, int level, int version) {
    super(id, level, version);
  }


  @Override
  public CSGPseudoPrimitive clone() {
    return new CSGPseudoPrimitive(this);
  }


  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      CSGPseudoPrimitive csgp = (CSGPseudoPrimitive) object;
      equal &= csgp.isSetCsgObjectRef() == isSetCsgObjectRef();
      if (equal && isSetCsgObjectRef()) {
        equal &= csgp.getCsgObjectRef().equals(getCsgObjectRef());
      }
    }
    return equal;
  }


  /**
   * Returns the value of csgObjectRef
   *
   * @return the value of csgObjectRef
   */
  public String getCsgObjectRef() {
    if (isSetCsgObjectRef()) {
      return csgObjectRef;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.primitiveType, this);
  }


  /**
   * Returns whether csgObjectRef is set
   *
   * @return whether csgObjectRef is set
   */
  public boolean isSetCsgObjectRef() {
    return csgObjectRef != null;
  }


  /**
   * Sets the value of csgObjectRef
   */
  public void setCsgObjectRef(String csgObjectRef) {
    String oldCsgObjectRef = this.csgObjectRef;
    this.csgObjectRef = csgObjectRef;
    firePropertyChange(SpatialConstants.primitiveType, oldCsgObjectRef, this.csgObjectRef);
  }


  /**
   * Unsets the variable csgObjectRef
   *
   * @return {@code true}, if csgObjectRef was set before,
   *         otherwise {@code false}
   */
  public boolean unsetCsgObjectRef() {
    if (isSetCsgObjectRef()) {
      String oldCsgObjectRef = csgObjectRef;
      csgObjectRef = null;
      firePropertyChange(SpatialConstants.primitiveType, oldCsgObjectRef, csgObjectRef);
      return true;
    }
    return false;
  }


  @Override
  public int hashCode() {
    final int prime = 983;//Change this prime number
    int hashCode = super.hashCode();
    if (isSetCsgObjectRef()) {
      hashCode += prime * getCsgObjectRef().hashCode();
    }
    return hashCode;
  }


  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetCsgObjectRef()) {
      attributes.remove("csgObjectRef");
      attributes.put(SpatialConstants.shortLabel + ":csgObjectRef", getCsgObjectRef());
    }
    return attributes;
  }


  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = (super.readAttribute(attributeName, prefix, value))
        && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.primitiveType)) {
        try {
          setCsgObjectRef(value);
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.primitiveType);
        }
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CSGPseudoPrimitive [csgObjectRef=");
    builder.append(csgObjectRef);
    builder.append("]");
    return builder.toString();
  }



}
