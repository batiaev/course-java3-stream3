package HM_4.ex1;

public class HM4 {

    int status = 1;

    public static void main(String[] args) {

        HM4 hm4 = new HM4();

        A a = new A(hm4, "A");
        B b = new B(hm4, "B");
        C c = new C(hm4, "C");

        a.start();
        b.start();
        c.start();
    }
}

class A extends Thread {
    private final HM4 hm4;

    A(HM4 hm4, String name) {
        this.hm4 = hm4;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            synchronized (hm4) {
                for (int i = 0; i < 5; i++) {
                    while (hm4.status != 1) {
                        hm4.wait();
                    }
                    System.out.print(getName());
                    hm4.status = 2;
                    hm4.notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class B extends Thread {

    private final HM4 hm4;

    B(HM4 hm4, String name) {
        this.hm4 = hm4;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            synchronized (hm4) {
                for (int i = 0; i < 5; i++) {
                    while (hm4.status != 2) {
                        hm4.wait();
                    }
                    System.out.print(getName());
                    hm4.status = 3;
                    hm4.notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class C extends Thread {

    private final HM4 hm4;

    C(HM4 hm4, String name) {
        this.hm4 = hm4;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            synchronized (hm4) {
                for (int i = 0; i < 5; i++) {
                    while (hm4.status != 3) {
                        hm4.wait();
                    }
                    System.out.print(getName());
                    hm4.status = 1;
                    hm4.notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
