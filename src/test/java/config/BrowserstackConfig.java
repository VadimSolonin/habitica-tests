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
    @DefaultValue("bs://615731d58494ac648745f5a4d1ab6d321cfe87f9")
    String getApp();
}
