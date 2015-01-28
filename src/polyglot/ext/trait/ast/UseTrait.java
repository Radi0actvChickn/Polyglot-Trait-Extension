package polyglot.ext.trait.ast;

import polyglot.ast.ClassMember;
import polyglot.types.MemberInstance;

public interface UseTrait extends ClassMember {
    @Override
    public MemberInstance memberInstance();
}
