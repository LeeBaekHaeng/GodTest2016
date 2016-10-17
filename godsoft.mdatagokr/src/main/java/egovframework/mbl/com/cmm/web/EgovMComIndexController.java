package egovframework.mbl.com.cmm.web;

/**
 * 컴포넌트 설치 후 설치된 컴포넌트들을 IncludedMblInfo annotation을 통해 찾아낸 후
 * 화면에 표시할 정보를 처리하는 Controller 클래스
 * <Notice>
 * 		개발시 메뉴 구조가 잡히기 전에 배포파일들에 포함된 공통 컴포넌트들의 목록성 화면에
 * 		URL을 제공하여 개발자가 편하게 활용하도록 하기 위해 작성된 것으로,
 * 		실제 운영되는 시스템에서는 적용해서는 안 됨
 *      실 운영 시에는 삭제해서 배포해도 좋음
 * <Disclaimer>
 * 		운영시에 본 컨트롤을 사용하여 메뉴를 구성하는 경우 성능 문제를 일으키거나
 * 		사용자별 메뉴 구성에 오류를 발생할 수 있음
 * @author 유지보수팀
 * @since 2013.12.27
 * @version 2.7.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2013.12.27	이기하 		최초 생성
 * </pre>
 */

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import egovframework.com.cmm.IncludedCompInfoVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.mbl.com.cmm.annotation.IncludedMblInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EgovMComIndexController implements ApplicationContextAware ,InitializingBean {

	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMComIndexController.class);

	private Map<Integer, IncludedCompInfoVO> map;

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		LOGGER.info("EgovMComIndexController setApplicationContext method has called!");
	}

	@RequestMapping("/EgovMobileMenu.mdo")
	public String mobileMenu(ModelMap model){

		// 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/mbl/com/uat/uia/EgovLoginUsr";
        }

		/*최초 한 번만 실행하여 map에 저장해 놓는다.*/
		if(map == null){
			map = new TreeMap<Integer, IncludedCompInfoVO>();
			RequestMapping rmAnnotation;
			IncludedMblInfo annotation;
			IncludedCompInfoVO zooVO;

			/*
			 * EgovMblLoginController가 AOP Proxy되는 바람에 클래스를 reflection으로 가져올 수 없음
			 * 방법을 찾을때까지 임시로 아래 코드 유지
			 */
			try{
				Class<?> loginMblController = Class.forName("egovframework.mbl.com.uat.uia.web.EgovMblLoginController");
				Method[] methods = loginMblController.getMethods();
				for(int i = 0; i< methods.length ;i++){
					annotation = methods[i].getAnnotation(IncludedMblInfo.class);

					if(annotation != null){
						LOGGER.debug("Found @IncludedMblInfo Method : {}", methods[i] );
						zooVO = new IncludedCompInfoVO();
						zooVO.setName(annotation.name());
						zooVO.setOrder(annotation.order());
						zooVO.setGid(annotation.gid());

						rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
						if("".equals(annotation.listUrl()) && rmAnnotation != null){
							zooVO.setListUrl(rmAnnotation.value()[0]);
						}
						else{
							zooVO.setListUrl(annotation.listUrl());
						}
						map.put(zooVO.getOrder(),zooVO);
					}
				}
			}
			catch(Exception e){
				LOGGER.error("No egovframework.mbl.com.uat.uia.web.EgovMblLoginController!!");
			}
			/* 여기까지 AOP Proxy로 인한 임시 코드 */

			/*@Controller Annotation 처리된 클래스를 모두 찾는다.*/
			Map<String, Object> myZoos = applicationContext.getBeansWithAnnotation(Controller.class);
			LOGGER.debug("How many Controllers : {}", myZoos.size());
			for (final Object myZoo : myZoos.values()) {
				Class<? extends Object> zooClass = myZoo.getClass();

				Method[] methods = zooClass.getMethods();
				LOGGER.debug("Controller Detected {}", zooClass);
				for(int i = 0; i< methods.length ;i++){
					annotation = methods[i].getAnnotation(IncludedMblInfo.class);

					if(annotation != null){
						//log.debug("Found @IncludedMblInfo Method : " + methods[i] );
						zooVO = new IncludedCompInfoVO();
						zooVO.setName(annotation.name());
						zooVO.setOrder(annotation.order());
						zooVO.setGid(annotation.gid());
						/*
						 * 목록형 조회를 위한 url 매핑은 @IncludedMblInfo나 @RequestMapping에서 가져온다
						 */
						rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
						if("".equals(annotation.listUrl())){
							zooVO.setListUrl(rmAnnotation.value()[0]);
						}
						else{
							zooVO.setListUrl(annotation.listUrl());
						}
						map.put(zooVO.getOrder(),zooVO);
					}
				}
			}
		}

		model.addAttribute("resultList", map.values());
		LOGGER.debug("EgovMComIndexController index is called " );
		return "egovframework/mbl/com/cmm/EgovMobileMain";
	}
}
