package cms.com;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

// TODO 필요할 경우, 스프링 컨텍스트/트랜잭션 등의 설정을 적용한다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:egovframework/spring/context-*.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public abstract class EgovOe1ComTestManage {

	@Resource(name = "dataSource")
	protected DataSource dataSource;

	/**
	 * TODO EgovQustnrRespondManageService에 정의된 메소드 만큼의 추상 메소드를 선언한다.
	 * @throws Exception 
	*/
	public abstract void testSelectQustnrRespondManageDetail() throws Exception;

}   
