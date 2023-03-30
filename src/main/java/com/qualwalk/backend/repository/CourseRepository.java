package com.qualwalk.backend.repository;

import com.qualwalk.backend.criteria.*;
import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.typehandler.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface CourseRepository {

    @Insert(value = "INSERT INTO COURSE " +
            "(COURSE, DESCRIPTION, ORIGINAL_PRICE, FINAL_PRICE, DISCOUNT,COURSE_CATEGORY, next_batch_date, image, IMAGE_NAME, IMAGE_SIZE, IMAGE_CONTENT_TYPE) " +
            "VALUES " +
            "(#{course},#{description},#{originalPrice},#{finalPrice},#{discount},#{courseCategory.ordinal}, #{nextBatchDate}, #{image}, #{imageName}, #{imageSize}, #{imageContentType})")
    Integer createCourse(CourseEntity entity);

    @Results(
            id = "course",
            value = {
                    @Result(property = "course", column = "course", id = true),
                    @Result(property = "description", column = "description"),
                    @Result(property = "originalPrice", column = "ORIGINALPRICE"),
                    @Result(property = "finalPrice", column = "FINALPRICE"),
                    @Result(property = "discount", column = "DISCOUNT"),
                    @Result(property = "nextBatchDate", column = "NEXTBATCHDATE"),
                    @Result(property = "courseCategory", column = "COURSECATEGORY", typeHandler = EnumWithCustomOrdinalTypeHandler.class)
            }
    )
    @Select(value = "SELECT " +
            "COURSE" +
            ", DESCRIPTION" +
            ", ORIGINAL_PRICE AS ORIGINALPRICE" +
            ", FINAL_PRICE AS FINALPRICE" +
            ", DISCOUNT" +
            ", COURSE_CATEGORY AS COURSECATEGORY " +
            ", NEXT_BATCH_DATE AS NEXTBATCHDATE " +
            "FROM COURSE WHERE COURSE = #{course}")
    CourseEntity getCourse(@Param("course") String course);

    @Update(value = "UPDATE COURSE " +
            "SET DESCRIPTION = #{description}," +
            "ORIGINAL_PRICE = #{originalPrice}," +
            "FINAL_PRICE = #{finalPrice}," +
            "DISCOUNT = #{discount}, " +
            "NEXT_BATCH_DATE = #{nextBatchDate}, " +
            "IMAGE = #{image}, " +
            "IMAGE_NAME = #{imageName}, " +
            "IMAGE_SIZE = #{imageSize}, " +
            "IMAGE_CONTENT_TYPE = #{imageContentType} "+
            "WHERE COURSE = #{course}")
    Integer modifyCourse(CourseEntity courseEntity);

    @Results(
            id = "courses",
            value = {
                    @Result(property = "course", column = "course", id = true),
                    @Result(property = "description", column = "description"),
                    @Result(property = "originalPrice", column = "ORIGINALPRICE"),
                    @Result(property = "finalPrice", column = "FINALPRICE"),
                    @Result(property = "discount", column = "DISCOUNT"),
                    @Result(property = "nextBatchDate", column = "NEXTBATCHDATE"),
                    @Result(property = "courseCategory", column = "COURSECATEGORY", typeHandler = EnumWithCustomOrdinalTypeHandler.class),
                    @Result(property = "totalRecordsCount", column = "TOTALRECORDCOUNT")
            }
    )
    @Select({
            "<script>" +
            "    SELECT " +
            "        COURSE" +
            "        , DESCRIPTION" +
            "        , ORIGINAL_PRICE AS ORIGINALPRICE" +
            "        , FINAL_PRICE AS FINALPRICE" +
            "        , DISCOUNT" +
            "        , COURSE_CATEGORY AS COURSECATEGORY" +
                    ", NEXT_BATCH_DATE AS NEXTBATCHDATE"+
            "        , COUNT(COURSE) OVER() AS TOTALRECORDCOUNT" +
            "        FROM COURSE " +
            "        <if test='params.searchString!=null'>" +
            "            WHERE UPPER(COURSE) LIKE UPPER('%'||#{params.searchString}||'%')" +
            "        </if>" +
            "        ORDER BY" +
            "        <choose>" +
            "            <when test='params.sortBy == @com.qualwalk.backend.enumeration.CourseSortBy@ORIGINAL_PRICE'>" +
            "                ORIGINAL_PRICE" +
            "            </when>" +
            "        </choose>" +
            "        <choose>" +
            "            <when test='params.sortBy == @com.qualwalk.backend.enumeration.CourseSortBy@FINAL_PRICE'>" +
            "                FINAL_PRICE" +
            "            </when>" +
            "        </choose>" +
            "        <choose>" +
            "            <when test='params.sortBy == @com.qualwalk.backend.enumeration.CourseSortBy@DISCOUNT'>" +
            "                DISCOUNT" +
            "            </when>" +
            "        </choose>" +
            "        <choose>" +
            "            <when test='params.sortDirection == @com.swantech.lang.core.domain.SortDirection@ASC'>" +
            "                ASC" +
            "            </when>" +
            "            <otherwise>" +
            "                DESC" +
            "            </otherwise>" +
            "        </choose>" +
            "        <when test='startRecord!=1'>" +
            "            LIMIT (#{endRecord} - #{startRecord}) + 1" +
            "        </when>" +
            "        <otherwise>" +
            "            LIMIT #{endRecord}" +
            "        </otherwise>" +
            "</script>"
    })
    List<CourseEntity> searchCourse(
            @Param("params") SearchCourseCriteria params,
            @Param("startRecord") int startRecord,
            @Param("endRecord") int endRecord
    );


    @Select(value = "SELECT IMAGE, IMAGE_NAME AS IMAGENAME, IMAGE_SIZE AS IMAGESIZE, IMAGE_CONTENT_TYPE AS IMAGECONTENTTYPE " +
            "FROM COURSE C WHERE C.COURSE=#{course} ")
    ImageEntity getImage(String course);

}
