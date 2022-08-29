package com.epam.rd.project.payments.model.dtos;

import com.epam.rd.project.payments.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String email;
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();
}
