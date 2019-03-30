package com.github.brunomndantas.jscrapper.support.urlSupplier;

import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import com.github.brunomndantas.jscrapper.core.urlSupplier.URLSupplierException;

public abstract class URLSupplier implements IURLSupplier {

    @Override
    public String get() throws URLSupplierException {
        try {
            return getURL();
        } catch (Exception e) {
            String msg = "Error getting url!";
            throw new URLSupplierException(msg, e);
        }
    }



    protected abstract String getURL() throws Exception;

}
