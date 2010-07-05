package org.sbml.jsbml.xml.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.sbml.jsbml.AlgebraicRule;
import org.sbml.jsbml.Annotation;
import org.sbml.jsbml.AssignmentRule;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.CompartmentType;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.EventAssignment;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.LocalParameter;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.QuantityWithDefinedUnit;
import org.sbml.jsbml.RateRule;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.SpeciesType;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.Variable;
import org.sbml.jsbml.resources.Resource;
import org.sbml.jsbml.xml.stax.ReadingParser;
import org.sbml.jsbml.xml.stax.SBMLObjectForXML;
import org.sbml.jsbml.xml.stax.WritingParser;

/**
 * 
 */
@SuppressWarnings("deprecation")
public class SBMLLevel1Version1Parser implements ReadingParser, WritingParser {

	/**
	 * 
	 */
	private String parserNamespace = "http://www.sbml.org/sbml/level1/version1";

	/**
	 * 
	 */
	protected HashMap<String, Class<? extends Object>> SBMLCoreElements;

	/**
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 * 
	 */
	public SBMLLevel1Version1Parser() throws InvalidPropertiesFormatException, IOException, ClassNotFoundException {
		SBMLCoreElements = new HashMap<String, Class<? extends Object>>();
		initializeCoreElements();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.WritingParser#getListOfSBMLElementsToWrite(java
	 * .lang.Object)
	 */
	public ArrayList<Object> getListOfSBMLElementsToWrite(Object sbase) {
		ArrayList<Object> listOfElementsToWrite = null;
		if (sbase instanceof SBase) {
			if (sbase instanceof SBMLDocument) {
				SBMLDocument sbmlDocument = (SBMLDocument) sbase;
				if (sbmlDocument.isSetModel()) {
					listOfElementsToWrite = new ArrayList<Object>();
					listOfElementsToWrite.add(sbmlDocument.getModel());
				}
			} else if (sbase instanceof Model) {

				Model model = (Model) sbase;
				listOfElementsToWrite = new ArrayList<Object>();
				if (model.isSetListOfUnitDefinitions()) {
					listOfElementsToWrite.add(model.getListOfUnitDefinitions());
				}
				if (model.isSetListOfCompartments()) {
					listOfElementsToWrite.add(model.getListOfCompartments());
				}
				if (model.isSetListOfSpecies()) {
					listOfElementsToWrite.add(model.getListOfSpecies());
				}
				if (model.isSetListOfParameters()) {
					listOfElementsToWrite.add(model.getListOfParameters());
				}
				if (model.isSetListOfRules()) {
					listOfElementsToWrite.add(model.getListOfRules());
				}
				if (model.isSetListOfReactions()) {
					listOfElementsToWrite.add(model.getListOfReactions());
				}

				if (listOfElementsToWrite.isEmpty()) {
					listOfElementsToWrite = null;
				}
			} else if (sbase instanceof ListOf<?>) {
				ListOf<SBase> listOf = (ListOf<SBase>) sbase;

				if (!listOf.isEmpty()) {
					listOfElementsToWrite = new ArrayList<Object>();
					for (int i = 0; i < listOf.size(); i++) {
						SBase element = listOf.get(i);

						if (element != null) {
							listOfElementsToWrite.add(element);
						}
					}
					if (listOfElementsToWrite.isEmpty()) {
						listOfElementsToWrite = null;
					}
				}
			} else if (sbase instanceof UnitDefinition) {
				UnitDefinition unitDefinition = (UnitDefinition) sbase;

				if (unitDefinition.isSetListOfUnits()) {
					listOfElementsToWrite = new ArrayList<Object>();
					listOfElementsToWrite.add(unitDefinition.getListOfUnits());
				}
			} else if (sbase instanceof Reaction) {
				Reaction reaction = (Reaction) sbase;
				listOfElementsToWrite = new ArrayList<Object>();

				if (reaction.isSetListOfReactants()) {
					listOfElementsToWrite.add(reaction.getListOfReactants());
				}
				if (reaction.isSetListOfProducts()) {
					listOfElementsToWrite.add(reaction.getListOfProducts());
				}
				if (reaction.isSetKineticLaw()) {
					listOfElementsToWrite.add(reaction.getKineticLaw());
				}

				if (listOfElementsToWrite.isEmpty()) {
					listOfElementsToWrite = null;
				}
			} else if (sbase instanceof KineticLaw) {
				KineticLaw kineticLaw = (KineticLaw) sbase;

				if (kineticLaw.isSetListOfParameters()) {
					listOfElementsToWrite = new ArrayList<Object>();
					listOfElementsToWrite.add(kineticLaw.getListOfParameters());
				}
			}
		}
		return listOfElementsToWrite;
	}

	/**
	 * 
	 * @return
	 */
	public String getParserNamespace() {
		return parserNamespace;
	}

	/**
	 * @throws IOException
	 * @throws InvalidPropertiesFormatException
	 * @throws ClassNotFoundException
	 * 
	 */
	private void initializeCoreElements()
			throws InvalidPropertiesFormatException, IOException,
			ClassNotFoundException {
		Properties p = new Properties();
		p.loadFromXML(Resource.getInstance().getStreamFromResourceLocation(
				"org/sbml/jsbml/resources/cfg/SBMLElementsLevel1Version1.xml"));
		for (Object k : p.keySet()) {
			String key = k.toString();
			SBMLCoreElements.put(key, Class.forName(p.getProperty(key)
					.toString()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.ReadingParser#processAttribute(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, boolean,
	 * java.lang.Object)
	 */
	public void processAttribute(String elementName, String attributeName,
			String value, String prefix, boolean isLastAttribute,
			Object contextObject) {
		boolean isAttributeRead = false;
		if (contextObject instanceof SBase) {
			SBase sbase = (SBase) contextObject;
			isAttributeRead = sbase.readAttribute(attributeName, prefix, value);
		} else if (contextObject instanceof Annotation) {
			Annotation annotation = (Annotation) contextObject;
			isAttributeRead = annotation.readAttribute(attributeName, prefix,
					value);
		}

		if (!isAttributeRead) {
			// TODO : throw new SBMLException ("The attribute " + attributeName
			// + " on the element " + elementName +
			// "is not part of the SBML specifications");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.ReadingParser#processCharactersOf(java.lang.String
	 * , java.lang.String, java.lang.Object)
	 */
	public void processCharactersOf(String elementName, String characters,
			Object contextObject) {
		// TODO : the basic SBML elements don't have any text. SBML syntax
		// error, throw an exception?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.ReadingParser#processEndDocument(org.sbml.jsbml
	 * .SBMLDocument)
	 */
	public void processEndDocument(SBMLDocument sbmlDocument) {

		if (sbmlDocument.isSetModel()) {
			Model model = sbmlDocument.getModel();

			if (model.isSetListOfRules()) {
				for (int i = 0; i < model.getNumRules(); i++) {
					Rule rule = model.getRule(i);
					if (rule instanceof AssignmentRule) {
						AssignmentRule assignmentRule = (AssignmentRule) rule;
						setAssignmentRuleVariable(assignmentRule, model);
					} else if (rule instanceof RateRule) {
						RateRule rateRule = (RateRule) rule;
						setRateRuleVariable(rateRule, model);
					}
				}
			}
			if (model.isSetListOfCompartments()) {
				for (int i = 0; i < model.getNumCompartments(); i++) {
					Compartment compartment = model.getCompartment(i);

					setCompartmentCompartmentType(compartment, model);
					setCompartmentOutside(compartment, model);
					setCompartmentUnits(compartment, model);
				}
			}
			if (model.isSetListOfEvents()) {
				for (int i = 0; i < model.getNumEvents(); i++) {
					Event event = model.getEvent(i);

					setEventTimeUnits(event, model);

					if (event.isSetListOfEventAssignments()) {

						for (int j = 0; j < event.getNumEventAssignments(); j++) {
							EventAssignment eventAssignment = event
									.getEventAssignment(j);

							setEventAssignmentVariable(eventAssignment, model);
						}
					}
				}
			}
			if (model.isSetListOfInitialAssignments()) {
				for (int i = 0; i < model.getNumInitialAssignments(); i++) {
					InitialAssignment initialAssignment = model
							.getInitialAssignment(i);

					setInitialAssignmentSymbol(initialAssignment, model);
				}
			}
			if (model.isSetListOfReactions()) {
				for (int i = 0; i < model.getNumReactions(); i++) {
					Reaction reaction = model.getReaction(i);

					setReactionCompartment(reaction, model);

					if (reaction.isSetListOfReactants()) {
						for (int j = 0; j < reaction.getNumReactants(); j++) {
							SpeciesReference speciesReference = reaction
									.getReactant(j);

							setSpeciesReferenceSpecies(speciesReference, model);
						}
					}
					if (reaction.isSetListOfProducts()) {
						for (int j = 0; j < reaction.getNumProducts(); j++) {
							SpeciesReference speciesReference = reaction
									.getProduct(j);

							setSpeciesReferenceSpecies(speciesReference, model);
						}
					}
					if (reaction.isSetListOfModifiers()) {
						for (int j = 0; j < reaction.getNumModifiers(); j++) {
							ModifierSpeciesReference modifierSpeciesReference = reaction
									.getModifier(j);

							setSpeciesReferenceSpecies(
									modifierSpeciesReference, model);
						}
					}
					if (reaction.isSetKineticLaw()) {
						KineticLaw kineticLaw = reaction.getKineticLaw();
						if (kineticLaw.isSetListOfParameters()) {
							for (int j = 0; j < kineticLaw.getNumParameters(); j++) {
								LocalParameter parameter = kineticLaw
										.getParameter(j);

								setParameterUnits(parameter, model);
							}
						}
					}
				}
			}
			if (model.isSetListOfSpecies()) {
				for (int i = 0; i < model.getNumSpecies(); i++) {
					Species species = model.getSpecies(i);

					setSpeciesSubstanceUnits(species, model);
					setSpeciesSpeciesType(species, model);
					setSpeciesConversionFactor(species, model);
					setSpeciesCompartment(species, model);
				}
			}
			if (model.isSetListOfParameters()) {
				for (int i = 0; i < model.getNumParameters(); i++) {
					Parameter parameter = model.getParameter(i);

					setParameterUnits(parameter, model);
				}
			}

		} else {
			// TODO : SBML syntax error, what to do?
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.ReadingParser#processEndElement(java.lang.String,
	 * java.lang.String, boolean, java.lang.Object)
	 */
	public boolean processEndElement(String elementName, String prefix,
			boolean isNested, Object contextObject) {

		if (elementName.equals("notes") && contextObject instanceof SBase) {
			SBase sbase = (SBase) contextObject;
			sbase.setNotes(sbase.getNotesBuffer().toString());
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.ReadingParser#processNamespace(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, boolean, boolean,
	 * java.lang.Object)
	 */
	public void processNamespace(String elementName, String URI, String prefix,
			String localName, boolean hasAttributes, boolean isLastNamespace,
			Object contextObject) {

		if (contextObject instanceof SBMLDocument) {
			SBMLDocument sbmlDocument = (SBMLDocument) contextObject;
			if (!URI.equals(parserNamespace)) {
				sbmlDocument.addNamespace(localName, prefix, URI);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.ReadingParser#processStartElement(java.lang.String
	 * , java.lang.String, boolean, boolean, java.lang.Object)
	 */
	public Object processStartElement(String elementName, String prefix,
			boolean hasAttributes, boolean hasNamespaces, Object contextObject) {
		if (SBMLCoreElements.containsKey(elementName)) {
			try {
				Object newContextObject = SBMLCoreElements.get(elementName)
						.newInstance();

				if (elementName.equals("notes")
						&& contextObject instanceof SBase) {
					SBase sbase = (SBase) contextObject;
					StringBuffer notes = (StringBuffer) newContextObject;
					sbase.setNotesBuffer(notes);
				} else if (elementName.equals("annotation")
						&& contextObject instanceof SBase) {
					SBase sbase = (SBase) contextObject;
					Annotation annotation = (Annotation) newContextObject;
					sbase.setAnnotation(annotation);

					return annotation;
				} else if (contextObject instanceof SBMLDocument) {
					SBMLDocument sbmlDocument = (SBMLDocument) contextObject;
					if (elementName.equals("model")) {
						Model model = (Model) newContextObject;
						model.setParentSBML(sbmlDocument);
						sbmlDocument.setModel(model);

						return model;
					}
				} else if (contextObject instanceof Model) {

					Model model = (Model) contextObject;
					if (newContextObject instanceof ListOf<?>) {
						if (elementName.equals("listOfUnitDefinitions")) {
							ListOf listOfUnitDefinitions = (ListOf) newContextObject;
							model
									.setListOfUnitDefinitions(listOfUnitDefinitions);

							return listOfUnitDefinitions;
						} else if (elementName.equals("listOfCompartments")) {
							ListOf listofCompartments = (ListOf) newContextObject;
							model.setListOfCompartments(listofCompartments);

							return listofCompartments;
						} else if (elementName.equals("listOfSpecies")) {
							ListOf listOfSpecies = (ListOf) newContextObject;
							model.setListOfSpecies(listOfSpecies);

							return listOfSpecies;
						} else if (elementName.equals("listOfParameters")) {
							ListOf listOfParameters = (ListOf) newContextObject;
							model.setListOfParameters(listOfParameters);

							return listOfParameters;
						} else if (elementName.equals("listOfRules")) {
							ListOf listOfRules = (ListOf) newContextObject;
							model.setListOfRules(listOfRules);

							return listOfRules;
						} else if (elementName.equals("listOfReactions")) {
							ListOf listOfReactions = (ListOf) newContextObject;
							model.setListOfReactions(listOfReactions);

							return listOfReactions;
						} else {
							// TODO : SBML syntax error, throw an exception?
						}
					} else {
						// TODO : SBML syntax error, throw an exception?
					}
				} else if (contextObject instanceof ListOf<?>) {
					ListOf list = (ListOf) contextObject;
					if (list.getParentSBMLObject() instanceof Model) {

						Model model = (Model) list.getParentSBMLObject();
						if (elementName.equals("unitDefinition")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfUnitDefinitions)) {
							UnitDefinition unitDefinition = (UnitDefinition) newContextObject;
							model.addUnitDefinition(unitDefinition);

							return unitDefinition;
						} else if (elementName.equals("compartment")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfCompartments)) {
							Compartment compartment = (Compartment) newContextObject;
							model.addCompartment(compartment);

							return compartment;
						} else if (elementName.equals("specie")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfSpecies)) {
							Species species = (Species) newContextObject;
							model.addSpecies(species);

							return species;
						} else if (elementName.equals("parameter")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfParameters)) {
							Parameter parameter = (Parameter) newContextObject;
							model.addParameter(parameter);

							return parameter;
						} else if (elementName.equals("algebraicRule")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfRules)) {
							AlgebraicRule rule = (AlgebraicRule) newContextObject;
							model.addRule(rule);

							return rule;
						} else if (elementName.equals("assignmentRule")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfRules)) {
							AssignmentRule rule = (AssignmentRule) newContextObject;
							model.addRule(rule);

							return rule;
						} else if (elementName.equals("reaction")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfReactions)) {
							Reaction reaction = (Reaction) newContextObject;
							model.addReaction(reaction);

							return reaction;
						} else {
							// TODO : SBML syntax error, throw an exception?
						}
					} else if (list.getParentSBMLObject() instanceof UnitDefinition) {
						UnitDefinition unitDefinition = (UnitDefinition) list
								.getParentSBMLObject();

						if (elementName.equals("unit")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfUnits)) {
							Unit unit = (Unit) newContextObject;
							unitDefinition.addUnit(unit);

							return unit;
						} else {
							// TODO : SBML syntax error, throw an exception?
						}
					} else if (list.getParentSBMLObject() instanceof Reaction) {
						Reaction reaction = (Reaction) list
								.getParentSBMLObject();

						if (elementName.equals("specieReference")) {
							SpeciesReference speciesReference = (SpeciesReference) newContextObject;

							if (list.getSBaseListType().equals(
									ListOf.Type.listOfReactants)) {
								reaction.addReactant(speciesReference);

								return speciesReference;
							} else if (list.getSBaseListType().equals(
									ListOf.Type.listOfProducts)) {
								reaction.addProduct(speciesReference);

								return speciesReference;
							} else {
								// TODO : SBML syntax error, throw an exception?
							}
						} else {
							// TODO : SBML syntax error, throw an exception?
						}
					} else if (list.getParentSBMLObject() instanceof KineticLaw) {
						KineticLaw kineticLaw = (KineticLaw) list
								.getParentSBMLObject();

						if (elementName.equals("parameter")
								&& list.getSBaseListType().equals(
										ListOf.Type.listOfParameters)) {
							LocalParameter localParameter = (LocalParameter) newContextObject;
							kineticLaw.addParameter(localParameter);

							return localParameter;
						} else {
							// TODO : SBML syntax error, throw an exception?
						}
					} else {
						// TODO : SBML syntax error, throw an exception?
					}
				} else if (contextObject instanceof UnitDefinition) {
					UnitDefinition unitDefinition = (UnitDefinition) contextObject;

					if (elementName.equals("listOfUnits")) {
						ListOf<Unit> listOfUnits = (ListOf<Unit>) newContextObject;
						unitDefinition.setListOfUnits(listOfUnits);

						return listOfUnits;
					}
				} else if (contextObject instanceof Reaction) {
					Reaction reaction = (Reaction) contextObject;

					if (elementName.equals("listOfReactants")) {
						ListOf listOfReactants = (ListOf) newContextObject;
						reaction.setListOfReactants(listOfReactants);
						listOfReactants
								.setSBaseListType(ListOf.Type.listOfReactants);

						return listOfReactants;
					} else if (elementName.equals("listOfProducts")) {
						ListOf listOfProducts = (ListOf) newContextObject;
						reaction.setListOfProducts(listOfProducts);
						listOfProducts
								.setSBaseListType(ListOf.Type.listOfProducts);

						return listOfProducts;
					} else if (elementName.equals("kineticLaw")) {
						KineticLaw kineticLaw = (KineticLaw) newContextObject;
						reaction.setKineticLaw(kineticLaw);

						return kineticLaw;
					} else {
						// TODO : SBML syntax error, throw an exception?
					}
				} else if (contextObject instanceof KineticLaw) {
					KineticLaw kineticLaw = (KineticLaw) contextObject;

					if (elementName.equals("listOfParameters")) {
						ListOf listOfLocalParameters = (ListOf) newContextObject;
						kineticLaw
								.setListOfLocalParameters(listOfLocalParameters);
						listOfLocalParameters
								.setSBaseListType(ListOf.Type.listOfParameters);

						return listOfLocalParameters;
					} else {
						// TODO : SBML syntax error, throw an exception?
					}
				} else {
					// TODO : SBML syntax error, throw an exception?
				}
			} catch (InstantiationException e) {
				// TODO : SBML object can't be instantiated, throw an exception?
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO : SBML object can't be instantiated, throw an exception?
				e.printStackTrace();
			}
		}
		return contextObject;
	}

	/**
	 * 
	 * @param rule
	 * @param model
	 */
	private void setAssignmentRuleVariable(AssignmentRule rule, Model model) {

		if (rule.isSetVariable()) {
			String variableID = rule.getVariable();

			Compartment compartment = model.getCompartment(variableID);
			Species species = null;
			SpeciesReference speciesReference = null;
			Parameter parameter = null;

			if (compartment == null) {
				species = model.getSpecies(variableID);

				if (species == null) {
					parameter = model.getParameter(variableID);

					if (parameter == null) {
						if (model.isSetListOfReactions()) {

							int i = 0;
							SpeciesReference sr = null;

							while (i <= model.getNumReactions() - 1
									&& sr == null) {
								Reaction reaction = model.getReaction(i);

								if (reaction != null) {
									sr = reaction.getReactant(variableID);
									if (sr == null) {
										sr = reaction.getProduct(variableID);
									}
								}
							}

							speciesReference = sr;

							if (speciesReference != null) {
								rule.setVariable(speciesReference);
							} else {
								// TODO : the variable ID doesn't match a SBML
								// component, throw an exception?
							}
						}
					} else {
						rule.setVariable(parameter);
					}
				} else {
					rule.setVariable(species);
				}
			} else {
				rule.setVariable(compartment);
			}
		}
	}

	/**
	 * 
	 * @param compartment
	 * @param model
	 */
	private void setCompartmentCompartmentType(Compartment compartment,
			Model model) {
		if (compartment.isSetCompartmentType()) {
			String compartmentTypeID = compartment.getCompartmentType();

			CompartmentType compartmentType = model
					.getCompartmentType(compartmentTypeID);

			if (compartmentType != null) {
				compartment.setCompartmentType(compartmentType);
			} else {
				// TODO : the compartmentType ID doesn't match a compartment,
				// throw an exception?
			}
		}
	}

	/**
	 * 
	 * @param compartment
	 * @param model
	 */
	private void setCompartmentOutside(Compartment compartment, Model model) {

		if (compartment.isSetOutside()) {
			String outsideID = compartment.getOutside();

			Compartment outside = model.getCompartment(outsideID);

			if (outside != null) {
				compartment.setOutside(outside);
			} else {
				// TODO : the compartment ID doesn't match a compartment, throw
				// an exception?
			}
		}
	}

	/**
	 * 
	 * @param compartment
	 * @param model
	 */
	private void setCompartmentUnits(Compartment compartment, Model model) {

		if (compartment.isSetUnits()) {
			String unitsID = compartment.getUnits();

			UnitDefinition unitDefinition = model.getUnitDefinition(unitsID);

			if (unitDefinition != null) {
				compartment.setUnits(unitDefinition);
			} else {
				// TODO : the unitDefinition ID doesn't match a unitDefinition,
				// throw an exception?
			}
		}
	}

	/**
	 * 
	 * @param eventAssignment
	 * @param model
	 */
	private void setEventAssignmentVariable(EventAssignment eventAssignment,
			Model model) {

		if (eventAssignment.isSetVariable()) {
			String variableID = eventAssignment.getVariable();

			Compartment compartment = model.getCompartment(variableID);
			Species species = null;
			SpeciesReference speciesReference = null;
			Parameter parameter = null;

			if (compartment == null) {
				species = model.getSpecies(variableID);

				if (species == null) {
					parameter = model.getParameter(variableID);

					if (parameter == null) {
						if (model.isSetListOfReactions()) {

							int i = 0;
							SpeciesReference sr = null;

							while (i <= model.getNumReactions() - 1
									&& sr == null) {
								Reaction reaction = model.getReaction(i);

								if (reaction != null) {
									sr = reaction.getReactant(variableID);
									if (sr == null) {
										sr = reaction.getProduct(variableID);
									}
								}
							}

							speciesReference = sr;

							if (speciesReference != null) {
								eventAssignment.setVariable(speciesReference);
							} else {
								// TODO : the variable ID doesn't match a SBML
								// component, throw an exception?
							}
						}
					} else {
						eventAssignment.setVariable(parameter);
					}
				} else {
					eventAssignment.setVariable(species);
				}
			} else {
				eventAssignment.setVariable(compartment);
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @param model
	 */
	private void setEventTimeUnits(Event event, Model model) {

		if (event.isSetTimeUnits()) {
			String timeUnitsID = event.getTimeUnits();

			UnitDefinition unitDefinition = model
					.getUnitDefinition(timeUnitsID);

			if (unitDefinition != null) {
				event.setTimeUnits(unitDefinition);
			} else {
				// TODO : the unitDefinition ID doesn't match a unitDefinition,
				// throw an exception?
			}
		}
	}

	/**
	 * 
	 * @param initialAssignment
	 * @param model
	 */
	private void setInitialAssignmentSymbol(
			InitialAssignment initialAssignment, Model model) {

		if (initialAssignment.isSetSymbol()) {
			String variableID = initialAssignment.getSymbol();

			Compartment compartment = model.getCompartment(variableID);
			Species species = null;
			SpeciesReference speciesReference = null;
			Parameter parameter = null;

			if (compartment == null) {
				species = model.getSpecies(variableID);

				if (species == null) {
					parameter = model.getParameter(variableID);

					if (parameter == null) {
						if (model.isSetListOfReactions()) {

							int i = 0;
							SpeciesReference sr = null;

							while (i <= model.getNumReactions() - 1
									&& sr == null) {
								Reaction reaction = model.getReaction(i);

								if (reaction != null) {
									sr = reaction.getReactant(variableID);
									if (sr == null) {
										sr = reaction.getProduct(variableID);
									}
								}
							}

							speciesReference = sr;

							if (speciesReference != null) {
								initialAssignment.setState(speciesReference);
							} else {
								// TODO : the variable ID doesn't match a SBML
								// component, throw an exception?
							}
						}
					} else {
						initialAssignment.setState(parameter);
					}
				} else {
					initialAssignment.setState(species);
				}
			} else {
				initialAssignment.setState(compartment);
			}
		}
	}

	/**
	 * 
	 * @param parameter
	 * @param model
	 */
	private void setParameterUnits(QuantityWithDefinedUnit parameter,
			Model model) {

		if (parameter.isSetUnits()) {
			String unitsID = parameter.getUnits();

			UnitDefinition unitDefinition = model.getUnitDefinition(unitsID);

			if (unitDefinition != null) {
				parameter.setUnits(unitDefinition);
			} else {
				// TODO : the unitDefinition ID doesn't match an unitDefinition,
				// throw an exception?
			}
		}
	}

	/**
	 * 
	 * @param parserNamespace
	 */
	public void setParserNamespace(String parserNamespace) {
		this.parserNamespace = parserNamespace;
	}

	/**
	 * 
	 * @param rule
	 * @param model
	 */
	private void setRateRuleVariable(RateRule rule, Model model) {
		if (rule.isSetVariable()) {
			String variableID = rule.getVariable();

			Compartment compartment = model.getCompartment(variableID);
			Species species = null;
			SpeciesReference speciesReference = null;
			Parameter parameter = null;

			if (compartment == null) {
				species = model.getSpecies(variableID);

				if (species == null) {
					parameter = model.getParameter(variableID);

					if (parameter == null) {
						if (model.isSetListOfReactions()) {

							int i = 0;
							SpeciesReference sr = null;

							while (i <= model.getNumReactions() - 1
									&& sr == null) {
								Reaction reaction = model.getReaction(i);

								if (reaction != null) {
									sr = reaction.getReactant(variableID);
									if (sr == null) {
										sr = reaction.getProduct(variableID);
									}
								}
							}

							speciesReference = sr;

							if (speciesReference != null) {
								rule.setVariable(speciesReference);
							} else {
								// TODO : the variable ID doesn't match a SBML
								// component, throw an exception?
							}
						}
					} else {
						rule.setVariable(parameter);
					}
				} else {
					rule.setVariable(species);
				}
			} else {
				rule.setVariable(compartment);
			}
		}
	}

	/**
	 * 
	 * @param reaction
	 * @param model
	 */
	private void setReactionCompartment(Reaction reaction, Model model) {

		if (reaction.isSetCompartment()) {
			String compartmentID = reaction.getCompartment();

			Compartment compartment = model.getCompartment(compartmentID);

			if (compartment != null) {
				reaction.setCompartment(compartment);
			} else {
				// TODO : the compartment ID doesn't match a compartment, throw
				// an exception?
			}
		}
	}

	/**
	 * 
	 * @param species
	 * @param model
	 */
	private void setSpeciesCompartment(Species species, Model model) {

		if (species.isSetCompartment()) {
			String compartmentID = species.getCompartment();

			Compartment compartment = model.getCompartment(compartmentID);

			if (compartment != null) {
				species.setCompartment(compartment);
			} else {
				// TODO : the compartment ID doesn't match a compartment, throw
				// an exception?
			}
		}
	}

	/**
	 * 
	 * @param species
	 * @param model
	 */
	private void setSpeciesConversionFactor(Species species, Model model) {

		if (species.isSetConversionFactor()) {
			String conversionFactorID = species.getConversionFactor();

			Parameter parameter = model.getParameter(conversionFactorID);

			if (parameter != null) {
				species.setConversionFactor(parameter);
			} else {
				// TODO : the parameter ID doesn't match a parameter, throw an
				// exception?
			}
		}
	}

	/**
	 * 
	 * @param speciesReference
	 * @param model
	 */
	private void setSpeciesReferenceSpecies(
			SimpleSpeciesReference speciesReference, Model model) {

		if (speciesReference.isSetSpecies()) {
			String speciesID = speciesReference.getSpecies();

			Species species = model.getSpecies(speciesID);

			if (species != null) {
				speciesReference.setSpecies(species);
			} else {
				// TODO : the species ID doesn't match a species, throw an
				// exception?
			}
		}
	}

	/**
	 * 
	 * @param species
	 * @param model
	 */
	private void setSpeciesSpeciesType(Species species, Model model) {

		if (species.isSetSpeciesType()) {
			String speciesTypeID = species.getSpeciesType();

			SpeciesType speciesType = model.getSpeciesType(speciesTypeID);

			if (speciesType != null) {
				species.setSpeciesType(speciesType);
			} else {
				// TODO : the speciesType ID doesn't match a speciesType, throw
				// an exception?
			}
		}
	}

	/**
	 * 
	 * @param species
	 * @param model
	 */
	private void setSpeciesSubstanceUnits(Species species, Model model) {

		if (species.isSetSubstanceUnits()) {
			String substanceUnitsID = species.getSubstanceUnits();

			UnitDefinition unitDefinition = model
					.getUnitDefinition(substanceUnitsID);

			if (unitDefinition != null) {
				species.setSubstanceUnits(unitDefinition);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.WritingParser#writeAttributes(org.sbml.jsbml.
	 * xml.stax.SBMLObjectForXML, java.lang.Object)
	 */
	public void writeAttributes(SBMLObjectForXML xmlObject,
			Object sbmlElementToWrite) {
		if (sbmlElementToWrite instanceof SBase) {
			SBase sbase = (SBase) sbmlElementToWrite;

			xmlObject.addAttributes(sbase.writeXMLAttributes());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.WritingParser#writeCharacters(org.sbml.jsbml.
	 * xml.stax.SBMLObjectForXML, java.lang.Object)
	 */
	public void writeCharacters(SBMLObjectForXML xmlObject,
			Object sbmlElementToWrite) {
		// TODO SBML components don't have any characters in the XML file. what
		// to do?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.WritingParser#writeElement(org.sbml.jsbml.xml
	 * .stax.SBMLObjectForXML, java.lang.Object, int)
	 */
	public void writeElement(SBMLObjectForXML xmlObject,
			Object sbmlElementToWrite) {

		if (sbmlElementToWrite instanceof SBase) {
			SBase sbase = (SBase) sbmlElementToWrite;
			if (!xmlObject.isSetName()) {
				if (sbase.getElementName().equals("species")) {
					xmlObject.setName("specie");
				} else if (sbase.getElementName().equals("speciesReference")) {
					xmlObject.setName("specieReference");
				} else if (sbase.getElementName().equals("assignementRule")) {
					AssignmentRule assignmentRule = (AssignmentRule) sbase;
					Variable variable = assignmentRule.getVariableInstance();

					if (variable instanceof Species) {
						xmlObject.setName("specieConcentrationRule");
					} else if (variable instanceof Compartment) {
						xmlObject.setName("compartmentVolumeRule");
					} else if (variable instanceof Parameter) {
						xmlObject.setName("parameterRule");
					}
				} else {
					xmlObject.setName(sbase.getElementName());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.xml.stax.WritingParser#writeNamespaces(org.sbml.jsbml.
	 * xml.stax.SBMLObjectForXML, java.lang.Object)
	 */
	public void writeNamespaces(SBMLObjectForXML xmlObject,
			Object sbmlElementToWrite) {
		if (sbmlElementToWrite instanceof SBase) {
			SBase sbase = (SBase) sbmlElementToWrite;

			if (sbase instanceof SBMLDocument) {
				SBMLDocument sbmlDocument = (SBMLDocument) sbmlElementToWrite;

				xmlObject.addAttributes(sbmlDocument
						.getSBMLDocumentNamespaces());
			}

			xmlObject.setPrefix("");
		}
	}

}
