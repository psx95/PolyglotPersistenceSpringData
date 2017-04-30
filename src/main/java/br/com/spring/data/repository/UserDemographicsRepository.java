/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spring.data.repository;

import br.com.spring.data.model.UserDemographics;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pranav
 */
public interface UserDemographicsRepository extends CrudRepository<UserDemographics, Long>{
    
}
