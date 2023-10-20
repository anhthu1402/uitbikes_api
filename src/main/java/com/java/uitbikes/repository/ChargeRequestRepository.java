package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.ChargeRequest;

public interface ChargeRequestRepository extends JpaRepository<ChargeRequest, Long> {

}
