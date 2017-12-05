package me.vanlin;

/**
 * Hello world!
 */
public class App {
    static interface ITest {
        private void sayHello() {
            System.out.println("sayHello interface");
        }
        default void haha() {
            sayHello();
        }
    }

    static interface IHello {
        private String sayWorld() {
            return "sayWorld interface";
        }

        default void haha() {
            System.out.println(sayWorld());
        }
    }

    static class Test implements ITest, IHello {
        public void sayHello() {
            haha();
        }

        @Override
        public void haha() {
            IHello.super.haha();
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.sayHello();
    }
}
