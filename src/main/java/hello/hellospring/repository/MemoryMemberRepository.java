package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


//@Repository//정형화된 패턴.
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {//이름 넘어 오고
        member.setId(++sequence);//member id를 sequence 순서대로 만들어주고
        store.put(member.getId(),member);//store 에다가 member id랑 member이름이랑 넣어주는. store 는 뭐지? 아 위에 있네
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {//Optional : Null 이 나와도 쉽게 뭔가를 해주는.
        //store 에서 꺼내면 된다.
        return Optional.ofNullable(store.get(id));//이렇게 감싸서 반환 해주면 NULL 될 가능성을 클라이언트에서 문제 해결을 할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        //람다식
        return store.values().stream()//loop
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//ArrayList : java.util 거인듯 많이 쓴대.
        //values : Map 의 Key 말고 Value(여기서는 Member 객체들의 ArrayList가 반환 되겠다)
    }

    public void clearStore(){
        store.clear();
    }
}
