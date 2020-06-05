package Domain.ChainOfResponsibility;

public interface Pipeline {

    void setNext(Pipeline pipeline);

    boolean process();
}
