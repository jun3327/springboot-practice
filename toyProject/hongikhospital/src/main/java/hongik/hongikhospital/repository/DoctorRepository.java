package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {
    private final EntityManager em;

    public Long save(Doctor doctor) {
        em.persist(doctor);
        return doctor.getId();
    }

    public Long saveAndFlush(Doctor doctor) {
        em.persist(doctor);
        em.flush();

        return doctor.getId();
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }

    //특정 진료과에 해당하는 의사 목록 조회
    public List<Doctor> findAllByDepartmentId(Long departmentId) {
        return em.createQuery("select d from Doctor d where d.department.id= :departmentId", Doctor.class)
                .setParameter("departmentId", departmentId)
                .getResultList();
    }
}
