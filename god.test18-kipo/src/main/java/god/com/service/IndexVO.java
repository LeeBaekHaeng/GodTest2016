package god.com.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class IndexVO extends ComDefaultVO {

	private String param1;

	private String param2;

	private List<String> param1s;

	private List<String> param2s;

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public List<String> getParam1s() {
		return param1s;
	}

	public void setParam1s(List<String> param1s) {
		this.param1s = param1s;
	}

	public List<String> getParam2s() {
		return param2s;
	}

	public void setParam2s(List<String> param2s) {
		this.param2s = param2s;
	}

}
