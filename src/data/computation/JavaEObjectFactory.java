package data.computation;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmt.modisco.java.emf.impl.NamedElementImpl;

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
	public final static String MODIFIER_TYPE = "Modifier";
	public final static String TYPE_TYPE = "TypeAccess";
	public final static String VARIABLE_TYPE = "VariableDeclarationFragment";
	
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
	 * @param currentElement
	 * @return
	 */
	public static String createJavaVariableEobject(EObject object) {
		if (!verifiedType(object, VARIABLE_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java variable type : "+ getType(object) + " given.");
		EStructuralFeature name = object.eClass().getEStructuralFeature("name");
		return (String) object.eGet(name);
	}
	
	/**
	 * 
	 * @param currentElement
	 * @return
	 */
	public static String createJavaTypeEobject(EObject object) {
		if (!verifiedType(object, TYPE_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java type type : "+ getType(object) + " given.");
		EStructuralFeature type = object.eClass().getEStructuralFeature("type");
		return ((NamedElementImpl) object.eGet(type)).getName();
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static JavaFunctionEObject createJavaFunctionEobject(EObject object) {
		if (!verifiedType(object, METHOD_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java method type : "+ getType(object) + " given.");
		return new JavaFunctionEObject(object);
	}
	
	/**
	 * 
	 * @param currentElement
	 * @return
	 */
	public static String createJavaModifierEobject(EObject object) {
		if (!verifiedType(object, MODIFIER_TYPE))
			throw new IllegalArgumentException("Given object cannot be resolve to Java type type : "+ getType(object) + " given.");
		EStructuralFeature visibility = object.eClass().getEStructuralFeature("visibility");
		return object.eGet(visibility).toString();
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
