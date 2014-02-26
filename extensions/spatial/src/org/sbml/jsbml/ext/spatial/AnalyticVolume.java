/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2014 jointly by the following organizations:
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
import java.util.regex.Pattern;

import org.sbml.jsbml.AbstractMathContainer;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.util.ResourceManager;
import org.sbml.jsbml.util.StringTools;
import org.sbml.jsbml.util.TreeNodeChangeEvent;

/**
 * @author Alex Thomas
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev$
 */
public class AnalyticVolume extends AbstractMathContainer implements SpatialNamedSBase{

  public static enum FunctionType {
    /**
     * Math child element contains an inequality
     */
    LAYERED,
    /**
     * Shape is represented by a real-valued function whose sign
     * indicates coverage by the shap
     */
    R_FUNCTION;
  }

  String spatialId;
  private String domainType;
  private FunctionType functionType;
  private Integer ordinal;

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = 1757917501241390228L;

  private static final ResourceBundle bundle = ResourceManager.getBundle("org.sbml.jsbml.ext.spatial.Messages");


  /**
   * 
   */
  public AnalyticVolume() {
    super();
  }

  /**
   * @param node
   */
  public AnalyticVolume(AnalyticVolume node) {
    super(node);
    if (node.isSetSpatialId()) {
      spatialId = new String(node.getSpatialId());
    }
    if (node.isSetOrdinal()) {
      ordinal = new Integer(node.getOrdinal());
    }
    if (node.isSetFunctionType()) {
      functionType = node.getFunctionType();
    }
  }

  public AnalyticVolume(String id, int level, int version)
  {
    super(level,version);
    spatialId = id;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractTreeNode#clone()
   */
  @Override
  public AnalyticVolume clone() {
    return new AnalyticVolume(this);
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      AnalyticVolume av = (AnalyticVolume) object;
      equal &= av.isSetSpatialId() == isSetSpatialId();
      if (equal && isSetSpatialId()) {
        equal &= av.getSpatialId().equals(getSpatialId());
      }
      equal &= av.isSetOrdinal() == isSetOrdinal();
      if (equal && isSetOrdinal()) {
        equal &= av.getOrdinal() == getOrdinal();
      }
      equal &= av.isSetDomainType() == isSetDomainType();
      if (equal && isSetDomainType()) {
        equal &= av.getDomainType() == getDomainType();
      }
      equal &= av.isSetFunctionType() == isSetFunctionType();
      if (equal && isSetFunctionType()) {
        equal &= av.getFunctionType() == getFunctionType();
      }
    }
    return equal;
  }


  /**
   * Returns the value of functionType
   *
   * @return the value of functionType
   */
  public FunctionType getFunctionType() {
    if (isSetFunctionType()) {
      return functionType;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.functionType, this);
  }


  /**
   * Returns whether functionType is set
   *
   * @return whether functionType is set
   */
  public boolean isSetFunctionType() {
    return functionType != null;
  }


  /**
   * Sets the value of functionType
   */
  public void setFunctionType(FunctionType functionType) {
    FunctionType oldFunctionType = this.functionType;
    this.functionType = functionType;
    firePropertyChange(SpatialConstants.functionType, oldFunctionType, this.functionType);
  }


  /**
   * Unsets the variable functionType
   *
   * @return {@code true}, if functionType was set before,
   *         otherwise {@code false}
   */
  public boolean unsetFunctionType() {
    if (isSetFunctionType()) {
      FunctionType oldFunctionType = functionType;
      functionType = null;
      firePropertyChange(SpatialConstants.functionType, oldFunctionType, functionType);
      return true;
    }
    return false;
  }


  /**
   * Returns the value of domainType
   *
   * @return the value of domainType
   */
  public String getDomainType() {
    if (isSetDomainType()) {
      return domainType;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.domainType, this);
  }


  /**
   * Returns whether domainType is set
   *
   * @return whether domainType is set
   */
  public boolean isSetDomainType() {
    return domainType != null;
  }


  /**
   * Sets the value of domainType
   */
  public void setDomainType(String domainType) {
    String oldDomainType = this.domainType;
    this.domainType = domainType;
    firePropertyChange(SpatialConstants.domainType, oldDomainType, this.domainType);
  }


  /**
   * Unsets the variable domainType
   *
   * @return {@code true}, if domainType was set before,
   *         otherwise {@code false}
   */
  public boolean unsetDomainType() {
    if (isSetDomainType()) {
      String oldDomainType = domainType;
      domainType = null;
      firePropertyChange(SpatialConstants.domainType, oldDomainType, domainType);
      return true;
    }
    return false;
  }


  /**
   * Returns the value of ordinal
   *
   * @return the value of ordinal
   */
  public Integer getOrdinal() {
    if (isSetOrdinal()) {
      return ordinal;
    }
    return null;
  }


  /**
   * Returns whether ordinal is set
   *
   * @return whether ordinal is set
   */
  public boolean isSetOrdinal() {
    return ordinal != null;
  }


  /**
   * Sets the value of ordinal
   */
  public void setOrdinal(int ordinal) {
    int oldOrdinal = this.ordinal;
    this.ordinal = ordinal;
    firePropertyChange(SpatialConstants.ordinal, oldOrdinal, this.ordinal);
  }


  /**
   * Unsets the variable ordinal
   *
   * @return {@code true}, if ordinal was set before,
   *         otherwise {@code false}
   */
  public boolean unsetOrdinal() {
    if (isSetOrdinal()) {
      int oldOrdinal = ordinal;
      ordinal = null;
      firePropertyChange(SpatialConstants.ordinal, oldOrdinal, ordinal);
      return true;
    }
    return false;
  }

  @Override
  public boolean unsetSpatialId() {
    if (isSetSpatialId()) {
      String oldSpatialId = spatialId;
      spatialId = null;
      firePropertyChange(SpatialConstants.spatialId, oldSpatialId, spatialId);
      return true;
    }
    return false;
  }

  @Override
  public void setSpatialId(String spatialId) {
    String oldSpatialId = this.spatialId;
    this.spatialId = spatialId;
    firePropertyChange(SpatialConstants.spatialId, oldSpatialId, this.spatialId);
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.ext.spatial.SpatialNamedSBase#isSetSpatialId()
   */
  @Override
  public boolean isSetSpatialId() {
    return spatialId != null;
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.ext.spatial.SpatialNamedSBase#getSpatialId()
   */
  @Override
  public String getSpatialId() {
    if (isSetSpatialId()) {
      return spatialId;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.spatialId, this);
  }


  @Override
  public int hashCode() {
    final int prime = 431;//Change this prime number
    int hashCode = super.hashCode();
    if (isSetSpatialId()) {
      hashCode += prime * getSpatialId().hashCode();
    }
    if (isSetFunctionType()) {
      hashCode += prime * getFunctionType().hashCode();
    }
    if (isSetDomainType()) {
      hashCode += prime * getDomainType().hashCode();
    }
    if (isSetOrdinal()) {
      hashCode += prime * getOrdinal().hashCode();
    }
    return hashCode;
  }


  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetSpatialId()) {
      attributes.remove("spatialId");
      attributes.put(SpatialConstants.shortLabel + ":spatialId", getSpatialId());
    }
    if (isSetDomainType()) {
      attributes.remove("domainType");
      attributes.put(SpatialConstants.shortLabel + ":domainType", getDomainType());
    }
    if (isSetOrdinal()) {
      attributes.remove("ordinal");
      attributes.put(SpatialConstants.shortLabel + ":ordinal", String.valueOf(getOrdinal()));
    }
    if (isSetFunctionType()) {
      attributes.remove("functionType");
      attributes.put(SpatialConstants.shortLabel + ":functionType", getFunctionType().toString());
    }
    if (isSetSBOTerm()) {
      attributes.remove(TreeNodeChangeEvent.sboTerm);
      attributes.put(SpatialConstants.shortLabel + ":" + TreeNodeChangeEvent.sboTerm, getSBOTermID());
    }
    if (isSetMetaId()) {
      attributes.remove(TreeNodeChangeEvent.metaId);
      attributes.put(SpatialConstants.shortLabel + ":" + TreeNodeChangeEvent.metaId, getMetaId());
    }
    return attributes;
  }


  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = (super.readAttribute(attributeName, prefix, value))
        && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.spatialId)) {
        try {
          setSpatialId(value);
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.spatialId);
        }
      }
      else if (attributeName.equals(SpatialConstants.ordinal)) {
        try {
          setOrdinal(StringTools.parseSBMLInt(value));
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.ordinal);
        }
      }
      else if (attributeName.equals(SpatialConstants.domainType)) {
        try {
          setDomainType(value);
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.domainType);
        }
      }
      else if (attributeName.equals(SpatialConstants.functionType)) {
        if (!Pattern.matches("[a-z]*", value)) {
          throw new SBMLException("The value is not all lower-case.");
        }
        setFunctionType(FunctionType.valueOf(value.toUpperCase()));
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
    builder.append("AnalyticVolume [spatialId=");
    builder.append(spatialId);
    builder.append(", domainType=");
    builder.append(domainType);
    builder.append(", functionType=");
    builder.append(functionType);
    builder.append(", ordinal=");
    builder.append(ordinal);
    builder.append("]");
    return builder.toString();
  }


}
