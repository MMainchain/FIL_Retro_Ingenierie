package data.computation;

import java.util.HashMap;

/**
 * 
 * @author Maël MAINCHAIN
 *
 */
public interface packageContainer {
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public JavaPackageEObject getPackage(String string);
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, JavaPackageEObject> getPackages();
}
