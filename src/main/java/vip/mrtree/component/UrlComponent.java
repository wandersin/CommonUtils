package vip.mrtree.component;

public class UrlComponent {
    private final String url;

    UrlComponent(String url) {
        this.url = url;
    }

    public String toUrlString() {
        return url;
    }
}
