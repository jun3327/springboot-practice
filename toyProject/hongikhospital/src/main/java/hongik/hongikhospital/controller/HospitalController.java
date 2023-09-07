package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Address;
import hongik.hongikhospital.repository.HospitalRepository;
import hongik.hongikhospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalRepository hospitalRepository;
    private final HospitalService hospitalService;

    @GetMapping("/hospitals/new")
    public String createHospitalForm(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());
        return "/hospitals/createHospitalForm";
    }

    @PostMapping("/hospitals/new")
    public String create(@Valid HospitalForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "hospitals/createHospitalForm";
        }

        hospitalService.createOne(form.getName(), form.getCity(),
                form.getStreet(), form.getZipcode());

        return "redirect:/";

    }
}