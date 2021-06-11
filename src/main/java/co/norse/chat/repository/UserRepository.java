package co.norse.chat.repository;

import co.norse.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String name);
    User findByUsername(String username);

    User findByEmail(String email);

    void deleteById(long id);

}
