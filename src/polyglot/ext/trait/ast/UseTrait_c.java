package polyglot.ext.trait.ast;

import polyglot.ast.Ext;
import polyglot.ast.Node_c;
import polyglot.ast.Term;
import polyglot.types.MemberInstance;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.util.SubtypeSet;

public class UseTrait_c extends Node_c implements UseTrait {

    private static final long serialVersionUID = SerialVersionUID.generate();

    public UseTrait_c(Position pos, Ext ext) {
        super(pos, ext);
        // TODO Auto-generated constructor stub
    }

    @Override
    public MemberInstance memberInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean reachable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Term reachable(boolean reachable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubtypeSet exceptions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Term exceptions(SubtypeSet exceptions) {
        // TODO Auto-generated method stub
        return null;
    }

}
