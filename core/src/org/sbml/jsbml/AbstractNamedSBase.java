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
 * 5. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml;

import java.text.MessageFormat;
import java.util.Map;

import org.sbml.jsbml.util.IdManager;
import org.sbml.jsbml.util.TreeNodeChangeEvent;
import org.sbml.jsbml.validator.SyntaxChecker;

/**
 * The base class for each SBML element with an optional id and name.
 * 
 * @author Andreas Dr&auml;ger
 * @author Nicolas Rodriguez
 * @author Marine Dumousseau
 * @since 0.8
 * @version $Rev$
 */
public abstract class AbstractNamedSBase extends AbstractSBase implements
NamedSBase {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -9186483076164094500L;

  /**
   * Checks whether the given idCandidate is a valid identifier according to
   * the SBML specifications.
   * 
   * @param idCandidate
   *            The {@link String} to be tested.
   * @param level
   *            Level of the SBML to be used.
   * @param version
   *            Version of the SBML to be used.
   * @return True if the argument satisfies the specification of identifiers
   *         in the SBML specifications or false otherwise.
   * @deprecated use {@link SyntaxChecker#isValidId(String, int, int)}
   */
  @Deprecated
  public static final boolean isValidId(String idCandidate, int level,
    int version) {
    return SyntaxChecker.isValidId(idCandidate, level, version);
  }

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
  public AbstractNamedSBase() {
    super();
    id = null;
    name = null;
  }

  /**
   * Creates an {@link AbstractNamedSBase} from a given {@link AbstractNamedSBase}.
   * 
   * @param nsb an {@code AbstractNamedSBase} object to clone
   */
  public AbstractNamedSBase(AbstractNamedSBase nsb) {
    super(nsb);
    id = nsb.isSetId() ? new String(nsb.getId()) : null;
    name = nsb.isSetName() ? new String(nsb.getName()) : null;
  }

  /**
   * Creates an {@link AbstractNamedSBase} from a level and version. By default, id
   * and name are {@code null}.
   * 
   * @param level the SBML level
   * @param version the SBML version
   */
  public AbstractNamedSBase(int level, int version) {
    super(level, version);
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
  public AbstractNamedSBase(String id) {
    this();
    setId(id);
  }

  /**
   * Creates an {@link AbstractNamedSBase} from an id, level and version.
   * 
   * @param id the id of this {@code AbstractNamedSBase}
   * @param level the SBML level
   * @param version the SBML version
   */
  public AbstractNamedSBase(String id, int level, int version) {
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
  public AbstractNamedSBase(String id, String name, int level, int version) {
    this(level, version);
    setId(id);
    setName(name);
  }

  /**
   * Checks if the sID is a valid identifier.
   * 
   * @param sID
   *            the identifier to be checked. If null or an invalid
   *            identifier, an exception will be thrown.
   * @return {@code true} only if the sID is a valid identifier.
   *         Otherwise this method throws an {@link IllegalArgumentException}.
   *         This is an intended behavior.
   * @throws IllegalArgumentException
   *             if the given id is not valid in this model.
   */
  boolean checkIdentifier(String sID) {
    if ((sID == null) || !SyntaxChecker.isValidId(sID, getLevel(), getVersion())) {
      throw new IllegalArgumentException(MessageFormat.format(
        "\"{0}\" is not a valid identifier for this {1}.", sID, getElementName()));
    }
    return true;
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
  @Override
  public String getId() {
    return isSetId() ? id : "";
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#getName()
   */
  @Override
  public String getName() {
    return isSetName() ? name : "";
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
  @Override
  public boolean isSetId() {
    return id != null;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#isSetName()
   */
  @Override
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

    // TODO: we should probably be careful there and check if there is a
    // prefix set before reading the id or name
    // as there are not defined at the level of the SBase on the SBML
    // specifications and some packages might define them in their own
    // name space.

    if (!isAttributeRead) {
      if (attributeName.equals("id") && (getLevel() > 1)) {
        setId(value);
        return true;
      } else if (attributeName.equals("name")) {
        setName(value);
        if (isSetLevel() && (getLevel() == 1)) {
          setId(value);
        }
        return true;
      }
    }
    return isAttributeRead;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#setId(java.lang.String)
   */
  @Override
  public void setId(String id) {
    String property = getLevel() == 1 ? TreeNodeChangeEvent.name : TreeNodeChangeEvent.id;
    String oldId = this.id;

    IdManager idManager = getIdManager(this);
    if (idManager != null) { // (oldId != null) // As the register and unregister are recursive, we need to call the unregister all the time until we have a non recursive method
      // Delete previous identifier only if defined.
      idManager.unregister(this); // TODO - do we need non recursive method on the IdManager interface ??
    }
    if ((id == null) || (id.trim().length() == 0)) {
      this.id = null;
    } else if (checkIdentifier(id)) {
      this.id = id;
    }
    if ((idManager != null) && !idManager.register(this)) {
      IdentifierException exc = new IdentifierException(this, this.id);
      this.id = oldId; // restore the previous setting!
      throw new IllegalArgumentException(exc);
    }
    firePropertyChange(property, oldId, this.id);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#setName(java.lang.String)
   */
  @Override
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
       * has to set the id (and not the name) when calling this method in Level 1 models.
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
  @Override
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
  @Override
  public void unsetId() {
    setId(null);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#unsetName()
   */
  @Override
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
        attributes.put("id", getId());
      } else {
        attributes.put("name", getId());
      }
    }
    if (isSetName()) {
      attributes.put("name", getName());
    }
    return attributes;
  }

}
