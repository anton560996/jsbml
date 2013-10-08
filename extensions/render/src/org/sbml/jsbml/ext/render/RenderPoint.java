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

import org.sbml.jsbml.AbstractSBase;
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
public class RenderPoint extends AbstractSBase implements Point3D {

	/**
   *
   */
  private static final long serialVersionUID = 6792387139122188270L;

	private Boolean absoluteX, absoluteY, absoluteZ;

  private Double x, y, z;

  /**
   * Creates an RenderPoint instance
   */
  public RenderPoint() {
    super();
    initDefaults();
  }

  /**
   * Clone constructor
   */
  public RenderPoint(RenderPoint obj) {
    super(obj);
    absoluteX = obj.absoluteX;
    absoluteY = obj.absoluteY;
    absoluteZ = obj.absoluteZ;
    x = obj.x;
    y = obj.y;
    z = obj.z;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#clone()
   */
  //@Override
  public RenderPoint clone() {
		return new RenderPoint(this);
	}

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#getAllowsChildren()
   */
  @Override
  public boolean getAllowsChildren() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#getChildAt(int)
   */
  @Override
  public SBase getChildAt(int childIndex) {
    if (childIndex < 0) {
      throw new IndexOutOfBoundsException(childIndex + " < 0");
    }
    int pos = 0;
    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}", childIndex,
      +Math.min(pos, 0)));
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#getChildCount()
   */
  @Override
  public int getChildCount() {
    return 0;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#getX()
   */
  //@Override
  public double getX() {
    if (isSetX()) {
      return x;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.x, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#getY()
   */
  //@Override
  public double getY() {
    if (isSetY()) {
      return y;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.y, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#getZ()
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
   * Initializes the default values using the namespace.
   */
  public void initDefaults() {
    addNamespace(RenderConstants.namespaceURI);
    z = 0d;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isAbsoluteX()
   */
  //@Override
  public boolean isAbsoluteX() {
    if (isSetAbsoluteX()) {
      return absoluteX;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteX, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isAbsoluteY()
   */
  //@Override
  public boolean isAbsoluteY() {
    if (isSetAbsoluteY()) {
      return absoluteY;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteY, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isAbsoluteZ()
   */
  //@Override
  public boolean isAbsoluteZ() {
    if (isSetAbsoluteZ()) {
      return absoluteZ;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(RenderConstants.absoluteZ, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isSetAbsoluteX()
   */
  //@Override
  public boolean isSetAbsoluteX() {
    return this.absoluteX != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isSetAbsoluteY()
   */
  //@Override
  public boolean isSetAbsoluteY() {
    return this.absoluteY != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isSetAbsoluteZ()
   */
  //@Override
  public boolean isSetAbsoluteZ() {
    return this.absoluteZ != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isSetX()
   */
  //@Override
  public boolean isSetX() {
    return this.x != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isSetY()
   */
  //@Override
  public boolean isSetY() {
    return this.y != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#isSetZ()
   */
  //@Override
  public boolean isSetZ() {
    return this.z != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#setAbsoluteX(java.lang.Boolean)
   */
  //@Override
  public void setAbsoluteX(Boolean absoluteX) {
    Boolean oldAbsoluteX = this.absoluteX;
    this.absoluteX = absoluteX;
    firePropertyChange(RenderConstants.absoluteX, oldAbsoluteX, this.absoluteX);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#setAbsoluteY(java.lang.Boolean)
   */
  //@Override
  public void setAbsoluteY(Boolean absoluteY) {
    Boolean oldAbsoluteY = this.absoluteY;
    this.absoluteY = absoluteY;
    firePropertyChange(RenderConstants.absoluteY, oldAbsoluteY, this.absoluteY);
  }

	/* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#setAbsoluteZ(java.lang.Boolean)
   */
  //@Override
  public void setAbsoluteZ(Boolean absoluteZ) {
    Boolean oldAbsoluteZ = this.absoluteZ;
    this.absoluteZ = absoluteZ;
    firePropertyChange(RenderConstants.absoluteZ, oldAbsoluteZ, this.absoluteZ);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#setX(java.lang.Double)
   */
  //@Override
  public void setX(Double x) {
    Double oldX = this.x;
    this.x = x;
    firePropertyChange(RenderConstants.x, oldX, this.x);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#setY(java.lang.Double)
   */
  //@Override
  public void setY(Double y) {
    Double oldY = this.y;
    this.y = y;
    firePropertyChange(RenderConstants.y, oldY, this.y);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#setZ(java.lang.Double)
   */
  //@Override
  public void setZ(Double z) {
    Double oldZ = this.z;
    this.z = z;
    firePropertyChange(RenderConstants.z, oldZ, this.z);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#toString()
   */
  //@Override
  @Override
  public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#unsetAbsoluteX()
   */
  public boolean unsetAbsoluteX() {
    if (isSetAbsoluteX()) {
      Boolean oldAbsoluteX = this.absoluteX;
      this.absoluteX = null;
      firePropertyChange(RenderConstants.absoluteX, oldAbsoluteX, this.absoluteX);
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#unsetAbsoluteY()
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

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#unsetAbsoluteZ()
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

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#unsetX()
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

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#unsetY()
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

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.RenderPoint#unsetZ()
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
  
  
  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetX()) {
      attributes.remove(RenderConstants.x);
      attributes.put(RenderConstants.shortLabel + ':' + RenderConstants.x,
        XMLTools.positioningToString(getX(), isAbsoluteX()));
    }
    if (isSetY()) {
      attributes.remove(RenderConstants.y);
      attributes.put(RenderConstants.shortLabel + ':' + RenderConstants.y,
        XMLTools.positioningToString(getY(), isAbsoluteY()));
    }
    if (isSetZ()) {
      attributes.remove(RenderConstants.z);
      attributes.put(RenderConstants.shortLabel + ':' + RenderConstants.z,
        XMLTools.positioningToString(getZ(), isAbsoluteZ()));
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
      if (attributeName.equals(RenderConstants.x)) {
        setX(XMLTools.parsePosition(value));
        setAbsoluteX(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.y)) {
        setY(XMLTools.parsePosition(value));
        setAbsoluteY(XMLTools.isAbsolutePosition(value));
      }
      else if (attributeName.equals(RenderConstants.z)) {
        setZ(XMLTools.parsePosition(value));
        setAbsoluteZ(XMLTools.isAbsolutePosition(value));
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }

}
