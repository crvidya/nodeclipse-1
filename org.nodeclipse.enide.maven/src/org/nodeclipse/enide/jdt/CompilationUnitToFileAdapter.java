package org.nodeclipse.enide.jdt;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jdt.core.ICompilationUnit;


/** The adapter factory class
http://stackoverflow.com/questions/775709/eclipse-pde-navigator-view-treeselection-obtaining-the-file-type-and-name
 */
public class CompilationUnitToFileAdapter implements IAdapterFactory {
    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adaptableObject instanceof ICompilationUnit)
            // note: "adapting" it here just means returning the ref'd IFile
            return (IFile) ((ICompilationUnit)adaptableObject).getResource();
        return null;
    }
    @Override
    public Class[] getAdapterList() {
        return new Class[] {IFile.class};
    }
}