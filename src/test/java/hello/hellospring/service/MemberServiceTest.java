package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;// = new MemberService();
    MemoryMemberRepository memberRepository;// = new MemoryMemberRepository();


    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);//DB 하나로 만들어서 되도록 하려고. memberservice 입장에서 보면 외부에서 넣어주는. dependency injection?
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {//!!!
        //given
        Member member = new Member();
        member.setName("inseok");

        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();//get? 모르겠으면 ctrl+alt B 해봐
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("InseokRyu");

        Member member2 = new Member();
        member2.setName("InseokRyu");//이름 중복

        //when
        Long saveId1 = memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//우측 로직을 실행하려고 하는데, Exception 으로 터지냐 T/F
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");//exception유형이 같다고 같은 위치가 아닐수 있으니 exception 문자열까지 같은지.. ㅇㅇ
        //assertThrows(NullPointerException.class,()->memberService.join(member2));//다른 exception 이 터지면

        /*
        //Long saveId2 = memberService.join(member2);//이름 중복. 아마 여기서 문제가생기겠지
        try{
            memberService.join(member2);
            fail();//?
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}