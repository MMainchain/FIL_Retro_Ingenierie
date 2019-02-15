package data.computation;

import java.util.ArrayList;
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
	
	public JavaPackageEObject(EObject javaPackage) {
		super(javaPackage);
	}

	public JavaPackageEObject getPackage(String string) {
		if (string.length() < 1) throw new IllegalArgumentException("Package name cannot be empty");
		String[] packagesNames = string.split(Pattern.quote("."), 2);
		String firstPackageName = packagesNames[0];
		
		Iterator<EObject> packages;
		packages = this.rootObject.eContents().iterator();
		
		while(packages.hasNext()) {
			EObject currentPackagesElement = packages.next();
			if (currentPackagesElement.eClass().getName().equals(JavaEObjectFactory.PACKAGE_TYPE) &&
					this.getEObjectName(currentPackagesElement).equals(firstPackageName)) {
				System.out.println("Go to package : " + this.getEObjectName(currentPackagesElement));
				JavaPackageEObject wantedPackage = JavaEObjectFactory.createJavaPackageEobject(currentPackagesElement);
				
				if (packagesNames.length > 1) {
					return wantedPackage.getPackage(packagesNames[1]);
				}
				
				return wantedPackage;
			}
		}
		
		throw new NoSuchElementException("No packages found for " + firstPackageName + " in " + this.getEObjectName());
	}
	
	/**
	 * 
	 */
	public List<JavaPackageEObject> getPackages() {
		List<JavaPackageEObject> wantedPackages = new ArrayList<JavaPackageEObject>();
		Iterator<EObject> packages;
		packages = this.rootObject.eContents().iterator();
		
		while(packages.hasNext()) {
			EObject currentPackagesElement = packages.next();
			if (currentPackagesElement.eClass().getName().equals(JavaEObjectFactory.PACKAGE_TYPE)) {
				wantedPackages.add(JavaEObjectFactory.createJavaPackageEobject(currentPackagesElement));
			}
		}
		
		return wantedPackages;
	}
	
	/**
	 * 
	 */
	public List<JavaClassEObject> getClasses() {
		List<JavaClassEObject> wantedClasses = new ArrayList<JavaClassEObject>();
		Iterator<EObject> classes;
		classes = this.rootObject.eContents().iterator();
		
		while(classes.hasNext()) {
			EObject currentClassesElement = classes.next();
			if (currentClassesElement.eClass().getName().equals(JavaEObjectFactory.CLASS_TYPE)) {
				wantedClasses.add(JavaEObjectFactory.createJavaClassEobject(currentClassesElement));
			}
		}
		
		return wantedClasses;
	}

}
