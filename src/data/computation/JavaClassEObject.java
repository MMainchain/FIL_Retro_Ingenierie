package data.computation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaClassEObject extends JavaEObject {
	
	private HashMap<String, JavaFieldEObject> fields;
	private HashMap<String, JavaFunctionEObject> functions;

	/**
	 * 
	 * @param classObject
	 */
	public JavaClassEObject(EObject classObject, EPackage ePackage) {
		super(classObject, ePackage);
		
		fields = new HashMap<String, JavaFieldEObject>();
		functions = new HashMap<String, JavaFunctionEObject>();
		
		EClass classClasse = (EClass) this.ePackage.getEClassifier("Classe");
		this.targetObject = ePackage.getEFactoryInstance().create(classClasse);
		this.targetObject.eSet(classClasse.getEStructuralFeature("name"), this.getEObjectName());
		
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		List<EObject> EObjectFields = new ArrayList<EObject>();
//		List<EObject> EObjectFunctions = new ArrayList<EObject>();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.FIELD_TYPE)) {
				JavaFieldEObject field = JavaEObjectFactory.createJavaFieldEobject(currentElement, ePackage);
				EObjectFields.add(field.getTargetObject());
				fields.put(field.getEObjectName(), field);
			}
			else if (currentElement.eClass().getName().equals(JavaEObjectFactory.METHOD_TYPE)) {
				JavaFunctionEObject function = JavaEObjectFactory.createJavaFunctionEobject(currentElement, ePackage);
//				EObjectFunctions.add(function.getTargetObject());
				functions.put(function.getEObjectName(), function);
			}
		}
		
		this.targetObject.eSet(classClasse.getEStructuralFeature("attributs"), EObjectFields);
//		this.targetObject.eSet(classClasse.getEStructuralFeature("?"), EObjectFunctions);
	}
	
	public HashMap<String, JavaFieldEObject> getFields() {
		return this.fields;
	}
	
	public HashMap<String, JavaFunctionEObject> getFunctions() {
		return this.functions;
	}
}
