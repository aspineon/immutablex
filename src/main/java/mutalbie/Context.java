package mutalbie;

import java.util.*;

/**
 * Created by mauricioaniche on 07/12/2017.
 */
public class Context {

    private Set<String> immutableClasses;
    private Collection<String> problems;

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

    public void addProblem (String currentClazz, String currentMethod) {
        problems.add("You suck: " + currentClazz + " " + currentMethod);
    }

    public Collection<String> getProblems () {
        return Collections.unmodifiableCollection(problems);
    }
}
