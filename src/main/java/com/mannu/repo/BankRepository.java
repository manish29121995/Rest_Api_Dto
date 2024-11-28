package com.mannu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mannu.entity.BankEntity;

public interface BankRepository extends JpaRepository<BankEntity, Integer> {

}
