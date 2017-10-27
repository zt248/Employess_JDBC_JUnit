package entity;

public class EmplDepa {

    private Long employeeId;
    private Long departmentId;


    public EmplDepa() {

    }


    public EmplDepa(Long employeeId, Long departmentId) {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }



    @Override
    public String toString() {
        return "EmplDepaDao{" +
                "employeeId=" + employeeId +
                ", departmentId=" + departmentId +
                '}';
    }
}
