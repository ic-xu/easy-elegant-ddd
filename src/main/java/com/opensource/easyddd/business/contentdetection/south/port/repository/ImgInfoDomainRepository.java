package com.opensource.easyddd.business.contentdetection.south.port.repository;

import com.opensource.easyddd.business.contentdetection.domain.ImgInfoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author chenxu
 */
@Repository
public interface ImgInfoDomainRepository extends JpaRepository<ImgInfoDomain, Long> {




}
