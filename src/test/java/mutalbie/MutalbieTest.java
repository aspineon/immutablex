package mutalbie;

import org.junit.Test;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class MutalbieTest {

    @Test
    public void simpleTest() {

        Context context = new Context();
        new JDT(context).calculate("/Users/mauricioaniche/workspace/mutalbie/fixtures/t1");
        System.out.println(context.getProblems());

    }

    @Test
    public void methodChaining() {

        Context context = new Context();
        new JDT(context).calculate("/Users/mauricioaniche/workspace/mutalbie/fixtures/t2");
        System.out.println(context.getProblems());

    }

    @Test
    public void alura() {

        Context context = new Context();
        new JDT(context).calculate("/Users/mauricioaniche/workspace/gnarus/src");
        System.out.println(context.getProblems());

    }
}
