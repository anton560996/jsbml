/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2015  jointly by the following organizations:
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

import java.util.Map;

import org.sbml.jsbml.AbstractNamedSBase;
import org.sbml.jsbml.UniqueNamedSBase;

/**
 * Introduced to FBC in version 2.
 * 
 * @author Andreas Dr&auml;ger
 * @version $Rev$
 * @since 1.1
 * @date 06.03.2015
 */
public class GeneProduct extends AbstractNamedSBase implements UniqueNamedSBase {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -6117717488126260095L;

  /**
   * 
   */
  private String geneProductIdentifier;

  /**
   * 
   */
  public GeneProduct() {
    super();
  }

  /**
   * @param nsb
   */
  public GeneProduct(GeneProduct nsb) {
    super(nsb);
  }

  /**
   * @param level
   * @param version
   */
  public GeneProduct(int level, int version) {
    super(level, version);
  }

  /**
   * @param id
   */
  public GeneProduct(String id) {
    super(id);
  }

  /**
   * @param id
   * @param level
   * @param version
   */
  public GeneProduct(String id, int level, int version) {
    super(id, level, version);
  }

  /**
   * @param id
   * @param name
   * @param level
   * @param version
   */
  public GeneProduct(String id, String name, int level, int version) {
    super(id, name, level, version);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#clone()
   */
  @Override
  public GeneProduct clone() {
    return new GeneProduct(this);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    GeneProduct other = (GeneProduct) obj;
    if (geneProductIdentifier == null) {
      if (other.geneProductIdentifier != null) {
        return false;
      }
    } else if (!geneProductIdentifier.equals(other.geneProductIdentifier)) {
      return false;
    }
    return true;
  }

  /**
   * Returns the value of geneProductIdentifier
   *
   * @return the value of geneProductIdentifier
   */
  public String getGeneProductIdentifier() {
    if (isSetGeneProductIdentifier()) {
      return geneProductIdentifier;
    }
    return "";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((geneProductIdentifier == null) ? 0 : geneProductIdentifier.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#isIdMandatory()
   */
  @Override
  public boolean isIdMandatory() {
    return true;
  }

  /**
   * Returns whether geneProductIdentifier is set
   *
   * @return whether geneProductIdentifier is set
   */
  public boolean isSetGeneProductIdentifier() {
    return geneProductIdentifier != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    // TODO Auto-generated method stub
    return super.readAttribute(attributeName, prefix, value);
  }

  /**
   * Sets the value of geneProductIdentifier
   * @param geneProductIdentifier
   */
  public void setGeneProductIdentifier(String geneProductIdentifier) {
    String oldGeneProductIdentifier = this.geneProductIdentifier;
    this.geneProductIdentifier = geneProductIdentifier;
    firePropertyChange(FBCConstants.geneProductIdentifier, oldGeneProductIdentifier, this.geneProductIdentifier);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getClass().getSimpleName());
    builder.append(" [geneProductIdentifier=");
    builder.append(geneProductIdentifier);
    builder.append(", ");
    builder.append("id=");
    builder.append(getId());
    builder.append(", ");
    builder.append("metaid=");
    builder.append(getMetaId());
    builder.append(", ");
    builder.append("name=");
    builder.append(getName());
    builder.append("]");
    return builder.toString();
  }

  /**
   * Unsets the variable geneProductIdentifier
   *
   * @return {@code true}, if geneProductIdentifier was set before,
   *         otherwise {@code false}
   */
  public boolean unsetGeneProductIdentifier() {
    if (isSetGeneProductIdentifier()) {
      String oldGeneProductIdentifier = geneProductIdentifier;
      geneProductIdentifier = null;
      firePropertyChange(FBCConstants.geneProductIdentifier, oldGeneProductIdentifier, geneProductIdentifier);
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    // TODO Auto-generated method stub
    return super.writeXMLAttributes();
  }

}
