package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.Id;
import polyglot.ast.MethodDecl;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.types.ConstructorInstance;
import polyglot.types.Flags;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.CFGBuilder;

public class TraitDecl_c extends Term_c implements TraitDecl {

    private static final long serialVersionUID = SerialVersionUID.generate();

    protected Id name;
    protected Flags flags;
    protected List<RequiredMethod> requiredMethods;
    protected List<MethodDecl> providedMethods;
    protected ConstructorInstance defaultCI;
    protected boolean implicitMembersAdded;

    public TraitDecl_c(Position pos, Id name,
            List<RequiredMethod> requiredMethods,
            List<MethodDecl> providedMethods) {
        super(pos);
        assert name != null && providedMethods != null; //requiredMethods can be empty
        this.name = name;
        this.requiredMethods = requiredMethods;
        this.providedMethods = providedMethods;
        flags = Flags.NONE;
    }

    @Override
    public Flags flags() {
        return flags;
    }

    @Override
    public Id id() {
        return name;
    }

    @Override
    public String name() {
        return name.id();
    }

    @Override
    public Term firstChild() {
        return null;
    }

    @Override
    public List<RequiredMethod> requiredMethods() {
        return requiredMethods;
    }

    @Override
    public List<MethodDecl> providedMethods() {
        return providedMethods;
    }

    /*
     * (non-Javadoc) This may be incorrect
     * @see polyglot.ast.Term_c#acceptCFG(polyglot.visit.CFGBuilder, java.util.List)
     */
    @Override
    public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
        v.visitCFGList(providedMethods, this, EXIT); //TODO: double-check this
        return succs;
    }

    /*
     * Returns this class if its field is the same as n or copies that class' field to the corresponding one here
     */
    protected <N extends TraitDecl_c> N flags(N n, Flags flags) {
        if (n.flags == flags) return n;
        n = copyIfNeeded(n);
        n.name = name;
        return n;
    }

    protected <N extends TraitDecl_c> N id(N n, Id name) {
        if (n.name == name) return n;
        n = copyIfNeeded(n);
        n.name = name;
        return n;
    }

    protected <N extends TraitDecl_c> N requiredMethods(N n,
            List<RequiredMethod> requiredMethods) {
        if (n.requiredMethods == requiredMethods) return n;
        n = copyIfNeeded(n);
        n.requiredMethods = requiredMethods;
        return n;
    }

    protected <N extends TraitDecl_c> N providedMethods(N n,
            List<MethodDecl> providedMethods) {
        if (n.providedMethods == providedMethods) return n;
        n = copyIfNeeded(n);
        n.providedMethods = providedMethods;
        return n;
    }

    @Override
    public TraitDecl flags(Flags flags) {
        return flags(this, flags);
    }

    @Override
    public TraitDecl id(Id id) {
        return id(this, id);
    }

    @Override
    public TraitDecl name(String name) {
        return id(this.name.id(name));
    }

    @Override
    public TraitDecl requiredMethods(List<RequiredMethod> requiredMethods) {
        return requiredMethods(this, requiredMethods);
    }

    @Override
    public TraitDecl providedMethods(List<MethodDecl> providedMethods) {
        return providedMethods(this, providedMethods);
    }

}
