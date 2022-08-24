package in.skytel.service;

import java.util.List;
import java.util.Map;

import in.skytel.entity.Plan;

public interface PlanService {

	public Map<Integer, String> planCategories();

	public List<Plan> getAllPlans();

	public boolean savePlan(Plan plan);

	public Plan getPlanById(Integer planId);

	public boolean updatePlan(Plan plan);

	public boolean deletePlanById(Integer planId);

	public boolean activeSw(Integer planId, String status);

}
