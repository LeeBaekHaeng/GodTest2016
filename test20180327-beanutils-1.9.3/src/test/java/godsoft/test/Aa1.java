package godsoft.test;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Aa1 implements Serializable {

	private Long id;

	private Aa2 aa2;

	// private List<Aa2> aa2s;

}
