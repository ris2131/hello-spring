package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//save 기능
    Optional<Member> findById(Long id);//id 로 회원 찾는 기능
    Optional<Member> findByName(String name);
    List<Member> findAll();//지금까지 온 회원 리스트를 반환




}
