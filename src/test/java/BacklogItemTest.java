import Domain.Activity;
import Domain.Observer.Member;
import Domain.Observer.NotificationPublisher;
import Domain.Role;
import Domain.StateBacklogItem.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BacklogItemTest {

    private Member member, scrumMaster;
    private BacklogItem backlogItem;
    private Activity activityA, activityB;

    private PrintStream originalSystemErr;
    private ByteArrayOutputStream systemErrContent;

    public BacklogItemTest() {
        backlogItem = new BacklogItem("test-item", "backlog item used for unit test");
        member = new Member("John", "john@slack", "john@mail.com", Role.DEVELOPER, true, true);
        scrumMaster = new Member("Pete", "pete@slack", "pete@mail.com", Role.SCRUM_MASTER, true, false);
        activityA = new Activity("test activity A", "test description");
        activityB = new Activity("test activity B", "test description");

        NotificationPublisher.getNotificationPublisher().subscribe(scrumMaster);
    }

    @BeforeEach
    void redirectSystemOutStream() {
        originalSystemErr = System.err;
        systemErrContent = new ByteArrayOutputStream();

        System.setErr(new PrintStream(systemErrContent));
    }

    @BeforeEach
    void clearActivities() {
        backlogItem.getActivities().clear();
    }

    @AfterEach
    void restoreSystemOutStream() {
        System.setErr(originalSystemErr);
    }

    @Test
    void backlogItemProgressTest() {
        BacklogItemState doingState = new DoingState(backlogItem);
        backlogItem.assignMember(member);
        backlogItem.nextState();
        Assertions.assertEquals(doingState.getClass(), backlogItem.getCurrentState().getClass());
    }

    @Test
    void backlogItemCannotProgressWithoutAssignedMemberTest() {
        backlogItem.nextState();
        Assertions.assertTrue(systemErrContent.toString().contains("backlog item needs to be assigned to project member first"));
    }

    @Test
    void backlogItemCanBeDoneWithoutActivitiesTest() {
        backlogItem.assignMember(member);
        backlogItem.nextState();
        backlogItem.nextState();
        Assertions.assertTrue(backlogItem.isCompleted());
    }

    @Test
    void backlogItemCannotBeDoneWhenActivitiesAreNotCompletedTest() {
        backlogItem.assignMember(member);
        backlogItem.addActivity(activityA);
        backlogItem.addActivity(activityB);
        activityA.assignMember(member);
        activityB.assignMember(member);

        backlogItem.nextState();
        activityA.markComplete();
        backlogItem.nextState();
        Assertions.assertTrue(systemErrContent.toString().contains("not all activities are completed"));
    }

    @Test
    void backlogItemCanBeDoneWhenAllActivitiesAreCompletedTest() {
        backlogItem.assignMember(member);
        backlogItem.addActivity(activityA);
        backlogItem.addActivity(activityB);
        activityA.assignMember(member);
        activityB.assignMember(scrumMaster);

        backlogItem.nextState();
        activityA.markComplete();
        activityB.markComplete();
        backlogItem.nextState();
        Assertions.assertTrue(backlogItem.isCompleted());
    }

    @Test
    void backlogItemCannotProgressToNextStateFromDoneTest() {
        backlogItem.assignMember(member);
        backlogItem.nextState();
        backlogItem.nextState();
        backlogItem.nextState();
        Assertions.assertTrue(systemErrContent.toString().contains("backlog item already is in its final state"));
    }

    @Test
    void backlogItemCanBeInitializedWhenDoneTest() {
        BacklogItemState toDoState = new ToDoState(backlogItem);

        backlogItem.assignMember(member);
        backlogItem.nextState();
        backlogItem.nextState();
        Assertions.assertTrue(backlogItem.isCompleted());
        backlogItem.initializeState();
        Assertions.assertFalse(backlogItem.isCompleted());
        Assertions.assertEquals(toDoState.getClass(), backlogItem.getCurrentState().getClass());
    }

    @Test
    void scrumMasterGetsNotifiedWhenBacklogItemIsInitializedTest() {
        backlogItem.assignMember(member);
        backlogItem.nextState();
        backlogItem.nextState();
        backlogItem.initializeState();
        Assertions.assertTrue(systemErrContent.toString().contains("Sending email to pete@mail.com" + " : " +
                "Backlog item test-item is moved from 'done' to 'to-do'"
        ));
    }
}
