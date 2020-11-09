package ru.itis.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserForm {
    private String email;
    private String name;
    private String password;
}
