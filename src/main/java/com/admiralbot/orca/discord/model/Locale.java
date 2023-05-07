package com.admiralbot.orca.discord.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Locales supported by Discord. Generated programmatically from Discord docs tables.
 */
public enum Locale {

    GB("en-GB", "English, UK", "English, UK"),
    EN_US("en-US", "English, US", "English, US"),
    ES_ES("es-ES", "Spanish", "Español"),
    FR("fr", "French", "Français"),
    HR("hr", "Croatian", "Hrvatski"),
    IT("it", "Italian", "Italiano"),
    LT("lt", "Lithuanian", "Lietuviškai"),
    HU("hu", "Hungarian", "Magyar"),
    NL("nl", "Dutch", "Nederlands"),
    NO("no", "Norwegian", "Norsk"),
    PL("pl", "Polish", "Polski"),
    PT_BR("pt-BR", "Portuguese, Brazilian", "Português do Brasil"),
    RO("ro", "Romanian, Romania", "Română"),
    FI("fi", "Finnish", "Suomi"),
    SV_SE("sv-SE", "Swedish", "Svenska"),
    VI("vi", "Vietnamese", "Tiếng Việt"),
    TR("tr", "Turkish", "Türkçe"),
    CS("cs", "Czech", "Čeština"),
    EL("el", "Greek", "Ελληνικά"),
    BG("bg", "Bulgarian", "български"),
    RU("ru", "Russian", "Pусский"),
    UK("uk", "Ukrainian", "Українська"),
    HI("hi", "Hindi", "हिन्दी"),
    TH("th", "Thai", "ไทย"),
    ZH_CN("zh-CN", "Chinese, China", "中文"),
    JA("ja", "Japanese", "日本語"),
    ZH_TW("zh-TW", "Chinese, Taiwan", "繁體中文"),
    KO("ko", "Korean", "한국어");

    @JsonValue private final String id;
    private final String languageName;
    private final String nativeName;

    Locale(String id, String languageName, String nativeName) {
        this.id = id;
        this.languageName = languageName;
        this.nativeName = nativeName;
    }

    public String getId() {
        return id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getNativeName() {
        return nativeName;
    }
}
