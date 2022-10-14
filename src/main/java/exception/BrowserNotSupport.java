package exception;

public class BrowserNotSupport extends IllegalArgumentException {
    public BrowserNotSupport(String browserName){
        super(String.format("Browser name with name = %s is invalid",browserName));
    }

}
