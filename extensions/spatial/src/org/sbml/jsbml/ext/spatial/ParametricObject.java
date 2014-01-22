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

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.PropertyUndefinedError;


/**
 * @author Alex Thomas
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev$
 */
public class ParametricObject extends AbstractSpatialNamedSBase {

  private String polygonType;
  private String domain;
  private PolygonObject polygonObject;
  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -8093810090391511545L;


  public ParametricObject() {
    super();
  }


  /**
   * @param node
   */
  public ParametricObject(ParametricObject po) {
    super(po);

    if (po.isSetDomain()) {
      domain = new String(po.getDomain());
    }

    if (po.isSetPolygonType()) {
      polygonType = new String(po.getPolygonType());
    }

    if (po.isSetPolygonObject()) {
      polygonObject = po.getPolygonObject().clone();
    }

  }


  /**
   * @param level
   * @param version
   */
  public ParametricObject(int level, int version) {
    super(level, version);
  }


  /**
   * 
   * @param id
   * @param level
   * @param version
   */
  public ParametricObject(String id, int level, int version) {
    super(id, level, version);
  }


  @Override
  public ParametricObject clone() {
    return new ParametricObject(this);
  }


  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      ParametricObject po = (ParametricObject) object;

      equal &= po.isSetDomain() == isSetDomain();
      if (equal && isSetDomain()) {
        equal &= po.getDomain().equals(getDomain());
      }

      equal &= po.isSetPolygonType() == isSetPolygonType();
      if (equal && isSetPolygonType()) {
        equal &= po.getPolygonType().equals(getPolygonType());
      }

      equal &= po.isSetPolygonObject() == isSetPolygonObject();
      if (equal && isSetPolygonObject()) {
        equal &= po.getPolygonObject().equals(getPolygonObject());
      }
    }
    return equal;
  }


  /**
   * Returns the value of polygonType
   *
   * @return the value of polygonType
   */
  public String getPolygonType() {
    if (isSetPolygonType()) {
      return polygonType;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.polygonType, this);
  }


  /**
   * Returns whether polygonType is set
   *
   * @return whether polygonType is set
   */
  public boolean isSetPolygonType() {
    return polygonType != null;
  }


  /**
   * Sets the value of polygonType
   */
  public void setPolygonType(String polygonType) {
    String oldPolygonType = this.polygonType;
    this.polygonType = polygonType;
    firePropertyChange(SpatialConstants.polygonType, oldPolygonType, this.polygonType);
  }


  /**
   * Unsets the variable polygonType
   *
   * @return {@code true}, if polygonType was set before,
   *         otherwise {@code false}
   */
  public boolean unsetPolygonType() {
    if (isSetPolygonType()) {
      String oldPolygonType = polygonType;
      polygonType = null;
      firePropertyChange(SpatialConstants.polygonType, oldPolygonType, polygonType);
      return true;
    }
    return false;
  }

  /**
   * Returns the value of domain
   *
   * @return the value of domain
   */
  public String getDomain() {
    if (isSetDomain()) {
      return domain;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.domain, this);
  }


  /**
   * Returns whether domain is set
   *
   * @return whether domain is set
   */
  public boolean isSetDomain() {
    return domain != null;
  }


  /**
   * Sets the value of domain
   */
  public void setDomain(String domain) {
    String oldDomain = this.domain;
    this.domain = domain;
    firePropertyChange(SpatialConstants.domain, oldDomain, this.domain);
  }


  /**
   * Unsets the variable domain
   *
   * @return {@code true}, if domain was set before,
   *         otherwise {@code false}
   */
  public boolean unsetDomain() {
    if (isSetDomain()) {
      String oldDomain = domain;
      domain = null;
      firePropertyChange(SpatialConstants.domain, oldDomain, domain);
      return true;
    }
    return false;
  }

  /**
   * Returns the value of polygonObject
   *
   * @return the value of polygonObject
   */
  public PolygonObject getPolygonObject() {
    if (isSetPolygonObject()) {
      return polygonObject;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.polygonObject, this);
  }


  /**
   * Returns whether polygonObject is set
   *
   * @return whether polygonObject is set
   */
  public boolean isSetPolygonObject() {
    return polygonObject != null;
  }


  /**
   * Sets the value of polygonObject
   */
  public void setPolygonObject(PolygonObject polygonObject) {
    PolygonObject oldPolygonObject = this.polygonObject;
    this.polygonObject = polygonObject;
    firePropertyChange(SpatialConstants.polygonObject, oldPolygonObject, this.polygonObject);
  }


  /**
   * Unsets the variable polygonObject
   *
   * @return {@code true}, if polygonObject was set before,
   *         otherwise {@code false}
   */
  public boolean unsetPolygonObject() {
    if (isSetPolygonObject()) {
      PolygonObject oldPolygonObject = polygonObject;
      polygonObject = null;
      firePropertyChange(SpatialConstants.polygonObject, oldPolygonObject, polygonObject);
      return true;
    }
    return false;
  }


  @Override
  public boolean getAllowsChildren() {
    return true;
  }


  @Override
  public int getChildCount() {
    int count = super.getChildCount();
    if (isSetPolygonObject()) {
      count++;
    }
    return count;
  }


  @Override
  public TreeNode getChildAt(int index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException(index + " < 0");
    }
    int count = super.getChildCount(), pos = 0;
    if (index < count) {
      return super.getChildAt(index);
    } else {
      index -= count;
    }
    if (isSetPolygonObject()) {
      if (pos == index) {
        return getPolygonObject();
      }
      pos++;
    }
    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}", index,
      +Math.min(pos, 0)));
  }


  @Override
  public int hashCode() {
    final int prime = 983;//Change this prime number
    int hashCode = super.hashCode();
    if (isSetDomain()) {
      hashCode += prime * getDomain().hashCode();
    }
    if (isSetPolygonType()) {
      hashCode += prime * getPolygonType().hashCode();
    }
    return hashCode;
  }


  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetDomain()) {
      attributes.remove("domain");
      attributes.put(SpatialConstants.shortLabel + ":domain", getDomain());
    }

    if (isSetPolygonType()) {
      attributes.remove("polygonType");
      attributes.put(SpatialConstants.shortLabel + ":polygonType",
        getPolygonType());
    }

    return attributes;
  }


  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = (super.readAttribute(attributeName, prefix, value))
        && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.domain)) {
        try {
          setDomain(value);
        } catch (Exception e) {
          MessageFormat.format(SpatialConstants.bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.domain);
        }
      }

      else if (attributeName.equals(SpatialConstants.polygonType)) {
        try {
          setPolygonType(value);
        } catch (Exception e) {
          MessageFormat.format(SpatialConstants.bundle.getString("COULD_NOT_READ"), value, SpatialConstants.polygonType);
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
    builder.append("ParametricObject [polygonType=");
    builder.append(polygonType);
    builder.append(", domain=");
    builder.append(domain);
    builder.append("]");
    return builder.toString();
  }



}
