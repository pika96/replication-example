package com.jiwoo.replicationtest.application.dto;

import com.jiwoo.replicationtest.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String name;

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getName());
    }
}
