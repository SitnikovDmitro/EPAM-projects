package com.ra.model.repository;

import com.ra.model.entity.Manager;
import com.ra.model.entity.Master;
import org.springframework.data.repository.CrudRepository;


public interface MasterRepository  extends CrudRepository<Master, String> {

}
