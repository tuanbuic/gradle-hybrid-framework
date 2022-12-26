package factoryBrowser;

public class BrowserNotSupportedException extends IllegalStateException {
    public BrowserNotSupportedException(String browser){
        super(String.format("Browser not support: %s",browser));
    }
}
