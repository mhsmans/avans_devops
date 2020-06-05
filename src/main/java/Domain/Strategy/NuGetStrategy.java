package Domain.Strategy;

public class NuGetStrategy implements PackageStrategy {

    @Override
    public boolean execute() {
        System.err.println("installing packages with NuGet");
        return true;
    }
}
