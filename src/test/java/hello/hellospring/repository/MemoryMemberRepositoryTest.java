package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {//여기에서는 public class 안해도 된다. (다른데에서 가져다 쓸게 아니기 때문)
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){//afterEach : 아래의 test가 끝날떄 마다 clearStore 해준다.
        repository.clearStore();

    }


    @Test // 이거 하면 test 된다.
    public void save(){
        Member member = new Member();//Member? : java.hello.hellospring.domain.Member 클래스
        member.setName("spring");//java.hello.hellospring.domain.Member 클래스 에 getter setter 설정 해줬었음.

        repository.save(member);// store 라는 Map 에 저장하는.

        Member result = repository.findById(member.getId()).get();

        //Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result); // 영어 읽는거 같네. ㅋㅋㅋ 이것도 람다식? //assert 영어뜻: 주장하다. 이게 맞냐 주장하는거임 T/F 로 뜬다.
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
