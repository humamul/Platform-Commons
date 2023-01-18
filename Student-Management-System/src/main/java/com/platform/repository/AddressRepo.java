package com.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.entity.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address	, Integer>{

}
