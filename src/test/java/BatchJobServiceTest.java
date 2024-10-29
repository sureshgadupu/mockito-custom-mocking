import dev.fullstackcode.junit.custommocking.BatchJobService;
import dev.fullstackcode.junit.custommocking.ChangeSetContext;
import dev.fullstackcode.junit.custommocking.PrivilegedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.github.stefanbirkner.systemlambda.SystemLambda;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BatchJobServiceTest {

  BatchJobService batchJobService;
  PrivilegedUser privilegedUser;
  ChangeSetContext changeSetContext;

  @BeforeEach
  void setUp() {
    privilegedUser = Mockito.mock(PrivilegedUser.class);
    batchJobService = new BatchJobService(privilegedUser);
  }

  @Test
  void scheduledDeleteTemporaryObjectsJob() throws Exception {
    changeSetContext = Mockito.mock(ChangeSetContext.class);
    when(privilegedUser.modifyUser(any())).thenReturn(changeSetContext);
    Mockito.doAnswer(
            invocation -> {
              Consumer<?> consumer = invocation.getArgument(0);
              consumer.accept(null);
              return null;
            })
        .when(changeSetContext)
        .run(any(Consumer.class));
    // Capture console output
    String consoleOutput =
        SystemLambda.tapSystemOut(
            () -> {
              batchJobService.scheduledDeleteTemporaryObjectsJob();
            });

    batchJobService.scheduledDeleteTemporaryObjectsJob();
      String expectedOutOut = "runDeleteTemporaryObjects method invoked"+System.lineSeparator()+
              "runDeleteTemporaryObjects method invoked";
    assertTrue(
        consoleOutput.contains(
                expectedOutOut),
        "Expected message 'runDeleteTemporaryVariants method invoked' was not found in console output.");
  }
}
