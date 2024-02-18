package hello.core2.service;

import hello.core2.member.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
