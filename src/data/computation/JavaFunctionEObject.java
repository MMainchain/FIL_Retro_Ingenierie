package data.computation;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @author Ma�l MAINCHAIN
 *
 */
public class JavaFunctionEObject extends JavaEObject implements ClassElement {
	
	private String visibility;
	private String type;
	private String name;

	/**
	 * 
	 * @param functionObject
	 */
	public JavaFunctionEObject(EObject functionObject, EPackage ePackage) {
		super(functionObject, ePackage);
		EStructuralFeature name = functionObject.eClass().getEStructuralFeature("name");
		this.name = (String) functionObject.eGet(name);
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.TYPE_TYPE)) {
				this.type = JavaEObjectFactory.createJavaTypeEobject(currentElement, ePackage);
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.MODIFIER_TYPE)) {
				this.visibility = JavaEObjectFactory.createJavaModifierEobject(currentElement, ePackage);
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
