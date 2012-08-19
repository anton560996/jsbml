package org.sbml.jsbml.test;


import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.text.parser.ParseException;

public class TestFunctionDefinition2 {

	public static void main(String[] args) throws ParseException {

		SBMLDocument doc = new SBMLDocument(2, 4);
		Model model = doc.createModel("testModel");
		
		Compartment cell = model.createCompartment("cell");
		Species s1 = model.createSpecies("S1", cell);
		
		String compartmentID = cell.getId();
		String speciesID = s1.getId();
		String direction = "Right";

		Event e1 = model.createEvent();
		
		ASTNode astNode1 = ASTNode.parseFormula("neighborQuantity" + direction + "(" + speciesID + 
				", getCompartmentLocationX(" + compartmentID + "), getCompartmentLocationY(" + compartmentID + "))");

		 // Adding the ASTNode to an element otherwise we cannot find the variable associated to an id
		e1.createTrigger().setMath(astNode1);

		// The warnings are happening when we call toFormula() 
		System.out.println("Formula , with no FD defined yet : " + astNode1.toFormula());

		model.createFunctionDefinition("neighborQuantityRight").setMath(ASTNode.parseFormula("lambda(s, x, y, 2 + x + y)"));
		model.createFunctionDefinition("neighborQuantityRightFull").setMath(ASTNode.parseFormula("lambda(s, x, y, 5)"));
		model.createFunctionDefinition("getCompartmentLocationX").setMath(ASTNode.parseFormula("lambda(c, 11)"));
		model.createFunctionDefinition("getCompartmentLocationY").setMath(ASTNode.parseFormula("lambda(c, 17)"));

		System.out.println("Formula , with FD defined : " + astNode1.toFormula());
		
		System.out.println("FD1 = " + model.getFunctionDefinition(0).getMath().toFormula());
		
		astNode1.updateVariables();
		
		System.out.println("Formula , with FD defined + update : " + astNode1.toFormula());
		
		
		ASTNode astNode = ASTNode.parseFormula("neighborQuantity" + direction + "Full(" + speciesID + 
				", getCompartmentLocationX(" + compartmentID + "), getCompartmentLocationY(" + compartmentID + "))");

		System.out.println("Formula 2 : " + astNode.toFormula());
		
		MathContainer mc = e1.getTrigger();
		
		for (int i = 0; i < mc.getChildCount(); ++i) {

			System.out.println("Child = " + mc.getChildAt(i));
			
			if (((ASTNode) mc.getChildAt(i)).isFunction() && 
					((ASTNode) mc.getChildAt(i)).getVariable().toString().contains("neighborQuantity" + direction)) 
			{
				System.out.println("NODE found !!!");
				
				// Test ASTNode remove + insertChild
				astNode1.removeChild(0);
				astNode1.insertChild(0, astNode);
			}										
		}
		
		// Test ASTNode replace
		astNode1.replaceChild(1, ASTNode.parseFormula("sin(X + Y + Z) / S1 + 2"));
		
		System.out.println("Formula , with FD defined + remove/insertChild : " + astNode1.toFormula());
		
		ListOf<Event> clonedListOfEvents = model.getListOfEvents().clone();
		
		System.out.println("Cloned list of events size : " + clonedListOfEvents.size());
		System.out.println("Cloned event 0 Trigger math  : " + clonedListOfEvents.get(0).getTrigger());
		
	}
}