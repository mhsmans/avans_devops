package Domain;

import Domain.StateSprint.Sprint;

import java.util.ArrayList;

public class Project {

    private String title;
    private Team team;
    private ProductBacklog productBacklog;
    private ArrayList<Sprint> sprints;
    private ArrayList<Repository> repositories;
    private Forum forum;

    public Project(String title, Team team, ProductBacklog productBacklog, ArrayList<Sprint> sprints, ArrayList<Repository> repositories, Forum forum) {
        this.title = title;
        this.team = team;
        this.productBacklog = productBacklog;
        this.sprints = sprints;
        this.repositories = repositories;
        this.forum = forum;
    }
}
