package com.qualwalk.backend.repository;

import com.qualwalk.backend.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface JobApplicationRepository {
    @Insert(value = "insert into JOB_APPLICATIONS(ID, EMAIL, PHONE_NUMBER, JOB_ID) " +
            "VALUES (#{id}, #{email}, #{phoneNumber}, #{jobId})")
    @SelectKey(
            statement = "SELECT NEXTVAL('PK_JOBS_APPLICATIONS')",
            before = true,
            keyColumn = "id",
            resultType=Integer.class,
            keyProperty = "id"
    )
    Integer createJobApplication(JobApplicationEntity input);

    @Results(
            id = "getAllApplicants"
    )
    @Select(value = {
            "select a.id, a.email, a.PHONE_NUMBER as phoneNumber, j.designation, j.description " +
                    "from JOB_APPLICATIONS a " +
                    "inner join jobs j on j.id = a.JOB_ID"
    })
    List<JobApplicantEntity> getAllApplicants();

    @Delete(value = {
            "DELETE FROM JOB_APPLICATIONS " +
                    "WHERE ID = #{id}"
    })
    Integer deleteJobApplicant(Integer id);
}
