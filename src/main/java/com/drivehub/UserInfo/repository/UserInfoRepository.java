package com.drivehub.UserInfo.repository;

import com.drivehub.UserInfo.entitys.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

}
