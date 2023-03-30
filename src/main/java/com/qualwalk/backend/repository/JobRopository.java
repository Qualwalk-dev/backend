package com.qualwalk.backend.repository;

import com.qualwalk.backend.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface JobRopository {

    @Insert(value = "insert into jobs(ID, DESIGNATION, LOCATION, MIN_YEARS, MAX_YEARS, DESCRIPTION) " +
            "VALUES (#{id}, #{designation}, #{location}, #{minYears}, #{maxYears}, #{description})")
    @SelectKey(
            statement = "SELECT NEXTVAL('PK_JOBS')",
            before = true,
            keyColumn = "id",
            resultType=Integer.class,
            keyProperty = "id"
    )
    Integer createJob(JobsEntity input);

    @Results(
            id = "getAllJobs"
    )
    @Select(value = {
            "SELECT ID " +
                    ", DESIGNATION " +
                    ", LOCATION " +
                    ", MIN_YEARS AS MINYEARS" +
                    ", MAX_YEARS AS MAXYEARS" +
                    ",DESCRIPTION FROM JOBS "
    })
    List<JobsEntity> getAllJobs();

    @Update(value = {
            "UPDATE JOBS " +
                    "SET DESIGNATION = #{designation}, " +
                    "LOCATION = #{location}, " +
                    "MIN_YEARS = #{minYears}, " +
                    "MAX_YEARS = #{maxYears}, " +
                    "DESCRIPTION = #{description} " +
                    "WHERE ID = #{id}"
    })
    Integer updateJob(JobsEntity jobsEntity);

    @Delete(value = {
            "DELETE FROM JOBS " +
                    "WHERE ID = #{id}"
    })
    Integer deleteJob(Integer id);


}
