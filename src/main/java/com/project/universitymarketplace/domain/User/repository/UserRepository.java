package com.project.universitymarketplace.domain.User.repository;

import com.project.universitymarketplace.domain.User.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
