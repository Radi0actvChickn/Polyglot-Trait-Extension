package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.AbstractExtFactory_c;
import polyglot.ast.ExtFactory;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.JLang;
import polyglot.ast.MethodDecl;
import polyglot.ast.NodeFactory_c;
import polyglot.ast.TypeNode;
import polyglot.ext.trait.types.TraitType;
import polyglot.types.Flags;
import polyglot.util.Position;

@SuppressWarnings("deprecation")
public class TraitNodeFactory_c extends NodeFactory_c implements
        TraitNodeFactory {
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

    @Override
    public JLang lang() {
        return lang;
    }

    @Override
    public RequiredMethod RequiredMethod(Position pos, Flags flags,
            TypeNode returnType, Id name, List<Formal> formals,
            List<TypeNode> throwTypes) {
        RequiredMethod n =
                new RequiredMethod_c(pos,
                                     flags,
                                     returnType,
                                     name,
                                     formals,
                                     throwTypes);
        n = ext(n, extFactory().extRequiredMethod());
        return n;
    }

    @Override
    public TraitDecl TraitDecl(Position pos, Id name,
            List<RequiredMethod> requiredMethods,
            List<MethodDecl> providedMethods) {
        TraitDecl n =
                new TraitDecl_c(pos, name, requiredMethods, providedMethods);
        n = ext(n, extFactory().extTraitDecl());
        return n;
    }

    @Override
    public UseTrait UseTrait(Position pos, TraitType trait) {
        UseTrait n = new UseTrait_c(pos, trait);
        n = ext(n, extFactory().extUseTrait());
        return n;
    }

    @Override
    protected TraitExtFactory extFactory() {
        return extFactory;
    }

}
