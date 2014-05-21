/*
 * $Id:  ArraysWriteTest.java 12:57:26 PM lwatanabe $
 * $URL: ArraysWriteTest.java $
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
package org.sbml.jsbml.ext.arrays.test;

import static org.junit.Assert.assertTrue;

import javax.xml.stream.XMLStreamException;

import org.junit.Before;
import org.junit.Test;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.AssignmentRule;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.ext.arrays.ArraysConstants;
import org.sbml.jsbml.ext.arrays.ArraysSBasePlugin;
import org.sbml.jsbml.ext.arrays.Dimension;
import org.sbml.jsbml.ext.arrays.Index;


/**
 * @author Leandro Watanabe
 * @version $Rev$
 * @since 1.0
 * @date May 19, 2014
 */
public class ArraysWriteTest {

  
  final String path = "extensions/arrays/test/org/sbml/jsbml/xml/test/data/arrays/example.xml";
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    SBMLDocument doc = new SBMLDocument(3,1);
    Model model = doc.createModel();
  
    Parameter n = new Parameter("n");
    n.setValue(10);
    model.addParameter(n);

    Parameter X = new Parameter("X");

    model.addParameter(X);
    ArraysSBasePlugin arraysSBasePluginX = new ArraysSBasePlugin(X);

    X.addExtension(ArraysConstants.shortLabel, arraysSBasePluginX);

    Dimension dimX = new Dimension("i");
    dimX.setSize(n.getId());
    dimX.setArrayDimension(0);

    arraysSBasePluginX.addDimension(dimX);

    Parameter Y = new Parameter("Y");

    model.addParameter(Y);
    ArraysSBasePlugin arraysSBasePluginY = new ArraysSBasePlugin(Y);

    Y.addExtension(ArraysConstants.shortLabel, arraysSBasePluginY);
    // TODO: Implement id manager
    Dimension dimY = new Dimension("j");
    dimY.setSize(n.getId());
    dimY.setArrayDimension(0);

    arraysSBasePluginY.addDimension(dimY);

    AssignmentRule rule = new AssignmentRule();
    model.addRule(rule);
    rule.setMetaId("rule");
    
    ArraysSBasePlugin arraysSBasePluginRule = new ArraysSBasePlugin(rule);
    rule.addExtension(ArraysConstants.shortLabel, arraysSBasePluginRule);
    
    Dimension dimRule = new Dimension("k");
    dimRule.setSize(n.getId());
    dimRule.setArrayDimension(0);
    arraysSBasePluginRule.addDimension(dimRule);
    
    Index indexRule = new Index();
    indexRule.setArrayDimension(0);
    indexRule.setReferencedAttribute("variable");
    ASTNode indexMath = new ASTNode();
    
    indexMath = ASTNode.diff(new ASTNode(9), new ASTNode("i"));
    indexRule.setMath(indexMath);
    arraysSBasePluginRule.addIndex(indexRule);
    
    rule.setVariable("Y");
    ASTNode ruleMath = new ASTNode("i");
    
    rule.setMath(ruleMath);

    SBMLWriter writer = new SBMLWriter();
    writer.writeSBMLToFile(doc, path);
    SBMLWriter.write(doc, System.out, ' ', (short) 2);
  }

  /**
   * Test if given parameter's plugin was set properly.
   */
  private boolean testParameterDimension(Model model, String id, String arrayId,
                                         String arrayName, String arraySize, int arrayDimension)
  {
    boolean result = true;
    
    Parameter p = model.getParameter(id);
    
    ArraysSBasePlugin arraysSBasePluginRule = (ArraysSBasePlugin) p.getExtension("arrays");
    
    Dimension dim = arraysSBasePluginRule.getDimension(arrayId);
    
    result &= dim.isSetName() ? dim.getName().equals(arrayName) : true;
    result &= dim.getSize().equals(arraySize);
    result &= dim.getArrayDimension() == arrayDimension;
    
    return result;
    
  }
  
  /**
   * Test if given rule's plugin was set properly.
   */
  private boolean testRuleDimension(Model model, int child, String arrayId,
                                         String arrayName, String arraySize, int arrayDimension)
  {
    boolean result = true;
    
    Rule r = model.getRule(child);
    
    ArraysSBasePlugin arraysSBasePluginRule = (ArraysSBasePlugin) r.getExtension("arrays");
    
    Dimension dim = arraysSBasePluginRule.getDimension(arrayId);
    
    result &= dim.isSetName() ? dim.getName().equals(arrayName) : true;
    result &= dim.getSize().equals(arraySize);
    result &= dim.getArrayDimension() == arrayDimension;
    
    return result;
    
  }
  
  /**
   * Test if given rule's index was set properly.
   */
  private boolean testRuleIndex(Model model, int child, String arraySize, int arrayDimension, ASTNode arrayMath)
  {
    
    Rule r = model.getRule(child);
    
    ArraysSBasePlugin arraysSBasePluginRule = (ArraysSBasePlugin) r.getExtension("arrays");
    
    Index index = arraysSBasePluginRule.getIndex(arrayDimension, "variable");
    
    ASTNode math = index.getMath();
    
    return math.equals(arrayMath);
    
  }
  
  @Test
  public void arrayReadTest() {
    try {
      SBMLDocument doc = SBMLReader.read(ArraysWriteTest.class.getResourceAsStream("../../../xml/test/data/arrays/example.xml"));
      SBMLWriter.write(doc, System.out, ' ', (short) 2);
      Model model = doc.getModel();
      assertTrue(testParameterDimension(model, "X", "i", null, "n", 0));
      assertTrue(testParameterDimension(model, "Y", "j", null, "n", 0));
      assertTrue(testRuleDimension(model, 0, "k", null, "n", 0));
      assertTrue(testRuleIndex(model,0,"n", 0, ASTNode.diff(new ASTNode(9), new ASTNode("i"))));
    } catch (XMLStreamException e) {
      assertTrue(false);
    }
  }
}
