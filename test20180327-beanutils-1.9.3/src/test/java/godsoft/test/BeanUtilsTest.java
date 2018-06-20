package godsoft.test;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

public class BeanUtilsTest {

	// @Test
	public void test() throws Exception {
		Object bean = null;

		bean = new Aa1();

		System.out.println("bean=" + bean);

		Object cloneBean = BeanUtils.cloneBean(bean);

		System.out.println("cloneBean=" + cloneBean);
	}

	// @Test
	public void test2() throws Exception {
		Aa1 aa1 = new Aa1();

		System.out.println("aa1=" + aa1);

		Object cloneBean = BeanUtils.cloneBean(aa1);

		System.out.println("cloneBean=" + cloneBean);
	}

	// @Test
	public void test3() throws Exception {
		Aa1 aa1 = new Aa1();
		Aa2 aa2 = new Aa2();
		aa1.setAa2(aa2);

		System.out.println("aa1=" + aa1);

		Object cloneBean = BeanUtils.cloneBean(aa1);

		System.out.println("cloneBean=" + cloneBean);
	}

	// @Test
	public void test4() throws Exception {
		Aa1 aa1 = new Aa1();
		Aa2 aa2 = new Aa2();
		aa1.setAa2(aa2);

		Object dest = new Aa1();
		Object orig = aa1;

		System.out.println("dest=" + dest);
		System.out.println("orig=" + orig);

		System.out.println("dest=" + dest.hashCode());
		System.out.println("orig=" + orig.hashCode());

		System.out.println("dest=" + System.identityHashCode(dest));
		System.out.println("orig=" + System.identityHashCode(orig));

		BeanUtils.copyProperties(dest, orig);

		System.out.println("dest=" + dest);
		System.out.println("orig=" + orig);

		System.out.println("dest=" + dest.hashCode());
		System.out.println("orig=" + orig.hashCode());

		System.out.println("dest=" + System.identityHashCode(dest));
		System.out.println("orig=" + System.identityHashCode(orig));
	}

	// @Test
	public void test5() throws Exception {
		Aa1 aa1 = new Aa1();
		Aa2 aa2 = new Aa2();
		aa1.setAa2(aa2);

		Object dest = new Aa1();
		Object orig = aa1;

		System.out.println("dest=" + dest);
		System.out.println("orig=" + orig);

		PropertyUtils.copyProperties(dest, orig);

		System.out.println("dest=" + dest);
		System.out.println("orig=" + orig);
	}

	// @Test
	public void test6() throws Exception {
		Aa1 aa1 = new Aa1();
		Aa2 aa2 = new Aa2();
		aa1.setAa2(aa2);

		Object source = aa1;
		Object target = new Aa1();

		System.out.println("source=" + source);
		System.out.println("target=" + target);

		org.springframework.beans.BeanUtils.copyProperties(source, target);

		System.out.println("source=" + source);
		System.out.println("target=" + target);
	}

	// @Test
	public void test7() throws Exception {
		Aa1 aa1 = new Aa1();
		Aa2 aa2 = new Aa2();
		aa1.setAa2(aa2);

		Aa1 object = aa1;
		Aa1 cloneObject = SerializationUtils.clone(object);

		System.out.println("object=" + object);
		System.out.println("cloneObject=" + cloneObject);
	}

	// @Test
	public void test8() throws Exception {
		Ba1 ba1 = new Ba1();
		Ba2 ba2 = new Ba2();
		ba1.setBa2(ba2);

		Object object = ba1;

		byte[] serialize = org.springframework.util.SerializationUtils.serialize(object);

		System.out.println("serialize=" + serialize);
	}

	@Test
	public void test9() throws Exception {
		Aa1 aa1 = new Aa1();
		Aa2 aa2 = new Aa2();
		aa1.setAa2(aa2);

		Object object = aa1;

		byte[] bytes = org.springframework.util.SerializationUtils.serialize(object);
		System.out.println("bytes=" + bytes);

		Object deserialize = org.springframework.util.SerializationUtils.deserialize(bytes);
		System.out.println("deserialize=" + deserialize);
	}

}
