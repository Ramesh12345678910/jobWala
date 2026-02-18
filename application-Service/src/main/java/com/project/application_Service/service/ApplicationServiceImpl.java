package com.project.application_Service.service;


import com.project.application_Service.client.AuthClient;
import com.project.application_Service.client.JobClient;
import com.project.application_Service.client.NotificationFeignClient;
import com.project.application_Service.dto.*;
import com.project.application_Service.exception.ApplicationNotFoundException;import com.project.application_Service.exception.JobNotFoundException;import com.project.application_Service.exception.NotHavePrivilageException;import com.project.application_Service.exception.UserNotFoundException;import com.project.application_Service.model.ApplicationEntity;import com.project.application_Service.model.ApplicationStatus;import com.project.application_Service.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import java.time.LocalDate;import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {


    @Autowired
    private NotificationFeignClient notificationFeignClient;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private JobClient jobClient;

    @Override
    public ApplicationResponseDto applyForJob(
            Integer candidateId,
            Integer jobId) {


       UserDto user = authClient.getUserById(candidateId);
        if(user==null){
            throw new UserNotFoundException("userId is not found");
        }
        if (!user.getRole().equals("USER")) {
            throw new NotHavePrivilageException("Only candidates can apply");
        }


        JobDto job = jobClient.getJobById(jobId);
        if(job==null){
            throw new JobNotFoundException("JobId is not found");
        }

        ApplicationEntity application = new ApplicationEntity();
        application.setCandidateId(candidateId);
        application.setJobId(jobId);
        application.setApplicationStatus(ApplicationStatus.APPLIED);
        application.setAppliedDate(LocalDate.now());
        application.setEmployerId(job.getEmployerId());
        applicationRepository.save(application);
        NotificationDto notification=new NotificationDto();
        notification.setTo("palisettibhaskarrameshsai@gmail.com");
        notification.setSubject("Job Application");
        notification.setBody("successfully applied for the following job"+"\n"
        +"job title:"+job.getTitle()+"\n"+"description:"+job.getDescription());
        notificationFeignClient.sendEmail(notification);
        return mapToDto(application);
    }

    @Override
    public List<ApplicationResponseDto> viewApplicationsByJob(
            Integer employerId,
            Integer jobId) {


        UserDto user = authClient.getUserById(employerId);
        if(user==null){
            throw new UserNotFoundException("EmployerId is not found");
        }
        if (!user.getRole().equals("EMPLOYER")) {
            throw new NotHavePrivilageException("Access denied");
        }


        JobDto job = jobClient.getJobById(jobId);
        if(job==null){
            throw new JobNotFoundException("JobId is not found");
        }


        if (!job.getEmployerId().equals(employerId)) {
            throw new NotHavePrivilageException("Not your job");
        }

        List<ApplicationEntity> applications =
                applicationRepository.findByJobId(jobId);

        if (applications.isEmpty()) {
            throw new ApplicationNotFoundException("No applications found");
        }

        return applications.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ApplicationResponseDto updateApplicationStatus(
            Integer employerId,
            Integer applicationId,
            ApplicationRequestDto requestDto) {

        ApplicationEntity application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException("Application not found"));

        JobDto job = jobClient.getJobById(application.getJobId());
        if(job==null){
            throw new JobNotFoundException("JobId is not found");
        }



        if (!job.getEmployerId().equals(employerId)) {
            throw new NotHavePrivilageException("Access denied");
        }

        application.setApplicationStatus(requestDto.getApplicationStatus());
        applicationRepository.save(application);
        NotificationDto notification=new NotificationDto();
        notification.setTo("palisettibhaskarrameshsai@gmail.com");
        notification.setSubject("Application Update");
        notification.setBody("Your application status is updated:"+"\n"
                +"job title:"+job.getTitle()+"\n"+"description:"+job.getDescription()+
                "\n"+"Application Status:"+application.getApplicationStatus());
        notificationFeignClient.sendEmail(notification);

        return mapToDto(application);
    }

    private ApplicationResponseDto mapToDto(ApplicationEntity application) {
        ApplicationResponseDto dto = new ApplicationResponseDto();
        dto.setApplicationId(application.getApplicationId());
        dto.setJobId(application.getJobId());
        dto.setCandidateId(application.getCandidateId());
        dto.setStatus(application.getApplicationStatus());
        dto.setAppliedDate(application.getAppliedDate());
        dto.setEmployerId(application.getEmployerId());
        return dto;
    }
    @Override
    public List<ApplicationResponseDto> viewAllAppliedJobs(Integer candidateId) {
        return applicationRepository.findByCandidateId(candidateId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }
}
