package in.skytel.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.skytel.constants.AppConstants;
import in.skytel.entity.Plan;
import in.skytel.props.AppProperties;
import in.skytel.service.PlanService;

@RestController
public class PlanRestController {

	@Autowired
	PlanService planService;

	private Map<String, String> messages;
	
	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService = planService;
		this.messages = appProps.getMessages();
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planService.planCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/createPlan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = "";
		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/allplans")
	public ResponseEntity<List<Plan>> Allplans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@PutMapping("/updateplan")
	public ResponseEntity<String> updatePlans(@RequestBody Plan plan) {
		String msg = "";
		boolean updatePlan = planService.updatePlan(plan);
		if (updatePlan) {
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<>(planById, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		String msg = "";
		boolean deletePlanById = planService.deletePlanById(planId);
		if (deletePlanById) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statuschange(Integer planId, String status) {
		String msg = "";
		boolean isStatusChange = planService.activeSw(planId, status);
		if (isStatusChange) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		} else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}