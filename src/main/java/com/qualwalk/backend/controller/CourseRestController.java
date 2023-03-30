package com.qualwalk.backend.controller;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.request.*;
import com.qualwalk.backend.response.*;
import com.qualwalk.backend.service.*;
import com.swantech.lang.core.domain.*;
import io.reactivex.rxjava3.schedulers.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    CourseService courseService;

    @Autowired
    ImageService imageService;

    @GetMapping("/categories")
    public DeferredResult<ResponseEntity<BaseResponse<Set<CategoryAndCourse>>>> getCourseCategories() {
        DeferredResult<ResponseEntity<BaseResponse<Set<CategoryAndCourse>>>> result = new DeferredResult<>();
        courseService.getCourseCategories().subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DeferredResult<ResponseEntity<BaseResponse<CourseEntity>>> createCourse(
            @RequestPart("course") CourseEntity course,
            @RequestPart("file") MultipartFile file
    ) throws IOException, ParseException {
        DeferredResult<ResponseEntity<BaseResponse<CourseEntity>>> result = new DeferredResult<>();
        courseService.createCourse(course, file).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public DeferredResult<ResponseEntity<BaseResponse<CourseEntity>>> modifyCourse(
            @RequestPart("course") String course,
            @RequestPart("file") MultipartFile file
    ) throws IOException, ParseException {
        DeferredResult<ResponseEntity<BaseResponse<CourseEntity>>> result = new DeferredResult<>();
        courseService.modifyCourse(course, file).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PostMapping("/search")
    public DeferredResult<ResponseEntity<BaseResponse<CourseSearchResponse>>> searchCourse(
            @RequestBody SearchCourseRequest request
    ) {
        DeferredResult<ResponseEntity<BaseResponse<CourseSearchResponse>>> result = new DeferredResult<>();
        courseService.searchCourse(request).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @GetMapping("/image/{course}")
    public DeferredResult<ResponseEntity<BaseResponse<ImageEntity>>> getImage(
            @PathVariable String course
    ) {
        DeferredResult<ResponseEntity<BaseResponse<ImageEntity>>> result = new DeferredResult<>();
        imageService.getCourseImage(course).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

}
