package com.mfdigital.apollo_users.core.repositories.specifications;

import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserSpecification implements Specification<User> {

    private final Sector sector;
    private final State state;
    private final UserRole role;
    private final String username;

    public UserSpecification(Sector sector, State state, UserRole role, String username) {
        this.sector = sector;
        this.state = state;
        this.role = role;
        this.username = username;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(this.sector)){
            predicates.add(criteriaBuilder.equal(root.get("sector"), this.sector));
        }
        if(Objects.nonNull(this.state)){
            predicates.add(criteriaBuilder.equal(root.get("state"), this.state));
        }
        if(Objects.nonNull(this.role)){
            predicates.add(criteriaBuilder.equal(root.get("userRole"), this.role));
        }
        if(StringUtils.isNotEmpty(this.username)){
            predicates.add(criteriaBuilder.like(root.get("username"), "%"+this.username+"%"));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
