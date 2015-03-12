package polyglot.ext.trait.ast;

import polyglot.ast.Ext;
import polyglot.ast.Id;
import polyglot.ast.Node_c;
import polyglot.ast.Term;
import polyglot.ext.trait.types.TraitTypeSystem;
import polyglot.ext.trait.types.TraitTypeSystem_c;
import polyglot.types.ClassType;
import polyglot.types.MemberInstance;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.util.SubtypeSet;

//TODO: this class
public class UseTrait_c extends Node_c implements UseTrait {

    private static final long serialVersionUID = SerialVersionUID.generate();

    protected Id name;
    protected ClassType host;
    protected MemberInstance mi;
    protected boolean reachable;
    protected SubtypeSet exceptions;
    private TraitTypeSystem ts = new TraitTypeSystem_c();

    public UseTrait_c(Position pos, Id name) {
        this(pos, name, null);
    }

    public UseTrait_c(Position pos, Id name, Ext ext) {
        super(pos, ext);
        assert name != null;
        this.name = name;
        host = (ClassType) mi.container();
    }

    public boolean isUsed(ClassType ct) {
        if (ts.isMember(mi, host)) {
            return true;
        }
        return false;
    }

    @Override
    public MemberInstance memberInstance() {
        return mi;
    }

    protected <N extends UseTrait_c> N memberInstance(N n, MemberInstance mi) {
        if (n.mi == mi) return n;
        n = copyIfNeeded(n);
        n.mi = mi;
        return n;
    }

    public UseTrait memberInstance(MemberInstance mi) {
        return memberInstance(this, mi);
    }

    @Override
    public boolean reachable() {
        return reachable;
    }

    @Override
    public SubtypeSet exceptions() {
        return exceptions;
    }

    @Override
    public Term exceptions(SubtypeSet exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    @Override
    public Term reachable(boolean reachable) {
        this.reachable = reachable;
        return this;
    }

    public ClassType host() {
        return host;
    }

}
