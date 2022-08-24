package in.skytel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.skytel.entity.Plan;
import in.skytel.repository.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepo planrepo;

	@Override
	public Map<Integer, String> planCategories() {

		List<Plan> categories = planrepo.findAll();
		Map<Integer, String> Categorymap = new HashMap<>();

		for (Plan category : categories) {
			Categorymap.put(category.getPlanId(), category.getPlanName());
		}

		return Categorymap;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planrepo.findAll();
	}

	@Override
	public boolean savePlan(Plan plan) {
		boolean status = false;
		Plan isSaved = planrepo.save(plan);
		if (isSaved != null) {
			status = true;
		}
		return status;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> isfindById = planrepo.findById(planId);
		if (isfindById.isPresent()) {
			return isfindById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		planrepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planrepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean activeSw(Integer planId, String status) {
		Optional<Plan> isfindById = planrepo.findById(planId);
		if(isfindById.isPresent()) {
		 Plan plan = isfindById.get();
		 plan.setActiveSw(status);
		 planrepo.save(plan);
		 return true;
		}
		return false;
	}

}
