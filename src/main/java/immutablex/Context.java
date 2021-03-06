package immutablex;

import java.util.*;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class Context {

    private Set<String> immutableClasses;
    private Collection<Problem> problems;

    public Context() {

        this.immutableClasses = new HashSet<>();
        this.problems = new ArrayList<>();
    }

    public void addImmutableClass(String clazz) {
        immutableClasses.add(clazz);
    }

    public Set<String> getAllImmutableClasses () {
        return Collections.unmodifiableSet(immutableClasses);
    }

    public boolean isImmutable (String qualifiedName) {
        return immutableClasses.contains(qualifiedName);
    }

    public void addProblem (String currentClazz, String currentMethod, String invokedMethod, int line) {
        problems.add(new Problem(currentClazz, currentMethod, invokedMethod, line));
    }

    public Collection<Problem> getProblems () {
        return Collections.unmodifiableCollection(problems);
    }
}
