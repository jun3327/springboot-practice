package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.domain.Reservation;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DoctorForm {

    @NotEmpty(message = "필수입력")
    private String name;

    private int career;

}

