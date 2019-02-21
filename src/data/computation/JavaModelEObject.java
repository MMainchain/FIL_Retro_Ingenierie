package data.computation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLContentHandlerImpl.XMI;
import org.eclipse.gmt.modisco.java.emf.impl.ModelImpl;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaModelEObject extends JavaEObject implements packageContainer {
	
	private HashMap<String, JavaPackageEObject> packages;

	/**
	 * 
	 * @param rootObject
	 */
	public JavaModelEObject(EObject modelObject, EPackage ePackage) {
		super(modelObject, ePackage);
		
		packages = new HashMap<String, JavaPackageEObject>();
		
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.PACKAGE_TYPE)) {
				JavaPackageEObject javaPackage = JavaEObjectFactory.createJavaPackageEobject(currentElement, ePackage);
				packages.put(this.getEObjectName(currentElement), javaPackage);
//				this.targetObject.eContents().add(javaPackage.getTargetObject());
			}
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public JavaPackageEObject getPackage(String string) {
		if (string.length() < 1) throw new IllegalArgumentException("Package name cannot be empty");
		String[] packagesNames = string.split(Pattern.quote("."), 2);
		String firstPackageName = packagesNames[0];
		
		JavaPackageEObject wantedPackage = packages.get(firstPackageName);
		
		if (packages == null)
			throw new NoSuchElementException("No packages found for " + firstPackageName + " in " + this.getEObjectName());
		
		if (packagesNames.length > 1) {
			return wantedPackage.getPackage(packagesNames[1]);
		}
		
		return wantedPackage;
	}

	/**
	 * 
	 */
	public HashMap<String, JavaPackageEObject> getPackages() {
		return this.packages;
	}

	/**
	 * 
	 * @param r
	 */
	public void save(Resource r) {
		r.getContents().add(this.targetObject);
	}
	
	@Override
	public EObject getTargetObject() {
		return this.targetObject;
	}
}
