package com.weghst.setaria.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.squareup.okhttp.HttpUrl;

/**
 * @author Kevin Zou <yong.zou@pilibaba.com>
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException {
        HttpUrl httpUrl = HttpUrl.parse("http://www.mkyong.com/java/how-to-convert-java-map-to-from-json-jackson/");

        HttpUrl.Builder builder = httpUrl.newBuilder();
        System.out.println(builder.addEncodedPathSegment("../hello/world").build());

        URL url = new URL("http://www.mkyong.com/java/how-to-convert-java-map-to-from-json-jackson/../hello");
        System.out.println(url.getPath());
        System.out.println("------------------------------");
        System.out.println(HttpUrl.parse("http://www.mkyong.com/java/how-to-convert-java-map-to-from-json-jackson/../hello"));

        System.out.println(httpUrl.pathSegments());
        HttpUrl httpUrl1 = builder.setPathSegment(0, "world").build();
        System.out.println(httpUrl1);
        System.out.println(httpUrl.url());

        System.out.println(httpUrl1.scheme());
        System.out.println(httpUrl.host());
        System.out.println(httpUrl.port());
    }
}
