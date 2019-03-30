package com.github.brunomndantas.jscrapper.support.urlSupplier;

public class FixedURLSupplier extends URLSupplier {

    private String url;



    public FixedURLSupplier(String url) {
        this.url = url;
    }



    @Override
    protected String getURL() {
        return this.url;
    }

}
