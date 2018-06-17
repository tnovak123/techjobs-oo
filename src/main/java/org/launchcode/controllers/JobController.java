package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        model.addAttribute("job", jobData.findById(id));

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        String aName = new String();
        String aEmployer = new String();
        String aLocation = new String();
        String aSkill = new String();
        String aPosition = new String();

        model.addAttribute("name", aName);
        model.addAttribute("employer.value", aEmployer);
        model.addAttribute("location.value", aLocation);
        model.addAttribute("coreCompetency.value", aSkill);
        model.addAttribute("positionType.value", aPosition);

        Employer bEmployer = new Employer(aEmployer);
        Location bLocation = new Location(aLocation);
        PositionType bPosition = new PositionType(aPosition);
        CoreCompetency bSkill = new CoreCompetency(aSkill);

        Job newJob = new Job(aName, bEmployer, bLocation, bPosition, bSkill);

        jobData.add(newJob);

        model.addAttribute("job", newJob);

        return "redirect:";

    }
}
