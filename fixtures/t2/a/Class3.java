package a;


public class Class3 {

    public void x(Class2 c2) {
        c2.one().setA();
    }

    public void y() {
        Class2 ok = new Class2();
        ok.one().setA();
    }
}