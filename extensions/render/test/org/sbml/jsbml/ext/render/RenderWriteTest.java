/*
 * $Id$
 * $URL$
 *
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2011 jointly by the following organizations: 
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

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.ext.layout.ExtendedLayoutModel;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutConstants;


/**
 * @author Florian Mittag
 * @author Andreas Dr&auml;ger
 * @version $Rev$
 * @since 0.8
 * @date 12.06.2012
 */
public class RenderWriteTest {

  /**
   * @param args
   * @throws XMLStreamException 
   * @throws SBMLException 
   */
  public static void main(String[] args) throws SBMLException, XMLStreamException {
    SBMLDocument doc = new SBMLDocument(3, 1);
    Model m = doc.createModel("m1");
    ExtendedLayoutModel elm = new ExtendedLayoutModel(m);
    m.addExtension(LayoutConstants.getNamespaceURI(m.getLevel(), m.getVersion()), elm);
    Layout l1 = elm.createLayout("l1");
    
    RenderLayoutPlugin rlp = new RenderLayoutPlugin(l1);
    rlp.createLocalRenderInformation("info1");
    l1.addExtension(RenderConstants.namespaceURI, rlp);
    
    SBMLWriter.write(doc, System.out, ' ', (short) 2);
  }
}
