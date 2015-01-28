package polyglot.ext.trait.ast;

import polyglot.ast.Id;
import polyglot.ast.TopLevelDecl;
import polyglot.types.Flags;

public interface TraitDecl extends TopLevelDecl {

    @Override
    Flags flags();

    TraitDecl flags(Flags flags);

    Id id();

    TraitDecl id(Id id);

    @Override
    String name();

    TraitDecl name(String name);
}
