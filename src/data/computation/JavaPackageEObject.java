package data.computation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaPackageEObject extends JavaEObject implements packageContainer, classContainer {
	
	private HashMap<String, JavaPackageEObject> packages;
	private HashMap<String, JavaClassEObject> classes;
	
	public JavaPackageEObject(EObject javaPackage, EPackage ePackage) {
		super(javaPackage, ePackage);
		packages = new HashMap<String, JavaPackageEObject>();
		classes = new HashMap<String, JavaClassEObject>();
		
		this.targetObject = new EObjectImpl() {};
		
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.PACKAGE_TYPE)) {
				JavaPackageEObject localPackage = JavaEObjectFactory.createJavaPackageEobject(currentElement, ePackage);
				packages.put(this.getEObjectName(currentElement), localPackage);
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.CLASS_TYPE)) {
				JavaClassEObject localClass = JavaEObjectFactory.createJavaClassEobject(currentElement, ePackage);
				classes.put(this.getEObjectName(currentElement), localClass);
			}
		}
	}

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
	 */
	public HashMap<String, JavaClassEObject> getClasses() {
		return this.classes;
	}
}
