package com.qualwalk.backend.repository;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.typehandler.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface ComboRepository {

    @Insert(value = "INSERT INTO COMBOS_HD(COMBO_CODE, COMBO_DESC, NEXT_BATCH_DATE, PRICE, " +
            "IMAGE, IMAGE_NAME, IMAGE_SIZE, IMAGE_CONTENT_TYPE) " +
            "VALUES (#{comboCode}, #{comboDescription}, #{nextBatchDate}, #{price}, " +
            "#{image},#{imageName},#{imageSize},#{imageContentType})")
    Integer createCombo(CombosHeaderEntity combosHeaderEntity);

    @Results(id = "getCombo", value = {
            @Result(property = "comboCode", column = "comboCode"),
            @Result(property = "comboDescription", column = "comboDescription"),
            @Result(property = "nextBatchDate", column = "nextBatchDate"),
            @Result(property = "price", column = "price")
    })
    @Select(
        value = "SELECT COMBO_CODE AS comboCode" +
                ", COMBO_DESC AS comboDescription" +
                ", NEXT_BATCH_DATE AS nextBatchDate" +
                ", PRICE " +
                "FROM COMBOS_HD " +
                "WHERE COMBO_CODE = #{comboCode}"
    )
    CombosHeaderEntity getCombo(String comboCode);

    @Insert(
            value = "INSERT INTO COMBOS_DT(COMBO_CODE, COURSE) " +
                    "VALUES (#{comboCode}, #{course})"
    )
    Integer addCourse(@Param("comboCode") String comboCode,@Param("course") String course);

    @Results(
            id = "course",
            value = {
                    @Result(property = "course", column = "course", id = true),
                    @Result(property = "description", column = "description"),
                    @Result(property = "originalPrice", column = "ORIGINALPRICE"),
                    @Result(property = "finalPrice", column = "FINALPRICE"),
                    @Result(property = "discount", column = "DISCOUNT"),
                    @Result(property = "courseCategory", column = "COURSECATEGORY", typeHandler = EnumWithCustomOrdinalTypeHandler.class)
            }
    )
    @Select(value = {
            "SELECT " +
                    "COMBO.COURSE" +
                    ", COURSE.DESCRIPTION" +
                    ", COURSE.ORIGINAL_PRICE AS ORIGINALPRICE" +
                    ", COURSE.FINAL_PRICE AS FINALPRICE" +
                    ", COURSE.DISCOUNT" +
                    ", COURSE.COURSE_CATEGORY AS COURSECATEGORY " +
                    "FROM COMBOS_DT COMBO " +
                    "INNER JOIN COURSE COURSE ON COURSE.COURSE = COMBO.COURSE " +
                    "WHERE COMBO.COMBO_CODE = #{comboCode}"
    })
    List<CourseEntity> getCourses(@Param("comboCode") String comboCode);

    @Results(id = "getCombos", value = {
            @Result(property = "comboCode", column = "comboCode"),
            @Result(property = "comboDescription", column = "comboDescription"),
            @Result(property = "nextBatchDate", column = "nextBatchDate"),
            @Result(property = "price", column = "price"),
            @Result(property = "courses", column = "comboCode", many = @Many(select = "getComboCourses"))
    })
    @Select(value = {
            "SELECT COMBO_CODE AS comboCode" +
            ", COMBO_DESC AS comboDescription" +
            ", NEXT_BATCH_DATE AS nextBatchDate" +
            ", PRICE " +
            "FROM COMBOS_HD "
    })
    List<CombosHeaderEntity> getCombos();

    @Results(
            id = "comboDetail",
            value = {
                    @Result(property = "course", column = "course", id = true),
                    @Result(property = "description", column = "description"),
                    @Result(property = "originalPrice", column = "ORIGINALPRICE"),
                    @Result(property = "finalPrice", column = "FINALPRICE"),
                    @Result(property = "discount", column = "DISCOUNT"),
                    @Result(property = "courseCategory", column = "COURSECATEGORY", typeHandler = EnumWithCustomOrdinalTypeHandler.class)
            }
    )
    @Select(value = "SELECT " +
            "combo.combo_code "+
            ", course.COURSE" +
            ", course.DESCRIPTION" +
            ", course.ORIGINAL_PRICE AS ORIGINALPRICE" +
            ", course.FINAL_PRICE AS FINALPRICE" +
            ", course.DISCOUNT" +
            ", course.COURSE_CATEGORY AS COURSECATEGORY " +
            "FROM COMBOS_DT combo " +
            "INNER JOIN COURSE course ON course.course = combo.course " +
            "WHERE combo.combo_code = #{comboCode}")
    List<ComboDetailEntity> getComboCourses(@Param("comboCode") String comboCode);

    @Delete(value = {
            "DELETE FROM COMBOS_DT " +
                    "WHERE COMBO_CODE = #{comboCode} AND " +
                    "COURSE = #{course} "

    })
    Integer deleteCourse(@Param("comboCode") String comboCode,@Param("course") String course);

    @Update(value = {
            "update combos_hd " +
                    "set combo_desc = #{comboDescription}, " +
                    "next_batch_date = #{nextBatchDate}, " +
                    "price = #{price} " +
                    "where combo_code = #{comboCode}"
    })
    Integer modifyCombo(CombosHeaderEntity input);

}
