package com.qualwalk.backend.repository;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.response.*;
import com.qualwalk.backend.typehandler.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.*;

import java.util.*;

@Mapper
public interface CourseCategoryRepository {

    @Results(id = "courseCategory", value = {
            @Result(property = "category", column = "CATEGORY", id = true, typeHandler = EnumWithCustomOrdinalTypeHandler.class),
            @Result(property = "description", column = "description")
    })
    @Select(value = "SELECT " +
            "CATEGORY," +
            "DESCRIPTION " +
            "FROM COURSE_CATEGORY")
    List<CourseCategoryEntity> findAll();

    @Results(id = "categoryAndCourse", value = {
            @Result(property = "category", column = "category"),
            @Result(property = "courses", javaType = List.class, many = @Many(select = "getCourses"), column = "COURSECATEGORY")
    })
    @Select(value = "SELECT " +
            "CC.CATEGORY AS COURSECATEGORY, "+
            "CC.DESCRIPTION as category " +
            "FROM course_category cc " )
    Set<CategoryAndCourse> findAllCourses();

    @Select(value = "SELECT " +
            "C.COURSE " +
            "from COURSE C " +
            "where c.course_category = #{category}")
    List<String> getCourses(String category);

}
