package Domain;

import Domain.Observer.Member;

import java.util.ArrayList;

public class Team {

    private String name;
    private ArrayList<Member> members;

    public Team(String name, ArrayList<Member> members) {
        this.name = name;
        this.members = members;
    }
}
