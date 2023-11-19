package edu.educate.model.baseModel;

import edu.educate.validator.LengthOrEmpty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class TitleLPEntity extends BaseEntity {

    @NotNull
    @LengthOrEmpty(min = 2, max = 50, message = "{general.ltTitle}")
    @Column(name = "lt_title", length = 50 )
    private String ltTitle;

    @NotNull
    @LengthOrEmpty(min = 2, max = 50, message = "{general.prTitle}")
    @Column(name = "pr_title", length = 50)
    private String prTitle;

}