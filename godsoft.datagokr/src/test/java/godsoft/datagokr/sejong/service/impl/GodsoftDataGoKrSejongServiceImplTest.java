package godsoft.datagokr.sejong.service.impl;

import godsoft.datagokr.sejong.service.GodsoftDataGoKrSejongService;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		// "classpath:egovframework/spring/com/context-*.xml",
		// "classpath:egovframework/spring/com/idgn/context-*.xml",
		// "classpath:godsoft/spring/datagokr/context-*.xml",
		"classpath:godsoft/spring/datagokr/context-common.xml",
		"classpath:godsoft/spring/datagokr/context-excel.xml" })
public class GodsoftDataGoKrSejongServiceImplTest {

	@Autowired
	private GodsoftDataGoKrSejongService godsoftDataGoKrSejongService;

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void test() throws Exception {
		String[] beanDefinitionNames = applicationContext
				.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}

		Map<String, Object> services = applicationContext
				.getBeansWithAnnotation(Service.class);
		System.out.println("services=" + services);

		Map<String, Object> vo = new HashMap<String, Object>();
		ModelMap model = new ModelMap();

		godsoftDataGoKrSejongService.selectSejong01List(vo, model);
	}

}
