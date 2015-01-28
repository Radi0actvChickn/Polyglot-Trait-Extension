package polyglot.ext.trait.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public interface TraitExtFactory extends ExtFactory {

    Ext extTraitDecl();

    Ext extUseTrait();

    Ext extRequiredMethod();
}
