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

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.util.SBaseChangeEvent;
import org.sbml.jsbml.util.SBaseChangeListener;
import org.sbml.jsbml.util.StringTools;
import org.sbml.jsbml.util.ValuePair;
import org.sbml.jsbml.xml.XMLNode;
import org.sbml.jsbml.xml.stax.SBMLWriter;


/**
 * The base class for each {@link SBase} component.
 * 
 * @author Andreas Dr&auml;ger
 * @author Nicolas Rodrigues
 * @author Marine Dumousseau
 * @since 0.8
 * @version $Rev$
 */
public abstract class AbstractSBase extends AbstractTreeNode implements SBase {

	/**
	 * 
	 * @author Nicolas Rodrigues
	 *
	 */
	private static enum NOTES_TYPE {
		/**
		 * 
		 */
		NotesAny,
		/**
		 * 
		 */
		NotesBody,
		/**
		 * 
		 */
		NotesHTML 
	};

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 8781459818293592636L;
	
	/**
	 * Returns true is the level and version combination is a valid one, false otherwise.
	 * 
	 * @param level the SBML level
	 * @param version the SBML version
	 * @return true is the level and version combination is a valid one, false otherwise.
	 */
	public static boolean isValidLevelAndVersionCombination(int level,
			int version) {
		switch (level) {
		case 1:
			return ((1 <= version) && (version <= 2));
		case 2:
			return ((1 <= version) && (version <= 4));
		case 3:
			return ((1 <= version) && (version <= 1));
		default:
			return false;
		}
	}
	
	/**
	 * annotations of the SBML component. Matches the annotation XML node in a
	 * SBML file.
	 */
	private Annotation annotation;
	
	/**
	 * map containing the SBML extension object of additional packages with the
	 * appropriate name space of the package.
	 */
	private Map<String, SBase> extensions;
	
	/**
	 * Level and version of the SBML component. Matches the level XML attribute of a SBML
	 * node.
	 */
	ValuePair<Integer, Integer> lv;
	
	/**
	 * metaid of the SBML component. Matches the metaid XML attribute of an
	 * element in a SBML file.
	 */
	private String metaId;
	
	/**
	 * 
	 */
	private SortedSet<String> namespaces;

	/**
	 * notes of the SBML component. Matches the notes XML node in a SBML file.
	 *
	 */
	private XMLNode notesXMLNode;
	
	/**
	 * sbo term of the SBML component. Matches the sboTerm XML attribute of an
	 * element in a SBML file.
	 */
	private int sboTerm;

	/**
	 * {@link Set} of listeners for this component
	 */
	protected Set<SBaseChangeListener> setOfListeners;

	/**
	 * Creates an AbstractSBase instance. 
	 * 
	 * <p>By default, the sboTerm is -1, the
	 * metaid, notes, parentSBMLObject, annotation, and
	 * notes are null. The level and version are set to -1.
	 * The setOfListeners list and the extensions hash map
	 * are empty.
	 */
	public AbstractSBase() {
		super();
		sboTerm = -1;
		metaId = null;
		notesXMLNode = null;
		lv = getLevelAndVersion();
		annotation = null;
		setOfListeners = new HashSet<SBaseChangeListener>();
		extensions = new HashMap<String, SBase>();
		namespaces = new TreeSet<String>();
	}

	/**
	 * Creates an {@link AbstractSBase} instance with the given Level and
	 * Version.
	 * 
	 * <p>
	 * By default, the sboTerm is -1, the metaid, notes,
	 * {@link #parent}, {@link #annotation}, and notes are null. The
	 * {@link #setOfListeners} list and the {@link #extensions} {@link Map} are
	 * empty.
	 * 
	 * @param level
	 *            the SBML level
	 * @param version
	 *            the SBML version
	 */
	public AbstractSBase(int level, int version) {
		this();
		if ((0 < level) && (level < 4)) {
			this.lv.setL(Integer.valueOf(level));
		} else {
			this.lv.setL(null);
		}
		if ((0 < version)) {
			this.lv.setV(Integer.valueOf(version));
		} else {
			this.lv.setV(null);
		}
		if (!hasValidLevelVersionNamespaceCombination()) {
			throw new LevelVersionError(this);
		}
	}

	/**
	 * Creates an AbstractSBase instance from a given AbstractSBase.
	 * 
	 * @param sb an <code>AbstractSBase</code> object to clone
	 */
	public AbstractSBase(SBase sb) {
		super(sb);
		
		// setOfListeners is needed in all other setXX methods
		setOfListeners = new HashSet<SBaseChangeListener>();
		// extensions is needed when doing getChildCount
		extensions = new HashMap<String, SBase>();

		if (sb instanceof AbstractSBase) {
			this.setOfListeners.addAll(((AbstractSBase) sb).setOfListeners);
		}
		
		if (sb.isSetLevel()) {
			setLevel(sb.getLevel());
		}
		if (sb.isSetVersion()) {
			setVersion(sb.getVersion());
		}
		if (sb.isSetSBOTerm()) {
			this.sboTerm = sb.getSBOTerm();
		} else {
			sboTerm = -1;
		}
		if (sb.isSetMetaId()) {
			this.metaId = new String(sb.getMetaId());
		}
		if (sb.isSetNotes()) {
			this.notesXMLNode = sb.getNotes().clone();
		}
		if (sb.isSetAnnotation()) {
			this.annotation = sb.getAnnotation().clone();
			this.annotation.parent = this;
		}
		// TODO : we need to clone these extensions objects !!
		if (sb.isExtendedByOtherPackages()) {
			this.extensions.putAll(sb.getExtensionPackages());
		}
		// TODO : clone namespaces
	}
		

	/**
	 * Adds recursively all given {@link SBaseChangeListener} instances to this
	 * element.
	 * 
	 * @param listeners the set of listeners to add
	 * @return true if the set of listeners is added with success.
	 * 
	 */
	public boolean addAllChangeListeners(Set<SBaseChangeListener> listeners) {
		boolean success = setOfListeners.addAll(listeners);
		Enumeration<TreeNode> children = children();
		while (children.hasMoreElements()) {
			TreeNode node = children.nextElement();
			if (node instanceof SBase) {
				success &= ((SBase) node).addAllChangeListeners(listeners);
			}
		}
		return success;
	}

	/**
	 * Adds recursively a listener to the {@link SBase} object and all of its
	 * sub-elements.
	 * 
	 * @param l the listener to add
	 */
	public void addChangeListener(SBaseChangeListener l) {
		setOfListeners.add(l);
		Enumeration<TreeNode> children = children();
		for (; children.hasMoreElements(); ) {
			TreeNode node = children.nextElement();
			if (node instanceof SBase) {
				((SBase) node).addChangeListener(l);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#addCVTerm(org.sbml.jsbml.CVTerm)
	 */
	public boolean addCVTerm(CVTerm term) {
		if (!isSetAnnotation()) {
			this.annotation = new Annotation();
			this.annotation.parent = this;
		}
		boolean returnValue = annotation.addCVTerm(term);
		// TODO: might be calling listeners twice.
		firePropertyChange(SBaseChangeEvent.addCVTerm, null, term);
		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#addExtension(java.lang.String, org.sbml.jsbml.SBase)
	 */
	public void addExtension(String namespace, SBase sbase) {
		this.extensions.put(namespace, sbase);
		addNamespace(namespace);
		firePropertyChange(SBaseChangeEvent.addExtension, null, this.extensions);
	}

	/**
	 * Adds an additional name space to the set of name spaces of this
	 * {@link SBase} if the given name space is not yet present within this
	 * {@link SortedSet}.
	 * 
	 * @param namespace the namespace to add
	 */
	public void addNamespace(String namespace) {
		this.namespaces.add(namespace);
		firePropertyChange(SBaseChangeEvent.addNamespace, null, namespace);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#appendNotes(java.lang.String)
	 */
	public void appendNotes(String notes) {

		// TODO : We need to perform plenty of check to see of which form are the notes given to this method
		// and perform the necessary conversion to append or set the notes correctly.
		
		if (!notes.trim().startsWith("<")) { // we assume that this is free text
			notes = "<notes>\n" +
			"  <body xmlns=\"" + JSBML.URI_XHTML_DEFINITION + "\">\n " +
			"    <p>" + notes + "</p>\n" +
			"  </body>\n" +
			"</notes>";
		} else if (!notes.trim().startsWith("<notes")) { // we assume the <notes> XML tag is missing
			notes = "<notes>\n" +
			"  " + notes + "\n" +
			"</notes>";
			
		}
		
		XMLNode addedNotes = XMLNode.convertStringToXMLNode(notes);
		
		if (isSetNotes()) {
			XMLNode oldNotes = notesXMLNode.clone();
			appendNotes(addedNotes);
			firePropertyChange(SBaseChangeEvent.notes, oldNotes, notesXMLNode);
		} else {
			setNotes(addedNotes);
		}
	}

	/**
	 * Appends notes to the existing notes.
	 * <p>This allows other notes to be preserved whilst
	 * adding additional information.
	 * 
	 * @param notes
	 */
	public void appendNotes(XMLNode notes) {
		
		if(notes == null) 
		{
			return;
		}

		String  name = notes.getName();

		// The content of notes in SBML can consist only of the following 
		// possibilities:
		//
		//  1. A complete XHTML document (minus the XML and DOCTYPE 
		//     declarations), that is, XHTML content beginning with the 
		//     html tag.
		//     (notesType is NotesHTML.)
		//
		//  2. The body element from an XHTML document.
		//     (notesType is NotesBody.) 
		//
		//  3. Any XHTML content that would be permitted within a body 
		//     element, each one must declare the XML namespace separately.
		//     (notesType is NotesAny.) 
		//


		NOTES_TYPE addedNotesType = NOTES_TYPE.NotesAny; 
		XMLNode   addedNotes = new XMLNode();
		
		//------------------------------------------------------------
		//
		// STEP1 : identifies the type of the given notes
		//
		//------------------------------------------------------------

		if (name == "notes")
		{
			/* check for notes tags on the added notes and strip if present and
		       the notes tag has "html" or "body" element */

		    if (notes.getChildCount() > 0)  
		    { 
		      // notes.getChildAt(0) must be "html", "body", or any XHTML
		      // element that would be permitted within a "body" element 
		      // (e.g. <p>..</p>,  <br>..</br> and so forth).

		      String cname = notes.getChildAt(0).getName();

		      if (cname == "html")
		      {
		        addedNotes = notes.getChildAt(0);
		        addedNotesType = NOTES_TYPE.NotesHTML;
		      }
		      else if (cname == "body") 
		      {
		        addedNotes = notes.getChildAt(0);
		        addedNotesType = NOTES_TYPE.NotesBody;
		      }
		      else
		      {
		        // the notes tag must NOT be stripped if notes.getChildAt(0) node 
		        // is neither "html" nor "body" element because the children of 
		        // the addedNotes will be added to the current notes later if the node 
		        // is neither "html" nor "body".
		        addedNotes = notes;
		        addedNotesType = NOTES_TYPE.NotesAny;
		      }
		    }
		    else
		    {
		      // the given notes is empty 
		    	  // TODO : log an error
		      return;
		    }
		  }
		  else
		  {
		    // if the XMLNode argument notes has been created from a string and 
		    // it is a set of subelements there may be a single empty node
		    // as parent - leaving this in doesn't affect the writing out of notes
		    // but messes up the check for correct syntax
			  
			  // TODO : check that we are doing that when parsing a String into XMLNode
			  
		    if (!notes.isStart() && !notes.isEnd() && !notes.isText() ) 
		    {
		      if (notes.getChildCount() > 0)
		      { 
		        addedNotes = notes;
		        addedNotesType = NOTES_TYPE.NotesAny;
		      }
		      else
		      {
		        // the given notes is empty 
		        return;
		      }
		    }
		    else
		    {
		      if (name == "html")
		      {
		        addedNotes = notes;
		        addedNotesType = NOTES_TYPE.NotesHTML;
		      }
		      else if (name == "body")
		      {
		        addedNotes = notes;
		        addedNotesType = NOTES_TYPE.NotesBody;
		      }
		      else
		      {
		        // The given notes node needs to be added to a parent node
		        // if the node is neither "html" nor "body" element because the 
		        // children of addedNotes will be added to the current notes later if the 
		        // node is neither "html" nor "body" (i.e. any XHTML element that 
		        // would be permitted within a "body" element)
		        addedNotes.addChild(notes);
		        addedNotesType = NOTES_TYPE.NotesAny;
		      }
		    }
		  }

		  //
		  // checks the addedNotes of "html" if the html tag contains "head" and 
		  // "body" tags which must be located in this order.
		  //
		  if (addedNotesType == NOTES_TYPE.NotesHTML)
		  {
		    if ((addedNotes.getChildCount() != 2) ||
		        ( (addedNotes.getChildAt(0).getName() != "head") ||
		          (addedNotes.getChildAt(1).getName() != "body")
		        )
		       )
		    {
		    	// TODO : log an error to the user or throw an exception or both ?		    	
		      return;
		    }
		  }

		  // We do not have a Syntax checker working on XMLNode !!		    
		  // check whether notes is valid xhtml ?? (libsbml is doing that)

		  if ( notesXMLNode != null )
		  {
		    //------------------------------------------------------------
		    //
		    //  STEP2: identifies the type of the existing notes 
		    //
		    //------------------------------------------------------------

		    NOTES_TYPE curNotesType   = NOTES_TYPE.NotesAny; 
		    XMLNode  curNotes = notesXMLNode;

		    // curNotes.getChildAt(0) must be "html", "body", or any XHTML
		    // element that would be permitted within a "body" element .

		    String cname = curNotes.getChildAt(0).getName();
		  
		    if (cname == "html")
		    {
		      XMLNode curHTML = curNotes.getChildAt(0);
		      //
		      // checks the curHTML if the html tag contains "head" and "body" tags
		      // which must be located in this order, otherwise nothing will be done.
		      //
		      if ((curHTML.getChildCount() != 2) ||
		          ( (curHTML.getChildAt(0).getName() != "head") ||
		            (curHTML.getChildAt(1).getName() != "body")
		          )
		         )
		      {
		    	  // TODO : log an error
		        return;
		      }
		      curNotesType = NOTES_TYPE.NotesHTML;
		    }
		    else if (cname == "body") 
		    {
		      curNotesType = NOTES_TYPE.NotesBody;
		    }
		    else
		    {
		      curNotesType = NOTES_TYPE.NotesAny;
		    }
		  
		    /*
		     * BUT we also have the issue of the rules relating to notes
		     * contents and where to add them ie we cannot add a second body element
		     * etc...
		     */

		    //------------------------------------------------------------
		    //
		    //  STEP3: appends the given notes to the current notes
		    //
		    //------------------------------------------------------------
		  
		    int i;
		  
		    if (curNotesType == NOTES_TYPE.NotesHTML)
		    {
		      XMLNode curHTML = curNotes.getChildAt(0); 
		      XMLNode curBody = curHTML.getChildAt(1);
		      
		      if (addedNotesType == NOTES_TYPE.NotesHTML)
		      {
		        // adds the given html tag to the current html tag
		  
		        XMLNode addedBody = addedNotes.getChildAt(1);   
		  
		        for (i=0; i < addedBody.getChildCount(); i++)
		        {
		          if (curBody.addChild(addedBody.getChildAt(i)) < 0 )
			    	  // TODO : log an error
		            return;          
		        }
		      }
		      else if ((addedNotesType == NOTES_TYPE.NotesBody) || (addedNotesType == NOTES_TYPE.NotesAny))
		      {
		        // adds the given body or other tag (permitted in the body) to the current 
		        // html tag
		  
		        for (i=0; i < addedNotes.getChildCount(); i++)
		        {
		          if (curBody.addChild(addedNotes.getChildAt(i)) < 0 )
			    	  // TODO : log an error
		            return;
		        }
		      }
		    }
		    else if (curNotesType == NOTES_TYPE.NotesBody)
		    {
		      if (addedNotesType == NOTES_TYPE.NotesHTML)
		      {
		        // adds the given html tag to the current body tag
		  
		        XMLNode addedHTML = new XMLNode(addedNotes);
		        XMLNode addedBody = addedHTML.getChildAt(1);
		        XMLNode curBody   = curNotes.getChildAt(0);
		  
		        for (i=0; i < curBody.getChildCount(); i++)
		        {
		          addedBody.insertChild(i,curBody.getChildAt(i));
		        }
		        
		        curNotes.removeChildren();
		        if (curNotes.addChild(addedHTML) < 0)
			    	  // TODO : log an error
		        	return;
		      }
		      else if ((addedNotesType == NOTES_TYPE.NotesBody) || (addedNotesType == NOTES_TYPE.NotesAny))
		      {
		        // adds the given body or other tag (permitted in the body) to the current 
		        // body tag
		  
		        XMLNode curBody = curNotes.getChildAt(0);
		  
		        for (i=0; i < addedNotes.getChildCount(); i++)
		        {
		          if (curBody.addChild(addedNotes.getChildAt(i)) < 0)
			    	  // TODO : log an error
		            return;
		        }
		      }
		    }
		    else if (curNotesType == NOTES_TYPE.NotesAny)
		    {
		      if (addedNotesType == NOTES_TYPE.NotesHTML)
		      {
		        // adds the given html tag to the current any tag permitted in the body.
		  
		        XMLNode addedHTML = new XMLNode(addedNotes);
		        XMLNode addedBody = addedHTML.getChildAt(1);
		  
		        for (i=0; i < curNotes.getChildCount(); i++)
		        {
		          addedBody.insertChild(i,curNotes.getChildAt(i));
		        }
		  
		        curNotes.removeChildren();
		        if (curNotes.addChild(addedHTML) < 0)
			    	  // TODO : log an error
		          return;
		      }
		      else if (addedNotesType == NOTES_TYPE.NotesBody)
		      {
		        // adds the given body tag to the current any tag permitted in the body.
		  
		        XMLNode addedBody = new XMLNode(addedNotes);
		  
		        for (i=0; i < curNotes.getChildCount(); i++)
		        {
		          addedBody.insertChild(i,curNotes.getChildAt(i));
		        }
		  
		        curNotes.removeChildren();
		        if (curNotes.addChild(addedBody) < 0)
			    	  // TODO : log an error
		          return;
		      }
		      else if (addedNotesType == NOTES_TYPE.NotesAny)
		      {
		        // adds the given any tag permitted in the boy to that of the current 
		        // any tag.
		  
		        for (i=0; i < addedNotes.getChildCount(); i++)
		        {
		          if (curNotes.addChild(addedNotes.getChildAt(i)) < 0)
			    	  // TODO : log an error
		            return;
		        }
		      }
		    }
		  }
		  else // if (mNotes == NULL)
		  {
		    // setNotes accepts XMLNode with/without top level notes tags.
			setNotes(notes);
		  }
	}

	/**
	 * Checks whether or not the given {@link SBase} has the same level and
	 * version configuration than this element. If the L/V combination for the
	 * given <code>sbase</code> is not yet defined, this method sets it to the
	 * identical values as it is for the current object.
	 * 
	 * @param sbase
	 *            the element to be checked.
	 * @return <code>true</code> if the given <code>sbase</code> and this object
	 *         have the same L/V configuration.
	 * @throws LevelVersionError
	 *             In case the given {@link SBase} has a different, but defined
	 *             Level/Version combination than this current {@link SBase}, an
	 *             {@link LevelVersionError} is thrown. This method is only
	 *             package-wide visible because it is not intended to be a
	 *             "real" check, rather than to indicate potential errors.
	 */
	protected boolean checkLevelAndVersionCompatibility(SBase sbase) {
		if (sbase.getLevelAndVersion().equals(getLevelAndVersion())) {
			return true;
		}
		if (isSetLevelAndVersion()
				&& (!sbase.isSetLevelAndVersion() || (sbase.isSetLevel()
						&& (sbase.getLevel() == getLevel()) && !sbase
						.isSetVersion())) && (sbase instanceof AbstractSBase)) {
			((AbstractSBase) sbase).setLevelAndVersion(getLevel(),
					getVersion(), true);
			return true;
		}
		throw new LevelVersionError(this, sbase);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public abstract AbstractSBase clone();

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractTreeNode#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		boolean equals = super.equals(object);
		if (equals) {
			/*
			 * Casting will be no problem because the super class has just
			 * checked that the class of this Object equals the class of the
			 * given object.
			 */
			SBase sbase = (SBase) object;
			equals &= sbase.isSetMetaId() == isSetMetaId();
			if (equals && sbase.isSetMetaId()) {
				equals &= sbase.getMetaId().equals(getMetaId());
			}
			/*
			 * All child nodes are already checked by the recursive method in
			 * AbstractTreeNode. We here have to check the following own items
			 * only:
			 */
			equals &= sbase.isSetSBOTerm() == isSetSBOTerm();
			if (equals && sbase.isSetSBOTerm()) {
				equals &= sbase.getSBOTerm() == getSBOTerm();
			}
			equals &= sbase.getLevelAndVersion().equals(getLevelAndVersion());
			/*
			 * Note: Listeners are not included in the equals check.
			 */
		}
		return equals;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.SBase#filterCVTerms(org.sbml.jsbml.CVTerm.Qualifier)
	 */
	public List<CVTerm> filterCVTerms(CVTerm.Qualifier qualifier) {
		return getAnnotation().filterCVTerms(qualifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.SBase#filterCVTerms(org.sbml.jsbml.CVTerm.Qualifier,
	 * java.lang.String)
	 */
	public List<String> filterCVTerms(CVTerm.Qualifier qualifier, String pattern) {
		List<String> l = new LinkedList<String>();
		for (CVTerm c : filterCVTerms(qualifier)) {
			l.addAll(c.filterResources(pattern));
		}
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		if (setOfListeners.size() > 0) {
			short changeType = -1; // no property change at all
			if ((oldValue == null) && (newValue != null)) {
				changeType = 0; // element added
			} else if ((oldValue != null) && (newValue == null)) {
				changeType = 1; // element removed
			} else if ((oldValue != null) && !oldValue.equals(newValue)) {
				changeType = 2; // real property change
			}
			if (0 <= changeType) {
				if ((changeType == 0) && (newValue instanceof SBase)) {
					for (SBaseChangeListener listener : setOfListeners) {
						listener.sbaseAdded((SBase) newValue);
					}
				} else if ((changeType == 1) && (oldValue instanceof SBase)) {
					for (SBaseChangeListener listener : setOfListeners) {
						listener.sbaseRemoved((SBase) oldValue);
					}
				} else {
					SBaseChangeEvent changeEvent = new SBaseChangeEvent(this,
							propertyName, oldValue, newValue);
					for (SBaseChangeListener listener : setOfListeners) {
						listener.stateChanged(changeEvent);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#fireSBaseAddedEvent()
	 */
	public void fireSBaseAddedEvent() {
		for (SBaseChangeListener listener : setOfListeners) {
			listener.sbaseAdded(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#fireSBaseRemovedEvent()
	 */
	public void fireSBaseRemovedEvent() {
		if (isSetMetaId()) {
			// update the set of meta identifiers within the SBMLDocument.
			SBMLDocument doc = getSBMLDocument();
			if (doc != null) {
				/*
				 * Recursively remove pointers to this element's and all
				 * sub-element's meta identifiers from the SBMLDocument.
				 */
				doc.registerMetaIds(this, true, true);
			}
		}
		for (SBaseChangeListener listener : setOfListeners) {
			listener.sbaseRemoved(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getAnnotation()
	 */
	public Annotation getAnnotation() {
		if (!isSetAnnotation()) {
			annotation = new Annotation();
			annotation.parent = this;
		}
		return annotation;
	}

	/**
	 * Returns the {@link Annotation} of this SBML object as a {@link String}.
	 * 
	 * @return the {@link Annotation} of this SBML object as a {@link String} or
	 *         an empty {@link String} if there are no {@link Annotation}.
	 * 
	 */
	public String getAnnotationString() {
		return isSetAnnotation() ? (new SBMLWriter()).writeAnnotation(this) : "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		if (childIndex < 0) {
			throw new IndexOutOfBoundsException(childIndex + " < 0");
		}
		int pos = 0;
		if (isSetNotes()) {
			if (childIndex == pos)  {
				return getNotes();
			}
			pos++;
		}
		if (isSetAnnotation()) {
			if (childIndex == pos) {
				return getAnnotation();
			}
			pos++;
		}
		if (extensions.size() > 0) {
			String exts[] = extensions.keySet().toArray(new String[0]);
			Arrays.sort(exts);
			return extensions.get(exts[childIndex - pos]);
		}
		throw new IndexOutOfBoundsException((getChildCount() == 0) ? String
				.format("Node %s has no children.", getElementName())
				: String.format("Index %d >= %d", childIndex, +((int) Math.min(
						pos, 0))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		int count = 0;
		if (isSetNotes()) {
			count++;
		}
		if (isSetAnnotation()) {
			count++;
		}
		return count + extensions.size();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getCVTerm(int)
	 */
	public CVTerm getCVTerm(int index) {
		if (isSetAnnotation()) {
			return annotation.getCVTerm(index);
		}
		throw new IndexOutOfBoundsException(String.format(
				"No such controlled vocabulary term with index %d.", index));
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getCVTerms()
	 */
	public List<CVTerm> getCVTerms() {
		return getAnnotation().getListOfCVTerms();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#getElementName()
	 */
	public String getElementName() {
		return StringTools.firstLetterLowerCase(getClass().getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getExtension(java.lang.String)
	 */
	public SBase getExtension(String namespace) {
		return this.extensions.get(namespace);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getExtensionPackages()
	 */
	public Map<String, SBase> getExtensionPackages() {
		return extensions;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getHistory()
	 */
	public History getHistory() {
		return isSetAnnotation() ? annotation.getHistory() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#getLevel()
	 */
	public int getLevel() {
		return isSetLevel() ? this.lv.getL().intValue() : -1;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getLevelAndVersion()
	 */
	public ValuePair<Integer, Integer> getLevelAndVersion() {
		if (this.lv == null) {
			this.lv = new ValuePair<Integer, Integer>(Integer.valueOf(-1),
					Integer.valueOf(-1));
		}
		return this.lv;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getMetaId()
	 */
	public String getMetaId() {
		return isSetMetaId() ? metaId : "";
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getModel()
	 */
	public Model getModel() {
		if (this instanceof Model) {
			return (Model) this;
		}
		return getParentSBMLObject() != null ? getParentSBMLObject().getModel()
				: null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getNamespaces()
	 */
	public SortedSet<String> getNamespaces() {
		// Need to separate the list of name spaces from the extensions.
		// SBase object directly from the extension need to set their name space.

		return this.namespaces;
	}

	/**
	 * Returns an <code>XMLNode</code> object that represent the notes of this element.
	 * 
	 * @return an <code>XMLNode</code> object that represent the notes of this element.
	 */
	public XMLNode getNotes() {
		return notesXMLNode;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getNotesString()
	 */
	public String getNotesString() {
		return notesXMLNode != null ? notesXMLNode.toXMLString() : "";
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getNumCVTerms()
	 */
	public int getNumCVTerms() {
		return isSetAnnotation() ? annotation.getListOfCVTerms().size() : 0;
	}

	/**
	 * This is equivalent to calling {@link #getParentSBMLObject()}, but this
	 * method is needed for {@link TreeNode}.
	 * 
	 * @return the parent element of this element.
	 * @see #getParentSBMLObject()
	 */
	@Override
	public SBase getParent() {
		return (SBase) super.getParent();
	}


	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getParentSBMLObject()
	 */
	public SBase getParentSBMLObject() {
		return getParent();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getSBMLDocument()
	 */
	public SBMLDocument getSBMLDocument() {
		if (this instanceof SBMLDocument) {
			return (SBMLDocument) this;
		}
		SBase parent = getParentSBMLObject();
		return (parent != null) ? parent.getSBMLDocument() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getSBOTerm()
	 */
	public int getSBOTerm() {
		return sboTerm;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getSBOTermID()
	 */
	public String getSBOTermID() {
		return SBO.intToString(sboTerm);
	}

	/**
	 * Returns all {@link SBaseChangeListener}s that are assigned to this
	 * element.
	 * 
	 * @return all {@link SBaseChangeListener}s that are assigned to this
	 * element.
	 */
	public Set<SBaseChangeListener> getSetOfSBaseChangedListeners() {
		return setOfListeners;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#getVersion()
	 */
	public int getVersion() {
		return isSetVersion() ? this.lv.getV().intValue() : -1;
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractTreeNode#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 7;
		int hashCode = super.hashCode();
		if (isSetMetaId()) {
			hashCode += prime * getMetaId().hashCode();
		}
		if (isSetSBOTerm()) {
			hashCode += prime * getSBOTerm();
		}
		return hashCode + prime * getLevelAndVersion().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#hasValidAnnotation()
	 */
	public boolean hasValidAnnotation() {
		if (isSetAnnotation()) {
			if (isSetMetaId()) {
				if (getAnnotation().getAbout().equals('#' + getMetaId())) {
					return true;
				}
			}
			if (getAnnotation().isSetNonRDFannotation() && !getAnnotation().isSetRDFannotation()) {
				return true;
			}
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#hasValidLevelVersionNamespaceCombination()
	 */
	public boolean hasValidLevelVersionNamespaceCombination() {
		return isValidLevelAndVersionCombination(getLevel(), getVersion());
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isExtendedByOtherPackages()
	 */
	public boolean isExtendedByOtherPackages() {
		return !this.extensions.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetAnnotation()
	 */
	public boolean isSetAnnotation() {
		return (annotation != null) && annotation.isSetAnnotation();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetHistory()
	 */
	public boolean isSetHistory() {
		if (isSetAnnotation()) {
			return annotation.isSetHistory();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetLevel()
	 */
	public boolean isSetLevel() {
		return (lv != null) && (lv.getL() != null)
				&& (lv.getL().intValue() > -1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetLevelAndVersion()
	 */
	public boolean isSetLevelAndVersion() {
		return isSetLevel() && isSetVersion();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetMetaId()
	 */
	public boolean isSetMetaId() {
		return metaId != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetNotes()
	 */
	public boolean isSetNotes() {
		return notesXMLNode != null;
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetParentSBMLObject()
	 */
	public boolean isSetParentSBMLObject() {
		return isSetParent();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetSBOTerm()
	 */
	public boolean isSetSBOTerm() {
		return sboTerm != -1;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#isSetVersion()
	 */
	public boolean isSetVersion() {
		return (lv != null) && (lv.getV() != null)
				&& (lv.getV().intValue() > -1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		if (attributeName.equals("sboTerm")) {
			setSBOTerm(value);
			return true;
		} else if (attributeName.equals("metaid")) {
			setMetaId(value);
			return true;
		}
		return false;
	}

	/**
	 * Removes all SBase change listeners from this element.
	 */
	public void removeAllSBaseChangedListeners() {
		setOfListeners.clear();
	}

	/**
	 * Removes recursively the given change listener from this element.
	 * 
	 * @param l the listener to remove.
	 */
	public void removeChangeListener(SBaseChangeListener l) {
		setOfListeners.remove(l);
		Enumeration<TreeNode> children = children();
		while(children.hasMoreElements()) {
			TreeNode node = children.nextElement();
			if (node instanceof SBase) {
				((SBase) node).removeChangeListener(l);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setAnnotation(org.sbml.jsbml.Annotation)
	 */
	public void setAnnotation(Annotation annotation) {
		Annotation oldAnnotation = this.annotation;
		this.annotation = annotation;
		this.annotation.parent = this;
		firePropertyChange(SBaseChangeEvent.setAnnotation, oldAnnotation, this.annotation);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setHistory(org.sbml.jsbml.History)
	 */
	public void setHistory(History history) {
		History oldHistory = getAnnotation().getHistory();
		annotation.setHistory(history);
		firePropertyChange(SBaseChangeEvent.history, oldHistory, history);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setLevel(int)
	 */
	public void setLevel(int level) {
		SBase parent = getParent();
		if ((parent != null) && (parent != this)
				&& parent.isSetLevel()) {
			if (level != parent.getLevel()) {
				throw new LevelVersionError(this, parent);
			}
		}
		Integer oldLevel = getLevelAndVersion().getL();
		this.lv.setL(Integer.valueOf(level));
		firePropertyChange(SBaseChangeEvent.level, oldLevel, this.lv.getL());
	}

	/**
	 * Sets recursively the level and version attribute for this element
	 * and all sub-elements.
	 * 
	 * @param level the SBML level
	 * @param version the SBML version
	 * @param strict a boolean to say if the method need to be strict or not (not used at the moment)
	 * @return true if the operation as been successful.
	 */
	boolean setLevelAndVersion(int level, int version, boolean strict) {
		if (isValidLevelAndVersionCombination(level, version)) {
			setLevel(level);
			setVersion(version);
			// TODO: perform necessary conversion!
			boolean success = true;
			Enumeration<TreeNode> children = children();
			TreeNode child;
			while (children.hasMoreElements()) {
				child = children.nextElement();
				if (child instanceof AbstractSBase) {
					success &= ((AbstractSBase) child).setLevelAndVersion(
							level, version, strict);
				}
			}
			return success;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setMetaId(java.lang.String)
	 */
	public void setMetaId(String metaId) {
		if (getLevel() == 1) {
			throw new PropertyNotAvailableError(SBaseChangeEvent.metaId, this);
		}
		SBMLDocument doc = getSBMLDocument();
		if (doc != null) {
			doc.registerMetaId(metaId, true);
		}
		String oldMetaId = this.metaId;
		this.metaId = metaId;
		firePropertyChange(SBaseChangeEvent.metaId, oldMetaId, metaId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.SBase#setNotes(java.lang.String)
	 */
	public void setNotes(String notes) {
		setNotes(XMLNode.convertStringToXMLNode(notes)); 
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setNotes(java.lang.String)
	 */
	public void setNotes(XMLNode notes) {
		XMLNode oldNotes = this.notesXMLNode;
		this.notesXMLNode = notes;
		this.notesXMLNode.setParent(this);
		firePropertyChange(SBaseChangeEvent.notes, oldNotes, this.notesXMLNode);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setParentSBML(org.sbml.jsbml.SBase)
	 */
	public void setParentSBML(SBase parent) {
		SBase oldParent = getParent();
		this.parent = parent;
		firePropertyChange(SBaseChangeEvent.parentSBMLObject, oldParent, parent);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setSBOTerm(int)
	 */
	public void setSBOTerm(int term) {
		if (getLevelAndVersion().compareTo(Integer.valueOf(2),
				Integer.valueOf(2)) < 0) {
			throw new PropertyNotAvailableError(SBaseChangeEvent.sboTerm, this);
		}
		if (!SBO.checkTerm(term)) {
			throw new IllegalArgumentException(String.format(
					"Cannot set invalid SBO term %d because it must not be smaller than zero or larger than 9999999."));
		}
		Integer oldTerm = Integer.valueOf(sboTerm);
		sboTerm = term;
		firePropertyChange(SBaseChangeEvent.sboTerm, oldTerm, sboTerm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#setSBOTerm(java.lang.String)
	 */
	public void setSBOTerm(String sboid) {
		setSBOTerm(SBO.stringToInt(sboid));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setThisAsParentSBMLObject(org.sbml.jsbml.SBase)
	 */
	public void setThisAsParentSBMLObject(SBase sbase) throws LevelVersionError {
		if ((sbase != null) && checkLevelAndVersionCompatibility(sbase)) {
			SBMLDocument doc = getSBMLDocument();
			if (doc != null) {
				/*
				 * In case that sbase did not have access to the document we
				 * have to recursively check the metaId property.
				 */
				doc.registerMetaIds(sbase, (sbase.getSBMLDocument() == null)
						&& (sbase instanceof AbstractSBase), false);
			}
			if (sbase instanceof AbstractSBase) {
				((AbstractSBase) sbase).parent = this;
				sbase.addAllChangeListeners(getSetOfSBaseChangedListeners());
			}
			sbase.fireSBaseAddedEvent();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#setVersion(int)
	 */
	public void setVersion(int version) {
		SBase parent = getParent();
		if ((parent != null) && (parent != this)
				&& parent.isSetVersion()) {
			if (version != parent.getVersion()) {
				throw new LevelVersionError(parent, this);
			}
		}
		Integer oldVersion = getLevelAndVersion().getV();
		this.lv.setV(Integer.valueOf(version));
		firePropertyChange(SBaseChangeEvent.version, oldVersion, version);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#unsetAnnotation()
	 */
	public void unsetAnnotation() {
		if (isSetAnnotation()) {
			Annotation oldAnnotation = annotation;
			annotation = null;
			firePropertyChange(SBaseChangeEvent.annotation, oldAnnotation,
					annotation);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#unsetCVTerms()
	 */
	public void unsetCVTerms() {
		if (isSetAnnotation() && getAnnotation().isSetListOfCVTerms()) {
			List<CVTerm> list = annotation.getListOfCVTerms();
			annotation.unsetCVTerms();
			firePropertyChange(SBaseChangeEvent.unsetCVTerms, list, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#unsetHistory()
	 */
	public void unsetHistory() {
		if (isSetAnnotation()) {
			History history = getHistory();
			this.annotation.unsetHistory();
			firePropertyChange(SBaseChangeEvent.history, history, getHistory());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#unsetMetaId()
	 */
	public void unsetMetaId() {
		if (isSetMetaId()) {
			SBMLDocument doc = getSBMLDocument();
			if (doc != null) {
				doc.registerMetaId(metaId, false);
			}
			String oldMetaId = metaId; 
			metaId = null;
			firePropertyChange(SBaseChangeEvent.metaId, oldMetaId, getMetaId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#unsetNotes()
	 */
	public void unsetNotes() {
		if (isSetNotes()) {
			XMLNode oldNotes = notesXMLNode;
			notesXMLNode = null;
			firePropertyChange(SBaseChangeEvent.notes, oldNotes, getNotes());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jlibsbml.SBase#unsetSBOTerm()
	 */
	public void unsetSBOTerm() {
		if (isSetSBOTerm()) {
			Integer oldSBOTerm = Integer.valueOf(sboTerm);
			sboTerm = -1;
			firePropertyChange(SBaseChangeEvent.sboTerm, oldSBOTerm, Integer
					.valueOf(getSBOTerm()));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.SBase#writeXMLAttributes()
	 */
	public Map<String, String> writeXMLAttributes() {
		HashMap<String, String> attributes = new HashMap<String, String>();

		if (1 < getLevel()) {
			if (isSetMetaId()) {
				attributes.put("metaid", getMetaId());
			}
			if (((getLevel() == 2) && (getVersion() >= 2)) || (getLevel() == 3)) {
				if (isSetSBOTerm()) {
					attributes.put("sboTerm", getSBOTermID());
				}
			}
		}
		return attributes;
	}

}
