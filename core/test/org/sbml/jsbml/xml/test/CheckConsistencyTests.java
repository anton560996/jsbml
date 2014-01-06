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
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.xml.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.junit.Before;
import org.junit.Test;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLError.SEVERITY;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.validator.SBMLValidator;

/**
 * 
 * @author  Nicolas Rodriguez
 * @since 0.8
 * @version $Rev$
 */
public class CheckConsistencyTests {


  public static String DATA_FOLDER = null;

  static {

    DATA_FOLDER = "test/org/sbml/jsbml/xml/test/data";

    if (System.getProperty("DATA_FOLDER") != null || System.getenv("DATA_FOLDER") != null) {
      DATA_FOLDER = System.getProperty("DATA_FOLDER");
      if (DATA_FOLDER == null) {
        DATA_FOLDER = System.getenv("DATA_FOLDER");
      }
    }
  }

  /**
   * 
   */
  @Before public void setUp() {
  }

  /**
   * Tries to validate biomodels file with id BIOMD0000000228.
   */
  @Test public void checkConsistency() throws IOException, XMLStreamException {
    String fileName = DATA_FOLDER + "/l2v4/BIOMD0000000228.xml";

    SBMLDocument doc = new SBMLReader().readSBML(fileName);

    int nbErrors = doc.checkConsistency();

    if (nbErrors > 0) {
      System.out.println("Found " + nbErrors + " errors on Biomodels 228 with the unit checking turned off.");
      doc.printErrors(System.out);
    }

    //assertTrue(nbErrors > 0); // sometimes there is an error, sometimes no errors !
    assertTrue(nbErrors == 0); // This is due to the way we are writing the Annotation, if it does not respect exactly the SBML specs
  }

  /**
   * Tries to validate biomodels file with id BIOMD0000000025, with all checks on.
   */
  @Test public void checkConsistencyAllChecks() throws IOException, XMLStreamException {
    String fileName = DATA_FOLDER + "/l2v1/BIOMD0000000025.xml";

    SBMLDocument doc = new SBMLReader().readSBML(fileName);

    doc.setConsistencyChecks(SBMLValidator.CHECK_CATEGORY.UNITS_CONSISTENCY, true);

    int nbErrors = doc.checkConsistency();

    int numRealErrors = doc.getErrorLog().getNumFailsWithSeverity(SEVERITY.ERROR);
    if (numRealErrors > 0) {
      System.out.println("# Found " + numRealErrors + " VALIDATION ERRORS !!!");
      doc.printErrors(System.out);
    }

    System.out.println("Found " + nbErrors + " errors/warnings on Biomodels 025 with the unit checking turned on.");
    assertTrue(nbErrors > 0);

    assertTrue(doc.getErrorLog().getNumFailsWithSeverity(SEVERITY.ERROR) == 0);

    assertTrue(nbErrors == doc.getErrorCount());
    assertTrue(nbErrors == doc.getErrorLog().getValidationErrors().size());

  }

  // TODO: test that the different possible consistency checks can be put on or off.
  // TODO: test the SBMLError class


  /**
   * Tries to validate biomodels file with id BIOMD0000000228.
   */
  @Test public void checkConsistency228() throws IOException, XMLStreamException {
    String fileName = DATA_FOLDER + "/l2v4/BIOMD0000000228.xml";

    SBMLDocument doc = new SBMLReader().readSBML(fileName);
    try {
      int nbErrors = doc.checkConsistency();

      System.out.println("Found " + nbErrors + " errors on Biomodels 228 with the unit checking turned off.");

    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Tries to validate biomodels file with id BIOMD0000000025.
   */
  @Test public void checkConsistency025() throws IOException, XMLStreamException {
    String fileName = DATA_FOLDER + "/l2v1/BIOMD0000000025.xml";

    SBMLDocument doc = new SBMLReader().readSBML(fileName);
    try {
      int nbErrors = doc.checkConsistency();

      System.out.println("Found " + nbErrors + " errors on Biomodels 025 with the unit checking turned off.");

    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }
}
