package com.airrentals.account_service.repository;

import com.airrentals.account_service.model.ProfileQueryObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileQueryObjectRepository extends JpaRepository<ProfileQueryObject, String> {
}
