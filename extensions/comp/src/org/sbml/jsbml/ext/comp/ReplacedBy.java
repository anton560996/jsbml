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
package org.sbml.jsbml.ext.comp;

import java.util.Map;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBase;

/**
 * A {@link ReplacedBy} object indicates when the parent object is to
 * be replaced by another object.
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 */
public class ReplacedBy extends SBaseRef {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -7067666288732978615L;

  private String submodelRef;

  /**
   * Creates a ReplacedBy instance
   */
  public ReplacedBy() {
    super();
    initDefaults();
  }

  /**
   * Creates a ReplacedBy instance with a level and version.
   * 
   * @param level
   * @param version
   */
  public ReplacedBy(int level, int version) {
    super(level, version);
  }

  /**
   * Clone constructor
   */
  public ReplacedBy(ReplacedBy obj) {
    super(obj);

    if (obj.isSetSubmodelRef()) {
      setSubmodelRef(obj.getSubmodelRef());
    }
  }

  /**
   * clones this class
   */
  @Override
  public ReplacedBy clone() {
    return new ReplacedBy(this);
  }

  /**
   * Initializes the default values using the namespace.
   */
  public void initDefaults() {
    setNamespace(CompConstants.namespaceURI);
  }



  /**
   * Returns the value of submodelRef
   * 
   * @return the value of submodelRef
   */
  public String getSubmodelRef() {

    if (isSetSubmodelRef()) {
      return submodelRef;
    }

    return "";
  }

  /**
   * Returns whether submodelRef is set
   * 
   * @return whether submodelRef is set
   */
  public boolean isSetSubmodelRef() {
    return submodelRef != null;
  }

  /**
   * Sets the value of the required submodelRef attribute.
   * 
   * The required attribute submodelRef takes a value which must be
   * the identifier of a {@link Submodel} object in the containing model.
   * This attribute is analogous to the corresponding attribute on
   * {@link ReplacedElement}; that is, the model referenced by the
   * {@link Submodel} object establishes the object namespaces for the portRef,
   * idRef, unitRef and metaIdRef attributes: only objects within the {@link Model}
   * object may be referenced by those attributes.
   * 
   */
  public void setSubmodelRef(String submodelRef) {
    String oldSubmodelRef = this.submodelRef;
    this.submodelRef = submodelRef;
    firePropertyChange(CompConstants.submodelRef, oldSubmodelRef, this.submodelRef);
  }

  /**
   * Unsets the variable submodelRef
   * @return {@code true}, if submodelRef was set before,
   *         otherwise {@code false}
   */
  public boolean unsetSubmodelRef() {
    if (isSetSubmodelRef()) {
      String oldSubmodelRef = submodelRef;
      submodelRef = null;
      firePropertyChange(CompConstants.submodelRef, oldSubmodelRef, submodelRef);
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ReplacedBy [submodelRef=" + submodelRef + "]";
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.comp.SBaseRef#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (isSetSubmodelRef()) {
      attributes.put(CompConstants.shortLabel + ":" + CompConstants.submodelRef, getSubmodelRef());
    }

    return attributes;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.comp.SBaseRef#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix,
    String value)
  {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix,
      value);
    if (!isAttributeRead) {
      isAttributeRead = true;

      if (attributeName.equals(CompConstants.submodelRef)) {
        setSubmodelRef(value);
      }
      else {
        isAttributeRead = false;
      }
    }

    return isAttributeRead;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractTreeNode#removeFromParent()
   */
  @Override
  public boolean removeFromParent() {
    if (parent != null && parent instanceof SBase) {
      if (((SBase) parent).isSetPlugin(CompConstants.shortLabel)) {
        CompSBasePlugin parentPlugin = (CompSBasePlugin) ((SBase) parent).getPlugin(CompConstants.shortLabel);
        return parentPlugin.unsetReplacedBy();
      }
    }

    return false;
  }
  
}
