package org.somenonprofit.pack1;

public class SynchronizedExample1 {

	public static void main(String[] args) {
		new Thread(new MyThread(), "one").start();
		new Thread(new MyThread(), "two").start();
		new Thread(new MyThread(), "three").start();
		new Thread(new MyThread(), "four").start();
	}
	
	private static class A {
		static int a=1;
		int b=0;
	}

	private static class B {
		void m() {
			A a = new A();
			synchronized (A.class) {
				System.out.println("in sync block " + Thread.currentThread().getName());
				System.out.println(A.a);
			}
			synchronized (a) {
				System.err.println("in instance sync block");
				System.err.println(a.b);
			}
		}
	}
	
	private static class MyThread implements Runnable {

		@Override
		public void run() {
			System.out.println("in run method " + Thread.currentThread().getName());
			new B().m();
		}
		
	}
}
