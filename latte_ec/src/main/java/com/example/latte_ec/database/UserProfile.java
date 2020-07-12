package com.example.latte_ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Dun Wenlong
 * @time 2020/7/11 16:38
 */

@Entity(nameInDb = "user_profile")

public class UserProfile {

    @Id
    public long userId = 0;
    public String name = null;
    public String avatar = null;
    public String gender = null;
    public String address = null;
@Generated(hash = 1202698052)
public UserProfile(long userId, String name, String avatar, String gender,
        String address) {
    this.userId = userId;
    this.name = name;
    this.avatar = avatar;
    this.gender = gender;
    this.address = address;
}
@Generated(hash = 968487393)
public UserProfile() {
}
public long getUserId() {
    return this.userId;
}
public void setUserId(long userId) {
    this.userId = userId;
}
public String getName() {
    return this.name;
}
public void setName(String name) {
    this.name = name;
}
public String getAvatar() {
    return this.avatar;
}
public void setAvatar(String avatar) {
    this.avatar = avatar;
}
public String getGender() {
    return this.gender;
}
public void setGender(String gender) {
    this.gender = gender;
}
public String getAddress() {
    return this.address;
}
public void setAddress(String address) {
    this.address = address;
}
}
