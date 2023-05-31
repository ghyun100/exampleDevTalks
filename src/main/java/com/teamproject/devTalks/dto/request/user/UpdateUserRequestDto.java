package com.teamproject.devTalks.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequestDto {

    @NotBlank
    private String password;
    @NotBlank
    private String userNickname;
    @NotBlank @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$")
    private String userPhoneNumber;
    @NotBlank
    private String userIntroduction;
    @NotNull @Size(min =0)
    private List<String> userHashtag;
    @NotBlank
    private String userProfileImageUrl;
    @NotNull
    private boolean  chatAcceptance;

}
