package com.qualwalk.backend.controller;

import com.fasterxml.jackson.core.*;
import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.response.*;
import com.qualwalk.backend.service.*;
import com.swantech.lang.core.domain.*;
import io.reactivex.rxjava3.schedulers.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@RestController
                    @RequestMapping("/combos")
public class ComboRestController {

    @Autowired
    ComboService comboService;


    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> createCombo(
            @RequestPart("combo") String combo,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> result = new DeferredResult<>();
        comboService.createCombo(combo, file).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/{comboCode}/course/{courseName}")
    public DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> addCourse(
            @PathVariable("comboCode") String comboCode,
            @PathVariable("courseName") String courseName
    ) {
        DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> result = new DeferredResult<>();
        comboService.addCourse(comboCode, courseName).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{comboCode}/course/{courseName}")
    public DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> deleteCourse(
            @PathVariable("comboCode") String comboCode,
            @PathVariable("courseName") String courseName
    ) {
        DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> result = new DeferredResult<>();
        comboService.deleteCourse(comboCode, courseName).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @GetMapping
    public DeferredResult<ResponseEntity<BaseResponse<List<CombosHeaderEntity>>>> getCombos() {
        DeferredResult<ResponseEntity<BaseResponse<List<CombosHeaderEntity>>>> result = new DeferredResult<>();
        comboService.getCombos().subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PutMapping
    public DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> modifyCombo(
            @RequestBody List<CombosHeaderEntity> input
    ) {
        DeferredResult<ResponseEntity<BaseResponse<ComboResponse>>> result = new DeferredResult<>();
        comboService.modifyCombo(input).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

}
