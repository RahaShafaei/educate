package edu.educate.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@jakarta.persistence.Table(name = "org_unit_post_person", schema = "dbo", catalog = "educate")
public class OrgUnitPostPersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "org_unit_post_person_id", nullable = false)
    private int orgUnitPostPersonId;

    public int getOrgUnitPostPersonId() {
        return orgUnitPostPersonId;
    }

    public void setOrgUnitPostPersonId(int orgUnitPostPersonId) {
        this.orgUnitPostPersonId = orgUnitPostPersonId;
    }

    @Basic
    @Column(name = "org_unit_id", nullable = false)
    private int orgUnitId;

    public int getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(int orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    @Basic
    @Column(name = "org_post_id", nullable = true)
    private Integer orgPostId;

    public Integer getOrgPostId() {
        return orgPostId;
    }

    public void setOrgPostId(Integer orgPostId) {
        this.orgPostId = orgPostId;
    }

    @Basic
    @Column(name = "person_id", nullable = false)
    private int personId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "from_date", nullable = true)
    private Date fromDate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "to_date", nullable = true)
    private Date toDate;

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

        OrgUnitPostPersonEntity that = (OrgUnitPostPersonEntity) o;

        if (orgUnitPostPersonId != that.orgUnitPostPersonId) return false;
        if (orgUnitId != that.orgUnitId) return false;
        if (personId != that.personId) return false;
        if (orgPostId != null ? !orgPostId.equals(that.orgPostId) : that.orgPostId != null) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (insertedAt != null ? !insertedAt.equals(that.insertedAt) : that.insertedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orgUnitPostPersonId;
        result = 31 * result + orgUnitId;
        result = 31 * result + (orgPostId != null ? orgPostId.hashCode() : 0);
        result = 31 * result + personId;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        return result;
    }
}
