package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.Icaros.models.UserLover;

@Repository
public interface UserLoverRepository extends JpaRepository<UserLover,Long>  {

}
