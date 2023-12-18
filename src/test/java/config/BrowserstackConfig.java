package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${envMobile}.properties"
})
public interface BrowserstackConfig extends Config {
    @Key("browserstackAccessKey")
    String getAccessKey();

    @Key("browserstackUserName")
    String getUserName();

    @Key("browserstackUrl")
    String getUrl();

    @Key("browserstackDevice")
    @DefaultValue("Google Pixel 3")
    String getDevice();

    @Key("browserstackOsVersion")
    @DefaultValue("9.0")
    String getOS();

    @Key("browserstackApp")
    @DefaultValue("bs://88df586f8cb86900b601ab3da2034f4cd17b1832")
    String getApp();
}
