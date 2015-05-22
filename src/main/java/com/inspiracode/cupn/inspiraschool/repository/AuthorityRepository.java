package com.inspiracode.cupn.inspiraschool.repository;

import com.inspiracode.cupn.inspiraschool.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
