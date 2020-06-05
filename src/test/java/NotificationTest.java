import Domain.Observer.Member;
import Domain.Observer.NotificationPublisher;
import Domain.Role;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NotificationTest {

    private Member developer, scrumMaster, productOwner;
    private NotificationPublisher notificationPublisher;
    private PrintStream originalSystemErr;
    private ByteArrayOutputStream systemErrContent;
    private String testNotification;

    public NotificationTest() {
        notificationPublisher = NotificationPublisher.getNotificationPublisher();

        developer = new Member("Pete", "pete@slack", "pete@mail.com", Role.DEVELOPER, true, false);
        scrumMaster = new Member("Oliver", "oliver@slack", "oliver@mail.com", Role.SCRUM_MASTER, true, true);
        productOwner = new Member("John", "john@slack", "john@mail.com", Role.PRODUCT_OWNER, false, true);

        testNotification = "This is a test notification.";
    }

    @BeforeEach
    void redirectSystemOutStream() {
        originalSystemErr = System.err;
        systemErrContent = new ByteArrayOutputStream();

        System.setErr(new PrintStream(systemErrContent));
    }

    @BeforeEach
    void removeSubscribers() {
        notificationPublisher.deleteSubscribers();
    }

    @AfterEach
    void restoreSystemOutStream() {
        System.setErr(originalSystemErr);
    }

    @Test
    void subscribeMemberToNotificationPublisherTest() {
        Assertions.assertEquals(0, notificationPublisher.getSubscribers().size());
        notificationPublisher.subscribe(developer);
        Assertions.assertSame(developer, notificationPublisher.getSubscribers().get(0));
        Assertions.assertEquals(1, notificationPublisher.getSubscribers().size());
    }

    @Test
    void sendNotificationToMultiplePlatformsTest() {
        Assertions.assertEquals(0, notificationPublisher.getSubscribers().size());
        notificationPublisher.subscribe(scrumMaster);
        notificationPublisher.notifyScrumMaster(testNotification);
        Assertions.assertTrue(systemErrContent.toString().contains("Sending email to " + scrumMaster.getEmail() + " : " + testNotification));
        Assertions.assertTrue(systemErrContent.toString().contains("Sending slack to " + scrumMaster.getSlack() + " : " + testNotification));
    }

    @Test
    void sendNotificationToAllSubscribersTest() {
        Assertions.assertEquals(0, notificationPublisher.getSubscribers().size());
        notificationPublisher.subscribe(developer);
        notificationPublisher.subscribe(scrumMaster);
        notificationPublisher.subscribe(productOwner);
        notificationPublisher.notifyAllSubscribers(testNotification);
        Assertions.assertTrue(systemErrContent.toString().contains("Sending email to " + developer.getEmail() + " : " + testNotification));
        Assertions.assertTrue(systemErrContent.toString().contains("Sending email to " + scrumMaster.getEmail() + " : " + testNotification));
        Assertions.assertTrue(systemErrContent.toString().contains("Sending slack to " + scrumMaster.getSlack() + " : " + testNotification));
        Assertions.assertTrue(systemErrContent.toString().contains("Sending slack to " + productOwner.getSlack() + " : " + testNotification));
    }

    @Test
    void unsubscribeFromPublisherTest() {
        Assertions.assertEquals(0, notificationPublisher.getSubscribers().size());
        notificationPublisher.subscribe(productOwner);
        notificationPublisher.notifyProductOwner(testNotification);
        Assertions.assertTrue(systemErrContent.toString().contains("Sending slack to " + productOwner.getSlack() + " : " + testNotification));
        systemErrContent.reset();
        notificationPublisher.unsubscribe(productOwner);
        notificationPublisher.notifyProductOwner(testNotification);
        Assertions.assertFalse(systemErrContent.toString().contains("Sending slack to " + productOwner.getSlack() + " : " + testNotification));
    }
}
