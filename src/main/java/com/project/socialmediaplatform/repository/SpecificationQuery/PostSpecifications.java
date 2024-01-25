package com.project.socialmediaplatform.repository.SpecificationQuery;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

import com.project.socialmediaplatform.model.Post;
import com.project.socialmediaplatform.model.User;

import jakarta.persistence.criteria.Predicate;

public class PostSpecifications {

    public static Specification<Post> captionContains(String searchTerm) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("caption")),
                "%" + searchTerm.toLowerCase() + "%");
    }

    public static Specification<Post> postedAfter(Date date) {
        return (root, query, criteriaBuilder) -> {
            Predicate postedAfterPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("postedOn"), date);
            Predicate isDeletedPredicted = criteriaBuilder.equal(root.get("isDeleted"),false);
            return criteriaBuilder.and(postedAfterPredicate,isDeletedPredicted);
            };
    }

    public static Specification<Post> onFriendPosts(User currentUser) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user"), currentUser);
    }

    public static Specification<Post> onFriendList(List<User> friends) {
        return (root, query, criteriaBuilder) -> root.get("user").in(friends);
    }

    public static Specification<Post> combineSpecifications(List<Specification<Post>> specs) {
        Specification<Post> combinedSpec = Specification.where(null);
        for (Specification<Post> spec : specs) {
            if (spec != null) {
                combinedSpec = combinedSpec.and(spec);
            }
        }
        return combinedSpec;
    }
}
