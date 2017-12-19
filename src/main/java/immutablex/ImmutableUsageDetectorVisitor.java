package immutablex;

import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class ImmutableUsageDetectorVisitor extends ASTVisitor {

    private Context context;
    private Set<String> variablesToMonitor;
    private String currentClazz;
    private String currentMethod;
    private CompilationUnit cu;

    public ImmutableUsageDetectorVisitor (Context context) {
        this.context = context;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        this.currentClazz = node.getName().toString();
        return super.visit(node);
    }

    @Override
    public boolean visit(CompilationUnit cu) {
        this.cu = cu;
        return super.visit(cu);
    }

    @Override
    public boolean visit(MethodDeclaration node) {

        variablesToMonitor = new HashSet<>();
        this.currentMethod = node.getName().toString();

        List<SingleVariableDeclaration> parameters = node.parameters();
        for(SingleVariableDeclaration p : parameters) {
            ITypeBinding binding = p.getType().resolveBinding();
            if(binding == null)
                continue;

            String paramName = p.getName().toString();
            variablesToMonitor.add(paramName);
        }

        return super.visit(node);
    }

    @Override
    public void endVisit(MethodDeclaration node) {
        variablesToMonitor.clear();
        this.currentMethod = null;
    }

    @Override
    public boolean visit(MethodInvocation node) {

        if(node.getExpression() == null) {
            System.out.println("TODO: implement static invocation " + currentClazz + "#" + node.toString());
            return super.visit(node);
        }
        String variableInvoked = node.getExpression().toString().split("\\.")[0];
        ITypeBinding binding = node.getExpression().resolveTypeBinding();
        if(binding == null)
            return super.visit(node);

        String className = binding.getQualifiedName();

        if(variablesToMonitor.contains(variableInvoked) && context.isImmutable(className)) {
            String invokedMethod = node.getName().toString();

            boolean problematic = invokedMethod.startsWith("set");
            if(problematic)
                context.addProblem(currentClazz, currentMethod, node.toString(), cu.getLineNumber(node.getStartPosition())-1);
        }

        return super.visit(node);
    }
}
