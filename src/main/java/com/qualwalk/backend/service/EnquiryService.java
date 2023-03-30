package com.qualwalk.backend.service;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.repository.*;
import com.qualwalk.backend.request.*;
import com.qualwalk.backend.response.*;
import com.qualwalk.backend.utility.*;
import com.swantech.lang.core.domain.*;
import com.swantech.lang.core.utility.*;
import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class EnquiryService {

    @Autowired
    EnquiryRepository enquiryRepository;

    public void postEnquiry(EnquiryEntity entity) {

        enquiryRepository.postEnquiry(entity);

    }

    public void postCorporateEnquiry(CorporateEnquiryEntity entity) {
        enquiryRepository.postCorporateEnquiry(entity);
    }

    public ByteArrayInputStream load() {
        List<EnquiryEntity> tutorials = enquiryRepository.getDataForDownload();

        ByteArrayInputStream in = ExcelUtility.tutorialsToExcel(tutorials);
        return in;
    }

    public Observable<BaseResponse<EnquirySearchResponse>> searchCourse(SearchEnquiryRequest request) {
        EnquirySearchResponse response = new EnquirySearchResponse(
                enquiryRepository.searchEnquiry(
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
