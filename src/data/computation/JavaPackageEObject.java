package data.computation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaPackageEObject extends JavaEObject implements packageContainer, classContainer {
	
	private HashMap<String, JavaPackageEObject> packages;
	private HashMap<String, JavaClassEObject> classes;
	
	public JavaPackageEObject(EObject javaPackage) {
		super(javaPackage);
		packages = new HashMap<String, JavaPackageEObject>();
		classes = new HashMap<String, JavaClassEObject>();
		
		Iterator<EObject> elements;
		elements = this.rootObject.eContents().iterator();
		
		while(elements.hasNext()) {
			EObject currentElement = elements.next();
			
			if (currentElement.eClass().getName().equals(JavaEObjectFactory.PACKAGE_TYPE)) {
				packages.put(this.getEObjectName(currentElement), JavaEObjectFactory.createJavaPackageEobject(currentElement));
			} else if (currentElement.eClass().getName().equals(JavaEObjectFactory.CLASS_TYPE)) {
				classes.put(this.getEObjectName(currentElement), JavaEObjectFactory.createJavaClassEobject(currentElement));
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
