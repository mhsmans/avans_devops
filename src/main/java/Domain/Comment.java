package Domain;

import Domain.Observer.Member;

public class Comment {

    private String id;
    private Member creator;
    private String body;

    public Comment(String id, Member creator, String body) {
        this.id = id;
        this.creator = creator;
        this.body = body;
    }
}
