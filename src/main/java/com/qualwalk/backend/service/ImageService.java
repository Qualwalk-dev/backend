package com.qualwalk.backend.service;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.repository.*;
import com.swantech.lang.core.domain.*;
import com.swantech.lang.core.utility.*;
import io.reactivex.rxjava3.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ImageService {

    @Autowired
    CourseRepository courseRepository;

    public Observable<BaseResponse<ImageEntity>> getCourseImage(String course) {
        return Observable.just(
                ResponseUtility.createBaseResponse(
                        this.courseRepository.getImage(course)
                )
        );
    }

}
