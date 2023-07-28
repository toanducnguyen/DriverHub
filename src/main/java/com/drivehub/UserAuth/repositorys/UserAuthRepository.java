package com.drivehub.UserAuth.repositorys;

import com.drivehub.UserAuth.entitys.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth,Long> {
    UserAuth findByEmail(String email);
}
