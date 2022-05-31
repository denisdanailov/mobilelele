package mobilelele.example.mobilelele.repository;

import mobilelele.example.mobilelele.model.entity.UserRole;
import mobilelele.example.mobilelele.model.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(RoleEnum role);
}
