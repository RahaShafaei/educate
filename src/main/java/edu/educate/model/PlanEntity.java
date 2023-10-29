package edu.educate.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "plan", schema = "dbo", catalog = "educate")
public class PlanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plan_id", nullable = false)
    private int planId;
    @Basic
    @Column(name = "org_unit_id", nullable = true)
    private Integer orgUnitId;
    @Basic
    @Column(name = "pr_course_id", nullable = true)
    private Integer prCourseId;
    @Basic
    @Column(name = "org_unit_post_person_id", nullable = true)
    private Integer orgUnitPostPersonId;
    @Basic
    @Column(name = "element_id_type", nullable = true)
    private Integer elementIdType;
    @Basic
    @Column(name = "element_id_status", nullable = true)
    private Integer elementIdStatus;
    @Basic
    @Column(name = "title", nullable = true, length = 255)
    private String title;
    @Basic
    @Column(name = "from_date", nullable = true)
    private Date fromDate;
    @Basic
    @Column(name = "to_date", nullable = true)
    private Date toDate;
    @Basic
    @Column(name = "deleted", nullable = true, length = 2)
    private String deleted;
    @Basic
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;
    @Basic
    @Column(name = "inserted_at", nullable = true)
    private Date insertedAt;
    @ManyToOne
    @JoinColumn(name = "pr_course_id", referencedColumnName = "pr_course_id")
    private PrCourseEntity prCourseByPrCourseId;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Integer getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(Integer orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public Integer getPrCourseId() {
        return prCourseId;
    }

    public void setPrCourseId(Integer prCourseId) {
        this.prCourseId = prCourseId;
    }

    public Integer getOrgUnitPostPersonId() {
        return orgUnitPostPersonId;
    }

    public void setOrgUnitPostPersonId(Integer orgUnitPostPersonId) {
        this.orgUnitPostPersonId = orgUnitPostPersonId;
    }

    public Integer getElementIdType() {
        return elementIdType;
    }

    public void setElementIdType(Integer elementIdType) {
        this.elementIdType = elementIdType;
    }

    public Integer getElementIdStatus() {
        return elementIdStatus;
    }

    public void setElementIdStatus(Integer elementIdStatus) {
        this.elementIdStatus = elementIdStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

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

        PlanEntity that = (PlanEntity) o;

        if (planId != that.planId) return false;
        if (orgUnitId != null ? !orgUnitId.equals(that.orgUnitId) : that.orgUnitId != null) return false;
        if (prCourseId != null ? !prCourseId.equals(that.prCourseId) : that.prCourseId != null) return false;
        if (orgUnitPostPersonId != null ? !orgUnitPostPersonId.equals(that.orgUnitPostPersonId) : that.orgUnitPostPersonId != null)
            return false;
        if (elementIdType != null ? !elementIdType.equals(that.elementIdType) : that.elementIdType != null)
            return false;
        if (elementIdStatus != null ? !elementIdStatus.equals(that.elementIdStatus) : that.elementIdStatus != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (insertedAt != null ? !insertedAt.equals(that.insertedAt) : that.insertedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planId;
        result = 31 * result + (orgUnitId != null ? orgUnitId.hashCode() : 0);
        result = 31 * result + (prCourseId != null ? prCourseId.hashCode() : 0);
        result = 31 * result + (orgUnitPostPersonId != null ? orgUnitPostPersonId.hashCode() : 0);
        result = 31 * result + (elementIdType != null ? elementIdType.hashCode() : 0);
        result = 31 * result + (elementIdStatus != null ? elementIdStatus.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        return result;
    }

    public PrCourseEntity getPrCourseByPrCourseId() {
        return prCourseByPrCourseId;
    }

    public void setPrCourseByPrCourseId(PrCourseEntity prCourseByPrCourseId) {
        this.prCourseByPrCourseId = prCourseByPrCourseId;
    }
}
