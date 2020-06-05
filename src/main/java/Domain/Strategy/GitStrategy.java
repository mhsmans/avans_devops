package Domain.Strategy;

public class GitStrategy implements ScmStrategy {

    @Override
    public boolean execute() {
        System.err.println("getting sources with Git...");
        return true;
    }
}
