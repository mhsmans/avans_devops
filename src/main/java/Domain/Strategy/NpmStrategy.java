package Domain.Strategy;

public class NpmStrategy implements PackageStrategy {

    @Override
    public boolean execute() {
        System.err.println("installing packages with npm");
        return true;
    }
}
