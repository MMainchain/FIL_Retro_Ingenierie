package data.computation;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaFieldEObject extends JavaEObject {
	
	private String visibility;
	private String type;
	private String name;

	/**
	 * 
	 * @param fieldObject
	 */
	public JavaFieldEObject(EObject fieldObject) {
		super(fieldObject);
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.VARIABLE_TYPE)) {
				this.name = JavaEObjectFactory.createJavaVariableEobject(currentElement);
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.TYPE_TYPE)) {
				this.type = JavaEObjectFactory.createJavaTypeEobject(currentElement);
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.MODIFIER_TYPE)) {
				this.visibility = JavaEObjectFactory.createJavaModifierEobject(currentElement);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVisibility() {
		return this.visibility;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	
	@Override
	public String getEObjectName() {
		return this.name;
	}
	
}
