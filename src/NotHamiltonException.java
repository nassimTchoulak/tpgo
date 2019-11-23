public class NotHamiltonException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println(" un cycle non hamiltonien ");
    }
}
