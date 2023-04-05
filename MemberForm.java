package hello.hellospring.controller;

public class MemberForm {
    private String name; //membersfrom.html에서의 input name과 매칭이 되면서 값이 들어갈 것이다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; //string을 setname을 호출해서 값을 넣어주고 getname으로 값을 꺼낸다.
    }
}
