package com.github.brunomndantas.jscrapper.support.urlSupplier;

import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;

import java.util.Map;

public class ParameterizedURLSupplier extends URLSupplier {

    private IURLSupplier source;
    public IURLSupplier getSource() { return this.source; }

    private Map<String, String> parameters;
    public Map<String, String> getParameters() { return this.parameters; }



    public ParameterizedURLSupplier(IURLSupplier source, Map<String, String> parameters) {
        this.source = source;
        this.parameters = parameters;
    }



    @Override
    protected String getURL() throws Exception {
        String url = this.source.get();

        for(String parameter : this.parameters.keySet())
            url = url.replace("{" + parameter + "}", this.parameters.get(parameter));

        return url;
    }

}
