package com.epam.rd.project.payments.model.dtos;

import com.epam.rd.project.payments.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private RoleType name;

}
