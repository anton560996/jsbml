package org.sbml.jsbml.validator.offline.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.util.StringTools;
import org.sbml.jsbml.validator.offline.SBMLPackage;

public abstract class AbstractFactoryManager implements FactoryManager, SBMLErrorCodes {
    /**
     * Log4j logger
     */
    protected static final transient Logger logger = Logger.getLogger(AbstractFactoryManager.class);

    @Override
    public List<Integer> getIdsForClass(Class<?> clazz, CheckCategory category, SBMLPackage[] packages) {
	List<Integer> list = new ArrayList<Integer>();
	// System.out.println("Get Ids for " + clazz.getName());

	if (clazz.equals(SBMLDocument.class)) {
	    list.add(ID_VALIDATE_DOCUMENT_TREE);
	    
	    
	} else if (clazz.equals(Model.class)) {
	    list.add(ID_VALIDATE_CORE_MODEL_TREE);
	    
	    for (SBMLPackage pkg: packages)
	    {
		try {
		    String varName = "ID_VALIDATE_" + pkg.toString().toUpperCase() + "_MODEL_TREE";
		    Field f = FactoryManager.class.getField(varName);
		    
		    int value = f.getInt(null);
		    list.add(value);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    logger.warn(e.getLocalizedMessage());
//		    e.printStackTrace();
		} 
	    }
	} 
//	else if (clazz.equals(Layout.class))
//	{
//	    list.add(FactoryManager.ID_VALIDATE_LAYOUT_TREE);
//	}

	// System.out.println("IDs for class " + clazz.getSimpleName());
	String categoryName = StringTools.firstLetterUpperCase(category.toString().toLowerCase());

	for (SBMLPackage pkg : packages) {
	    try {
		String pkgName = StringTools.firstLetterUpperCase(pkg.toString());

		Method m = this.getClass().getMethod("add" + pkgName + categoryName + clazz.getSimpleName() + "Ids", List.class);
		m.invoke(this, list);
	    } catch (Exception e) {
		logger.debug(e.getMessage());
	    }
	}

	return list;
    }

    protected static void addRangeToList(List<Integer> list, int from, int to) {
	for (int i = from; i <= to; i++) {
	    list.add(i);
	}
    }
}
