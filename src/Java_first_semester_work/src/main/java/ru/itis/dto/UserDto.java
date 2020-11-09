package ru.itis.dto;

import lombok.*;
import ru.itis.models.User;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String name;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static UserDto fromOptional(Optional<UserDto> optionalUserDto) {
        return optionalUserDto.orElse(null);
    }

    public static User userFromOptional(Optional<User> optionalUser) {
        return optionalUser.orElse(null);
    }
}
