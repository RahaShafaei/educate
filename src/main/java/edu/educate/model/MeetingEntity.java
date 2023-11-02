package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "meeting", schema = "dbo", catalog = "educate")
public class MeetingEntity extends TitleEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

}
