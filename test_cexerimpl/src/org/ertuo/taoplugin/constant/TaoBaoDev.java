package org.ertuo.taoplugin.constant;

public enum TaoBaoDev {
    //http://gw.api.tbsandbox.com/router/rest
    App_Api_Host("http://gw.api.taobao.com/router/rest"), App_Key("12168005"), App_Secret(
                                                                                          "7e5709217a12bb665a437f52a92c8909");

    /**
     * @return Returns the value.
     */
    private String value;

    TaoBaoDev(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }
}
