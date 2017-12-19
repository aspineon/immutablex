package immutablex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class ImmutableXTest {

    private String path;

    @Before
    public void beforeClass(){
        this.path = this.getClass().getResource("/").getPath() + "../../fixtures/";
    }
    @Test
    public void immutableDependency() {

        Context context = new Context();
        new JDTRunner(context).calculate(path + "t1");

        Assert.assertEquals(2, context.getProblems().size());
        Assert.assertTrue(context.getProblems().contains(new Problem("Class3", "b", "c.setB(\"alberto\")", 9)));
        Assert.assertTrue(context.getProblems().contains(new Problem("Class3", "b", "c.setB(\"alberto\")", 11)));

    }

    @Test
    public void methodChaining() {

        Context context = new Context();
        new JDTRunner(context).calculate(path + "t2");

        Assert.assertEquals(1, context.getProblems().size());
        Assert.assertTrue(context.getProblems().contains(new Problem("Class3", "x", "c2.one().setA()", 6)));

    }

}
