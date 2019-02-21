package data.computation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

public abstract class JavaEObject {
	protected EPackage ePackage;
	protected EObject rootObject;
	protected EObject targetObject;
	
	public JavaEObject(EObject rootObject, EPackage ePackage) {
		this.rootObject = rootObject;
		this.ePackage = ePackage;
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
	
	public EObject getTargetObject() {
		return this.targetObject;
	}
}
