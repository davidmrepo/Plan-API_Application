package in.skytel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.skytel.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
