package vip.mrtree.component;

import vip.mrtree.utils.CollectionUtils;
import vip.mrtree.utils.MapUtils;
import vip.mrtree.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlComponentBuilder {
    private static final String PARAMETER_JOINER = "&";
    private static final String URL_JOINER = "?";
    private static final String PORT_JOINER = ":";
    private static final String PATH_JOINER = "/";

    private Protocol scheme; // http https
    private String domain;
    private Integer port;
    private final List<String> pathList = new ArrayList<>();
    private final Map<String, String> parameter = new HashMap<>();

    private StringBuilder url;

    private UrlComponentBuilder() {

    }

    public static UrlComponentBuilder newInstance() {
        return new UrlComponentBuilder();
    }

    public UrlComponentBuilder scheme(Protocol scheme) {
        this.scheme = scheme;
        return this;
    }

    public UrlComponentBuilder domain(String domain) {
        if (!StringUtils.isEmpty(domain)) {
            this.domain = domain;
        }
        return this;
    }

    public UrlComponentBuilder port(int port) {
        this.port = port;
        return this;
    }

    public UrlComponentBuilder path(String path) {
        if (StringUtils.isEmpty(path)) {
            return this;
        }
        this.pathList.add(path);
        return this;
    }

    public UrlComponentBuilder parameter(Map<String, String> map) {
        if (MapUtils.isEmpty(map)) {
            return this;
        }
        this.parameter.putAll(map);
        return this;
    }

    public UrlComponentBuilder parameter(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return this;
        }
        parameter.put(key, StringUtils.isEmpty(value) ? "" : value);
        return this;
    }

    public UrlComponent build() {
        return new UrlComponent(compose());
    }

    private String compose() {
        this.url = new StringBuilder();
        if (scheme != null) {
            this.url.append(scheme.getValue());
        }
        this.url.append(StringUtils.isEmpty(domain) ? "" : clearPath(domain));
        if (port != null) {
            this.url.append(PORT_JOINER).append(port);
        }
        if (CollectionUtils.isNotEmpty(pathList)) {
            pathList.forEach(path -> this.url.append(PATH_JOINER).append(clearPath(path)));
        }
        if (MapUtils.isNotEmpty(parameter)) {
            this.url.append(URL_JOINER).append(MapUtils.join(parameter, PARAMETER_JOINER));
        }
        return clearPath(this.url.toString());
    }

    private String clearPath(String path) {
        while (path.startsWith(PATH_JOINER)) {
            path = path.substring(1);
        }
        while (path.endsWith(PATH_JOINER)) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public enum Protocol {
        HTTP("http://"),
        HTTPS("https://");

        private final String value;

        Protocol(String value) {
            this.value = value;
        }

        private String getValue() {
            return this.value;
        }
    }
}
