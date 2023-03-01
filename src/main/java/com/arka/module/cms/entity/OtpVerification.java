package com.arka.module.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "otp_verification")
public class OtpVerification {
	 private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "OTP_ID")
		private long id;
		
		@Column(name = "OTP")
		private String otp;
		
		@Column(name = "EMAIL")
		private String email;
		
		@CreationTimestamp
		@Column(name = "OTP_GENERATED_TIME")
		private Date otpGeneratedTime;
		
		@Column(name = "FLAG")
		private int flag;
		
		
		public boolean isOTPRequired() {
		        
		         
		        long currentTimeInMillis = System.currentTimeMillis();
		        long otpRequestedTimeInMillis = this.otpGeneratedTime.getTime();
		         
		        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
		            // OTP expires
		            return false;
		        }
		         
		        return true;
		    }
	

}
