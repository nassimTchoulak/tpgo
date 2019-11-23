public class PathNotInitializedException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println(" Static graph and estimation not inited ");
    }
}
