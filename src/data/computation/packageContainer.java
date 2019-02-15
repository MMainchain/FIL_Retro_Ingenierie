package data.computation;

import java.util.List;

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
	public List<JavaPackageEObject> getPackages();
}
