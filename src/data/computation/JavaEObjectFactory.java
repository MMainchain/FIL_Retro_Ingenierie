package data.computation;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public class JavaEObjectFactory {
	
	public final static String MODEL_TYPE = "Model";
	public final static String PACKAGE_TYPE = "Package";
	public final static String CLASS_TYPE = "ClassDeclaration";
	public final static String FIELD_TYPE = "FieldDeclaration";
	public final static String METHOD_TYPE = "MethodDeclaration";
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static JavaModelEObject createJavaModelEobject(EObject object) {
		if (!verifiedType(object, MODEL_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java Model type : "+ getType(object) + " given.");
		return new JavaModelEObject(object);
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static JavaPackageEObject createJavaPackageEobject(EObject object) {
		if (!verifiedType(object, PACKAGE_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java package type : "+ getType(object) + " given.");
		return new JavaPackageEObject(object);
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static JavaClassEObject createJavaClassEobject(EObject object) {
		if (!verifiedType(object, CLASS_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java class type : "+ getType(object) + " given.");
		return new JavaClassEObject(object);
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static JavaFieldEObject createJavaFieldEobject(EObject object) {
		if (!verifiedType(object, FIELD_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java field type : "+ getType(object) + " given.");
		return new JavaFieldEObject(object);
	}
	
	/**
	 * 
	 * @param object
	 * @param type
	 * @return
	 */
	private static boolean verifiedType(EObject object, String type) {
		return object.eClass().getName().equals(type);
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	private static String getType(EObject object) {
		return object.eClass().getName();
	}

}
