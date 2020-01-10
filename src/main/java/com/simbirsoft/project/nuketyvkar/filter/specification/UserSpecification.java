package com.simbirsoft.project.nuketyvkar.filter.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import com.simbirsoft.project.nuketyvkar.model.User;
import com.simbirsoft.project.nuketyvkar.model.User_;

@UtilityClass
public class UserSpecification {

    public static Specification<User> findByUsername(String username) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.USERNAME), username));
    }

}