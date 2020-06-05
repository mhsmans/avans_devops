package Domain.Strategy;

import Domain.ChainOfResponsibility.Operation;

public class PackageOperation extends Operation {

    private PackageStrategy packageStrategy;

    public PackageOperation(PackageStrategy strategy) {
        this.packageStrategy = strategy;
    }

    @Override
    public boolean process() {
        if(packageStrategy.execute()) {
            super.setPipelineSuccess(true);
            return super.process();
        } else {
            super.setPipelineSuccess(false);
            return super.process();
        }

    }
}
