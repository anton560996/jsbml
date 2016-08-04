/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * Copyright (C) 2009-2016 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.validator.offline.constraints;

import java.util.Set;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.EventAssignment;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.Variable;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;;

public class EventAssignmentConstraints extends AbstractConstraintDeclaration {

  @Override
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName) {
    // TODO Auto-generated method stub

  }


  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category) {
    switch (category) {
    case GENERAL_CONSISTENCY:
      if (level > 1) {
        set.add(CORE_21211);
        set.add(CORE_21212);
      }

      if (level == 3) {
        set.add(CORE_21213);
      }
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case MODELING_PRACTICE:
      break;
    case OVERDETERMINED_MODEL:
      break;
    case SBO_CONSISTENCY:
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }


  @Override
  @SuppressWarnings("deprecation")
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<?> func = null;

    switch (errorCode) {
    case CORE_21211:
      func = new ValidationFunction<EventAssignment>() {

        @Override
        public boolean check(ValidationContext ctx, EventAssignment ea) {

          if (ea.isSetVariable()) {
            Variable var = ea.getVariableInstance();
            boolean isComSpecOrPar = (var instanceof Compartment)
                || (var instanceof Species) || (var instanceof Parameter);

            if (ctx.getLevel() == 2) {
              return isComSpecOrPar;
            } else {
              return isComSpecOrPar || (var instanceof SpeciesReference);
            }

          }

          return true;
        }
      };

    case CORE_21212:
      func = new ValidationFunction<EventAssignment>() {

        @Override
        public boolean check(ValidationContext ctx, EventAssignment ea) {

          if (ea.isSetVariable()) {
            Variable var = ea.getVariableInstance();

            return var != null && var.isConstant();
          }

          return true;
        }
      };

    case CORE_21213:
      func = new ValidationFunction<EventAssignment>() {

        @Override
        public boolean check(ValidationContext ctx, EventAssignment ea) {

          return ea.isSetMath();
        }
      };
    }

    return func;
  }
}
