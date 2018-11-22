import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterDetailTest {

	// @Test
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

	@Test
	public void test2() throws Exception {
		System.out.println("이백행");

		MasterDAO masterDAO = new MasterDAO();
		DetailDAO detailDAO = new DetailDAO();

		MasterVO vo = test2Param();

		// master insert
		MasterVO result = masterDAO.insert(vo);

		System.out.println("result=" + result);
		System.out.println("getMasterId=" + result.getMasterId());
		System.out.println("");

		for (DetailVO detail : vo.getDetails()) {
			// setMasterId
			detail.setMasterId(result.getMasterId());

			System.out.println("detail=" + detail);
			System.out.println("getMasterId=" + detail.getMasterId());
			System.out.println("getDetailId=" + detail.getDetailId());
			System.out.println("getDetailNm=" + detail.getDetailNm());
			System.out.println("");

			// detail insert
			DetailVO result2 = detailDAO.insert(detail);
			System.out.println("result2=" + result2);
			System.out.println("getMasterId=" + result2.getMasterId());
			System.out.println("getDetailId=" + result2.getDetailId());
			System.out.println("getDetailNm=" + result2.getDetailNm());
			System.out.println("");
		}
	}

	public MasterVO test2Param() {
		MasterVO vo = new MasterVO();
		vo.setMasterNm("마스터1");

		List<DetailVO> details = new ArrayList<>();

		DetailVO detail = new DetailVO();
		// detail.setDetailId(1);
		detail.setDetailNm("디테일1");
		details.add(detail);

		detail = new DetailVO();
		// detail.setDetailId(2);
		detail.setDetailNm("디테일2");
		details.add(detail);

		detail = new DetailVO();
		// detail.setDetailId(2);
		detail.setDetailNm("디테일3");
		details.add(detail);

		vo.setDetails(details);

		System.out.println("vo=" + vo);
		System.out.println("getMasterNm=" + vo.getMasterNm());
		System.out.println("getDetails=" + vo.getDetails());
		System.out.println("");

		return vo;
	}

}

class MasterVO {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private int masterId;
	private String masterNm;

	private List<DetailVO> details;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public String getMasterNm() {
		return masterNm;
	}

	public void setMasterNm(String masterNm) {
		this.masterNm = masterNm;
	}

	public List<DetailVO> getDetails() {
		return details;
	}

	public void setDetails(List<DetailVO> details) {
		this.details = details;
	}

}

class DetailVO {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private int masterId;

	private int detailId;

	private String detailNm;

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

	public String getDetailNm() {
		return detailNm;
	}

	public void setDetailNm(String detailNm) {
		this.detailNm = detailNm;
	}

}

class MasterDAO {

	private List<MasterVO> masters = new ArrayList<>();

	public MasterVO select() {
		MasterVO vo = new MasterVO();
		vo.setMasterId(1);
		return vo;
	}

	public MasterVO insert(MasterVO vo) {
		// MasterVO result = new MasterVO();
		// result.setMasterId(1);
		// return result;

		int size = masters.size();
		int masterId = size + 1;
		vo.setMasterId(masterId);
		masters.add(vo);
		return masters.get(size);
	}

}

class DetailDAO {

	private List<DetailVO> details = new ArrayList<>();

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

	public DetailVO insert(DetailVO vo) {
		// DetailVO result = new DetailVO();
		// result.setMasterId(vo.getMasterId());
		// result.setDetailId(1);
		// result.setDetailNm(vo.getDetailNm());
		// return result;

		int size = details.size();
		int detailId = size + 1;
		vo.setDetailId(detailId);
		details.add(vo);
		return details.get(size);
	}

}