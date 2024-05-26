package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.Events_UserLover;

@Repository
public interface Events_UserLoverRepository extends JpaRepository<Events_UserLover, Long>{

}
