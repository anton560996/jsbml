/*
 * $Id$
 * $URL$
 *
 *
 *==================================================================================
 * Copyright (c) 2009-2010 the copyright is held jointly by the individual
 * authors. See the file AUTHORS for the list of authors.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */

package org.sbml.jsbml.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.EventAssignment;
import org.sbml.jsbml.ExplicitRule;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.UnitDefinition;


/**
 * Generates a sub model containing the elements passed as argument and all
 * the necessary dependencies.
 * 
 * <p> You do not need to fill all of the parameter arrays. Here is how the sub model generation works :
 * <li>If you give some compartment ids, all the species inside these compartment are included.
 * <li>If you give some species ids (and all added species from the compartments), all the reactions where one of these species is present are included
 * <li>All the species involved in the reactions given as parameter or added automatically from the given species are included.
 * <li>All compartments where one of the included species is present is included.
 * <li>All needed UnitDefinitions, SpeciesTypes and CompartmentTypes are included.
 * <li>All the rules and events passed as parameter along the one needed are included.
 * <li>All the global parameter are added automatically at the moment, without checking if there are used or not.
 * <li>All the functionDefinitions used in the included mathML are added automatically.
 *
 * 
 * @author rodrigue
 * @author chenli
 *
 */
public class SubModel {

	
    /**
     * Generates a sub model containing the elements passed as argument and all
     * the necessary dependencies.
     * 
     * <p> You do not need to fill all of the parameter arrays. Here is how the sub model generation works :
     * <li>If you give some compartment ids, all the species inside these compartment are included.
     * <li>If you give some species ids (and all added species from the compartments), all the reactions where one of these species is present are included
     * <li>All the species involved in the reactions given as parameter or added automatically from the given species are included.
     * <li>All compartments where one of the included species is present is included.
     * <li>All needed UnitDefinitions, SpeciesTypes and CompartmentTypes are included.
     * <li>All the rules and events passed as parameter along the one needed are included.
     * <li>All the global parameter are added automatically at the moment, without checking if there are used or not.
     * <li>All the functionDefinitions used in the included mathML are added automatically.
     *  
     * <p>
     * @param model the SBML model where we want to extract a sub-model.
     * @param compartmentsIds the list of compartment to keep
     * @param speciesIds the list of species to keep
     * @param reactsIds the list of reactions to keep
     * @param rulesIds the list of rules to keep
     * @param eventsIds the list of events to keep
     * @return a sub model containing the elements passed as argument and all
     * the necessary dependencies.
     */
    @SuppressWarnings("deprecation")
	public static SBMLDocument generateSubModel(
            Model model,
            String[] compartmentsIds,
            String[] speciesIds,
            String[] reactsIds,
            String[] rulesIds,
            String[] eventsIds) 
    {


    	// TODO : need to be sure that all needed elements have a metaid, likes rules, events, ...
    	
    	// TODO : would probably be easier to use only ArrayList, instead of arrays. Not sure if it would be better to store the Objects instead of the ids.
    	
    	// TODO : need to check for InitialAssignment and Constraint
    	
        //
        // initial
        //

        // submodel id
        Calendar current = Calendar.getInstance();
        String subModelId = "SUBMODEL" + Long.toString(current.getTimeInMillis());

        // creating submodel object
        Model subModel = new Model(2, 4);
        subModel.setId(subModelId);
        subModel.setMetaId(subModelId);

        // SBMLDocument object for submodel
        SBMLDocument subModelSbmlDocument = new SBMLDocument(2, 4);
        subModelSbmlDocument.setModel(subModel);
        subModel.setParentSBML(subModelSbmlDocument);

        speciesIds = getRelatedSpecies(model, null, compartmentsIds, speciesIds);
        reactsIds = getRelatedReactions(model, reactsIds, speciesIds);
        
        Set<String> relatedFunctionsIdSet = new HashSet<String>();
        Set<String> allFunctionsIdSet = new HashSet<String>();
        
        for (FunctionDefinition functionDefinition : model.getListOfFunctionDefinitions()) {
        	allFunctionsIdSet.add(functionDefinition.getId());
        }

        // TODO : the added rules or events can contain some species, compartment or speciesReferences (for L3) that could
        // not be present in the list of included elements !!!

        //
        // annotations
        //

        subModel.setNotes("<body xmlns=\"http://www.w3.org/1999/xhtml\">" +
                "<p>This is a sub-model automatically generated by BioModels Database. The generation was based on selected elements of Model " + model.getId() + 
                ". According to the <a href=\"http://www.ebi.ac.uk/biomodels/legal.html\">terms of use</a>, this sub-model is not related with Model " + model.getId() +
                " any more. <br />To retrieve the complete model, please visit <a href=\"model.getId()\">BioModels Database</a>.</p></body>");
        

        // TODO : set annotations
        
//        setAnnotations(subModel,
//                null,
//                null,
//                new ArrayList(),
//                DatetimeProcessor.instance.convertToGMT(current.getTime()),
//                DatetimeProcessor.instance.convertToGMT(current.getTime()),
//                null,
//                Publication.UNPUBL);


        //
        // Reactions.
        //

        if (reactsIds != null) {
            for (int i = 0; i < reactsIds.length; i++) {
                Reaction relatedReaction = model.getReaction(reactsIds[i]);
                subModel.addReaction(relatedReaction);
                if (relatedReaction.getKineticLaw() != null) {
                    getRelatedFunctionsId(relatedFunctionsIdSet, allFunctionsIdSet, relatedReaction.getKineticLaw().getMath());
                }
            }
        }


        //
        // compartments and compartments type
        //

        speciesIds = getRelatedSpecies(model, reactsIds, compartmentsIds, speciesIds);
        compartmentsIds = getRelatedCompartments(model, compartmentsIds, speciesIds);
        Map<String, UnitDefinition> unitsMap = new HashMap<String, UnitDefinition>();

        for (int i = 0; i < compartmentsIds.length; i++) {
            Compartment relatedCompartment = model.getCompartment(compartmentsIds[i]);
            subModel.addCompartment(relatedCompartment);
            processUnitsMap(unitsMap, model, relatedCompartment.getUnits());

            // check compartment type
            if (relatedCompartment.getCompartmentType() != null && subModel.getCompartmentType(relatedCompartment.getCompartmentType()) == null) {
                subModel.addCompartmentType(relatedCompartment.getCompartmentTypeInstance());
            }
        }


        //
        // species and species type
        //

        for (int i = 0; i < speciesIds.length; i++) {
            Species relatedSpecies = model.getSpecies(speciesIds[i]);
            subModel.addSpecies(relatedSpecies);
            processUnitsMap(unitsMap, model, relatedSpecies.getSubstanceUnits());
            // check species type
            if (relatedSpecies.getSpeciesType() != null && subModel.getSpeciesType(relatedSpecies.getSpeciesType()) == null) {
                subModel.addSpeciesType(relatedSpecies.getSpeciesTypeInstance());
            }
        }


        //
        // rules
        //
        
        rulesIds = getRelatedRules(model, rulesIds, compartmentsIds, speciesIds);

        if (rulesIds != null && rulesIds.length > 0) {
            for (String ruleId : rulesIds) {
            	for (Rule modelRule : model.getListOfRules()) {
            		if (modelRule.getMetaId().equals(ruleId)) {
            			subModel.addRule(modelRule);
            			if (modelRule.getMath() != null) {
            				getRelatedFunctionsId(relatedFunctionsIdSet, allFunctionsIdSet, modelRule.getMath());
            			}
            		}
            	}
            }
        }


        //
        // events
        //

        eventsIds = getRelatedEvents(model, eventsIds, compartmentsIds, speciesIds);

        if (eventsIds != null && eventsIds.length > 0) {
            for (int i = 0; i < eventsIds.length; i++) {
            	for (Event modelEvent : model.getListOfEvents()) {
            		if (modelEvent.getMetaId().equals(eventsIds[i])) {
            			subModel.addEvent(modelEvent);
            			if (modelEvent.getTrigger() != null) {
            				getRelatedFunctionsId(relatedFunctionsIdSet, allFunctionsIdSet, modelEvent.getTrigger().getMath());
            			}
            			if (modelEvent.getDelay() != null) {
            				getRelatedFunctionsId(relatedFunctionsIdSet, allFunctionsIdSet, modelEvent.getDelay().getMath());
            			}
            		}
            	}
            }
        }


        //
        // parameters.
        //

        // TODO : try not to add all the parameters !!
        // When we do getRelatedFunctions, we could search for the related species and parameter !!
        
        for (int i = 0; i < model.getListOfParameters().size(); i++) {
            subModel.addParameter(model.getListOfParameters().get(i));
            processUnitsMap(unitsMap, model, model.getListOfParameters().get(i).getUnits());
        }


        //
        // units
        //

        for (UnitDefinition unitDefinition : unitsMap.values()) {
            subModel.addUnitDefinition(unitDefinition);
        }


        //
        // FunctionDefinition
        //

        for (String functionDefinitionId : relatedFunctionsIdSet) {
            FunctionDefinition func = model.getFunctionDefinition(functionDefinitionId);
            getRelatedFunctionsId(relatedFunctionsIdSet, allFunctionsIdSet, func.getMath());
        }
        for (String functionDefinitionId : relatedFunctionsIdSet) {
            subModel.addFunctionDefinition(model.getFunctionDefinition(functionDefinitionId));
        }

        return subModelSbmlDocument;
    }

    
    private static void processUnitsMap(Map<String, UnitDefinition> unitsMap, Model model, String elementUnits) {
    	
        UnitDefinition unit = model.getUnitDefinition(elementUnits);
        
        if (unit != null && !unitsMap.containsKey(unit.getId())) {
            unitsMap.put(unit.getId(), unit);
        }
    }

    //
    //
    //
    //
    //

    private static boolean contains(ListOf<? extends SimpleSpeciesReference> speciesReferences,	Species species) {
    	
    	for (SimpleSpeciesReference speciesReference : speciesReferences) {
    		if (speciesReference.getSpecies().equals(species)) {
    			return true;
    		}
    	}

    	return false;
	}


	/**
     * 
     * @param model
     * @param compartmentsIds
     * @param speciesIds
     * @return
     */
    private static String[] getRelatedCompartments(
            Model model,
            String[] compartmentsIds,
            String[] speciesIds) {

    	Logger debugLogger = Logger.getLogger(SubModel.class);
        debugLogger.debug("getRelatedCompartments");

        // get all related compartments
        ArrayList<String> relatedCompartmentsList = new ArrayList<String>();

        if ( compartmentsIds != null || (speciesIds != null) ) {
            for (Compartment compartment : model.getListOfCompartments()) {

                String compartmentId = compartment.getId();

                boolean thisCompartmentSelected = false;

                if ( compartmentsIds != null ) {
                    for ( int j = 0; j < compartmentsIds.length; j++ ) {
                        if (compartmentId.equals(compartmentsIds[j])) {
                            thisCompartmentSelected = true;
                            break;
                        }
                    }
                }

                if ( !thisCompartmentSelected && (speciesIds != null) ) {
                    for ( int j = 0; j < speciesIds.length; j++ ){
                    	Species species = model.getSpecies(speciesIds[j]);
                        
                    	thisCompartmentSelected = species.getCompartment().equals(compartmentId);
        
                        if (thisCompartmentSelected) {
                            break;
                        }
                    }
                }

                if (thisCompartmentSelected) {
                    relatedCompartmentsList.add(compartmentId);
                }
            }
        }
        // convert to array
        if (relatedCompartmentsList == null || relatedCompartmentsList.isEmpty() ) {
            compartmentsIds = null;
        
            debugLogger.debug("getRelatedCompartments : related compartmentsIds is empty !!!!\n\n");

        } else {
            compartmentsIds = new String[relatedCompartmentsList.size()];
            Iterator<String> compartmentsIter = relatedCompartmentsList.iterator();
            
            for (int i = 0 ; i < compartmentsIds.length; i++ ) {
                compartmentsIds[i] = compartmentsIter.next();
            }
            
            debugLogger.debug("getRelatedCompartments : related compartmentsIds = " + Arrays.toString(compartmentsIds) + "\n\n");
            
        }

        return compartmentsIds;
    }

    
    //
    //
    //
    //
    //
    
    /**
     * 
     * @param model
     * @param reactsIds
     * @param compartmentsIds
     * @param speciesIds
     * @return an array of related species id or null if no related species are found.
     */
    private static String[] getRelatedSpecies(
            Model model,
            String[] reactsIds,
            String[] compartmentsIds,
            String[] speciesIds) {
        
    	Logger debugLogger = Logger.getLogger(SubModel.class);
    	debugLogger.debug("getRelatedSpecies ");
    	debugLogger.debug("getRelatedSpecies : reactsIds : " + Arrays.toString(reactsIds));
    	debugLogger.debug("getRelatedSpecies : compartmentsIds : " + Arrays.toString(compartmentsIds));
    	debugLogger.debug("getRelatedSpecies : speciesIds : " + Arrays.toString(speciesIds) + "\n");

    	// get the related species
        ArrayList<String> selectedSpecies = new ArrayList<String>();
        
        if ( speciesIds != null || compartmentsIds != null || reactsIds != null) {
        	
            for (Species species : model.getListOfSpecies()) {
                
                String speciesId = species.getId();
                boolean thisSpeciesSelected = false;
                
                if ( speciesIds != null ) {
                    for ( int j = 0; j < speciesIds.length; j++ ){
                        if (speciesId.equals(speciesIds[j])) {
                            thisSpeciesSelected = true;
                            break;
                        }
                    }
                }
                
                if ( !thisSpeciesSelected  &&  compartmentsIds !=  null ) {
                    for ( int j = 0; j < compartmentsIds.length; j++ ){
                        if ( (species.getCompartment()).equals(compartmentsIds[j]) ) {
                            thisSpeciesSelected = true;
                            break;
                        }
                    }
                }
                
                if ( !thisSpeciesSelected  &&  reactsIds !=  null ) {
                    for ( int j = 0; j < reactsIds.length; j++ ){
                    	Reaction reaction = model.getReaction(reactsIds[j]);
                        
                        thisSpeciesSelected = contains(reaction.getListOfReactants(), species);
                        if ( !thisSpeciesSelected ) {
                            thisSpeciesSelected = contains(reaction.getListOfProducts(), species);
                        }
                        if ( !thisSpeciesSelected ) {
                            thisSpeciesSelected = contains(reaction.getListOfModifiers(), species);
                        }
                        
                        if (thisSpeciesSelected) { break; }
                    }
                }
                
                if (thisSpeciesSelected) { selectedSpecies.add(speciesId); }
            }
        }
        // convert to array
        if (selectedSpecies == null || selectedSpecies.isEmpty() ) {
            speciesIds = null;

            debugLogger.debug("getRelatedSpecies : related speciesIds is empty !!!!! \n\n");

        } else {
            speciesIds = selectedSpecies.toArray(new String[selectedSpecies.size()]);

            debugLogger.debug("\ngetRelatedSpecies : related speciesIds = " + Arrays.toString(speciesIds) + "\n\n");

        }
        
        return speciesIds;
    }
    
    
    
    private static String[] getRelatedRules(
            Model model,
            String[] rulesIds,
            String[] compartmentsIds,
            String[] speciesIds) 
    {
    	Logger debugLogger = Logger.getLogger(SubModel.class);
    	debugLogger.debug("getRelatedRules ");
    	debugLogger.debug("getRelatedRules : rulesIds : " + Arrays.toString(rulesIds));
    	debugLogger.debug("getRelatedRules : compartmentsIds : " + Arrays.toString(compartmentsIds));
    	debugLogger.debug("getRelatedRules : speciesIds : " + Arrays.toString(speciesIds) + "\n");

    	// get the related rules
        ArrayList<String> selectedRules = new ArrayList<String>();
        
        if (rulesIds != null) {
        	for (String ruleId : rulesIds) {
        		selectedRules.add(ruleId);
        	}
        }
        
        for (Rule rule : model.getListOfRules()) {
        	
        	if (rule instanceof ExplicitRule) {
        	
        		String variableId = ((ExplicitRule) rule).getVariable();
        		
        		for (String speciesId : speciesIds) {
        			if (speciesId.equals(variableId)) {
        				selectedRules.add(rule.getMetaId());
        			}
        		}
        		for (String compartmentId : compartmentsIds) {
        			if (compartmentId.equals(variableId)) {
        				selectedRules.add(rule.getMetaId());
        			}
        		}
        		for (Parameter parameter : model.getListOfParameters()) {
        			if (parameter.getId().equals(variableId)) {
        				selectedRules.add(rule.getMetaId());
        			}
        		}

        	} else {
        		String ruleId = rule.getMetaId();
        		if (ruleId != null) {
        			selectedRules.add(ruleId);
        		}
        	}
        }
        
        // TODO : check the math of rule to check if we need to add any more species or compartment
        
        // convert to array
        if (selectedRules == null || selectedRules.isEmpty() ) {
            rulesIds = null;

            debugLogger.debug("getRelatedRules : related RulesIds is empty !!!!! \n\n");

        } else {
            rulesIds = selectedRules.toArray(new String[selectedRules.size()]);

            debugLogger.debug("\ngetRelatedRules : related RulesIds = " + Arrays.toString(rulesIds) + "\n\n");

        }
        
        return rulesIds;
    }
    
    
    private static String[] getRelatedEvents(
            Model model,
            String[] eventsIds,
            String[] compartmentsIds,
            String[] speciesIds) 
    {
    	Logger debugLogger = Logger.getLogger(SubModel.class);
    	debugLogger.debug("getRelatedEvents ");
    	debugLogger.debug("getRelatedEvents : eventsIds : " + Arrays.toString(eventsIds));
    	debugLogger.debug("getRelatedEvents : compartmentsIds : " + Arrays.toString(compartmentsIds));
    	debugLogger.debug("getRelatedEvents : speciesIds : " + Arrays.toString(speciesIds) + "\n");

    	// get the related events
        ArrayList<String> selectedEvents = new ArrayList<String>();
        
        if (eventsIds != null) {
        	for (String eventId : eventsIds) {
        		selectedEvents.add(eventId);
        	}
        }
        
        for (Event event : model.getListOfEvents()) {

        	// TODO : check the trigger math 
        	// TODO : check the delay math 
        	
        	for (EventAssignment eventAssigment : event.getListOfEventAssignments()) {
        		
        		String variableId = null; eventAssigment.getVariable();
        		
        		for (String speciesId : speciesIds) {
        			if (speciesId.equals(variableId)) {
        				selectedEvents.add(event.getMetaId());
        			}
        		}
        		for (String compartmentId : compartmentsIds) {
        			if (compartmentId.equals(variableId)) {
        				selectedEvents.add(event.getMetaId());
        			}
        		}
        		for (Parameter parameter : model.getListOfParameters()) {
        			if (parameter.getId().equals(variableId)) {
        				selectedEvents.add(event.getMetaId());
        			}
        		}
        	}

        }
        
        // convert to array
        if (selectedEvents == null || selectedEvents.isEmpty() ) {
            eventsIds = null;

            debugLogger.debug("getRelatedEvents : related EventsIds is empty !!!!! \n\n");

        } else {
            eventsIds = selectedEvents.toArray(new String[selectedEvents.size()]);

            debugLogger.debug("\ngetRelatedEvents : related EventsIds = " + Arrays.toString(eventsIds) + "\n\n");

        }
        
        return eventsIds;
    }
    
    
    //
    //
    //
    //
    //
    
    /**
     * Returns an array of reaction id related to the <code>reactsIds</code> or <code>speciesIds</code>.
     * 
     * All the reactions in <code>reactsIds</code> should be part of the result if 
     * the function is called properly with valid reacionId.
     * <br>
     * The reactions, where the species in the <code>speciesIds</code> array are involved as reactant, 
     * product or modifier, are added in the returned reaction id array.
     * 
     * @param model
     * @param reactsIds the list of reactions selected by the user to create a sub-model
     * @param speciesIds the list of species selected by the user to create a sub-model
     * @return an array of related reactions id or null if no related reactions are found.
     */
    private static String[] getRelatedReactions(
            Model model,
            String[] reactsIds,
            String[] speciesIds) {
    	
    	Logger debugLogger = Logger.getLogger(SubModel.class);
        
    	debugLogger.debug("getRelatedReactions ");
    	debugLogger.debug("getRelatedReactions : selected reactsIds : " + Arrays.toString(reactsIds));
    	debugLogger.debug("getRelatedReactions : selected speciesIds : " + Arrays.toString(speciesIds) + "\n");
    	
        // get all related reactions
        ArrayList<String> relatedReactsList = new ArrayList<String>();
        
        if ( reactsIds != null || speciesIds != null ) {
        	
            for (Reaction reaction : model.getListOfReactions()) {

            	String reactionId = reaction.getId();

            	 debugLogger.debug("getRelatedReactions : reaction = " + reactionId);
                
                boolean thisReactionSelected = false;
                
                if ( reactsIds != null ) {
                    for ( int j = 0; j < reactsIds.length; j++ ) {
                        if (reactionId.equals(reactsIds[j])) {
                            thisReactionSelected = true;
                            break;
                        }
                    }
                }
                
                if (!thisReactionSelected && (speciesIds != null)) {
                    for ( int j = 0; j < speciesIds.length; j++ ){
                    	
                    	Species species = model.getSpecies(speciesIds[j]);
                    	
                    	// debugLogger.debug("getRelatedReactions : speciesId = " + speciesIds[j]);
                    	// debugLogger.debug("getRelatedReactions : reactant = " + Arrays.toString(reaction.getReactants().toArray()));
                    	// debugLogger.debug("getRelatedReactions : product = " + Arrays.toString(reaction.getProducts().toArray()));
                    	// debugLogger.debug("getRelatedReactions : modifier = " + Arrays.toString(reaction.getModifiers().toArray()));
                    	
                        // thisReactionSelected = reaction.getReactants().contains(species); // not working !! to investigate may be !!
                        thisReactionSelected = contains(reaction.getListOfReactants(), species);

                    	// debugLogger.debug("getRelatedReactions : contains.own = " + contains(reaction.getReactants(), species));
                        
                        if (!thisReactionSelected) {
                            thisReactionSelected = contains(reaction.getListOfProducts(), species);
                        }
                        if (!thisReactionSelected) {
                            thisReactionSelected = contains(reaction.getListOfModifiers(), species);
                        }
                        if (thisReactionSelected) { break; }
                    }
                }
                
                if (thisReactionSelected) {
                    relatedReactsList.add(reactionId);
                }
            }
        }
        
        // convert to array
        if (relatedReactsList == null || relatedReactsList.isEmpty() ) {
            reactsIds = null;
            
            debugLogger.debug("getRelatedReactions : related reactsIds is empty !!!!\n\n");
            
        } else {
            reactsIds = new String[relatedReactsList.size()];
            Iterator<String> reactsIter = relatedReactsList.iterator();
            
            for (int i = 0 ; i < reactsIds.length; i++ ) {
                reactsIds[i] = reactsIter.next();
            }
            
        	debugLogger.debug("getRelatedReactions : related reactsIds : " + Arrays.toString(reactsIds) + "!!!!\n\n");
        }
        
        return reactsIds;
    }

    //
    //
    //
    //
    //

    private static void getRelatedFunctionsId(Set<String> relatedFunctionsSet, Set<String> allFunctionsIdSet, ASTNode mathNode) {

            getRelatedFunctions(relatedFunctionsSet, allFunctionsIdSet, mathNode);
    }

    //
    //
    //
    //
    //

    private static void getRelatedFunctions(Set<String> relatedFunctionsSet, Set<String> allFunctionsIdSet, ASTNode mathNode) {

        if ((mathNode.isName() || mathNode.isFunction())
                && allFunctionsIdSet.contains(mathNode.getName().trim())
                && !relatedFunctionsSet.contains(mathNode.getName().trim())) {

            relatedFunctionsSet.add(mathNode.getName().trim());
            
            return;
        }

        for (int i = 0; i < mathNode.getNumChildren(); i++) {

            getRelatedFunctions(relatedFunctionsSet, allFunctionsIdSet, mathNode.getChild(i));
        }
    }

    
}
