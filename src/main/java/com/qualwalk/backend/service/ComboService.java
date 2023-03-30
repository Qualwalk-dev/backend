package com.qualwalk.backend.service;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.repository.*;
import com.qualwalk.backend.response.*;
import com.swantech.lang.core.domain.*;
import com.swantech.lang.core.utility.*;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.math.*;
import java.util.*;

@Service
public class ComboService {

    @Autowired
    ComboRepository comboRepository;

    public Observable<BaseResponse<ComboResponse>> createCombo(String combo, MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CombosHeaderEntity combosHeaderEntity = objectMapper.readValue(combo, CombosHeaderEntity.class);
        combosHeaderEntity.setImageName(StringUtils.cleanPath(file.getOriginalFilename()));
        combosHeaderEntity.setImage(file.getBytes());
        combosHeaderEntity.setImageSize(BigDecimal.valueOf(file.getSize()));
        combosHeaderEntity.setImageContentType(file.getContentType());
        comboRepository.createCombo(combosHeaderEntity);
        return Observable.just(ResponseUtility.createBaseResponse(
                new ComboResponse(
                        comboRepository.getCombo(combosHeaderEntity.getComboCode()),
                        new ArrayList<>()
                ))
        );
    }

    public Observable<BaseResponse<ComboResponse>> addCourse(String comboCode, String courseName) {
        comboRepository.addCourse(comboCode, courseName);
        return Observable.just(ResponseUtility.createBaseResponse(
                new ComboResponse(
                        comboRepository.getCombo(comboCode),
                        comboRepository.getCourses(comboCode)
                ))
        );
    }

    public Observable<BaseResponse<ComboResponse>> deleteCourse(String comboCode, String courseName) {
        comboRepository.deleteCourse(comboCode, courseName);
        return Observable.just(ResponseUtility.createBaseResponse(
                new ComboResponse(
                        comboRepository.getCombo(comboCode),
                        comboRepository.getCourses(comboCode)
                ))
        );
    }

    public Observable<BaseResponse<List<CombosHeaderEntity>>> getCombos() {
        return Observable.just(
                ResponseUtility.createBaseResponse(
                        comboRepository.getCombos()
                )
        );
    }

    public Observable<BaseResponse<ComboResponse>> modifyCombo(List<CombosHeaderEntity> input) {
        input.forEach(e -> comboRepository.modifyCombo(e));
        return Observable.just(
                ResponseUtility.createBaseResponse(
                       null
                )
        );
    }

}
