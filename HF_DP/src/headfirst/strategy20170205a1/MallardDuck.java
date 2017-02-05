package headfirst.strategy20170205a1;

public class MallardDuck extends Duck {

	public MallardDuck() {
		// 청둥오리

		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("저는 물오리입니다");

		System.out.println("I'm a real Mallard duck");

		System.out.println("I'm a real Mallard duck");

		System.out.println("청둥오리");
	}

}
