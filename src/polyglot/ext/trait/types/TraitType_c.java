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

    /**
     * Used for deserializing types
     */
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
    public abstract Job job();

    /*
     * (non-Javadoc) Traits can only be top-level declarations
     * @see polyglot.types.ClassType_c#kind()
     */
    @Override
    public Kind kind() {
        return TOP_LEVEL;
    };

    /*
     * (non-Javadoc) Traits cannot be contained within other traits
     * @see polyglot.types.ClassType_c#container()
     */
    @Override
    public ReferenceType container() {
        return null;
    }

    @Override
    public abstract String name();

    @Override
    public boolean isTopLevel() {
        return true;
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return false;
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

    /*
     * (non-Javadoc) Overrides the superclass method because a trait is not a class
     * @see polyglot.types.ClassType_c#toClass()
     */
    @Override
    public ClassType toClass() {
        return null;
    }

    @Override
    public abstract Package package_();

    @Override
    public abstract Flags flags();

    /*
     * (non-Javadoc) There are no constructors for a trait
     * @see polyglot.types.ClassType_c#constructors()
     */
    @Override
    public List<? extends ConstructorInstance> constructors() {
        return null;
    };

    /*
     * (non-Javadoc) There can be no classes declared in a trait
     * @see polyglot.types.ClassType_c#memberClasses()
     */
    @Override
    public List<? extends ClassType> memberClasses() {
        return null;
    };

    @Override
    public abstract List<? extends MethodInstance> methods();

    /*
     * (non-Javadoc) There can be no fields declared in a trait
     * @see polyglot.types.ClassType_c#fields()
     */
    @Override
    public List<? extends FieldInstance> fields() {
        return null;
    };

    /*
     * (non-Javadoc) There can be no interfaces implemented in a Trait
     * @see polyglot.types.ClassType_c#interfaces()
     */
    @Override
    public List<? extends ReferenceType> interfaces() {
        return null;
    };

    /*
     * (non-Javadoc) There can be no superTypes of Traits (yet...)
     * @see polyglot.types.ClassType_c#superType()
     */
    @Override
    public Type superType() {
        return null;
    };

    @Override
    public List<? extends MemberInstance> members() {
        List<MemberInstance> l = new LinkedList<>();
        l.addAll(methods());
        return l;
    }

    @Override
    public boolean hasRequiredMethods() {
        for (MemberInstance member : members()) {
            if (member instanceof RequiredMethodInstance) {
                return true;
            }
        }
        return false;
    }

}
