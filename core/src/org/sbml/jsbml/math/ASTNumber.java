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

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.util.TreeNodeChangeEvent;


/**
 * An Abstract Syntax Tree (AST) node representing a number
 * in a mathematical expression.
 * 
 * @author Victor Kofia
 * @version $Rev$
 * @since 1.0
 * @date May 30, 2014
 */
// TODO: Should this class be abstract?
public class ASTNumber extends AbstractASTNode {

  /**
   * Creates a new {@link ASTNumber} that lacks a pointer
   * to its containing {@link MathContainer}.
   */
  public ASTNumber() {
    super();
    setParentSBMLObject(null);
    ASTNumber old = this;
    firePropertyChange(TreeNodeChangeEvent.initialValue, old, this);
  }

  /**
   * Copy constructor; Creates a deep copy of the given {@link ASTNumber}.
   * 
   * @param astNumber
   *            the {@link ASTNumber} to be copied.
   */
  public ASTNumber(ASTNumber astNumber) {
    super();
    setParentSBMLObject(null);
    ASTNumber old = this;
    firePropertyChange(TreeNodeChangeEvent.initialValue, old, this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractTreeNode#clone()
   */
  @Override
  public TreeNode clone() {
    // TODO: Make the return type more specific, or completely remove this method if the class becomes abstract. Cloning will not really work in derived classes, because not all properties will be copied, unless we override this method everywhere.
    return new ASTNumber(this);
  }

  /* (non-Javadoc)
   * @see javax.swing.tree.TreeNode#getAllowsChildren()
   */
  @Override
  public boolean getAllowsChildren() {
    return false;
  }

  /* (non-Javadoc)
   * @see javax.swing.tree.TreeNode#getChildAt(int)
   */
  @Override
  public ASTNumber getChildAt(int childIndex) {
    throw new IndexOutOfBoundsException();
  }

  /* (non-Javadoc)
   * @see javax.swing.tree.TreeNode#getChildCount()
   */
  @Override
  public int getChildCount() {
    return 0;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ASTNumber [strict=");
    builder.append(strict);
    builder.append(", type=");
    builder.append(type);
    builder.append("]");
    return builder.toString();
  }

}
