package com.qualwalk.backend.controller;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.service.*;
import com.swantech.lang.core.domain.*;
import io.reactivex.rxjava3.schedulers.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.*;

import java.util.*;

@RestController
@RequestMapping("/jobs")
public class JobRestController {

    @Autowired
    JobService jobService;

    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping
    public DeferredResult<ResponseEntity<BaseResponse<JobsEntity>>> createJob(
            @RequestBody JobsEntity jobsEntity
    ) {
        DeferredResult<ResponseEntity<BaseResponse<JobsEntity>>> result = new DeferredResult<>();
        jobService.createJob(jobsEntity).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }


    @GetMapping
    public DeferredResult<ResponseEntity<BaseResponse<List<JobsEntity>>>> getAllJobs() {
        DeferredResult<ResponseEntity<BaseResponse<List<JobsEntity>>>> result = new DeferredResult<>();
        jobService.getAllJobs().subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping
    public DeferredResult<ResponseEntity<BaseResponse<JobsEntity>>> modifyJob(
            @RequestBody JobsEntity jobsEntity
    ) {
        DeferredResult<ResponseEntity<BaseResponse<JobsEntity>>> result = new DeferredResult<>();
        jobService.modifyJob(jobsEntity).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public DeferredResult<ResponseEntity<BaseResponse<Integer>>> deleteJob(
            @PathVariable Integer id
    ) {
        DeferredResult<ResponseEntity<BaseResponse<Integer>>> result = new DeferredResult<>();
        jobService.deleteJob(id).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PostMapping("/apply")
    public DeferredResult<ResponseEntity<BaseResponse<Integer>>> applyJob(
            @RequestBody JobApplicationEntity entity
    ) {
        DeferredResult<ResponseEntity<BaseResponse<Integer>>> result = new DeferredResult<>();
        jobService.createJobApplication(entity).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/applicants")
    public DeferredResult<ResponseEntity<BaseResponse<List<JobApplicantEntity>>>> getAllApplicants() {
        DeferredResult<ResponseEntity<BaseResponse<List<JobApplicantEntity>>>> result = new DeferredResult<>();
        jobService.getAllApplicants().subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/applicants/{id}")
    public DeferredResult<ResponseEntity<BaseResponse<Integer>>> deleteApplicants(
            @PathVariable Integer id
    ) {
        DeferredResult<ResponseEntity<BaseResponse<Integer>>> result = new DeferredResult<>();
        jobService.deleteJobApplication(id).subscribeOn(Schedulers.io())
                .subscribe(
                        res -> result.setResult(ResponseEntity.ok(res)),
                        ex -> result.setErrorResult(ex)
                );
        return result;
    }

}
