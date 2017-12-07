package mutalbie;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class AnnotationsRequestor extends FileASTRequestor {

    private ASTVisitor visitor;

    public AnnotationsRequestor(ASTVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void acceptAST(String sourceFilePath,
                          CompilationUnit cu) {

        cu.accept(visitor);

    }
}
