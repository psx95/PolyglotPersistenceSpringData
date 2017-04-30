/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spring.data.mongo.repository;

import java.io.Serializable;
import br.com.spring.data.mongo.model.UserFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Pranav
 */
public interface UserFeedbackRepository extends MongoRepository<UserFeedback, Long>{
    @Override
    public <S extends UserFeedback> S save(S entity);
}
