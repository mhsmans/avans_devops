package Domain.Strategy;

public class SubversionStrategy implements ScmStrategy {

    @Override
    public boolean execute() {
        System.err.println("getting sources with Subversion...");
        return true;
    }
}
