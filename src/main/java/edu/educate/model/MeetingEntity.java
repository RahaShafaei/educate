package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "meeting", schema = "dbo", catalog = "educate")
public class MeetingEntity extends TitleEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @Lob
    @Column(name = "file_value")
    private byte[] fileValue;

    @Column(name = "file_type")
    private String fileType;

    public PlansEntity getPlan() {
        return (PlansEntity)ifEntityIsDeleted(plan);
    }
}
