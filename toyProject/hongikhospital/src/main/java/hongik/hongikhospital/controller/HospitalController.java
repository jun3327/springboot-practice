package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Address;
import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.repository.DepartmentRepository;
import hongik.hongikhospital.repository.HospitalRepository;
import hongik.hongikhospital.service.HospitalDto;
import hongik.hongikhospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/hospitals/new")
    public String createHospitalForm(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());
        return "hospitals/createHospitalForm";
    }

    @PostMapping("/hospitals/new")
    public String create(@Valid HospitalForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "hospitals/createHospitalForm";
        }

        hospitalService.createOne(form.getName(), form.getCity(),
                form.getStreet());

        return "redirect:/";
    }

    @GetMapping("/hospitals")
    public String hospitals(Model model) {
        List<HospitalDto> hospitalDtos = hospitalService.findAll();
        model.addAttribute("hospitals", hospitalDtos);

        return "hospitals/hospitalsList";
    }
}
