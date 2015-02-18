package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.MethodDecl_c;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.types.Flags;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public class RequiredMethod_c extends MethodDecl_c implements RequiredMethod {

    private static final long serialVersionUID = SerialVersionUID.generate();

    public RequiredMethod_c(Position pos, Flags flags, TypeNode returnType,
            Id name, List<Formal> formals, List<TypeNode> throwTypes) {
        super(pos, flags, returnType, name, formals, throwTypes, null);
    }

    /**
     * Returns null since {@code RequiredMethod} has no body
     * @return null
     */
    @Override
    public Block body() {
        return null;
    }

    @Override
    public Term codeBody() {
        return null;
    }
}
