package com.qualwalk.backend.repository;

import com.qualwalk.backend.criteria.*;
import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.typehandler.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface EnquiryRepository {

    @Insert(value = "INSERT INTO ENQUIRY " +
            "(ID, EMAIL, FIRSTNAME, LASTNAME, LEARNING_MODE, MESSAGE) " +
            "VALUES " +
            "(#{id},#{email}, #{firstname}, #{lastname}, " +
            "#{learningModes.ordinal}, #{message}) ")
    @SelectKey(
            statement = "SELECT NEXTVAL('PK_ENQUIRY')",
            before = true,
            keyColumn = "id",
            resultType=Integer.class,
            keyProperty = "id"
    )
    Integer postEnquiry(EnquiryEntity entity);

    @Results(id = "courseCategory", value = {
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "firstname", column = "FIRSTNAME"),
            @Result(property = "lastname", column = "LASTNAME"),
            @Result(property = "learningModes", column = "LEARNINGMODE", typeHandler = EnumWithCustomOrdinalTypeHandler.class),
            @Result(property = "message", column = "MESSAGE")
    })
    @Select(value = "SELECT " +
            "E.EMAIL" +
            ", E.FIRSTNAME" +
            ", E.LASTNAME" +
            ", E.LEARNING_MODE AS LEARNINGMODE" +
            ", E.MESSAGE " +
            "FROM ENQUIRY E " +
            "WHERE CREATED_ON BETWEEN " +
            "(SELECT ENQUIRY_LAST_DOWNLOADED FROM ENQUIRY_LAST_DOWNLOADED ) AND NOW()")
    List<EnquiryEntity> getDataForDownload();

    @Results(id = "enquiries", value = {
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "firstname", column = "FIRSTNAME"),
            @Result(property = "lastname", column = "LASTNAME"),
            @Result(property = "learningModes", column = "LEARNINGMODE", typeHandler = EnumWithCustomOrdinalTypeHandler.class),
            @Result(property = "message", column = "MESSAGE"),
            @Result(property = "createdOn", column = "CREATEDON"),
            @Result(property = "totalRecordsCount", column = "TOTALRECORDCOUNT")

    })
    @Select({
            "<script>" +
            "    SELECT" +
            "        E.EMAIL, " +
            "        E.FIRSTNAME, " +
            "        E.LASTNAME, " +
            "        E.LEARNING_MODE AS LEARNINGMODE, " +
            "        E.MESSAGE, " +
            "        E.CREATED_ON AS CREATEDON, " +
            "        COUNT(EMAIL) OVER() AS TOTALRECORDCOUNT"+
            "    FROM ENQUIRY E " +
            "    <if test='params.searchString!=null'>" +
            "        WHERE UPPER(E.EMAIL) LIKE UPPER('%'||#{params.searchString}||'%') " +
            "    </if>" +
            "    ORDER BY " +
            "    <choose> " +
            "        <when test='params.sortBy == @com.qualwalk.backend.enumeration.EnquirySortBy@CREATED_ON'>" +
            "            CREATED_ON" +
            "        </when>" +
            "    </choose>" +
            "    <choose>" +
            "        <when test='params.sortDirection == @com.swantech.lang.core.domain.SortDirection@ASC'>" +
            "            ASC" +
            "        </when>" +
            "        <otherwise>" +
            "            DESC" +
            "        </otherwise>" +
            "    </choose>" +
                "<choose> "+
                "    <when test='startRecord!=1'>" +
                "        LIMIT (#{endRecord} - #{startRecord}) + 1" +
                "    </when>" +
                "    <otherwise>" +
                "        LIMIT #{endRecord}" +
                "    </otherwise>" +
                "</choose>"+
            "</script>"
    })
    List<EnquiryEntity> searchEnquiry(
            @Param("params") SearchEnquiryCriteria params,
            @Param("startRecord") int startRecord,
            @Param("endRecord") int endRecord
    );


    @Insert(value = "INSERT INTO ENQUIRY " +
            "(ID, EMAIL, FIRSTNAME, LASTNAME, LEARNING_MODE, MESSAGE, CORPORATE, COMPANY, PHONE, JOB_TITLE) " +
            "VALUES " +
            "(#{id},#{email}, #{firstname}, #{lastname}, " +
            "#{learningModes.ordinal}, #{message}, #{isCorporate}, #{company}, #{phone}, #{jobTitle}) ")
    @SelectKey(
            statement = "SELECT NEXTVAL('PK_ENQUIRY')",
            before = true,
            keyColumn = "id",
            resultType=Integer.class,
            keyProperty = "id"
    )
    Integer postCorporateEnquiry(CorporateEnquiryEntity entity);

}
