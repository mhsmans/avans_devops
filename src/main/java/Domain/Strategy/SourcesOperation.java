package Domain.Strategy;

import Domain.ChainOfResponsibility.Operation;

public class SourcesOperation extends Operation {

    private ScmStrategy scmStrategy;

    public SourcesOperation(ScmStrategy strategy) {
        this.scmStrategy = strategy;
    }

    @Override
    public boolean process() {
        if (scmStrategy.execute()) {
            super.setPipelineSuccess(true);
            return super.process();
        } else {
            super.setPipelineSuccess(false);
            return super.process();
        }
    }
}
