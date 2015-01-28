package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.Ext;
import polyglot.ast.Id;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.types.ConstructorInstance;
import polyglot.types.Flags;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.CFGBuilder;

public class TraitDecl_c extends Term_c implements TraitDecl {

    private static final long serialVersionUID = SerialVersionUID.generate();

    protected Flags flags;
    protected Id name;
    protected ConstructorInstance defaultCI;
    protected boolean implicitMembersAdded;

    public TraitDecl_c(Position pos) {
        super(pos);
    }

    public TraitDecl_c(Position pos, Ext ext) {
        super(pos, ext);
    }

    @Override
    public Flags flags() {
        return flags;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Term firstChild() {
        return null;
    }

    @Override
    public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
        return null;
    }

    @Override
    public TraitDecl flags(Flags flags) {
        return null;
    }

    @Override
    public Id id() {
        return name;
    }

    @Override
    public TraitDecl id(Id id) {
        return null;
    }

    @Override
    public TraitDecl name(String name) {
        return null;
    }

}
