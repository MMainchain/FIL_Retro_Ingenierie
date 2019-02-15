package data.computation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class JavaEObject {
	protected EObject rootObject;
	
	public JavaEObject(EObject rootObject) {
		this.rootObject = rootObject;
	}
	
	public EObject getEObject() {
		return this.rootObject;
	}
	
	public String getEObjectName() {
		EStructuralFeature name = rootObject.eClass().getEStructuralFeature("name");
		return (String) rootObject.eGet(name);
	}
	
	public String getEObjectName(EObject object) {
		EStructuralFeature name = object.eClass().getEStructuralFeature("name");
		return (String) object.eGet(name);
	}
}
