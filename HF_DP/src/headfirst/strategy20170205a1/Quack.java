package headfirst.strategy20170205a1;

public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("꽥");

		System.out.println("Quack");

		System.out.println("Quack");

		System.out.println("(오리가) 꽥꽥 우는 소리");
	}

}
