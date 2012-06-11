/*
 * $Id$
 * $URL$
 *
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2012 jointly by the following organizations: 
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
public class Rectangle extends GraphicalPrimitive2D implements Point3D {
  /**
   * 
   */
  private static final long serialVersionUID = -4314411828208615411L;
  private Double x, y, z;
  private Double width, height;
  private Boolean absoluteX, absoluteY, absoluteZ;
  private Boolean absoluteWidth, absoluteHeight;
  private Double rx, ry;
  private Boolean absoluteRx, absoluteRy;

  /**
   * @return the value of rx
   */
  public double getRx() {
    if (isSetRx()) {
      return rx;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.rx, this);
  }

  /**
   * @return whether rx is set 
   */
  public boolean isSetRx() {
    return this.rx != null;
  }

  /**
   * Set the value of rx
   */
  public void setRx(Double rx) {
    Double oldRx = this.rx;
    this.rx = rx;
    firePropertyChange(RenderConstants.rx, oldRx, this.rx);
  }

  /**
   * Unsets the variable rx 
   * @return <code>true</code>, if rx was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetRx() {
    if (isSetRx()) {
      Double oldRx = this.rx;
      this.rx = null;
      firePropertyChange(RenderConstants.rx, oldRx, this.rx);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of ry
   */
  public double getRy() {
    if (isSetRy()) {
      return ry;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.ry, this);
  }

  /**
   * @return whether ry is set 
   */
  public boolean isSetRy() {
    return this.ry != null;
  }

  /**
   * Set the value of ry
   */
  public void setRy(Double ry) {
    Double oldRy = this.ry;
    this.ry = ry;
    firePropertyChange(RenderConstants.ry, oldRy, this.ry);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.Point3D#isAbsoluteX()
   */
  //@Override
  public boolean isAbsoluteX() {
    if (isSetAbsoluteX()) {
      return absoluteX;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteX, this);
  }

  /**
   * @return whether absoluteX is set 
   */
  //@Override
  public boolean isSetAbsoluteX() {
    return this.absoluteX != null;
  }

  /**
   * Set the value of absoluteX
   */
  //@Override
  public void setAbsoluteX(Boolean absoluteX) {
    Boolean oldAbsoluteX = this.absoluteX;
    this.absoluteX = absoluteX;
    firePropertyChange(RenderConstants.absoluteX, oldAbsoluteX, this.absoluteX);
  }

  /**
   * Unsets the variable absoluteX 
   * @return <code>true</code>, if absoluteX was set before, 
   *         otherwise <code>false</code>
   */
  //@Override
  public boolean unsetAbsoluteX() {
    if (isSetAbsoluteX()) {
      Boolean oldAbsoluteX = this.absoluteX;
      this.absoluteX = null;
      firePropertyChange(RenderConstants.absoluteX, oldAbsoluteX, this.absoluteX);
      return true;
    }
    return false;
  }

  /**
   * @return the value of absoluteY
   */
  //@Override
  public boolean isAbsoluteY() {
    if (isSetAbsoluteY()) {
      return absoluteY;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteY, this);
  }

  /**
   * @return whether absoluteY is set 
   */
  //@Override
  public boolean isSetAbsoluteY() {
    return this.absoluteY != null;
  }

  /**
   * Set the value of absoluteY
   */
  //@Override
  public void setAbsoluteY(Boolean absoluteY) {
    Boolean oldAbsoluteY = this.absoluteY;
    this.absoluteY = absoluteY;
    firePropertyChange(RenderConstants.absoluteY, oldAbsoluteY, this.absoluteY);
  }

  /**
   * Unsets the variable absoluteY 
   * @return <code>true</code>, if absoluteY was set before, 
   *         otherwise <code>false</code>
   */
  //@Override
  public boolean unsetAbsoluteY() {
    if (isSetAbsoluteY()) {
      Boolean oldAbsoluteY = this.absoluteY;
      this.absoluteY = null;
      firePropertyChange(RenderConstants.absoluteY, oldAbsoluteY, this.absoluteY);
      return true;
    }
    return false;
  }

  /**
   * @return the value of absoluteZ
   */
  //@Override
  public boolean isAbsoluteZ() {
    if (isSetAbsoluteZ()) {
      return absoluteZ;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteZ, this);
  }

  /**
   * @return whether absoluteZ is set 
   */
  //@Override
  public boolean isSetAbsoluteZ() {
    return this.absoluteZ != null;
  }

  /**
   * Set the value of absoluteZ
   */
  //@Override
  public void setAbsoluteZ(Boolean absoluteZ) {
    Boolean oldAbsoluteZ = this.absoluteZ;
    this.absoluteZ = absoluteZ;
    firePropertyChange(RenderConstants.absoluteZ, oldAbsoluteZ, this.absoluteZ);
  }

  /**
   * Unsets the variable absoluteZ 
   * @return <code>true</code>, if absoluteZ was set before, 
   *         otherwise <code>false</code>
   */
  //@Override
  public boolean unsetAbsoluteZ() {
    if (isSetAbsoluteZ()) {
      Boolean oldAbsoluteZ = this.absoluteZ;
      this.absoluteZ = null;
      firePropertyChange(RenderConstants.absoluteZ, oldAbsoluteZ, this.absoluteZ);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of absoluteRx
   */
  public boolean isAbsoluteRx() {
    if (isSetAbsoluteRx()) {
      return absoluteRx;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteRx, this);
  }

  /**
   * @return whether absoluteRx is set 
   */
  public boolean isSetAbsoluteRx() {
    return this.absoluteRx != null;
  }

  /**
   * Set the value of absoluteRx
   */
  public void setAbsoluteRx(Boolean absoluteRx) {
    Boolean oldAbsoluteRx = this.absoluteRx;
    this.absoluteRx = absoluteRx;
    firePropertyChange(RenderConstants.absoluteRx, oldAbsoluteRx, this.absoluteRx);
  }

  /**
   * Unsets the variable absoluteRx 
   * @return <code>true</code>, if absoluteRx was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetAbsoluteRx() {
    if (isSetAbsoluteRx()) {
      Boolean oldAbsoluteRx = this.absoluteRx;
      this.absoluteRx = null;
      firePropertyChange(RenderConstants.absoluteRx, oldAbsoluteRx, this.absoluteRx);
      return true;
    }
    return false;
  }

  /**
   * @return the value of absoluteRy
   */
  public boolean isAbsoluteRy() {
    if (isSetAbsoluteRy()) {
      return absoluteRy;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteRy, this);
  }

  /**
   * @return whether absoluteRy is set 
   */
  public boolean isSetAbsoluteRy() {
    return this.absoluteRy != null;
  }

  /**
   * Set the value of absoluteRy
   */
  public void setAbsoluteRy(Boolean absoluteRy) {
    Boolean oldAbsoluteRy = this.absoluteRy;
    this.absoluteRy = absoluteRy;
    firePropertyChange(RenderConstants.absoluteRy, oldAbsoluteRy, this.absoluteRy);
  }

  /**
   * Unsets the variable absoluteRy 
   * @return <code>true</code>, if absoluteRy was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetAbsoluteRy() {
    if (isSetAbsoluteRy()) {
      Boolean oldAbsoluteRy = this.absoluteRy;
      this.absoluteRy = null;
      firePropertyChange(RenderConstants.absoluteRy, oldAbsoluteRy, this.absoluteRy);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of absoluteHeight
   */
  public boolean isAbsoluteHeight() {
    if (isSetAbsoluteHeight()) {
      return absoluteHeight;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteHeight, this);
  }

  /**
   * @return whether absoluteHeight is set 
   */
  public boolean isSetAbsoluteHeight() {
    return this.absoluteHeight != null;
  }

  /**
   * Set the value of absoluteHeight
   */
  public void setAbsoluteHeight(Boolean absoluteHeight) {
    Boolean oldAbsoluteHeight = this.absoluteHeight;
    this.absoluteHeight = absoluteHeight;
    firePropertyChange(RenderConstants.absoluteHeight, oldAbsoluteHeight, this.absoluteHeight);
  }

  /**
   * Unsets the variable absoluteHeight 
   * @return <code>true</code>, if absoluteHeight was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetAbsoluteHeight() {
    if (isSetAbsoluteHeight()) {
      Boolean oldAbsoluteHeight = this.absoluteHeight;
      this.absoluteHeight = null;
      firePropertyChange(RenderConstants.absoluteHeight, oldAbsoluteHeight, this.absoluteHeight);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of absoluteWidth
   */
  public boolean isAbsoluteWidth() {
    if (isSetAbsoluteWidth()) {
      return absoluteWidth;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteWidth, this);
  }

  /**
   * @return whether absoluteWidth is set 
   */
  public boolean isSetAbsoluteWidth() {
    return this.absoluteWidth != null;
  }

  /**
   * Set the value of absoluteWidth
   */
  public void setAbsoluteWidth(Boolean absoluteWidth) {
    Boolean oldAbsoluteWidth = this.absoluteWidth;
    this.absoluteWidth = absoluteWidth;
    firePropertyChange(RenderConstants.absoluteWidth, oldAbsoluteWidth, this.absoluteWidth);
  }

  /**
   * Unsets the variable absoluteWidth 
   * @return <code>true</code>, if absoluteWidth was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetAbsoluteWidth() {
    if (isSetAbsoluteWidth()) {
      Boolean oldAbsoluteWidth = this.absoluteWidth;
      this.absoluteWidth = null;
      firePropertyChange(RenderConstants.absoluteWidth, oldAbsoluteWidth, this.absoluteWidth);
      return true;
    }
    return false;
  }

  /**
   * Unsets the variable ry 
   * @return <code>true</code>, if ry was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetRy() {
    if (isSetRy()) {
      Double oldRy = this.ry;
      this.ry = null;
      firePropertyChange(RenderConstants.ry, oldRy, this.ry);
      return true;
    }
    return false;
  }

  /**
   * @return the value of x
   */
  //@Override
  public double getX() {
    if (isSetX()) {
      return x;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.x, this);
  }

  /**
   * @return whether x is set 
   */
  //@Override
  public boolean isSetX() {
    return this.x != null;
  }

  /**
   * Set the value of x
   */
  //@Override
  public void setX(Double x) {
    Double oldX = this.x;
    this.x = x;
    firePropertyChange(RenderConstants.x, oldX, this.x);
  }

  /**
   * Unsets the variable x 
   * @return <code>true</code>, if x was set before, 
   *         otherwise <code>false</code>
   */
  //@Override
  public boolean unsetX() {
    if (isSetX()) {
      Double oldX = this.x;
      this.x = null;
      firePropertyChange(RenderConstants.x, oldX, this.x);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of y
   */
  //@Override
  public double getY() {
    if (isSetY()) {
      return y;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.y, this);
  }

  /**
   * @return whether y is set 
   */
  //@Override
  public boolean isSetY() {
    return this.y != null;
  }

  /**
   * Set the value of y
   */
  //@Override
  public void setY(Double y) {
    Double oldY = this.y;
    this.y = y;
    firePropertyChange(RenderConstants.y, oldY, this.y);
  }

  /**
   * Unsets the variable y 
   * @return <code>true</code>, if y was set before, 
   *         otherwise <code>false</code>
   */
  //@Override
  public boolean unsetY() {
    if (isSetY()) {
      Double oldY = this.y;
      this.y = null;
      firePropertyChange(RenderConstants.y, oldY, this.y);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of z
   */
  //@Override
  public double getZ() {
    if (isSetZ()) {
      return z;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.z, this);
  }

  /**
   * @return whether z is set 
   */
  //@Override
  public boolean isSetZ() {
    return this.z != null;
  }

  /**
   * Set the value of z
   */
  //@Override
  public void setZ(Double z) {
    Double oldZ = this.z;
    this.z = z;
    firePropertyChange(RenderConstants.z, oldZ, this.z);
  }

  /**
   * Unsets the variable z 
   * @return <code>true</code>, if z was set before, 
   *         otherwise <code>false</code>
   */
  //@Override
  public boolean unsetZ() {
    if (isSetZ()) {
      Double oldZ = this.z;
      this.z = null;
      firePropertyChange(RenderConstants.z, oldZ, this.z);
      return true;
    }
    return false;
  }

  /**
   * @return the value of height
   */
  public double getHeight() {
    if (isSetHeight()) {
      return height;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.height, this);
  }

  /**
   * @return whether height is set 
   */
  public boolean isSetHeight() {
    return this.height != null;
  }

  /**
   * Set the value of height
   */
  public void setHeight(Double height) {
    Double oldHeight = this.height;
    this.height = height;
    firePropertyChange(RenderConstants.height, oldHeight, this.height);
  }

  /**
   * Unsets the variable height 
   * @return <code>true</code>, if height was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetHeight() {
    if (isSetHeight()) {
      Double oldHeight = this.height;
      this.height = null;
      firePropertyChange(RenderConstants.height, oldHeight, this.height);
      return true;
    }
    return false;
  }
  
  /**
   * @return the value of width
   */
  public double getWidth() {
    if (isSetWidth()) {
      return width;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.width, this);
  }

  /**
   * @return whether width is set 
   */
  public boolean isSetWidth() {
    return this.width != null;
  }

  /**
   * Set the value of width
   */
  public void setWidth(Double width) {
    Double oldWidth = this.width;
    this.width = width;
    firePropertyChange(RenderConstants.width, oldWidth, this.width);
  }

  /**
   * Unsets the variable width 
   * @return <code>true</code>, if width was set before, 
   *         otherwise <code>false</code>
   */
  public boolean unsetWidth() {
    if (isSetWidth()) {
      Double oldWidth = this.width;
      this.width = null;
      firePropertyChange(RenderConstants.width, oldWidth, this.width);
      return true;
    }
    return false;
  }
  
  /**
   * Creates an Rectangle instance 
   */
  public Rectangle() {
    super();
    initDefaults();
  }

  /**
   * Clone constructor
   */
  public Rectangle(Rectangle obj) {
    super(obj);
    x = obj.x;
    y = obj.y;
    z = obj.z;
    rx = obj.rx;
    ry = obj.ry;
    height = obj.height;
    width = obj.width;
    absoluteX = obj.absoluteX;
    absoluteY = obj.absoluteY;
    absoluteZ = obj.absoluteZ;
    absoluteHeight = obj.absoluteHeight;
    absoluteWidth = obj.absoluteWidth;
    absoluteRx = obj.absoluteRx;
    absoluteRy = obj.absoluteRy;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.GraphicalPrimitive2D#clone()
   */
  @Override
  public Rectangle clone() {
    return new Rectangle(this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.GraphicalPrimitive2D#initDefaults()
   */
  @Override
  public void initDefaults() {
    addNamespace(RenderConstants.namespaceURI);
    z = 0d;
    rx = 0d;
    ry = 0d;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.GraphicalPrimitive1D#getAllowsChildren()
   */
  @Override
  public boolean getAllowsChildren() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.GraphicalPrimitive1D#getChildCount()
   */
  @Override
  public int getChildCount() {
    return 0;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.GraphicalPrimitive1D#getChildAt(int)
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
   * @see org.sbml.jsbml.AbstractSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (isSetX()) {
      attributes.remove(RenderConstants.x);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.x,
        XMLTools.positioningToString(getX(), isAbsoluteX()));
    }
    if (isSetY()) {
      attributes.remove(RenderConstants.y);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.y,
        XMLTools.positioningToString(getY(), isAbsoluteY()));
    }
    if (isSetZ()) {
      attributes.remove(RenderConstants.z);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.z,
        XMLTools.positioningToString(getZ(), isAbsoluteZ()));
    }
    if (isSetWidth()) {
      attributes.remove(RenderConstants.width);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.width,
        XMLTools.positioningToString(getWidth(), isAbsoluteWidth()));
    }
    if (isSetHeight()) {
      attributes.remove(RenderConstants.height);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.height,
        XMLTools.positioningToString(getHeight(), isAbsoluteHeight()));
    }
    if (isSetRx()) {
      attributes.remove(RenderConstants.rx);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.rx,
        XMLTools.positioningToString(getRx(), isAbsoluteRx()));
    }
    if (isSetRy()) {
      attributes.remove(RenderConstants.ry);
      attributes.put(RenderConstants.shortLabel + ":" + RenderConstants.ry,
        XMLTools.positioningToString(getRy(), isAbsoluteRy()));
    }
    
    return attributes;
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix, value);
    if (!isAttributeRead) {
      isAttributeRead = true;
      // TODO: catch Exception if Enum.valueOf fails, generate logger output
      if (attributeName.equals(RenderConstants.cx)) {
        setX(XMLTools.parsePosition(value));
        setAbsoluteX(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.cy)) {
        setY(XMLTools.parsePosition(value));
        setAbsoluteY(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.cz)) {
        setZ(XMLTools.parsePosition(value));
        setAbsoluteZ(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.width)) {
        setWidth(XMLTools.parsePosition(value));
        setAbsoluteWidth(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.height)) {
        setHeight(XMLTools.parsePosition(value));
        setAbsoluteHeight(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.rx)) {
        setRx(XMLTools.parsePosition(value));
        setAbsoluteRx(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.ry)) {
        setRy(XMLTools.parsePosition(value));
        setAbsoluteRy(XMLTools.isAbsolutePosition(value));
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }

}
