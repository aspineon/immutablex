package immutablex;

import java.util.Collection;

/**
 * Created by mauricioaniche on 19/12/2017.
 */
public class Runner {

    public static void main (String[] args) {

        if(args == null || args.length == 0) {
            System.out.println("Usage: java -jar immutablex.jar <path to the project>");
            System.exit(-1);
        }

        String path = args[0];
        Context context = new Context();
        new JDTRunner(context).calculate(path);

        Collection<Problem> problems = context.getProblems();

        if(problems.isEmpty()) {
            System.out.println("No problems found! You are the king of immutability!");
            System.exit(0);
        } else {
            for(Problem problem : problems) {
                System.out.println(problem.getClazz()+"#" + problem.getMethod() + ":" + problem.getLine() + " -> invalid invocation: " + problem.getInvokedMethod());
            }
            System.exit(-2);
        }
    }
}
