/*
 * $Id:  LinearGradientTest.java 1733 May 14, 2014 7:44:59 PM yvazirabad $
 * $URL: https://svn.code.sf.net/p/jsbml/code/trunk/extensions/render/test/org/sbml/jsbml/ext/render/test/LinearGradientTest.java $
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
 * 6. Marquette University, Milwaukee, WI, USA
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.render.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sbml.jsbml.ext.render.LinearGradient;


/**
 * @author Ibrahim Vazirabad
 * @version $Rev 1733$
 * @since 1.0
 * @date May 14, 2014
 */
public class LinearGradientTest {

  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getAllowsChildren()}.
   */
  @Test
  public void testGetAllowsChildren() {
    LinearGradient linGrad=new LinearGradient();
    assertTrue(!linGrad.getAllowsChildren());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getChildCount()}.
   */
  @Test
  public void testGetChildCount() {
    LinearGradient linGrad=new LinearGradient();
    assertEquals("getChildCountError",linGrad.getChildCount(),0);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getX1()}.
   */
  @Test
  public void testGetX1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setX1(0.02d);
    assertEquals(linGrad.getX1(),0.02d,0.0001d);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#isSetX1()}.
   */
  @Test
  public void testIsSetX1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setX1(0.02d);
    assertTrue(linGrad.isSetX1());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#setX1(java.lang.Double)}.
   */
  @Test
  public void testSetX1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setX1(0.02d);
    assertTrue(Double.compare(linGrad.getX1(),0.02d)==0);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getX2()}.
   */
  @Test
  public void testGetX2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setX2(0.02d);
    assertEquals(linGrad.getX2(),0.02d,0.0001d);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#isSetX2()}.
   */
  @Test
  public void testIsSetX2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setX2(0.02d);
    assertTrue(linGrad.isSetX2());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#setX2(java.lang.Double)}.
   */
  @Test
  public void testSetX2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY1(0.02d);
    assertTrue(Double.compare(linGrad.getY1(),0.02d)==0);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getY1()}.
   */
  @Test
  public void testGetY1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY1(0.02d);
    assertEquals(linGrad.getY1(),0.02d,0.0001d);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#isSetY1()}.
   */
  @Test
  public void testIsSetY1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY1(0.02d);
    assertTrue(linGrad.isSetY1());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#setY1(java.lang.Double)}.
   */
  @Test
  public void testSetY1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY1(0.02d);
    assertTrue(Double.compare(linGrad.getY1(),0.02d)==0);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getY2()}.
   */
  @Test
  public void testGetY2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY2(0.02d);
    assertEquals(linGrad.getY2(),0.02d,0.0001d);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#isSetY2()}.
   */
  @Test
  public void testIsSetY2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY2(0.02d);
    assertTrue(linGrad.isSetY2());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#setY2(java.lang.Double)}.
   */
  @Test
  public void testSetY2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setY2(0.02d);
    assertTrue(Double.compare(linGrad.getY2(),0.02d)==0);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getZ1()}.
   */
  @Test
  public void testGetZ1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setZ1(0.02d);
    assertEquals(linGrad.getZ1(),0.02d,0.0001d);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#isSetZ1()}.
   */
  @Test
  public void testIsSetZ1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setZ1(0.02d);
    assertTrue(linGrad.isSetZ1());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#setZ1(java.lang.Double)}.
   */
  @Test
  public void testSetZ1() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setZ1(0.02d);
    assertTrue(Double.compare(linGrad.getZ1(),0.02d)==0);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#getZ2()}.
   */
  @Test
  public void testGetZ2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setZ2(0.02d);
    assertEquals(linGrad.getZ2(),0.02d,0.0001d);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#isSetZ2()}.
   */
  @Test
  public void testIsSetZ2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setZ2(0.02d);
    assertTrue(linGrad.isSetZ2());
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.render.LinearGradient#setZ2(java.lang.Double)}.
   */
  @Test
  public void testSetZ2() {
    LinearGradient linGrad=new LinearGradient();
    linGrad.setZ2(0.02d);
    assertTrue(Double.compare(linGrad.getZ2(),0.02d)==0);
  }
}
