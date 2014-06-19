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
 * 5. The Babraham Institute, Cambridge, UK
 * 6. The University of Toronto, Toronto, ON, Canada
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.math;

import org.apache.log4j.Logger;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.TreeNodeChangeEvent;



/**
 * An Abstract Syntax Tree (AST) node representing the delay function
 * 
 * @author Victor Kofia
 * @version $Rev$
 * @since 1.0
 * @date May 30, 2014
 */
public class ASTCSymbolDelayNode extends ASTBinaryFunctionNode implements
ASTCSymbolNode {

  /**
   * A {@link Logger} for this class.
   */
  private static final Logger logger = Logger.getLogger(ASTCSymbolDelayNode.class);

  /**
   * name attribute for MathML element
   */
  protected String name;

  /**
   * definitionURL attribute for MathML element
   */
  protected String definitionURL;

  /**
   * encodingURL attribute for MathML element
   */
  private String encodingURL;

  /**
   * The URI for the definition of the csymbol for delay.
   */
  public static final transient String URI_DELAY_DEFINITION = "http://www.sbml.org/sbml/symbols/delay";

  /**
   * Creates a new {@link ASTCSymbolDelayNode}.
   */
  public ASTCSymbolDelayNode() {
    super();
    setName(null);
    setDefinitionURL(null);
    setEncodingURL(null);
  }

  /**
   * Copy constructor; Creates a deep copy of the given {@link ASTCSymbolDelayNode}.
   * 
   * @param astFunction
   *            the {@link ASTCSymbolDelayNode} to be copied.
   */
  public ASTCSymbolDelayNode(ASTCSymbolDelayNode node) {
    super(node);
    setName(node.getName());
    setDefinitionURL(node.getDefinitionURL());
    setEncodingURL(node.getEncodingURL());
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#getDefinitionURL()
   */
  @Override
  public String getDefinitionURL() {
    if (isSetDefinitionURL()) {
      return definitionURL;
    }
    PropertyUndefinedError error = new PropertyUndefinedError("definitionURL", this);
    if (isStrict()) {
      throw error;
    }
    logger.warn(error);
    return "";
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#getEncodingURL()
   */
  @Override
  public String getEncodingURL() {
    if (isSetEncodingURL()) {
      return encodingURL;
    }
    PropertyUndefinedError error = new PropertyUndefinedError("encodingURL", this);
    if (isStrict()) {
      throw error;
    }
    logger.warn(error);
    return "";
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#getName()
   */
  @Override
  public String getName() {
    if (isSetName()) {
      return name;
    }
    PropertyUndefinedError error = new PropertyUndefinedError("name", this);
    if (isStrict()) {
      throw error;
    }
    logger.warn(error);
    return "";
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolBaseNode#isSetDefinitionURL()
   */
  @Override
  public boolean isSetDefinitionURL() {
    return definitionURL != null;
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#isSetEncodingURL()
   */
  @Override
  public boolean isSetEncodingURL() {
    return encodingURL != null;
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolBaseNode#isSetName()
   */
  @Override
  public boolean isSetName() {
    return name != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#setDefinitionURL(java.lang.String)
   */
  @Override
  public void setDefinitionURL(String definitionURL) {
    String old = this.definitionURL;
    this.definitionURL = definitionURL;
    firePropertyChange(TreeNodeChangeEvent.definitionURL, old, encodingURL);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#setEncodingURL(java.lang.String)
   */
  @Override
  public void setEncodingURL(String encodingURL) {
    String old = this.encodingURL;
    this.encodingURL = encodingURL;
    firePropertyChange(TreeNodeChangeEvent.encoding, old, this.encodingURL);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCSymbolNode#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    // TODO: As in all set methods we will have to do more: listeners need to be notified about the change (this applies to all other classes and set methods in this package as well). Hence, we should also create an abstract version of ASTCSymbolNode in order to avoid too much code duplication. We will have to copy the code to ASTCiNumberNode because we don't have multiple inheritance, but we should have at least one abstract super class for the remaining types.
    String old = this.name;
    this.name = name;
    firePropertyChange(TreeNodeChangeEvent.name, old, this.name);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ASTCSymbolDelayNode [name=");
    builder.append(name);
    builder.append(", definitionURL=");
    builder.append(definitionURL);
    builder.append(", encodingURL=");
    builder.append(encodingURL);
    builder.append(", strict=");
    builder.append(strict);
    builder.append(", type=");
    builder.append(type);
    builder.append("]");
    return builder.toString();
  }

}
