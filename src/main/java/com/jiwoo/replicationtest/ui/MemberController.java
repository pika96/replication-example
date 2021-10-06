package com.jiwoo.replicationtest.ui;

import com.jiwoo.replicationtest.application.MemberService;
import com.jiwoo.replicationtest.application.dto.MemberDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> showMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @PostMapping("/members")
    public ResponseEntity<Void> addMember(@RequestBody MemberDto memberRequest) {
        memberService.addMember(memberRequest);
        return ResponseEntity.noContent().build();
    }
}
