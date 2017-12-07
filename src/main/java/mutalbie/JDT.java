package mutalbie;

import com.google.common.collect.Lists;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class JDT {

    private static final int MAX_AT_ONCE;

    static {
        long maxMemory= Runtime.getRuntime().maxMemory() / (1 << 20); // in MiB

        if      (maxMemory >= 2000) MAX_AT_ONCE= 400;
        else if (maxMemory >= 1500) MAX_AT_ONCE= 300;
        else if (maxMemory >= 1000) MAX_AT_ONCE= 200;
        else if (maxMemory >=  500) MAX_AT_ONCE= 100;
        else                        MAX_AT_ONCE=  25;
    }

    private Context context;

    public JDT(Context context) {
        this.context = context;
    }


    public void calculate(String path) {
        String[] srcDirs = FileUtils.getAllDirs(path);
        String[] javaFiles = FileUtils.getAllJavaFiles(path);

        List<List<String>> partitions = Lists.partition(Arrays.asList(javaFiles), MAX_AT_ONCE);


        for(List<String> partition : partitions) {
            ASTParser parser = ASTParser.newParser(AST.JLS8);

            parser.setResolveBindings(true);
            parser.setBindingsRecovery(true);

            Map<String, String> options = JavaCore.getOptions();
            JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
            parser.setCompilerOptions(options);
            parser.setEnvironment(null, srcDirs, null, true);
            parser.createASTs(partition.toArray(new String[partition.size()]), null, new String[0],
                    new AnnotationsRequestor(new AnnotationsVisitor(context)), null);
        }


        for(List<String> partition : partitions) {
            ASTParser parser = ASTParser.newParser(AST.JLS8);

            parser.setResolveBindings(true);
            parser.setBindingsRecovery(true);

            Map<String, String> options = JavaCore.getOptions();
            JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
            parser.setCompilerOptions(options);
            parser.setEnvironment(null, srcDirs, null, true);
            parser.createASTs(partition.toArray(new String[partition.size()]), null, new String[0],
                    new AnnotationsRequestor(new ImmutableUsageDetectorVisitor(context)), null);
        }


    }


}
