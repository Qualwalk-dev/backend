package com.qualwalk.backend.service;

import com.fasterxml.jackson.databind.*;
import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.repository.*;
import com.qualwalk.backend.request.*;
import com.qualwalk.backend.response.*;
import com.swantech.lang.core.domain.*;
import com.swantech.lang.core.utility.*;
import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.math.*;
import java.util.*;

@Slf4j
@Service
public class CourseService {

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Autowired
    CourseRepository courseRepository;

    public Observable<BaseResponse<Set<CategoryAndCourse>>> getCourseCategories() {
        return Observable.just(
                ResponseUtility.createBaseResponse(
                        courseCategoryRepository.findAllCourses()
                )
        );
    }

    public Observable<BaseResponse<CourseEntity>> createCourse(CourseEntity courseEntity, MultipartFile file) throws IOException, ParseException {
        courseEntity.setImageName(StringUtils.cleanPath(file.getOriginalFilename()));
        courseEntity.setImage(file.getBytes());
        courseEntity.setImageSize(BigDecimal.valueOf(file.getSize()));
        courseEntity.setImageContentType(file.getContentType());

        courseRepository.createCourse(courseEntity);
        return Observable.just(ResponseUtility.createBaseResponse(courseRepository.getCourse(courseEntity.getCourse())));
    }

    public Observable<BaseResponse<CourseEntity>> modifyCourse(String course, MultipartFile file) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        CourseEntity courseEntity = objectMapper.readValue(course, CourseEntity.class);
        courseEntity.setImageName(StringUtils.cleanPath(file.getOriginalFilename()));
        courseEntity.setImage(file.getBytes());
        courseEntity.setImageSize(BigDecimal.valueOf(file.getSize()));
        courseEntity.setImageContentType(file.getContentType());
        courseRepository.modifyCourse(courseEntity);
        return Observable.just(ResponseUtility.createBaseResponse(courseRepository.getCourse(courseEntity.getCourse())));
    }

    public Observable<BaseResponse<CourseSearchResponse>> searchCourse(SearchCourseRequest request) {
        CourseSearchResponse response = new CourseSearchResponse(
                courseRepository.searchCourse(
                        request.getCriteria(),
                        request.getStartRecord(),
                        request.getEndRecord()
                )
        , request.getEndRecord() - request.getStartRecord() );
        return Observable.just(
                ResponseUtility.createBaseResponse(
                        response
                )
        );
    }

}
