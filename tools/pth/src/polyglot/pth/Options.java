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
package polyglot.pth;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 */
public class Options {
    private final static int USAGE_FLAG_WIDTH = 21;
    private final static int USAGE_SCREEN_WIDTH = 75;

    protected List<String> inputFilenames = new LinkedList<>();

    // verbosity level, from 0 to 9.
    protected int verbosity = 5;

    // classpath for the compiler.
    protected String classpath = null;

    // path for the test directory.
    protected String testpath = null;

    // Extra command line args for the compiler
    protected String extraArgs = null;

    // filter for tests
    protected String testFilter = null;

    // show latest test results only
    protected boolean showResultsOnly = false;

    // test only the ones that failed last time
    protected boolean testPreviouslyFailedOnly = false;

    // delete output files after test
    protected boolean deleteOutputFiles = true;

    // array of the possible command line options.
    // the order in the array is the order that they will be applied in.
    protected CommandLineOption[] commandLineOpts =
            {
                    new CommandLineOption(new String[] { "f", "filter" },
                                          "regexp",
                                          "only execute the tests for which the regular expression regexp occurs somewhere in the test's name.") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            testFilter =
                                    ".*" + getStringArg(++index, args) + ".*";
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "p", "preserve" },
                                          "preserve output files between tests; default is to delete") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            deleteOutputFiles = false;
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "q", "question" },
                                          "show the latest results for the scripts; don't run any tests") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            showResultsOnly = true;
                            return index + 1;
                        }
                    },
                    new CommandLineOption("s",
                                          "only execute the tests which did not succeed last time they were executed.") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            testPreviouslyFailedOnly = true;
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "v" },
                                          "n",
                                          "set verbosity level to n. n should be between 0 (quiet) and 9 (most verbose).") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            verbosity = getIntArg(++index, args);
                            if (verbosity < 0 || verbosity > 9) {
                                throw new IllegalArgumentException("Verbosity must be "
                                        + "between 0 and 9 inclusive.");
                            }
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "cp", "classpath" },
                                          "path",
                                          "set the class path for the Polyglot compiler.") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            classpath = getStringArg(++index, args);
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "testpath" },
                                          "path",
                                          "where to find test files.") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            testpath = getStringArg(++index, args);
                            if (!testpath.endsWith("/")) testpath += "/";
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "args" },
                                          "extraArgs",
                                          "provide additional command line arguments to the Polyglot compiler.") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            extraArgs = getStringArg(++index, args);
                            return index + 1;
                        }
                    },
                    new CommandLineOption(new String[] { "h", "help", "?" },
                                          "Display this message.") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            usage(System.out);
                            System.exit(0);
                            return index + 1;
                        }
                    }, new CommandLineOption(new String[] {}, "Files") {
                        @Override
                        protected int invoke(int index, String[] args) {
                            return index;
                        }

                        @Override
                        protected int parse(int index, String[] args) {
                            if (!args[index].startsWith("-"))
                                inputFilenames.add(args[index++]);
                            return index;
                        }
                    }, };

    protected void usage(PrintStream out) {
        out.println("Polyglot Test Harness");
        out.println("Usage: pth [options] scriptFile ...");
        out.println("where options include: ");

        for (CommandLineOption commandLineOpt : commandLineOpts) {
            usageForSwitch(out, commandLineOpt);
        }
    }

    protected void usageForSwitch(PrintStream out, CommandLineOption arg) {
        if (arg.switches.length == 0) return;

        out.print("  ");
        // cur is where the cursor is on the screen.
        int cur = 2;

        for (int i = 0; i < arg.switches.length; i++) {
            String flag = arg.switches[i];
            out.print("-" + flag);
            cur += 1 + flag.length();
            if (arg.additionalArg != null) {
                out.print(" " + arg.additionalArg);
                cur += 1 + arg.additionalArg.length();
            }
            if (i < arg.switches.length - 1) {
                out.print(", ");
                cur += 2;
            }
        }

        // print space to get up to indentation level
        if (cur < USAGE_FLAG_WIDTH) {
            printSpaces(out, USAGE_FLAG_WIDTH - cur);
        }
        else {
            // the flag is long. Get a new line before printing the
            // description.
            out.println();
            printSpaces(out, USAGE_FLAG_WIDTH);
        }
        cur = USAGE_FLAG_WIDTH;

        // break up the description.
        StringTokenizer st = new StringTokenizer(arg.explanation);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (cur + s.length() > USAGE_SCREEN_WIDTH) {
                out.println();
                printSpaces(out, USAGE_FLAG_WIDTH);
                cur = USAGE_FLAG_WIDTH;
            }
            out.print(s);
            cur += s.length();
            if (st.hasMoreTokens()) {
                if (cur + 1 > USAGE_SCREEN_WIDTH) {
                    out.println();
                    printSpaces(out, USAGE_FLAG_WIDTH);
                    cur = USAGE_FLAG_WIDTH;
                }
                else {
                    out.print(" ");
                    cur++;
                }
            }
        }
        out.println();
    }

    protected static void printSpaces(PrintStream out, int n) {
        while (n-- > 0) {
            out.print(' ');
        }
    }

    protected void parseCommandLine(String[] args) {
        int ind = 0;
        if (args == null || args.length == 0) {
            args = new String[] { "-h" };
        }
        while (ind < args.length) {
            int newInd = ind;
            for (int i = 0; i < commandLineOpts.length && newInd == ind; i++) {
                CommandLineOption a = commandLineOpts[i];
                newInd = a.parse(ind, args);
            }

            if (newInd == ind) {
                throw new IllegalArgumentException("Unknown switch: "
                        + args[ind] + "\nTry -h for help.");
            }
            ind = newInd;
        }
    }

}

abstract class CommandLineOption {
    final String[] switches;
    final String additionalArg;
    final String explanation;

    public CommandLineOption(String swtch, String explanation) {
        this(swtch, null, explanation);
    }

    public CommandLineOption(String swtch, String additionalArgs,
            String explanation) {
        this.switches = new String[] { swtch };
        this.explanation = explanation;
        this.additionalArg = additionalArgs;
    }

    public CommandLineOption(String[] switches, String explanation) {
        this(switches, null, explanation);
    }

    public CommandLineOption(String[] switches, String additionalArgs,
            String explanation) {
        this.switches = switches;
        this.explanation = explanation;
        this.additionalArg = additionalArgs;
    }

    private int currOpt;

    protected int parse(int currentInd, String[] args) {
        String s = args[currentInd];
        if (s.startsWith("-")) {
            while (s.startsWith("-")) {
                s = s.substring(1);
            }

            for (String switche : this.switches) {
                if (switche.equals(s)) {
                    // the command line matches.
                    currOpt = currentInd;
                    return invoke(currentInd, args);
                }
            }
        }
        return currentInd;
    }

    protected abstract int invoke(int index, String[] args);

    protected int getIntArg(int index, String[] args) {
        try {
            return Integer.parseInt(args[index]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Expected an integer for the "
                    + "option " + args[currOpt]
                    + (additionalArg == null ? "" : (" " + additionalArg)));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected an integer, not "
                    + args[index]);
        }
    }

    protected String getStringArg(int index, String[] args) {
        try {
            return args[index];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Expected a string for the "
                    + "option " + args[currOpt]
                    + (additionalArg == null ? "" : (" " + additionalArg)));
        }
    }
}
