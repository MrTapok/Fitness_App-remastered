package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.entities.WorkoutType;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutTypeRepository extends JpaRepository<WorkoutType, Integer> {
}
