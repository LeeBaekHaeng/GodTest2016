package godsoft.test;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Aa2 implements Serializable {

	private Long id;

	private Aa1 aa1;

}
