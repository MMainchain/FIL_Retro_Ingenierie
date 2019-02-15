package data.computation;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaClassEObject extends JavaEObject {
	
	private HashMap<String, JavaFieldEObject> fields;
//	private HashMap<String, JavaMethodEObject> methods;

	/**
	 * 
	 * @param classObject
	 */
	public JavaClassEObject(EObject classObject) {
		super(classObject);
		
		fields = new HashMap<String, JavaFieldEObject>();
		
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.FIELD_TYPE)) {
				JavaFieldEObject field = JavaEObjectFactory.createJavaFieldEobject(currentElement);
				fields.put(field.getEObjectName(), field);
			}
		}
	}
	
	public HashMap<String, JavaFieldEObject> getFields() {
		return this.fields;
	}

}
