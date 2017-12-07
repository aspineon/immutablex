package mutalbie;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class Context {

    private Set<String> immutableClasses;
    private Set<String> problems;

    public Context() {

        this.immutableClasses = new HashSet<>();
        this.problems = new HashSet<>();
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

    public void addProblem (String currentClazz, String currentMethod) {
        problems.add("You suck: " + currentClazz + " " + currentMethod);
    }

    public Set<String> getProblems () {
        return Collections.unmodifiableSet(problems);
    }
}
