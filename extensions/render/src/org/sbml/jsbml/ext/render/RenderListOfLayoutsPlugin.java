/*
 * $Id$
 * $URL$
 *
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
package org.sbml.jsbml.ext.render;

import java.text.MessageFormat;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.util.filters.NameFilter;


/**
 * @author Jakob Matthes
 * @version $Rev$
 * @since 1.0
 * @date 16.05.2012
 */
public class RenderListOfLayoutsPlugin extends AbstractRenderPlugin {

  /**
   *
   */
  private static final long serialVersionUID = -4727110538908666931L;

  /**
   *
   */
  private ListOf<GlobalRenderInformation> listOfGlobalRenderInformation;

  /**
   * Creates an RenderModelPlugin instance
   */
  public RenderListOfLayoutsPlugin(ListOf<Layout> listOfLayouts) {
    super(listOfLayouts);
    initDefaults();
  }

  /**
   * Clone constructor
   */
  public RenderListOfLayoutsPlugin(RenderListOfLayoutsPlugin obj) {
    super(obj);
  }

  /**
   * @param field
   */
  public boolean addGlobalRenderInformation(GlobalRenderInformation field) {
    return getListOfGlobalRenderInformation().add(field);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.AbstractRenderPlugin#clone()
   */
  @Override
  public RenderListOfLayoutsPlugin clone() {
    return new RenderListOfLayoutsPlugin(this);
  }

  /**
   * create a new GlobalRenderInformation element and adds it to the ListOfGlobalRenderInformation list
   * <p><b>NOTE:</b>
   * only use this method, if ID is not mandatory in GlobalRenderInformation
   * otherwise use @see createGlobalRenderInformation(String id)!</p>
   */
  public GlobalRenderInformation createGlobalRenderInformation() {
    return createGlobalRenderInformation(null);
  }

  /**
   * create a new GlobalRenderInformation element and adds it to the ListOfGlobalRenderInformation list
   */
  public GlobalRenderInformation createGlobalRenderInformation(String id) {
    SBase sBase = getExtendedSBase();
    GlobalRenderInformation field = new GlobalRenderInformation(id, sBase.getLevel(), sBase.getVersion());
    addGlobalRenderInformation(field);
    return field;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.AbstractRenderPlugin#getAllowsChildren()
   */
  @Override
  public boolean getAllowsChildren() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.AbstractRenderPlugin#getChildAt(int)
   */
  @Override
  public SBase getChildAt(int childIndex) {
    if (childIndex < 0) {
      throw new IndexOutOfBoundsException(childIndex + " < 0");
    }
    int pos = 0;
     if (isSetListOfGlobalRenderInformation()) {
       if (pos == childIndex) {
         return getListOfGlobalRenderInformation();
       }
       pos++;
     }
    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}", childIndex,
      +Math.min(pos, 0)));
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.AbstractRenderPlugin#getChildCount()
   */
  @Override
  public int getChildCount() {
    int count = super.getChildCount();
     if (isSetListOfGlobalRenderInformation()) {
      count++;
     }
    return count;
  }

  /**
   * @return the listOfGlobalRenderInformation
   */
  public ListOf<GlobalRenderInformation> getListOfGlobalRenderInformation() {
    if (!isSetListOfGlobalRenderInformation()) {
      SBase sBase = getExtendedSBase();
      listOfGlobalRenderInformation = new ListOf<GlobalRenderInformation>(sBase.getLevel(), sBase.getVersion());
      listOfGlobalRenderInformation.setNamespace(RenderConstants.namespaceURI);
      listOfGlobalRenderInformation.setSBaseListType(ListOf.Type.other);
      sBase.registerChild(listOfGlobalRenderInformation);
    }
    return listOfGlobalRenderInformation;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.render.AbstractRenderPlugin#initDefaults()
   */
  @Override
  public void initDefaults() {
//    TODO setNamespace(RenderConstants.namespaceURI);
  }

  /**
   * @return {@code true}, if listOfGlobalRenderInformation contains at least one element,
   *         otherwise {@code false}
   */
  public boolean isSetListOfGlobalRenderInformation() {
    if ((listOfGlobalRenderInformation == null) || listOfGlobalRenderInformation.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * @param field
   */
  public boolean removeGlobalRenderInformation(GlobalRenderInformation field) {
    if (isSetListOfGlobalRenderInformation()) {
      return getListOfGlobalRenderInformation().remove(field);
    }
    return false;
  }

  /**
   * @param i
   */
  public void removeGlobalRenderInformation(int i) {
    if (!isSetListOfGlobalRenderInformation()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfGlobalRenderInformation().remove(i);
  }

  /**
   *
   * @param id
   */
  public void removeGlobalRenderInformation(String id) {
    getListOfGlobalRenderInformation().removeFirst(new NameFilter(id));
  }


  /**
   * @param listOfGlobalRenderInformation
   */
  public void setListOfGlobalRenderInformation(ListOf<GlobalRenderInformation> listOfGlobalRenderInformation) {
    unsetListOfGlobalRenderInformation();
    this.listOfGlobalRenderInformation = listOfGlobalRenderInformation;
    getExtendedSBase().registerChild(this.listOfGlobalRenderInformation);
  }

  /**
   * @return {@code true}, if listOfGlobalRenderInformation contained at least one element,
   *         otherwise {@code false}
   */
  public boolean unsetListOfGlobalRenderInformation() {
    if (isSetListOfGlobalRenderInformation()) {
      ListOf<GlobalRenderInformation> oldGlobalRenderInformation = this.listOfGlobalRenderInformation;
      this.listOfGlobalRenderInformation = null;
      oldGlobalRenderInformation.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }

}
