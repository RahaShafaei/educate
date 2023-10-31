package edu.educate.dto;

import edu.educate.model.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PersonMapper {

    public PersonDto toDto(PersonEntity person) {

        PersonDto personDto = new PersonDto();

        personDto.setId(person.getId());
        personDto.setDeleted(person.getDeleted());
        personDto.setDeletedAt(person.getDeletedAt());
        personDto.setInsertedAt(person.getInsertedAt());

        personDto.setFname(person.getFname());
        personDto.setLname(person.getLname());
        personDto.setFatherName(person.getFatherName());
        personDto.setNlCode(person.getNlCode());
        personDto.setPrCode(person.getPrCode());
        personDto.setTel(person.getTel());

        return personDto;
    }
}
