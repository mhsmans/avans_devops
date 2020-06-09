import Domain.ChainOfResponsibility.Pipeline;
import Domain.Observer.Member;
import Domain.Role;
import Domain.SprintBacklog;
import Domain.StateSprint.Sprint;
import Domain.Strategy.*;
import Domain.Team;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SprintTest {

    private Sprint sprintA, sprintB;
    private Date startDate, endDate;
    private Team team;
    private Member developer, scrumMaster;
    private SprintBacklog sprintBacklog;
    private Pipeline pipeline, packagePipe;
    private ScmStrategy scmStrategy;
    private PackageStrategy packageStrategy;

    public SprintTest() {
        startDate = new GregorianCalendar(2020, Calendar.MARCH, 1).getTime();
        endDate = new GregorianCalendar(2020, Calendar.JUNE, 8).getTime();
        developer = new Member("Pete", "pete@slack", "pete@mail.com", Role.DEVELOPER, true, true);
        scrumMaster = new Member("John", "john@slack", "john@mail.com", Role.SCRUM_MASTER, true, true);

        ArrayList<Member> members = new ArrayList<>();
        members.add(developer);
        members.add(scrumMaster);
        team = new Team("project team", members);

        sprintBacklog = new SprintBacklog("test sprint");

        scmStrategy = new GitStrategy();
        packageStrategy = new NpmStrategy();
        pipeline = new SourcesOperation(scmStrategy);
        packagePipe = new PackageOperation(packageStrategy);
        pipeline.setNext(packagePipe);

        sprintA = new Sprint("test sprint", startDate, endDate, team, sprintBacklog, false, pipeline);
        sprintB = new Sprint("test release sprint", startDate, endDate, team, sprintBacklog, true, pipeline);
    }

    @Test
    void sprintProgressionTest() {
    }
}
