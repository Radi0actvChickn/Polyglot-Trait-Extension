/*******************************************************************************
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2012 Polyglot project group, Cornell University
 * Copyright (c) 2006-2012 IBM Corporation
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program and the accompanying materials are made available under
 * the terms of the Lesser GNU Public License v2.0 which accompanies this
 * distribution.
 *
 * The development of the Polyglot project has been supported by a
 * number of funding sources, including DARPA Contract F30602-99-1-0533,
 * monitored by USAF Rome Laboratory, ONR Grants N00014-01-1-0968 and
 * N00014-09-1-0652, NSF Grants CNS-0208642, CNS-0430161, CCF-0133302,
 * and CCF-1054172, AFRL Contract FA8650-10-C-7022, an Alfred P. Sloan
 * Research Fellowship, and an Intel Research Ph.D. Fellowship.
 *
 * See README for contributors.
 ******************************************************************************/
package polyglot.ext.trait.ast;

import polyglot.ast.Ext;
import polyglot.ext.jl7.ast.JL7AbstractExtFactory_c;

public final class TraitExtFactory_c extends JL7AbstractExtFactory_c {

    public TraitExtFactory_c() {
        super();
    }

    public TraitExtFactory_c(TraitExtFactory nextExtFactory) {
        super(nextExtFactory);
    }

    /**
     * The next extFactory in the chain. Whenever an extension is instantiated,
     * the next extFactory should be called to see if it also has an extension,
     * and if so, the extensions should be joined together using the method
     * {@code composeExts}
     */
    private TraitExtFactory nextExtFactory;

    /*
     * Created following the same style as with other methods in AbstractExtFactory_c
     */
    public final Ext extRequiredMethod() {
        Ext e = extRequiredMethodImpl();

        if (nextExtFactory != null) {
            Ext e2 = nextExtFactory.extRequiredMethod();
            e = composeExts(e, e2);
        }
        return postExtRequiredMethod(e);
    }

    public final Ext extTraitDecl() {
        Ext e = extTraitDeclImpl();

        if (nextExtFactory != null) {
            Ext e2 = nextExtFactory.extTraitDecl();
            e = composeExts(e, e2);
        }
        return postExtTraitDecl(e);
    }

    public final Ext extUseTrait() {
        Ext e = extUseTraitImpl();

        if (nextExtFactory != null) {
            Ext e2 = nextExtFactory.extUseTrait();
            e = composeExts(e, e2);
        }
        return postExtUseTrait(e);
    }

    protected Ext extRequiredMethodImpl() {
        return extProcedureDeclImpl();
    }

    protected Ext extTraitDeclImpl() {
        return extTermImpl();
    }

    protected Ext extUseTraitImpl() {
        return extClassMemberImpl();
    }

    protected Ext postExtRequiredMethod(Ext ext) {
        return postExtProcedureDecl(ext);
    }

    protected Ext postExtTraitDecl(Ext ext) {
        return postExtTerm(ext);
    }

    protected Ext postExtUseTrait(Ext ext) {
        return postExtClassMember(ext);
    }
}
