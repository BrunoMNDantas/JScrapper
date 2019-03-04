package com.github.brunomndantas.jscrapper.core.pageBuilder;

import com.github.brunomndantas.jscrapper.core.Page;

public interface IPageBuilder {

    Page build(Object instance) throws PageBuilderException;

}
