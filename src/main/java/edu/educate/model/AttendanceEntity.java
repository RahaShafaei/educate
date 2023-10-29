package edu.educate.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@jakarta.persistence.Table(name = "attendance", schema = "dbo", catalog = "educate")
public class AttendanceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "attendance_id", nullable = false)
    private int attendanceId;

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    @Basic
    @Column(name = "org_unit_post_person_id", nullable = true)
    private Integer orgUnitPostPersonId;

    public Integer getOrgUnitPostPersonId() {
        return orgUnitPostPersonId;
    }

    public void setOrgUnitPostPersonId(Integer orgUnitPostPersonId) {
        this.orgUnitPostPersonId = orgUnitPostPersonId;
    }

    @Basic
    @Column(name = "plan_id", nullable = true)
    private Integer planId;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "element_id", nullable = true)
    private Integer elementId;

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    @Basic
    @Column(name = "grade", nullable = true, precision = 0)
    private Double grade;

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "deleted", nullable = true, length = 2)
    private String deleted;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "inserted_at", nullable = true)
    private Date insertedAt;

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendanceEntity that = (AttendanceEntity) o;

        if (attendanceId != that.attendanceId) return false;
        if (orgUnitPostPersonId != null ? !orgUnitPostPersonId.equals(that.orgUnitPostPersonId) : that.orgUnitPostPersonId != null)
            return false;
        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        if (elementId != null ? !elementId.equals(that.elementId) : that.elementId != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (insertedAt != null ? !insertedAt.equals(that.insertedAt) : that.insertedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attendanceId;
        result = 31 * result + (orgUnitPostPersonId != null ? orgUnitPostPersonId.hashCode() : 0);
        result = 31 * result + (planId != null ? planId.hashCode() : 0);
        result = 31 * result + (elementId != null ? elementId.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        return result;
    }
}
