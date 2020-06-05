package Domain.ChainOfResponsibility;

public abstract class Operation implements Pipeline {

    private Pipeline nextOperation;
    private boolean pipelineSuccess;

    @Override
    public void setNext(Pipeline pipeline) {
        this.nextOperation = pipeline;
    }

    public final void setPipelineSuccess(boolean success) {
        this.pipelineSuccess = success;
    }

    @Override
    public boolean process(){
        if (nextOperation != null && pipelineSuccess) {
            nextOperation.process();
        }
        return pipelineSuccess;
    };
}
