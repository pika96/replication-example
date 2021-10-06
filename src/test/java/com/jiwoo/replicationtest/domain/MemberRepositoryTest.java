package com.jiwoo.replicationtest.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.jiwoo.replicationtest.application.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("Circular Slave DB")
    @Test
    void findMemberCircularSlaveDB() {
        //given
        Member pika = Member.create("pika");
        Member save = memberRepository.save(pika);
        //when
        Member member1 = memberRepository.findById(save.getId()).orElseThrow();
        Member member2 = memberRepository.findById(save.getId()).orElseThrow();
        Member member3 = memberRepository.findById(save.getId()).orElseThrow();
        Member member4 = memberRepository.findById(save.getId()).orElseThrow();

        //then
        assertThat(member1.getName()).isEqualTo("pika");
    }
}