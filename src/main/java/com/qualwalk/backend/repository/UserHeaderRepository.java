package com.qualwalk.backend.repository;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.request.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface UserHeaderRepository {
    @Insert(value = "INSERT INTO USER_HD " +
            "(EMAIL) " +
            "VALUES " +
            "(#{email})")
    Integer createUser(UserData userData);

    @Insert(value = "INSERT INTO USER_DET " +
            "(EMAIL, USERNAME, FIRSTNAME, LASTNAME) " +
            "VALUES " +
            "(#{email}, #{username}, #{firstname}, #{lastname})")
    Integer addUserDetails(UserData userData);

    @Results(value = {
            @Result(column = "EMAIL", property = "email"),
            @Result(column = "USERNAME", property = "username")
    })
    @Select(value = "SELECT EMAIL, USERNAME " +
            "FROM USER_DET " +
            "WHERE USERNAME = #{username} ")
    Optional<UserDetailEntity> findUsername(@Param("username") String username);

    @Update(value = "UPDATE USER_HD " +
            "SET IS_VERIFIED = 'Y' " +
            "WHERE EMAIL = #{email}")
    Integer verifyUser(@Param("email") String email);

}
