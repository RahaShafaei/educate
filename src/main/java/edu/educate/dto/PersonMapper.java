package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PersonMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public PersonDto toDto(PersonEntity person) {

        PersonDto personDto = new PersonDto();

        dtoMapperUtils.populateCommonFields(person, personDto);

        personDto.setFname(person.getFname());
        personDto.setLname(person.getLname());
        personDto.setFatherName(person.getFatherName());
        personDto.setNlCode(person.getNlCode());
        personDto.setPrCode(person.getPrCode());
        personDto.setTel(person.getTel());

        return personDto;
    }
}
