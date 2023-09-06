package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public String save(Department department) {
        em.persist(department);
        return department.getName();
    }

    //특정 병원의 특정 진료과 조회, 예외 처리 필요
    public Department findOneByDepartmentIdAndHospitalId(Long departmentId, Long hospitalId) {
        return em.createQuery("select d from Department d where d.id = :departmentId and d.hospital.id = :hospitalId", Department.class)
                .setParameter("departmentId", departmentId)
                .setParameter("hospitalId", hospitalId)
                .getSingleResult();
    }

    //특정 병원의 진료과 모두 조회
    public List<Department> findAllByHospitalId(Long hospitalId) {
        return em.createQuery("select d from Department d where d.hospital.id = :hospitalId", Department.class)
                .setParameter("hospitalId", hospitalId)
                .getResultList();
    }

    //전화번호로 조회, 예외 처리 필요
    public Department findOneByPhoneNumber(Long number) {
        return em.createQuery("select d from Department d where d.phoneNumber = :number", Department.class)
                .setParameter("number", number)
                .getSingleResult();
    }

    // 병원 ID와 전화번호로 진료과 조회
    public List<Department> findByHospitalIdAndPhoneNumber(Long hospitalId, int phoneNumber) {
        return em.createQuery("select d from Department d where d.hospital.id = :hospitalId and d.phoneNumber = :phoneNumber", Department.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }
}