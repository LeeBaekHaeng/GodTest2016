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

package egovframework.sample.bat.domain.person;

import java.util.ArrayList;
import java.util.List;

import egovframework.sample.bat.domain.order.Address;

/**
 * PersonService
 * 
 * @author 배치실행개발팀
 * @since 2012.06.27
 * @version 1.0
 * @see <pre>
 *      개정이력(Modification Information)
 *   
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 * 2012.06.27  배치실행개발팀     최초 생성
 * </pre>
 */

public class PersonService {

	private static final int GENERATION_LIMIT = 10;

	private int generatedCounter = 0;

	private int processedCounter = 0;

	public Person getData() {
		if (generatedCounter >= GENERATION_LIMIT)
			return null;

		Person person = new Person();
		Address address = new Address();
		Child child = new Child();
		List<Child> children = new ArrayList<Child>(1);

		children.add(child);

		person.setFirstName("John" + generatedCounter);
		person.setAge(20 + generatedCounter);
		address.setCity("Johnsville" + generatedCounter);
		child.setName("Little Johny" + generatedCounter);

		person.setAddress(address);
		person.setChildren(children);

		generatedCounter++;
        
		return person;
	}

	/*
	 * Badly designed method signature which accepts multiple implicitly related
	 * arguments instead of a single Person argument.
	 */
	public void processPerson(String name, String city) {
		processedCounter++;
	}

	public int getReturnedCount() {
		return generatedCounter;
	}

	public int getReceivedCount() {
		return processedCounter;
	}
}
