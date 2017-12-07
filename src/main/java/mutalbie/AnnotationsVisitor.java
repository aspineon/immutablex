package mutalbie;

import org.eclipse.jdt.core.dom.*;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class AnnotationsVisitor extends ASTVisitor {

    private Context context;
    private String packageName = "";
    private String clazzName = "";

    public AnnotationsVisitor (Context context) {
        this.context = context;
    }

    public boolean visit(PackageDeclaration node) {
        this.packageName = node.getName().toString();
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        this.clazzName = node.getName().toString();
        return super.visit(node);
    }

    public boolean visit(MarkerAnnotation node) {

        String annName = node.getTypeName().toString();

        if(annName.toString().equals("ImmutableX"))
            context.addImmutableClass(packageName + "." + clazzName);

        return super.visit(node);
    }

}
