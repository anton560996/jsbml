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
 * 6. The University of Utah, Salt Lake City, UT, USA
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation. A copy of the license agreement is provided 
 * in the file named "LICENSE.txt" included with this software distribution 
 * and also available online as <http://sbml.org/Software/JSBML/License>. 
 * ---------------------------------------------------------------------------- 
 */
package org.sbml.jsbml.ext.arrays.validator;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLError;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.arrays.Dimension;
import org.sbml.jsbml.ext.arrays.Index;


/**
 * The {@link ArraysValidator} is used to ensure that an {@link SBMLDocument} does
 * not violate any validation rules specified in the Arrays Package Specifications.
 * 
 * @author Leandro Watanabe
 * @version $Rev$
 * @since 1.0
 * @date Jun 15, 2014
 */
public class ArraysValidator {
  
  /**
   * Validates the given SBMLDocument
   * 
   * @param document - a document that needs to be validated
   * @return a list of errors
   */
  public static List<SBMLError> validate(SBMLDocument document) {
    List<SBMLError> listOfErrors = new ArrayList<SBMLError>();

    Enumeration<TreeNode> children = document.children();
    
    while(children.hasMoreElements()) {
      TreeNode child = children.nextElement();
      validate(document.getModel(), child, listOfErrors);
    }
   
    return listOfErrors;
  }
  
  /**
   * Recursively checks all nodes in the document.
   * 
   * @param model
   * @param node
   * @param listOfErrors
   */
  private static void validate(Model model, TreeNode node, List<SBMLError> listOfErrors) {
    
    @SuppressWarnings("unchecked")
    Enumeration<TreeNode> children = node.children();
    if(node instanceof SBase) {
      listOfErrors.addAll(ExtendedSBaseValidator.validate(model, (SBase) node));
      if(node instanceof Dimension) {
        listOfErrors.addAll(DimensionValidator.validate(model, (Dimension) node));
      }
      if(node instanceof Index) {
        listOfErrors.addAll(IndexValidator.validate(model, (Index) node));
      }
      if(node instanceof MathContainer) {
        listOfErrors.addAll(ArraysMathValidator.validate(model, (MathContainer) node));
      }
    } 

    
    while(children.hasMoreElements()) {
      TreeNode child = children.nextElement();
      validate(model, child, listOfErrors);
    }
    
  }

}
