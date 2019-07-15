## 线程间的通信
当一个线程需要调用其他线程才能满足业务要求时，
就需要进行线程间的通信,我们这是可以通过Thread内的 join()方法实现

    
    主线程mian
    public class Test{
        public static void main(String[] args) {
            A a = new A();
            B b = new B(a);
            a.start();
            b.start();
        }
    }
    
    线程A
    class A extends Thread {

        @Override
        public void run() {
            System.out.println("Who are you ?");
        }
    }

    线程B，此时线程B需要调用线程A，才可完成操作
    class B extends Thread{
    //    A a = new A();
        private A a;
        B(A a){
            this.a=a;
        }
        @Override
        public void run() {
            try {
                a.join();
                System.out.println("IS B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }