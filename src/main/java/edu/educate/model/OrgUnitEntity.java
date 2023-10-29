package edu.educate.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@jakarta.persistence.Table(name = "org_unit", schema = "dbo", catalog = "educate")
public class OrgUnitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "org_unit_id", nullable = false)
    private int orgUnitId;

    public int getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(int orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    @Basic
    @Column(name = "parent_org_unit_id", nullable = true)
    private Integer parentOrgUnitId;

    public Integer getParentOrgUnitId() {
        return parentOrgUnitId;
    }

    public void setParentOrgUnitId(Integer parentOrgUnitId) {
        this.parentOrgUnitId = parentOrgUnitId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "descr", nullable = true, length = 255)
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

        OrgUnitEntity that = (OrgUnitEntity) o;

        if (orgUnitId != that.orgUnitId) return false;
        if (parentOrgUnitId != null ? !parentOrgUnitId.equals(that.parentOrgUnitId) : that.parentOrgUnitId != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (insertedAt != null ? !insertedAt.equals(that.insertedAt) : that.insertedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orgUnitId;
        result = 31 * result + (parentOrgUnitId != null ? parentOrgUnitId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        return result;
    }
}
