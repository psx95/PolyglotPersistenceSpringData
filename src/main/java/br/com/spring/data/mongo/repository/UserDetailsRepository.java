/**
 * 
 */
package br.com.spring.data.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.data.mongo.model.UserDetails;

/**
 * @author cad_rfirmino
 *
 */
@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, Long> {
        @Override
	public <S extends UserDetails> S save(S entity);
}
