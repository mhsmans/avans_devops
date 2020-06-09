import Domain.DiscussionThread;
import Domain.Forum;
import Domain.Observer.Member;
import Domain.Role;
import Domain.StateBacklogItem.BacklogItem;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ForumTest {

    private BacklogItem backlogItem;
    private Forum forum;
    private Member member;

    private PrintStream originalSystemErr;
    private ByteArrayOutputStream systemErrContent;

    public ForumTest() {
        backlogItem = new BacklogItem("test-item", "test description");
        forum = new Forum();
        member = new Member("pete", "pete@slack", "pete@mail.com", Role.DEVELOPER, true, true);
    }

    @BeforeEach
    void redirectSystemOutStream() {
        originalSystemErr = System.err;
        systemErrContent = new ByteArrayOutputStream();

        System.setErr(new PrintStream(systemErrContent));
    }

    @AfterEach
    void restoreSystemOutStream() {
        System.setErr(originalSystemErr);
    }

    @Test
    void openThreadForBacklogItemTest() {
        forum.addDiscussionThread(backlogItem, "test-item thread");
        Assertions.assertEquals(1, forum.getDiscussionThreads().size());
    }

    @Test
    void addCommentToThreadTest() {
        forum.addDiscussionThread(backlogItem, "test-item thread");
        DiscussionThread discussionThread = forum.getDiscussionThreads().get(0);
        discussionThread.addComment(member, "test comment");
        Assertions.assertEquals(1, discussionThread.getComments().size());
    }

    @Test
    void cannotAddThreadToClosedItemTest() {
        backlogItem.assignMember(member);
        backlogItem.nextState();
        backlogItem.nextState();
        Assertions.assertTrue(backlogItem.isCompleted());
        forum.addDiscussionThread(backlogItem, "test-item thread");
        Assertions.assertTrue(systemErrContent.toString().contains("unable to add discussion thread; item is closed"));
    }

    @Test
    void cannotAddCommentToClosedItemTest() {
        backlogItem.assignMember(member);
        backlogItem.nextState();
        forum.addDiscussionThread(backlogItem, "test-item thread");
        backlogItem.nextState();
        Assertions.assertTrue(backlogItem.isCompleted());

        DiscussionThread discussionThread = forum.getDiscussionThreads().get(0);
        Assertions.assertNotNull(discussionThread);
        discussionThread.addComment(member, "test comment");
        Assertions.assertTrue(systemErrContent.toString().contains("discussion is closed"));
    }
}
