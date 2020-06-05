import Domain.ChainOfResponsibility.Pipeline;
import Domain.Strategy.*;
import org.junit.jupiter.api.*;

public class PipelineTest {

    private Pipeline scmPipeline, packagePipeline;
    private ScmStrategy scmStrategy;
    private PackageStrategy packageStrategy;

    public PipelineTest() {
        scmStrategy = new GitStrategy();
        packageStrategy = new NuGetStrategy();

        scmPipeline = new SourcesOperation(scmStrategy);
        packagePipeline = new PackageOperation(packageStrategy);

        scmPipeline.setNext(packagePipeline);
    }

    @Test
    void simplePipelineTest() {
        Assertions.assertTrue(scmPipeline.process());
    }
}
