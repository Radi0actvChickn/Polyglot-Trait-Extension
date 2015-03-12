package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.MethodDecl;
import polyglot.ast.NodeFactory;
import polyglot.ast.TypeNode;
import polyglot.types.Flags;
import polyglot.util.Position;

public interface TraitNodeFactory extends NodeFactory {

    RequiredMethod RequiredMethod(Position pos, Flags flags,
            TypeNode returnType, Id name, List<Formal> formals,
            List<TypeNode> throwTypes);

    TraitDecl TraitDecl(Position pos, Id name,
            List<RequiredMethod> requiredMethods,
            List<MethodDecl> providedMethods);

    UseTrait UseTrait(Position pos, Id name);
}
