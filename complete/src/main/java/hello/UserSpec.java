package hello;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class UserSpec implements Specification<User> {

	private User user;

	public UserSpec(User user) {
		this.user = user;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> restrictions = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(user.getId())) {
			restrictions.add(criteriaBuilder.equal(root.get("id"), user.getId()));
		}

		if (!StringUtils.isEmpty(user.getEmail())) {
			// restrictions.add(criteriaBuilder.equal(root.get("email"), user.getEmail()));
			restrictions.add(criteriaBuilder.like(root.get("email"), "%" + user.getEmail() + "%"));
		}

		if (!StringUtils.isEmpty(user.getName())) {
			restrictions.add(criteriaBuilder.equal(root.get("name"), user.getName()));
		}

		return criteriaBuilder.and(restrictions.toArray(new Predicate[] {}));
	}

}
