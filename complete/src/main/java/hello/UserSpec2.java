package hello;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class UserSpec2 {

	public static Specification<User> idEqual(User user) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (!StringUtils.isEmpty(user.getId())) {
					return criteriaBuilder.equal(root.get("id"), user.getId());
				}

				return null;
			}
		};
	}

	public static Specification<User> emailEqual(User user) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (!StringUtils.isEmpty(user.getEmail())) {
					return criteriaBuilder.equal(root.get("email"), user.getEmail());
				}

				return null;
			}
		};
	}

	public static Specification<User> likeEmail(User user) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (!StringUtils.isEmpty(user.getEmail())) {
					return criteriaBuilder.like(root.get("email"), "%" + user.getEmail() + "%");
				}

				return null;
			}
		};
	}

	public static Specification<User> nameEqual(User user) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (!StringUtils.isEmpty(user.getName())) {
					return criteriaBuilder.equal(root.get("name"), user.getName());
				}

				return null;
			}
		};
	}

}
