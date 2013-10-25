/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
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
package org.sbml.jsbml.ext.comp;

import java.util.Map;

import org.sbml.jsbml.AbstractNamedSBase;
import org.sbml.jsbml.NamedSBase;
import org.sbml.jsbml.util.TreeNodeChangeEvent;

/**
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 */
public abstract class AbstractNamedSBaseRef extends SBaseRef implements NamedSBase {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -7590217205832827913L;

	/**
	 * id of the SBML component (can be optional depending on the level and
	 * version). Matches the id attribute of an element in a SBML file.
	 */
	private String id;

	/**
	 * name of the SBML component (can be optional depending on the level and
	 * version). Matches the name attribute of an element in a SBML file.
	 */
	private String name;

	/**
	 * Creates an {@link AbstractNamedSBase}. By default, id and name are {@code null}.
	 */
	public AbstractNamedSBaseRef() {
		super();
		id = null;
		name = null;
	}

	/**
	 * Creates an {@link AbstractNamedSBase} from a given {@link AbstractNamedSBase}.
	 * 
	 * @param nsb an {@code AbstractNamedSBase} object to clone
	 */
	public AbstractNamedSBaseRef(AbstractNamedSBaseRef nsb) {
		super(nsb);
		this.id = nsb.isSetId() ? new String(nsb.getId()) : null;
		this.name = nsb.isSetName() ? new String(nsb.getName()) : null;
	}

	/**
	 * Creates an {@link AbstractNamedSBase} from a level and version. By default, id
	 * and name are {@code null}.
	 * 
	 * @param level the SBML level
	 * @param version the SBML version
	 */
	public AbstractNamedSBaseRef(int level, int version) {
		this();
		setLevel(level);
		setVersion(version);
	}

	/**
	 * Creates an {@link AbstractNamedSBase} with the given identifier. Note
	 * that with this constructor the level and version of the element are not
	 * specified. These elements are however required to ensure the validity of
	 * the SBML data structure. Without level and version, it may not be
	 * possible to serialize this class to SBML.
	 * 
	 * @param id the id of this {@code AbstractNamedSBase}
	 */
	public AbstractNamedSBaseRef(String id) {
		this();
		setId(id);
	}
	
	/**
	 * Creates an AbctractNamedSBase from an id, level and version.
	 * 
	 * @param id the id of this {@code AbstractNamedSBase}
	 * @param level the SBML level
	 * @param version the SBML version
	 */
	public AbstractNamedSBaseRef(String id, int level, int version) {
		this(id, null, level, version);
	}

	/**
	 * Creates an AbctractNamedSBase from an id, name, level and version.
	 * 
	 * @param id the id of this {@code AbstractNamedSBase}
	 * @param name the name of this {@code AbstractNamedSBase}
	 * @param level the SBML level
	 * @param version the SBML version
	 */
	public AbstractNamedSBaseRef(String id, String name, int level, int version) {
		this(level, version);
		setId(id);
		setName(name);
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		boolean equals = super.equals(object);
		if (equals) {
			NamedSBase nsb = (NamedSBase) object;
			equals &= nsb.isSetId() == isSetId();
			if (equals && isSetId()) {
				equals &= nsb.getId().equals(getId());
			}
			equals &= nsb.isSetName() == isSetName();
			if (equals && nsb.isSetName()) {
				equals &= nsb.getName().equals(getName());
			}
		}
		return equals;
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#getId()
	 */
	public String getId() {
		return isSetId() ? this.id : "";
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#getName()
	 */
	public String getName() {
		return isSetName() ? this.name : "";
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 829;
		int hashCode = super.hashCode();
		if (isSetId()) {
			hashCode += prime * getId().hashCode();
		}
		if (isSetName()) {
			hashCode += prime * getName().hashCode();
		}
		return hashCode;
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#isSetId()
	 */
	public boolean isSetId() {
		return id != null;
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#isSetName()
	 */
	public boolean isSetName() {
		return name != null;
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);

		// TODO : we should probably be careful there and check if there is a
		// prefix set before reading the id or name
		// as there are not defined at the level of the SBase on the SBML
		// specifications and some packages might define them in their own
		// name space.

		if (!isAttributeRead) {
			if (attributeName.equals("id") && (getLevel() > 1)) {
				this.setId(value);
				return true;
			} else if (attributeName.equals("name")) {
				this.setName(value);
				if (isSetLevel() && (getLevel() == 1)) {
					this.setId(value);
				}
				return true;
			}
		}
		return isAttributeRead;
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#setId(java.lang.String)
	 */
	public void setId(String id) {
		String property = getLevel() == 1 ? TreeNodeChangeEvent.name : TreeNodeChangeEvent.id;
		String oldId = this.id;

		/*
		 
		  // TODO : check which model we should get to have the correct id namespace
		 
		Model model = getModel();
    if ((oldId != null) && (model != null)) {
      // Delete previous identifier only if defined.
      model.registerIds(this.getParent(), this, false, true);
    }
    */
		
    if ((id == null) || (id.trim().length() == 0)) {
      this.id = null;
    } else { // else if (checkIdentifier(id)) {
      this.id = id;
    }
    /*
		if ((model != null) && !model.registerIds(this.getParent(), this, false, false)) {
        IdentifierException exc = new IdentifierException(this, this.id);
        this.id = oldId; // restore the previous setting!
        throw new IllegalArgumentException(exc);
    }
    */
		firePropertyChange(property, oldId, this.id);
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#setName(java.lang.String)
	 */
	public void setName(String name) {
		// removed the call to the trim() function as a name with only space
		// should be considered valid.
		String oldName = this.name;
		if ((name == null) || (name.length() == 0)) {
			this.name = null;
		} else {
			this.name = name;
		}
		if (!isSetId() && (getLevel() == 1)) {
      /*
       * Note: In Level 1 there is no id attribute but the name is actually the
       * id. Since Level 2 the name attribute has been intended to be a human-readable
       * name, not a unique identifier (this was the meaning in Level 1). JSBML therefore
       * has to set the id as well when calling this method in Level 1 models.
       */
			setId(name);
		} else {
			// else part to avoid calling this method twice.
			firePropertyChange(TreeNodeChangeEvent.name, oldName, this.name);
		}
	}

	/**
	 * Returns the name of the component, if it is available. Otherwise,
	 * the identifier is returned. If both is not possible, the class name of
	 * this element is returned.
	 */
	public String toString() {
		if (isSetName() && (getName().length() > 0)) {
			return name;
		}
		if (isSetId()) {
			return id;
		}
		return getElementName();
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#unsetId()
	 */
	public void unsetId() {
		setId(null);
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#unsetName()
	 */
	public void unsetName() {
		setName(null);
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();
		
		if (isSetId()) {
			if (getLevel() != 1) {
				attributes.put(CompConstant.shortLabel + ":id", getId());
			} else {
				attributes.put("name", getId());
			}
		}
		if (isSetName()) {
			attributes.put(CompConstant.shortLabel + ":name", getName());
		}
		return attributes;
	}


}
