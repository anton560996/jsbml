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

import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.SBOValidationConstraints;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownAttributeValidationFunction;;

/**
 * @author Roman
 * @since 1.2
 * @date 04.08.2016
 */
public class SimpleSpeciesReferenceConstraints
  extends AbstractConstraintDeclaration {

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
      set.add(CORE_21111);
      set.add(CORE_21117);
      
      if (level > 1) {
        set.add(CORE_20611);
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
      if ((level == 2 && version > 1) || level > 2)
      {
        set.add(CORE_10708);
      }
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }


  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<SimpleSpeciesReference> func = null;

    switch (errorCode) {
    case CORE_10708:
      return SBOValidationConstraints.isParticipantRole;
      
    case CORE_20611:
      func = new ValidationFunction<SimpleSpeciesReference>() {

        @Override
        public boolean check(ValidationContext ctx, SimpleSpeciesReference sr) {

          Species s = sr.getSpeciesInstance();

    
          if (s != null) {

            return !s.isConstant() || s.isBoundaryCondition();
          }

          return true;
        }
      };
      break;
      
    case CORE_21111:
      func = new ValidationFunction<SimpleSpeciesReference>() {

        @Override
        public boolean check(ValidationContext ctx, SimpleSpeciesReference sr) {

          return sr.getSpeciesInstance() != null;
        }
      };
      break;
      
    case CORE_21117:
      func = new UnknownAttributeValidationFunction<SimpleSpeciesReference>() {
        
        @Override
        public boolean check(ValidationContext ctx, SimpleSpeciesReference c) {
          // species is a mandatory attribute
          if (!c.isSetSpecies()) { // TODO - may be moved it to ModifierSpeciesReferenceConstraints ?
            return false;
          }
          return super.check(ctx, c);
        }
      };
      break;

    }

    return func;
  }
}
