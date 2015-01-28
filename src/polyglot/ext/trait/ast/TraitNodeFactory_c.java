package polyglot.ext.trait.ast;

import polyglot.ast.AbstractExtFactory_c;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;
import polyglot.ast.JLang;
import polyglot.ast.NodeFactory_c;
import polyglot.util.Position;

@SuppressWarnings("deprecation")
public class TraitNodeFactory_c extends NodeFactory_c {
    private final JLang lang;
    private final TraitExtFactory extFactory;

    public TraitNodeFactory_c(JLang lang) {
        this(lang, AbstractExtFactory_c.emptyExtFactory);
    }

    public TraitNodeFactory_c(JLang lang, ExtFactory extFactory) {
        this.lang = lang;
        this.extFactory = (TraitExtFactory) extFactory;
        initEnums();
    }

    public TraitDecl TraitDecl(Position pos, Ext ext) {
        TraitDecl n = new TraitDecl_c(pos, ext);
        n = ext(n, extFactory().extTraitDecl());
        return n;
    }

    @Override
    protected TraitExtFactory extFactory() {
        return extFactory;
    }

}
