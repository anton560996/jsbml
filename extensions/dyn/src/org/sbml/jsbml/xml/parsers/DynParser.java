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
 * 6. Boston University, Boston, MA, USA
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.xml.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mangosdk.spi.ProviderFor;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.SBasePlugin;
import org.sbml.jsbml.ext.dyn.DynCompartmentPlugin;
import org.sbml.jsbml.ext.dyn.DynConstants;
import org.sbml.jsbml.ext.dyn.DynElement;
import org.sbml.jsbml.ext.dyn.DynEventPlugin;
import org.sbml.jsbml.ext.dyn.DynSBasePlugin;
import org.sbml.jsbml.ext.dyn.SpatialComponent;

/**
 * This class is used to parse the dynamic structures extension package elements
 * and attributes. The namespaceURI URI of this parser is
 * "http://www.sbml.org/sbml/level3/version1/dyn/version1". This parser is able
 * to read and write elements of the dyn package (implements ReadingParser and
 * WritingParser).
 * 
 *
 * @author Harold Gomez
 * @since 1.0
 * @version $Rev$
 */
@ProviderFor(ReadingParser.class)
public class DynParser extends AbstractReaderWriter implements PackageParser {

  private Logger logger = Logger.getLogger(DynParser.class);

  @Override
  public List<String> getNamespaces() {
    return DynConstants.namespaces;
  }

  @Override
  public SBasePlugin createPluginFor(SBase sbase) {
    if (sbase != null) {
      if (sbase instanceof Event) {
        return new DynEventPlugin((Event) sbase);
      } else if (sbase instanceof Compartment) {
        return new DynCompartmentPlugin((Compartment) sbase);
      } else {
        return new DynSBasePlugin(sbase);
      }
    }
    return null;
  }

  @Override
  public String getNamespaceFor(int level, int version, int packageVersion) {
    if (level == DynConstants.MIN_SBML_LEVEL
        && version == DynConstants.MIN_SBML_VERSION
        && packageVersion == DynConstants.PACKAGE_VERSION) {
      return DynConstants.namespaceURI_L3V1V1;
    }
    return null;
  }

  @Override
  public List<String> getPackageNamespaces() {
    return getNamespaces();
  }

  @Override
  public String getPackageName() {
    return DynConstants.shortLabel;
  }

  @Override
  public boolean isRequired() {
    return false;
  }

  @Override
  public Object processStartElement(String elementName, String uri,
    String prefix, boolean hasAttributes, boolean hasNamespaces,
    Object contextObject) {

    if (contextObject instanceof Compartment) {
      Compartment compartment = (Compartment) contextObject;

      DynCompartmentPlugin dynCompartment = null;
      if (compartment.getExtension(DynConstants.namespaceURI) != null) {
        dynCompartment = (DynCompartmentPlugin) compartment
            .getExtension(DynConstants.namespaceURI);
      } else {
        dynCompartment = new DynCompartmentPlugin(compartment);
        compartment.addExtension(DynConstants.namespaceURI,
          dynCompartment);
      }

      if (elementName.equals(DynConstants.listOfSpatialComponents)) {
        ListOf<SpatialComponent> componentList = dynCompartment
            .getListOfSpatialComponents();
        return componentList;
      }
    }

    else if (contextObject instanceof Event) {
      Event event = (Event) contextObject;
      DynEventPlugin dynEvent = null;
      if (event.getExtension(DynConstants.namespaceURI) != null) {
        dynEvent = (DynEventPlugin) event
            .getExtension(DynConstants.namespaceURI);
      } else {
        dynEvent = new DynEventPlugin(event);
        event.addExtension(DynConstants.namespaceURI, dynEvent);
      }
      if (elementName.equals(DynConstants.listOfDynElements)) {
        ListOf<DynElement> listOfDynElements = dynEvent
            .getListOfDynElements();
        return listOfDynElements;
      }
    }

    else if (contextObject instanceof ListOf<?>) {
      ListOf<?> listOf = (ListOf<?>) contextObject;

      Object newElement = createListOfChild(listOf, elementName);

      if (newElement != null) {
        return newElement;
      }
    }
    return contextObject;
  }

  @Override
  public void processAttribute(String elementName, String attributeName,
    String value, String uri, String prefix, boolean isLastAttribute,
    Object contextObject) {

    if (contextObject instanceof Event) {
      Event event = (Event) contextObject;
      DynEventPlugin dynEvent = null;
      if (event.getExtension(DynConstants.namespaceURI) != null) {
        dynEvent = (DynEventPlugin) event
            .getExtension(DynConstants.namespaceURI);
      } else {
        dynEvent = new DynEventPlugin(event);
        event.addExtension(DynConstants.namespaceURI, dynEvent);
      }
      contextObject = dynEvent;
    }
    /*		else if (attributeName.equals(DynConstants.cboTerm)) {
			SBase component = (SBase) contextObject;
			DynSBasePlugin extSBase = null;
			if (component.getExtension(DynConstants.namespaceURI) != null) {
				extSBase = (DynSBasePlugin) component
						.getExtension(DynConstants.namespaceURI);
			} else {
				extSBase = new DynSBasePlugin(component);
				component.addExtension(DynConstants.namespaceURI, extSBase);
			}
			contextObject = .setCBO(extSBase.getCBOTerm());
		}*/
    super.processAttribute(elementName, attributeName, value, uri, prefix,
      isLastAttribute, contextObject);
  }

  @Override
  public String getShortLabel() {
    return DynConstants.shortLabel;
  }

  @Override
  public String getNamespaceURI() {
    return DynConstants.namespaceURI;
  }

  @Override
  public List<Object> getListOfSBMLElementsToWrite(Object treeNode) {
    if (logger.isDebugEnabled()) {
      logger.debug("getListOfSBMLElementsToWrite: "
          + treeNode.getClass().getCanonicalName());
    }

    List<Object> listOfElementsToWrite = new ArrayList<Object>();

    if (treeNode instanceof SBase
        && ((SBase) treeNode).getExtension(getNamespaceURI()) != null) {
      SBasePlugin sbasePlugin = ((SBase) treeNode)
          .getExtension(getNamespaceURI());

      if (sbasePlugin != null) {
        listOfElementsToWrite = super
            .getListOfSBMLElementsToWrite(sbasePlugin);
        logger.debug("getListOfSBMLElementsToWrite: nb children = "
            + sbasePlugin.getChildCount());
      }
    } else {
      listOfElementsToWrite = super
          .getListOfSBMLElementsToWrite(treeNode);
    }

    return listOfElementsToWrite;
  }

  @Override
  protected Object createListOfChild(ListOf<?> listOf, String elementName) {

    Object parentSBase = listOf.getParent();

    if (parentSBase == null) {
      return null;
    } else if (parentSBase instanceof Compartment
        || parentSBase instanceof Event) {
      parentSBase = ((SBase) parentSBase)
          .getExtension(DynConstants.namespaceURI);
    }

    String createMethodName = "create"
        + elementName.substring(0, 1).toUpperCase()
        + elementName.substring(1);
    Method createMethod = null;

    if (logger.isDebugEnabled()) {
      logger.debug("Method '" + createMethodName + "' will be used");
    }

    try {
      createMethod = parentSBase.getClass().getMethod(createMethodName,
        (Class<?>[]) null);
      return createMethod.invoke(parentSBase, (Object[]) null);

    } catch (SecurityException e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Method '" + createMethodName
          + "' is not accessible on "
          + parentSBase.getClass().getSimpleName());
        e.printStackTrace();
      }
    } catch (NoSuchMethodException e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Method '" + createMethodName
          + "' does not exist on "
          + parentSBase.getClass().getSimpleName());
      }
    } catch (IllegalArgumentException e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Problem invoking the method '" + createMethodName
          + "' on " + parentSBase.getClass().getSimpleName());
        logger.debug(e.getMessage());
      }
    } catch (IllegalAccessException e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Problem invoking the method '" + createMethodName
          + "' on " + parentSBase.getClass().getSimpleName());
        logger.debug(e.getMessage());
      }
    } catch (InvocationTargetException e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Problem invoking the method '" + createMethodName
          + "' on " + parentSBase.getClass().getSimpleName());
        logger.debug(e.getMessage());
      }
    }

    return null;
  }

}
