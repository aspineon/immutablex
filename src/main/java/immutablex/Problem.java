package immutablex;

/**
 * Created by mauricioaniche on 19/12/2017.
 */
public class Problem {
    private final String clazz;
    private final String method;
    private final String invokedMethod;
    private final int line;

    public Problem (String clazz, String method, String invokedMethod, int line) {
        this.clazz = clazz;
        this.method = method;
        this.invokedMethod = invokedMethod;
        this.line = line;
    }

    public String getClazz () {
        return clazz;
    }

    public String getMethod () {
        return method;
    }

    public String getInvokedMethod () {
        return invokedMethod;
    }

    public int getLine () {
        return line;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem problem = (Problem) o;

        if (line != problem.line) return false;
        if (!clazz.equals(problem.clazz)) return false;
        if (!method.equals(problem.method)) return false;
        return invokedMethod.equals(problem.invokedMethod);
    }

    @Override
    public int hashCode () {
        int result = clazz.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + invokedMethod.hashCode();
        result = 31 * result + line;
        return result;
    }

    @Override
    public String toString () {
        return "Problem{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", invokedMethod='" + invokedMethod + '\'' +
                ", line=" + line +
                '}';
    }
}

