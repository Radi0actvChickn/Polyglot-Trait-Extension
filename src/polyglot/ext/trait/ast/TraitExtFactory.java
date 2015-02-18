package polyglot.ext.trait.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public interface TraitExtFactory extends ExtFactory {

    /*
     * Methods being called by TraitNodeFactory
     */
    Ext extTraitDecl();

    Ext extUseTrait();

    Ext extRequiredMethod();
}
