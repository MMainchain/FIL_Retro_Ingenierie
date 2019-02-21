package data.computation;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

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
	public JavaFieldEObject(EObject fieldObject, EPackage ePackage) {
		super(fieldObject, ePackage);
		
		EClass classAttribut = (EClass) this.ePackage.getEClassifier("Attribut");
		
		this.targetObject = this.ePackage.getEFactoryInstance().create(classAttribut);
		
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.VARIABLE_TYPE)) {
				this.name = JavaEObjectFactory.createJavaVariableEobject(currentElement, ePackage);
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.TYPE_TYPE)) {
				this.type = JavaEObjectFactory.createJavaTypeEobject(currentElement, ePackage);
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.MODIFIER_TYPE)) {
				this.visibility = JavaEObjectFactory.createJavaModifierEobject(currentElement, ePackage);
			}
		}
		
		this.targetObject.eSet(classAttribut.getEStructuralFeature("name"), this.name);
		this.targetObject.eSet(classAttribut.getEStructuralFeature("type"), this.type);
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
