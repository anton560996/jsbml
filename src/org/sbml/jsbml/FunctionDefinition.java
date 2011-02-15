/*
 * $Id$
 * $URL$
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

package org.sbml.jsbml;

import java.util.Map;

import org.sbml.jsbml.text.parser.ParseException;

/**
 * Represents the functionDefinition XML element of a SBML file.
 * 
 * @author Andreas Dr&auml;ger
 * @author marine
 */
public class FunctionDefinition extends AbstractMathContainer implements
		CallableSBase {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 5103621145642898899L;
	/**
	 * Error message to indicate that an incorrect {@link Type} has been passed
	 * to a method.
	 */
	private static final String ILLEGAL_ASTNODE_TYPE_MSG = "Math element is expected to be of type %s, but given is %s.";
	/**
	 * Represents the "id" attribute of a functionDefinition element.
	 */
	private String id;
	/**
	 * Represents the "name" attribute of a functionDefinition element.
	 */
	private String name;

	/**
	 * Creates a FunctionDefinition instance. By default, id and name are null.
	 */
	public FunctionDefinition() {
		super();
		this.id = null;
		this.name = null;
	}

	/**
	 * Creates a FunctionDefinition instance from a given FunctionDefinition.
	 * 
	 * @param sb
	 */
	public FunctionDefinition(FunctionDefinition sb) {
		super(sb);
		if (sb.isSetId()) {
			this.id = new String(sb.getId());
		} else {
			this.id = null;
		}
		if (isSetName()) {
			this.name = new String(sb.getName());
		} else {
			this.name = null;
		}
	}

	/**
	 * Creates a FunctionDefinition instance from a level and version. By
	 * default, name is null.
	 * 
	 * @param level
	 * @param version
	 */
	public FunctionDefinition(int level, int version) {
		super(level, version);
		if (getLevel() < 2) {
			throw new IllegalArgumentException(String.format(
					"Cannot create a %s with Level = %s.", getElementName(),
					getLevel()));
		}
	}

	/**
	 * Creates a FunctionDefinition instance from an id, ASTNode, level and
	 * version. By default, name is null. If the ASTNode is not of type lambda,
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param id
	 * @param lambda
	 * @param level
	 * @param version
	 */
	public FunctionDefinition(String id, ASTNode lambda, int level, int version) {
		super(lambda, level, version);
		if (!lambda.isLambda()) {
			throw new IllegalArgumentException(String.format(
									ILLEGAL_ASTNODE_TYPE_MSG,
									ASTNode.Type.LAMBDA, lambda.getType()));
		}
		if (id != null) {
			this.id = new String(id);
		} else {
			this.id = null;
		}
		this.name = null;
	}

	/**
	 * Creates a FunctionDefinition instance from an id, level and version.
	 * 
	 * @param id
	 * @param level
	 * @param version
	 */
	public FunctionDefinition(String id, int level, int version) {
		super(level, version);
		if (id != null) {
			this.id = new String(id);
		} else {
			this.id = null;
		}
		this.name = null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#clone()
	 */
	public FunctionDefinition clone() {
		return new FunctionDefinition(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof FunctionDefinition) {
			boolean equal = super.equals(o);
			FunctionDefinition fd = (FunctionDefinition) o;
			if ((!fd.isSetId() && isSetId()) || (fd.isSetId() && !isSetId())) {
				return false;
			}
			if (fd.isSetId() && isSetId()) {
				equal &= fd.getId().equals(getId());
			}

			if ((!fd.isSetName() && isSetName())
					|| (fd.isSetName() && !isSetName())) {
				return false;
			}
			if (fd.isSetName() && isSetName()) {
				equal &= fd.getName().equals(getName());
			}
			return equal;
		}
		return false;
	}

	/**
	 * Get the nth argument to this function.
	 * 
	 * Callers should first find out the number of arguments to the function by
	 * calling {@link #getNumArguments()}.
	 * 
	 * @param n
	 *            an integer index for the argument sought.
	 * @return the nth argument (bound variable) passed to this
	 *         {@link FunctionDefinition}.
	 */
	public ASTNode getArgument(int n) {
		if (getNumArguments() < n) {
			throw new IndexOutOfBoundsException(String.format(
					"No such argument with index %d.", n));
		}
		return getMath().getChild(n);
	}

	/**
	 * Get the argument named name to this {@link FunctionDefinition}.
	 * 
	 * @param name
	 *            the exact name (case-sensitive) of the sought-after argument
	 * @return the argument (bound variable) having the given name, or null if
	 *         no such argument exists.
	 */
	public ASTNode getArgument(String name) {
		ASTNode arg = null;
		for (int i=0; i<getNumArguments(); i++) {
			arg = getArgument(i);
			if (arg.getName().equals(name)) {
				return arg;
			}
		}
		return arg;
	}

	/**
	 * Get the mathematical expression that is the body of this
	 * {@link FunctionDefinition} object.
	 * 
	 * @return the body of this {@link FunctionDefinition} as an Abstract Syntax
	 *         Tree, or null if no body is defined.
	 */
	public ASTNode getBody() {
		return isSetMath() ? getMath().getRightChild() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#getId()
	 */
	public String getId() {
		return isSetId() ? this.id : "";
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#getName()
	 */
	public String getName() {
		return isSetName() ? name : "";
	}

	/**
	 * Get the number of arguments (bound variables) taken by this
	 * {@link FunctionDefinition}.
	 * 
	 * @return the number of arguments (bound variables) that must be passed to
	 *         this {@link FunctionDefinition}.
	 */
	public int getNumArguments() {
		return isSetMath() && (getMath().getNumChildren() > 1) ? getMath()
				.getNumChildren() - 1 : 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractSBase#getParent()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ListOf<FunctionDefinition> getParent() {
		return (ListOf<FunctionDefinition>) super.getParent();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#isSetId()
	 */
	public boolean isSetId() {
		return id != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#isSetName()
	 */
	public boolean isSetName() {
		return name != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#readAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);
		if (!isAttributeRead) {
			if (attributeName.equals("id")) {
				setId(value);
				return true;
			} else if (attributeName.equals("name")) {
				setName(value);
				return true;
			}
		}
		return isAttributeRead;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#setFormula(java.lang.String)
	 */
	@Override
	public void setFormula(String formula) throws ParseException {
		ASTNode math = ASTNode.parseFormula(formula);
		if (!math.isLambda()) {
			throw new IllegalArgumentException(String.format(
					ILLEGAL_ASTNODE_TYPE_MSG, ASTNode.Type.LAMBDA, math
							.getType()));
		}
		setMath(math);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#setId(java.lang.String)
	 */
	public void setId(String id) {
		if (getLevel() < 2) {
			throw new PropertyNotAvailableError(SBaseChangedEvent.id, this);
		}
		String oldID = this.id;
		this.id = id;
		firePropertyChange(SBaseChangedEvent.id, oldID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#setMath(org.sbml.jsbml.ASTNode)
	 */
	@Override
	public void setMath(ASTNode math) {
		if (getLevel() < 2) {
			// throw new PropertyNotAvailableError(SBaseChangedEvent.id, this);
			// We can use internally ASTNode even if working on level 1 model !!
		}

		if (!math.isLambda()) {
			throw new IllegalArgumentException(String.format(
					ILLEGAL_ASTNODE_TYPE_MSG, ASTNode.Type.LAMBDA, math
							.getType()));
		}
		super.setMath(math);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#setName(java.lang.String)
	 */
	public void setName(String name) {
		if (getLevel() < 2) {
			throw new PropertyNotAvailableError(SBaseChangedEvent.id, this);
		}
		String oldName = this.name;
		this.name = name;
		firePropertyChange(SBaseChangedEvent.name, oldName, name);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#toString()
	 */
	@Override
	public String toString() {
		if (isSetName() && getName().length() > 0) {
			return name;
		}
		if (isSetId()) {
			return id;
		}
		String name = getClass().getName();
		return name.substring(name.lastIndexOf('.') + 1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#unsetId()
	 */
	public void unsetId() {
		this.id = null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.NamedSBase#unsetName()
	 */
	public void unsetName() {
		this.name = null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractMathContainer#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();

		if (isSetId() && (getLevel() > 1)) {
			attributes.put("id", getId());
		}
		if (isSetName() && (getLevel() > 1)) {
			attributes.put("name", getName());
		}

		return attributes;
	}
}
