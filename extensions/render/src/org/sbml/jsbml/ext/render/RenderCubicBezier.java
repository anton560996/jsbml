/*
 * $Id$
 * $URL$
 *
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2013 jointly by the following organizations: 
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
package org.sbml.jsbml.ext.render;

import java.text.MessageFormat;
import java.util.Map;

import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.SBase;


/**
 * @author Eugen Netz
 * @author Alexander Diamantikos
 * @author Jakob Matthes
 * @author Jan Rudolph
 * @version $Rev$
 * @since 1.0
 * @date 08.05.2012
 */
public class RenderCubicBezier extends RenderPoint {
  
  /**
   * 
   */
  private static final long serialVersionUID = -2426257418589249467L;
  private Boolean absoluteX1, absoluteY1, absoluteZ1;
  private Boolean absoluteX2, absoluteY2, absoluteZ2;
  private Double x1, y1, z1;
  private Double x2, y2, z2;

  /**
   * Creates an RenderCubicBezier instance 
   */
  public RenderCubicBezier() {
    super();
    initDefaults();
  }
  
  /**
   * Clone constructor
   */
  public RenderCubicBezier(RenderCubicBezier obj) {
    super(obj);
    absoluteX1 = obj.absoluteX1;
    absoluteY1 = obj.absoluteY1;
    absoluteZ2 = obj.absoluteZ2;
    absoluteX2 = obj.absoluteX2;
    absoluteY2 = obj.absoluteY2;
    absoluteZ2 = obj.absoluteZ2;
    x1 = obj.x1;
    y1 = obj.y1;
    z1 = obj.z1;
    x2 = obj.x2;
    y2 = obj.y2;
    z2 = obj.z2;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#clone()
   */
  @Override
  public RenderCubicBezier clone() {
    return new RenderCubicBezier(this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#getAllowsChildren()
   */
  @Override
  public boolean getAllowsChildren() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#getChildAt(int)
   */
  @Override
  public SBase getChildAt(int childIndex) {
    if (childIndex < 0) {
      throw new IndexOutOfBoundsException(childIndex + " < 0");
    }
    int pos = 0;
    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}", childIndex,
      +((int) Math.min(pos, 0))));
  }
  
  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#getChildCount()
   */
  @Override
  public int getChildCount() {
    return 0;
  }

  /**
   * @return the value of x1
   */
  public double getX1() {
    if (isSetX1()) {
      return x1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.x1, this);
  }
    
  /**
   * @return the value of x2
   */
  public double getX2() {
    if (isSetX2()) {
      return x2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.x2, this);
  }

  /**
   * @return the value of y1
   */
  public double getY1() {
    if (isSetY1()) {
      return y1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.y1, this);
  }

  /**
   * @return the value of y2
   */
  public double getY2() {
    if (isSetY2()) {
      return y2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.y2, this);
  }

  /**
   * @return the value of z1
   */
  public double getZ1() {
    if (isSetZ1()) {
      return z1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.z1, this);
  }

  /**
   * @return the value of z2
   */
  public double getZ2() {
    if (isSetZ2()) {
      return z2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.z2, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#initDefaults()
   */
  @Override
  public void initDefaults() {
    addNamespace(RenderConstants.namespaceURI);
    z1 = 0d;
    z2 = 0d;
  }

  /**
   * @return the value of absoluteX1
   */
  public boolean isAbsoluteX1() {
    if (isSetAbsoluteX1()) {
      return absoluteX1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteX1, this);
  }

  /**
   * @return the value of absoluteX2
   */
  public boolean isAbsoluteX2() {
    if (isSetAbsoluteX2()) {
      return absoluteX2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteX2, this);
  }

  /**
   * @return the value of absoluteY1
   */
  public boolean isAbsoluteY1() {
    if (isSetAbsoluteY1()) {
      return absoluteY1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteY1, this);
  }

  /**
   * @return the value of absoluteY2
   */
  public boolean isAbsoluteY2() {
    if (isSetAbsoluteY2()) {
      return absoluteY2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteY2, this);
  }

  /**
   * @return the value of absoluteZ1
   */
  public boolean isAbsoluteZ1() {
    if (isSetAbsoluteZ1()) {
      return absoluteZ1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteZ1, this);
  }

  /**
   * @return the value of absoluteZ2
   */
  public boolean isAbsoluteZ2() {
    if (isSetAbsoluteZ2()) {
      return absoluteZ2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteZ2, this);
  }
  
  /**
   * @return whether absoluteX1 is set 
   */
  public boolean isSetAbsoluteX1() {
    return this.absoluteX1 != null;
  }

  /**
   * @return whether absoluteX2 is set 
   */
  public boolean isSetAbsoluteX2() {
    return this.absoluteX2 != null;
  }

  /**
   * @return whether absoluteY1 is set 
   */
  public boolean isSetAbsoluteY1() {
    return this.absoluteY1 != null;
  }

  /**
   * @return whether absoluteY2 is set 
   */
  public boolean isSetAbsoluteY2() {
    return this.absoluteY2 != null;
  }

  /**
   * @return whether absoluteZ1 is set 
   */
  public boolean isSetAbsoluteZ1() {
    return this.absoluteZ1 != null;
  }

  /**
   * @return whether absoluteZ2 is set 
   */
  public boolean isSetAbsoluteZ2() {
    return this.absoluteZ2 != null;
  }

  /**
   * @return whether x1 is set 
   */
  public boolean isSetX1() {
    return this.x1 != null;
  }

  /**
   * @return whether x2 is set 
   */
  public boolean isSetX2() {
    return this.x2 != null;
  }
  
  /**
   * @return whether y1 is set 
   */
  public boolean isSetY1() {
    return this.y1 != null;
  }

  /**
   * @return whether y2 is set 
   */
  public boolean isSetY2() {
    return this.y2 != null;
  }

  /**
   * @return whether z1 is set 
   */
  public boolean isSetZ1() {
    return this.z1 != null;
  }

  /**
   * @return whether z2 is set 
   */
  public boolean isSetZ2() {
    return this.z2 != null;
  }
  
  /**
   * Set the value of absoluteX1
   */
  public void setAbsoluteX1(boolean absoluteX1) {
    Boolean oldAbsoluteX1 = this.absoluteX1;
    this.absoluteX1 = absoluteX1;
    firePropertyChange(RenderConstants.absoluteX1, oldAbsoluteX1, this.absoluteX1);
  }

  /**
   * Set the value of absoluteX2
   */
  public void setAbsoluteX2(boolean absoluteX2) {
    Boolean oldAbsoluteX2 = this.absoluteX2;
    this.absoluteX2 = absoluteX2;
    firePropertyChange(RenderConstants.absoluteX2, oldAbsoluteX2, this.absoluteX2);
  }

  /**
   * Set the value of absoluteY1
   */
  public void setAbsoluteY1(boolean absoluteY1) {
    Boolean oldAbsoluteY1 = this.absoluteY1;
    this.absoluteY1 = absoluteY1;
    firePropertyChange(RenderConstants.absoluteY1, oldAbsoluteY1, this.absoluteY1);
  }

  /**
   * Set the value of absoluteY2
   */
  public void setAbsoluteY2(boolean absoluteY2) {
    Boolean oldAbsoluteY2 = this.absoluteY2;
    this.absoluteY2 = absoluteY2;
    firePropertyChange(RenderConstants.absoluteY2, oldAbsoluteY2, this.absoluteY2);
  }
  
  /**
   * Set the value of absoluteZ1
   */
  public void setAbsoluteZ1(boolean absoluteZ1) {
    Boolean oldAbsoluteZ1 = this.absoluteZ1;
    this.absoluteZ1 = absoluteZ1;
    firePropertyChange(RenderConstants.absoluteZ1, oldAbsoluteZ1, this.absoluteZ1);
  }

  /**
   * Set the value of absoluteZ2
   */
  public void setAbsoluteZ2(boolean absoluteZ2) {
    Boolean oldAbsoluteZ2 = this.absoluteZ2;
    this.absoluteZ2 = absoluteZ2;
    firePropertyChange(RenderConstants.absoluteZ2, oldAbsoluteZ2, this.absoluteZ2);
  }

  /**
   * Set the value of x1
   */
  public void setX1(Double x1) {
    Double oldX1 = this.x1;
    this.x1 = x1;
    firePropertyChange(RenderConstants.x1, oldX1, this.x1);
  }

  /**
   * Set the value of x2
   */
  public void setX2(Double x2) {
    Double oldX2 = this.x2;
    this.x2 = x2;
    firePropertyChange(RenderConstants.x2, oldX2, this.x2);
  }

  /**
   * Set the value of y1
   */
  public void setY1(Double y1) {
    Double oldY1 = this.y1;
    this.y1 = y1;
    firePropertyChange(RenderConstants.y1, oldY1, this.y1);
  }

  /**
   * Set the value of y2
   */
  public void setY2(Double y2) {
    Double oldY2 = this.y2;
    this.y2 = y2;
    firePropertyChange(RenderConstants.y2, oldY2, this.y2);
  }

  /**
   * Set the value of z1
   */
  public void setZ1(Double z1) {
    Double oldZ1 = this.z1;
    this.z1 = z1;
    firePropertyChange(RenderConstants.z1, oldZ1, this.z1);
  }

  /**
   * Set the value of z2
   */
  public void setZ2(Double z2) {
    Double oldZ2 = this.z2;
    this.z2 = z2;
    firePropertyChange(RenderConstants.z2, oldZ2, this.z2);
  }

  /**
   * Unsets the variable absoluteX1 
   * @return {@code true}, if absoluteX1 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetAbsoluteX1() {
    if (isSetAbsoluteX1()) {
      Boolean oldAbsoluteX1 = this.absoluteX1;
      this.absoluteX1 = null;
      firePropertyChange(RenderConstants.absoluteX1, oldAbsoluteX1, this.absoluteX1);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable absoluteX2 
   * @return {@code true}, if absoluteX2 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetAbsoluteX2() {
    if (isSetAbsoluteX2()) {
      Boolean oldAbsoluteX2 = this.absoluteX2;
      this.absoluteX2 = null;
      firePropertyChange(RenderConstants.absoluteX2, oldAbsoluteX2, this.absoluteX2);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable absoluteY1 
   * @return {@code true}, if absoluteY1 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetAbsoluteY1() {
    if (isSetAbsoluteY1()) {
      Boolean oldAbsoluteY1 = this.absoluteY1;
      this.absoluteY1 = null;
      firePropertyChange(RenderConstants.absoluteY1, oldAbsoluteY1, this.absoluteY1);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable absoluteY2 
   * @return {@code true}, if absoluteY2 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetAbsoluteY2() {
    if (isSetAbsoluteY2()) {
      Boolean oldAbsoluteY2 = this.absoluteY2;
      this.absoluteY2 = null;
      firePropertyChange(RenderConstants.absoluteY2, oldAbsoluteY2, this.absoluteY2);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable absoluteZ1 
   * @return {@code true}, if absoluteZ1 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetAbsoluteZ1() {
    if (isSetAbsoluteZ1()) {
      Boolean oldAbsoluteZ1 = this.absoluteZ1;
      this.absoluteZ1 = null;
      firePropertyChange(RenderConstants.absoluteZ1, oldAbsoluteZ1, this.absoluteZ1);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable absoluteZ2 
   * @return {@code true}, if absoluteZ2 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetAbsoluteZ2() {
    if (isSetAbsoluteZ2()) {
      Boolean oldAbsoluteZ2 = this.absoluteZ2;
      this.absoluteZ2 = null;
      firePropertyChange(RenderConstants.absoluteZ2, oldAbsoluteZ2, this.absoluteZ2);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable x1 
   * @return {@code true}, if x1 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetX1() {
    if (isSetX1()) {
      Double oldX1 = this.x1;
      this.x1 = null;
      firePropertyChange(RenderConstants.x1, oldX1, this.x1);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable x2 
   * @return {@code true}, if x2 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetX2() {
    if (isSetX2()) {
      Double oldX2 = this.x2;
      this.x2 = null;
      firePropertyChange(RenderConstants.x2, oldX2, this.x2);
      return true;
    }
    return false;
  }
  
  /**
   * Unsets the variable y1 
   * @return {@code true}, if y1 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetY1() {
    if (isSetY1()) {
      Double oldY1 = this.y1;
      this.y1 = null;
      firePropertyChange(RenderConstants.y1, oldY1, this.y1);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable y2 
   * @return {@code true}, if y2 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetY2() {
    if (isSetY2()) {
      Double oldY2 = this.y2;
      this.y2 = null;
      firePropertyChange(RenderConstants.y2, oldY2, this.y2);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable z1 
   * @return {@code true}, if z1 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetZ1() {
    if (isSetZ1()) {
      Double oldZ1 = this.z1;
      this.z1 = null;
      firePropertyChange(RenderConstants.z1, oldZ1, this.z1);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable z2 
   * @return {@code true}, if z2 was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetZ2() {
    if (isSetZ2()) {
      Double oldZ2 = this.z2;
      this.z2 = null;
      firePropertyChange(RenderConstants.z2, oldZ2, this.z2);
      return true;
    }
    return false;
  }

  
  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetX1()) {
      attributes.remove(RenderConstants.x1);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.x1,
        XMLTools.positioningToString(getX1(), isAbsoluteX1()));
    }
    if (isSetX2()) {
      attributes.remove(RenderConstants.x2);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.x2,
        XMLTools.positioningToString(getX2(), isAbsoluteX2()));
    }
    if (isSetY1()) {
      attributes.remove(RenderConstants.y1);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.y1,
        XMLTools.positioningToString(getY1(), isAbsoluteY1()));
    }
    if (isSetY2()) {
      attributes.remove(RenderConstants.y2);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.y2,
        XMLTools.positioningToString(getY2(), isAbsoluteY2()));
    }
    if (isSetZ1()) {
      attributes.remove(RenderConstants.z1);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.z1,
        XMLTools.positioningToString(getZ1(), isAbsoluteZ1()));
    }
    if (isSetZ2()) {
      attributes.remove(RenderConstants.z2);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.z2,
        XMLTools.positioningToString(getZ2(), isAbsoluteZ2()));
    }
    return attributes;
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix, value);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(RenderConstants.x1)) {
        setX1(XMLTools.parsePosition(value));
        setAbsoluteX1(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.x2)) {
        setX2(XMLTools.parsePosition(value));
        setAbsoluteX2(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.y1)) {
        setY1(XMLTools.parsePosition(value));
        setAbsoluteY1(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.y2)) {
        setY2(XMLTools.parsePosition(value));
        setAbsoluteY2(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.z1)) {
        setZ1(XMLTools.parsePosition(value));
        setAbsoluteZ1(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.z2)) {
        setZ2(XMLTools.parsePosition(value));
        setAbsoluteZ2(XMLTools.isAbsolutePosition(value));
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }
  
}
