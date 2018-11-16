/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package egovframework.sample.bat.domain.trade;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * TradeRowMapper
 * 
 * @author 배치실행개발팀
 * @since 2012. 07.25
 * @version 1.0
 * @see <pre>
 *      개정이력(Modification Information)
 *   
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012. 07.25  배치실행개발팀     최초 생성
 *  </pre>
 */

public class TradeRowMapper implements RowMapper {
	
	public static final int ISIN_COLUMN = 1;
	public static final int QUANTITY_COLUMN = 2;
	public static final int PRICE_COLUMN = 3;
	public static final int CUSTOMER_COLUMN = 4;
	public static final int ID_COLUMN = 5;
	public static final int VERSION_COLUMN = 6;

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Trade trade = new Trade(rs.getLong(ID_COLUMN));
		
		trade.setIsin(rs.getString(ISIN_COLUMN));
		trade.setQuantity(rs.getLong(QUANTITY_COLUMN));
		trade.setPrice(rs.getBigDecimal(PRICE_COLUMN));
		trade.setCustomer(rs.getString(CUSTOMER_COLUMN));
		trade.setVersion(rs.getInt(VERSION_COLUMN));
		
		return trade;
	}

}
