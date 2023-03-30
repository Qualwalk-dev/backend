package com.qualwalk.backend.service;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.repository.*;
import com.swantech.lang.core.domain.*;
import com.swantech.lang.core.utility.*;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class JobService {

    @Autowired
    JobRopository jobRopository;

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public Observable<BaseResponse<JobsEntity>> createJob(JobsEntity jobsEntity) {
        jobRopository.createJob(jobsEntity);
        return Observable.just(ResponseUtility.createBaseResponse(jobsEntity));
    }

    public Observable<BaseResponse<List<JobsEntity>>> getAllJobs() {
        return Observable.just(ResponseUtility.createBaseResponse(
                jobRopository.getAllJobs()
        ));
    }

    public Observable<BaseResponse<JobsEntity>> modifyJob(JobsEntity jobsEntity) {
        jobRopository.updateJob(jobsEntity);
        return Observable.just(ResponseUtility.createBaseResponse(jobsEntity));
    }

    public Observable<BaseResponse<Integer>> deleteJob(Integer id) {
        return Observable.just(ResponseUtility.createBaseResponse(jobRopository.deleteJob(id)));
    }

    public Observable<BaseResponse<Integer>> createJobApplication(JobApplicationEntity jobApplicationEntity) {
        return Observable.just(ResponseUtility.createBaseResponse(jobApplicationRepository.createJobApplication(jobApplicationEntity)));
    }

    public Observable<BaseResponse<List<JobApplicantEntity>>> getAllApplicants() {
        return Observable.just(ResponseUtility.createBaseResponse(jobApplicationRepository.getAllApplicants()));
    }

    public Observable<BaseResponse<Integer>> deleteJobApplication(Integer id) {
        return Observable.just(ResponseUtility.createBaseResponse(jobApplicationRepository.deleteJobApplicant(id)));
    }

}
