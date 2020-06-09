package Domain;

import Domain.Observer.Member;

public class Comment {

    private String id;
    private Member creator;
    private String body;

    private Comment(String id, Member creator, String body) {
        this.id = id;
        this.creator = creator;
        this.body = body;
    }

    public Comment(Member creator, String body) {
        this("unique generated id", creator, body);
    }
}
