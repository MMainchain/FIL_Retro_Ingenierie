package tp.fil.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import data.computation.JavaClassEObject;
import data.computation.JavaEObjectFactory;
import data.computation.JavaModelEObject;

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
			dataModel = resSet.createResource(URI.createFileURI("test_data.xmi"));
			
			// Get EPackage
			EPackage ePackage = (EPackage) dataMetamodel.getContents().get(0);
			// Get model EClass
			EClass testModel = (EClass) ePackage.getEClassifier("Model");
			// Create a model Object
			EObject testModelObject = ePackage.getEFactoryInstance().create(testModel);
			// Instantiate an EClass
			List<EObject> listClass = new ArrayList<EObject>();
			// Get root model
			JavaModelEObject model = JavaEObjectFactory.createJavaModelEobject(javaModel.getContents().get(0), ePackage);
			// Go to wanted package and get classes
			HashMap<String, JavaClassEObject> classes = model.getPackage("com.sun.javaee.blueprints.petstore.model").getClasses();
			// List class
			Iterator<Entry<String, JavaClassEObject>> iteratorClasses = classes.entrySet().iterator();
			while(iteratorClasses.hasNext()) {
				Entry<String, JavaClassEObject> pair = iteratorClasses.next();
				// Add class to list
				listClass.add(pair.getValue().getTargetObject());
		        iteratorClasses.remove();
			}
			
			// Add list class to model
			testModelObject.eSet(testModel.getEStructuralFeature("classes"), listClass);
			// Save model
			dataModel.getContents().add(testModelObject);
			dataModel.save(null);
			
			javaModel.unload();
			dataModel.unload();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
