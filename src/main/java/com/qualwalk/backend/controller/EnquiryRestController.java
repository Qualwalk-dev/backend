package com.qualwalk.backend.controller;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.request.*;
import com.qualwalk.backend.response.*;
import com.qualwalk.backend.service.*;
import com.swantech.lang.core.domain.*;
import io.reactivex.rxjava3.schedulers.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.*;

import java.time.*;

@RestController
@RequestMapping("/enquiry")
public class EnquiryRestController {

    @Autowired
    EnquiryService enquiryService;

    @PostMapping
    public ResponseEntity postEnquiry(@RequestBody EnquiryEntity entity) {
        enquiryService.postEnquiry(entity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Resource> getEnquiries() {
        String filename = "enquiries_"+ LocalDateTime.now().toString() +".xlsx";
        InputStreamResource file = new InputStreamResource(enquiryService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @PostMapping("/search")
    public DeferredResult<ResponseEntity<BaseResponse<EnquirySearchResponse>>> searchEnquiry(
            @RequestBody SearchEnquiryRequest request
    ) {
        DeferredResult<ResponseEntity<BaseResponse<EnquirySearchResponse>>> result = new DeferredResult<>();
        enquiryService.searchCourse(request).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PostMapping("/corporate")
    public ResponseEntity postCorporateEnquiry(@RequestBody CorporateEnquiryEntity entity) {
        enquiryService.postCorporateEnquiry(entity);
        return ResponseEntity.ok().build();
    }


}
