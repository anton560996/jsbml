/*
 * $Id: Event.java 38 2009-11-05 15:50:38Z niko-rodrigue $
 * $URL: https://jsbml.svn.sourceforge.net/svnroot/jsbml/trunk/src/org/sbml/jsbml/Event.java $
 *
 * 
 *==================================================================================
 * Copyright (c) 2009 The jsbml team.
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

package org.sbml.jsbml.element;

import java.util.HashMap;

import org.sbml.jsbml.xml.CurrentListOfSBMLElements;

/**
 * @author Andreas Dr&auml;ger <a
 *         href="mailto:andreas.draeger@uni-tuebingen.de">
 *         andreas.draeger@uni-tuebingen.de</a>
 * 
 */
public class Event extends AbstractNamedSBase {
	/**
	 * 
	 */
	private boolean useValuesFromTriggerTime;
	/**
	 * 
	 */
	private Trigger trigger;
	/**
	 * 
	 */
	private ListOf<EventAssignment> listOfEventAssignments;
	/**
	 * 
	 */
	private Delay delay;
	/**
	 * 
	 */
	private UnitDefinition timeUnits;
	
	/**
	 * 
	 */
	private String timeUnitsID;
	
	/**
	 * 
	 */
	public Event() {
		super();
		this.trigger = null;
		this.delay = null;
		this.listOfEventAssignments = null;
		this.timeUnits = null;
		this.timeUnitsID = null;
	}
	
	/**
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public Event(Event event) {
		super(event);
		if (event.isSetTrigger())
			setTrigger(event.getTrigger().clone());
		else
			trigger = null;
		this.useValuesFromTriggerTime = event.isUseValuesFromTriggerTime();
		if (event.isSetDelay()) {
			setDelay(event.getDelay().clone());
		} else
			this.delay = null;
		setListOfEventAssignments((ListOf<EventAssignment>) event.getListOfEventAssignments().clone());
		this.timeUnits = event.isSetTimeUnits() ? event.getTimeUnitsInstance()
				.clone() : null;
	}

	/**
	 * 
	 * @param level
	 * @param version
	 */
	public Event(int level, int version) {
		super(level, version);
		initDefaults();
	}

	/**
	 * 
	 * @param id
	 */
	public Event(String id, int level, int version) {
		super(id, level, version);
		initDefaults();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Event(String id, String name, int level, int version) {
		super(id, name, level, version);
		initDefaults();
	}

	/**
	 * 
	 * @param eventass
	 */
	public void addEventAssignement(EventAssignment eventass) {
		if (!isSetListOfEventAssignments()){
			this.listOfEventAssignments = new ListOf<EventAssignment>();
		}
		listOfEventAssignments.add(eventass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.SBase#clone()
	 */
	// @Override
	public Event clone() {
		return new Event(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.SBase#equals(java.lang.Object)
	 */
	// @Override
	public boolean equals(Object o) {
		boolean equal = super.equals(o);
		if (o instanceof Event) {
			Event e = (Event) o;
			equal &= e.getUseValuesFromTriggerTime() == getUseValuesFromTriggerTime();
			equal &= e.getListOfEventAssignments().equals(
					getListOfEventAssignments());
			if ((e.isSetDelay() && !isSetDelay())
					|| (!e.isSetDelay() && isSetDelay()))
				return false;
			else if (e.isSetDelay() && isSetDelay())
				equal &= e.getDelay().equals(getDelay());
			if ((!e.isSetTrigger() && isSetTrigger())
					|| (e.isSetTrigger() && !isSetTrigger()))
				return false;
			else if (e.isSetTrigger() && isSetTrigger())
				equal &= e.getTrigger().equals(getTrigger());
			if ((!e.isSetTimeUnits() && isSetTimeUnits())
					|| (e.isSetTimeUnits() && !isSetTimeUnits()))
				return false;
			else if (e.isSetTimeUnits() && isSetTimeUnits())
				equal &= e.getTimeUnits().equals(getTimeUnits());
			return equal;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public Delay getDelay() {
		return delay;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public EventAssignment getEventAssignment(int n) {
		if (isSetListOfEventAssignments()){
			return listOfEventAssignments.get(n);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public ListOf<EventAssignment> getListOfEventAssignments() {
		return listOfEventAssignments;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumEventAssignments() {
		if (isSetListOfEventAssignments()){
			return listOfEventAssignments.size();
		}
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public String getTimeUnits() {
		return isSetTimeUnitsID() ? timeUnitsID : null;
	}

	/**
	 * 
	 * @return
	 */
	public UnitDefinition getTimeUnitsInstance() {
		return timeUnits;
	}
	
	/**
	 * 
	 * @return
	 */
	public Trigger getTrigger() {
		return trigger;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getUseValuesFromTriggerTime() {
		return useValuesFromTriggerTime;
	}

	/**
	 * 
	 */
	public void initDefaults() {
		useValuesFromTriggerTime = true;
		setTrigger(new Trigger(getLevel(), getVersion()));
		setListOfEventAssignments(new ListOf<EventAssignment>(getLevel(), getVersion()));
		timeUnits = null;
		delay = null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSetDelay() {
		return delay != null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSetTimeUnits() {
		return timeUnits != null;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSetTimeUnitsID() {
		return timeUnitsID != null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSetTrigger() {
		return trigger != null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isUseValuesFromTriggerTime() {
		return useValuesFromTriggerTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSetListOfEventAssignments() {
		return listOfEventAssignments != null;
	}

	/**
	 * 
	 * @param delay
	 */
	public void setDelay(Delay delay) {
		this.delay = delay;
		this.delay.parentSBMLObject = this;
		this.delay.sbaseAdded();
	}

	/**
	 * 
	 * @param listOfEventAssignments
	 */
	public void setListOfEventAssignments(
			ListOf<EventAssignment> listOfEventAssignments) {
		this.listOfEventAssignments = listOfEventAssignments;
		this.listOfEventAssignments.setCurrentList(CurrentListOfSBMLElements.listOfEventAssignments);
		setThisAsParentSBMLObject(this.listOfEventAssignments);
		stateChanged();
	}

	/**
	 * 
	 * @param timeUnits
	 */
	public void setTimeUnits(String timeUnits) {
		this.timeUnits = (UnitDefinition) getModel().findNamedSBase(timeUnits);
	}

	/**
	 * 
	 * @param timeUnits
	 */
	public void setTimeUnits(UnitDefinition timeUnits) {
		this.timeUnits = timeUnits;
		stateChanged();
	}

	/**
	 * 
	 * @param trigger
	 */
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
		this.trigger.parentSBMLObject = this;
		this.trigger.sbaseAdded();
	}

	/**
	 * 
	 * @param useValuesFromTriggerTime
	 */
	public void setUseValuesFromTriggerTime(boolean useValuesFromTriggerTime) {
		this.useValuesFromTriggerTime = useValuesFromTriggerTime;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.Rule#isSpeciesConcentration()
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix, String value){
		boolean isAttributeRead = super.readAttribute(attributeName, prefix, value);
		
		if (!isAttributeRead){
			if (attributeName.equals("useValuesFromTriggerTime")){
				if (value.equals("true")){
					this.setUseValuesFromTriggerTime(true);
					return true;
				}
				else if (value.equals("false")){
					this.setUseValuesFromTriggerTime(false);
					return true;
				}
			}
			else if (attributeName.equals("timeUnits")){
				this.setTimeUnits(value);
			}
		}
		return isAttributeRead;
	}

	@Override
	public HashMap<String, String> writeXMLAttributes() {
		HashMap<String, String> attributes = super.writeXMLAttributes();
		
		if (useValuesFromTriggerTime){
			attributes.put("useValuesFromTriggerTime", "true");
		}
		else {
			attributes.put("useValuesFromTriggerTime", "false");
		}
		
		if (isSetTimeUnitsID()){
			attributes.put("timeUnits", getTimeUnits());
		}
		
		return attributes;
	}
}
