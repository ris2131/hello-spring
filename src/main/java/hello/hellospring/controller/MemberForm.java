package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {//우린 getter 로 볼수 있다?
        return name;
    }

    public void setName(String name) {//spring 이 이걸 통해 넣어주고?
        this.name = name;
    }
}
