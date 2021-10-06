package com.jiwoo.replicationtest.application;

import com.jiwoo.replicationtest.application.dto.MemberDto;
import com.jiwoo.replicationtest.domain.Member;
import com.jiwoo.replicationtest.domain.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberDto> findAll() {
        return memberRepository.findAll()
                .stream().map(MemberDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addMember(MemberDto memberRequest) {
        memberRepository.save(Member.create(memberRequest.getName()));
    }
}
