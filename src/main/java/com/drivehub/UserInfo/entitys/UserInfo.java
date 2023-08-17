package com.drivehub.UserInfo.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="userInfo")
public class UserInfo {
    @Id
    @Column
    private long userId;

    @Column
    private String userName;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    public UserInfo(long userId, String userName, String phoneNumber, String address) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
}
