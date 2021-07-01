package hello.hellospring.domain;

public class Member {
    private Long id;//system stores it
    private String name;//

    //getter setter
    //getId : id 를 받아와주는
    public Long getId() {
        return id;
    }
    //setId : id 를 설정 해주는
    public void setId(Long id) {
        this.id = id;
    }
    //getName :  name 을 받아와주는.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
