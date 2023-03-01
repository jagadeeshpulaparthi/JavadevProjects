package com.arka.module.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.module.cms.entity.OtpVerification;

public interface OtpVerificationRepo extends JpaRepository<OtpVerification, Long>{
	OtpVerification findByEmailAndOtp(String email,String otp);

}
