package hello.core2.repository;

import hello.core2.member.Member;

public interface MemberRepository {
    void save(Member member);   //가입

    Member findById(Long memberId); //조회
}