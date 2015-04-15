package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.Ext;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.MethodDecl_c;
import polyglot.ast.TypeNode;
import polyglot.ext.trait.types.RequiredMethodInstance;
import polyglot.types.Flags;
import polyglot.types.MemberInstance;
import polyglot.types.MethodInstance;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public class RequiredMethod_c extends MethodDecl_c implements RequiredMethod {
    private static final long serialVersionUID = SerialVersionUID.generate();

    protected RequiredMethodInstance rmi;

    public RequiredMethod_c(Position pos, Flags flags, TypeNode returnType,
            Id name, List<Formal> formals, List<TypeNode> throwTypes) {
        super(pos, flags, returnType, name, formals, throwTypes, null);
    }

    public RequiredMethod_c(Position pos, Flags flags, TypeNode returnType,
            Id name, List<Formal> formals, List<TypeNode> throwTypes, Ext ext) {
        super(pos, flags, returnType, name, formals, throwTypes, null, ext);
    }

    /* - START - Borrowed from superclass to make sure "mi" refers to this.mi and not super.mi */
    @Override
    public boolean isDisambiguated() {
        return rmi != null && rmi.isCanonical() && super.isDisambiguated();
    }

    @Override
    public MemberInstance memberInstance() {
        return rmi;
    }

    @Override
    public MethodInstance methodInstance() {
        return rmi;
    }

    /*  - END -  Borrowed from superclass to make sure "mi" refers to this.mi and not super.mi */

    public RequiredMethod requiredMethodInstance(RequiredMethodInstance mi) {
        return requiredMethodInstance(this, mi);
    }

    protected <N extends RequiredMethod_c> N requiredMethodInstance(N n,
            RequiredMethodInstance mi) {
        if (n.rmi == mi) return n;
        n = copyIfNeeded(n);
        n.rmi = mi;
        return n;
    }

}
