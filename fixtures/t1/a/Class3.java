package a;

public class Class3 {

    public void b(Class2 c) {

        int a = 1;
        a = 10;

        c.setB("alberto");
        c.b();
        c.setB("alberto");

    }

    public void x() {
        Class2 c2 = new Class2();
        c2.setB("xxxx");
    }

    public void setb() {

    }

    public String getB() {
        return "c";
    }
}