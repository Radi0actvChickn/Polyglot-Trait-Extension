package polyglot.ext.trait.types;

import java.util.LinkedList;
import java.util.List;

import polyglot.frontend.Job;
import polyglot.types.ClassType;
import polyglot.types.ClassType_c;
import polyglot.types.ConstructorInstance;
import polyglot.types.Declaration;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.MemberInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Package;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public abstract class TraitType_c extends ClassType_c implements TraitType {
    private static final long serialVersionUID = SerialVersionUID.generate();

    protected TraitType_c() {
    }

    public TraitType_c(TypeSystem ts) {
        this(ts, null);
    }

    public TraitType_c(TypeSystem ts, Position pos) {
        super(ts, pos);
        decl = this;
    }

    @Override
    public TraitType_c copy() {
        TraitType_c n = (TraitType_c) super.copy();
        n.memberCache = null;
        return n;
    }

    protected TraitType decl;

    @Override
    public Declaration declaration() {
        return decl;
    }

    @Override
    public void setDeclaration(Declaration decl) {
        this.decl = (TraitType) decl;
    }

    @Override
    public boolean inStaticContext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFlags(Flags flags) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setContainer(ReferenceType container) {
        // TODO Auto-generated method stub

    }

    @Override
    public abstract Job job();

    @Override
    public abstract Kind kind();

    @Override
    public abstract TraitType outer();

    @Override
    public abstract String name();

    @Override
    public boolean isTopLevel() {
        return kind() == TOP_LEVEL;
    }

    @Override
    public boolean isLocal() {
        return kind() == LOCAL;
    }

    @Override
    public boolean isAnonymous() {
        return kind() == ANONYMOUS;
    }

    @Override
    public boolean isCanonical() {
        return true;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    public TraitType toTrait() {
        return this;
    }

    @Override
    public abstract Package package_();

    @Override
    public abstract Flags flags();

    @Override
    public abstract List<? extends ConstructorInstance> constructors();

    @Override
    public abstract List<? extends ClassType> memberClasses();

    @Override
    public abstract List<? extends MethodInstance> methods();

    @Override
    public abstract List<? extends FieldInstance> fields();

    @Override
    public abstract List<? extends ReferenceType> interfaces();

    @Override
    public abstract Type superType();

    @Override
    public List<? extends MemberInstance> members() {
        List<MemberInstance> l = new LinkedList<>();
        l.addAll(methods());
        return l;
    }

}
