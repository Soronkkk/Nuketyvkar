package com.simbirsoft.project.nuketyvkar.filter.specification;

import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import com.simbirsoft.project.nuketyvkar.model.RoomRole_;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class RoomRoleSpecification {

    public static Specification<RoomRole> findByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoomRole_.NAME), name);
    }

}