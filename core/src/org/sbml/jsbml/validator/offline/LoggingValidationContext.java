/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2017 jointly by the following organizations:
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

package org.sbml.jsbml.validator.offline;

import java.util.HashSet;
import java.util.Set;

import org.sbml.jsbml.SBMLError;
import org.sbml.jsbml.SBMLErrorLog;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.constraints.AnyConstraint;
import org.sbml.jsbml.validator.offline.constraints.CoreSpecialErrorCodes;
import org.sbml.jsbml.validator.offline.factory.SBMLErrorFactory;

/**
 * A subclass of {@link ValidationContext} which implements the
 * {@link ValidationListener} interface to track his own validation process.
 * <p>
 * A instance of this class creates a {@link SBMLErrorLog} which contains a
 * {@link SBMLError} object for every broken constraint.
 * <p>
 * The level and version parameter are
 * used to determine which rules will be checked.
 * <p>
 * For more informations about the SBML specifications look up
 * <a href="http://www.sbml.org"> sbml.org </a>
 * 
 * @author Roman
 * @author rodrigue
 * @since 1.2
 */
public class LoggingValidationContext extends ValidationContext implements ValidationListener {

  /**
   * 
   */
  private SBMLErrorLog log;


  /**
   * Creates a new {@link LoggingValidationContext} instance.
   * 
   * @param level the SBML level
   * @param version the SBML version
   */
  public LoggingValidationContext(int level, int version) {
    this(level, version, null, new HashSet<CHECK_CATEGORY>());
    this.addValidationListener(this);
  }


  /**
   * Creates a new {@link LoggingValidationContext} instance.
   * 
   * @param level the SBML level
   * @param version the SBML version
   * @param rootConstraint the root constraint
   * @param categories the set of {@link CHECK_CATEGORY} to use during validation
   */
  public LoggingValidationContext(int level, int version,
    AnyConstraint<Object> rootConstraint, Set<CHECK_CATEGORY> categories) {
    super(level, version, rootConstraint, categories);
    log = new SBMLErrorLog();
  }

  @Override
  public void clear() {
    super.clear();
    this.clearErrorLog();
  }

  /**
   * Clears the error log.
   */
  public void clearErrorLog() {
    this.log.clearLog();
  }

  /**
   * Gets the {@link SBMLErrorLog} of this context.
   * 
   * @return the {@link SBMLErrorLog} of this context.
   */
  public SBMLErrorLog getErrorLog() {
    return this.log;
  }


  /**
   * Logs an {@link SBMLError} into the {@link SBMLErrorLog}.
   * 
   * @param id the error id to log
   */
  public void logFailure(int id) {
    
    if (id == CoreSpecialErrorCodes.ID_GROUP || id == CoreSpecialErrorCodes.ID_VALIDATE_TREE_NODE)
    {
      return;
    }
    
    logger.debug("Constraint " + id + " is broken!");
    
    // Try to create the SBMLError from the .json file
    SBMLError e =
      SBMLErrorFactory.createError(id, this.getLevel(), this.getVersion());
    
    if (e != null) {
      this.log.add(e);
      
    } else {
      logger.warn("Couldn't load SBMLError for error code " + id);
      SBMLError defaultError = new SBMLError();
      defaultError.setCode(id);
      this.log.add(defaultError);
    }
  }


  @Override
  public void didValidate(ValidationContext ctx, AnyConstraint<?> c, Object o, boolean success) {
    // System.out.println("Checked " + c.getId());
    if (!success) {
      logFailure(c.getErrorCode()); // TODO - remove when new system is in place !!
      
      // TODO - the ValidationFunction should fill in the SBMLErrors directly to the ValidationContext to make things much simpler !!!
      // They can all call #logFailure(int) to start with, then we can start to build custom error message for each error code.
      
    }
  }


  @Override
  public void willValidate(ValidationContext ctx, AnyConstraint<?> c, Object o) {
    // nothing need to be done    
  }
}
