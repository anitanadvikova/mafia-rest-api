package org.example.mafia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GamerDto {

    private String nickname;

    private String pic;

    private String sex;

    private String email;
}
