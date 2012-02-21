/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2012 jointly by the following organizations:
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
package org.sbml.jsbml.ext.groups;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.util.TreeNodeChangeEvent;

/**
 * This class represents the listOf extension for the group package
 *
 * @author Marine Dumousseau
 * @since 1.0
 * @version $Rev$
 */
public class ListOfGroups<T extends SBase> extends ListOf<T>{

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -1320591187114439942L;

	/**
	 * 
	 * @return
	 */
    public GroupList getCurrentList() {
        return listType;
    }

    /**
     * name of the list at it appears in the SBMLFile.
     */
    private GroupList listType = GroupList.none;

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#equals(java.lang.Object)
      */
	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object o) {
        if (o instanceof ListOfGroups<?>) {
            boolean equals = super.equals(o);
            // TODO : test the type of list
            ListOfGroups<SBase> listOf = (ListOfGroups<SBase>) o;
            equals &= getCurrentList() == listOf.getCurrentList();
            return listOf.containsAll(this) && equals;
        }
        return false;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.sbml.jlibsbml.SBase#getAnnotationString()
	 */
	public String getElementName() {
		String name = getCurrentList().toString();
		return name;
	}

   /**
	 * Sets the SBaseListType of this ListOf instance to 'listType'.
	 *
	 * @param listType
	 */
	public void setCurrentList(GroupList currentList) {
		GroupList oldList = this.listType;
		this.listType = currentList;
		firePropertyChange(TreeNodeChangeEvent.currentList, oldList, this.listType);
	}

}
