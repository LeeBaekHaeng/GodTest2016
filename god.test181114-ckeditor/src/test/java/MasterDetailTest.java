import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterDetailTest {

	@Test
	public void test() throws Exception {
		// fail("Not yet implemented");

		System.out.println("이백행");

		MasterDAO masterDAO = new MasterDAO();
		DetailDAO detailDAO = new DetailDAO();

		MasterVO master = masterDAO.select();
		List<DetailVO> details = detailDAO.selectList();

		System.out.println("master=" + master);
		System.out.println("details=" + details);

		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(master));

		master.setDetails(details);

		System.out.println("details=" + details);

		System.out.println(mapper.writeValueAsString(master));
	}

}

class MasterVO {

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private int masterId;

	private List<DetailVO> details;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public List<DetailVO> getDetails() {
		return details;
	}

	public void setDetails(List<DetailVO> details) {
		this.details = details;
	}

}

class DetailVO {

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private int masterId;

	private int detailId;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

}

class MasterDAO {

	public MasterVO select() {
		MasterVO vo = new MasterVO();
		vo.setMasterId(1);
		return vo;
	}

}

class DetailDAO {

	public List<DetailVO> selectList() {
		List<DetailVO> results = new ArrayList<>();

		DetailVO vo = new DetailVO();
		vo.setMasterId(1);
		vo.setDetailId(1);
		results.add(vo);

		vo = new DetailVO();
		vo.setMasterId(1);
		vo.setDetailId(2);
		results.add(vo);

		return results;
	}

}