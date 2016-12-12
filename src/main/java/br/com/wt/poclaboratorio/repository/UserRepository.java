package br.com.wt.poclaboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.wt.poclaboratorio.modelo.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long>{
	@Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password=?2")
	User findByusernameandpassword(String username,String password);
	User findByusername(String username);
}
