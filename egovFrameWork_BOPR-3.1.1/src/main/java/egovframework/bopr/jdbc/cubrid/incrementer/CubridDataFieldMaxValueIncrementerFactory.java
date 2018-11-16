package egovframework.bopr.jdbc.cubrid.incrementer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.support.DefaultDataFieldMaxValueIncrementerFactory;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class CubridDataFieldMaxValueIncrementerFactory extends DefaultDataFieldMaxValueIncrementerFactory {
	private DataSource dataSource;

	public CubridDataFieldMaxValueIncrementerFactory(DataSource dataSource) {
		super(dataSource);
		this.dataSource = dataSource;
	}

	@Override
	public DataFieldMaxValueIncrementer getIncrementer(String incrementerType,
			String incrementerName) {
		
		return  new CubridSequenceMaxValueIncrementer(dataSource, incrementerName);
	}

}
