package tp.fil.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import data.computation.JavaClassEObject;
import data.computation.JavaEObjectFactory;
import data.computation.JavaModelEObject;
import data.computation.JavaPackageEObject;

public class DataComputation {
	
	public static void main(String[] args) {
		try {
			Resource javaModel;
			Resource dataModel;
			Resource dataMetamodel;
			
			ResourceSet resSet = new ResourceSetImpl();
			resSet.getResourceFactoryRegistry().
				getExtensionToFactoryMap().
				put("ecore", new EcoreResourceFactoryImpl());
			resSet.getResourceFactoryRegistry().
				getExtensionToFactoryMap().
				put("xmi", new XMIResourceFactoryImpl());
			
			JavaPackage.eINSTANCE.eClass();
			
			dataMetamodel = resSet.createResource(URI.createFileURI("src/tp/fil/resources/Data.ecore"));
			dataMetamodel.load(null);
			EPackage.Registry.INSTANCE.put("http://data", 
					dataMetamodel.getContents().get(0));
			
			javaModel = resSet.createResource(URI.createFileURI("PetStore_java.xmi"));
			javaModel.load(null);
			
			dataModel = resSet.createResource(URI.createFileURI("PetStore_data.xmi"));
			
			// Get root model
			JavaModelEObject model = JavaEObjectFactory.createJavaModelEobject(javaModel.getContents().get(0));
			// Go to wanted package and get classes
			List<JavaClassEObject> classes = model.getPackage("com.sun.javaee.blueprints.petstore.model").getClasses();
			
			// List class
			System.out.println("Class of com.sun.javaee.blueprints.petstore.model : ");
			Iterator<JavaClassEObject> iteratorClasses = classes.iterator();
			while(iteratorClasses.hasNext()) {
				JavaClassEObject javaClass = iteratorClasses.next();
				System.out.println(javaClass.getEObjectName());
			}
			
			//TO BE COMPLETED
			
			/*
			 * End of the part to be completed...
			 */
			
			dataModel.save(null);
			dataModel.save(new FileOutputStream("out_java.xmi"), null);
			
			javaModel.unload();
			dataModel.unload();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static EObject getPackage(String packageName, EObject root) {
		Iterator<EObject> packages;
		packages = root.eContents().iterator();
		
		while(packages.hasNext()) {
			EObject currentPackagesElement = packages.next();
			if (currentPackagesElement.eClass().getName().equals("Package") &&
					getEObjectName(currentPackagesElement).equals(packageName)) {
				System.out.println("Go to package : " + getEObjectName(currentPackagesElement));
				return currentPackagesElement;
			}
		}
		return null;
	}
	
	public static String getEObjectName(EObject object) {
		EStructuralFeature name = object.eClass().getEStructuralFeature("name");
		return (String) object.eGet(name);
	}
	
}
