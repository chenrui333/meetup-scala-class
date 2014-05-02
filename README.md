## Meetup Scala Class

Welcome to Meetup's Scala class!

This project will help make it easy to get started with Scala.

### Using the REPL

You can launch an interactive REPL (a *read evaluate print loop*) by
running the `./sbt console` command in your terminal (such as
`Terminal.app` on OSX). When you run this command you will see some
output and a welcome message, such as:

```
[info] Loading global plugins from /Users/osheim/.sbt/0.13/plugins
[info] Set current project to meetup-scala-class (in build file:/Users/osheim/meetup-scala-class/)
[info] Starting scala interpreter...
[info]
Welcome to Scala version 2.10.4 (Java HotSpot(TM) 64-Bit Server VM, Java 1.7.0_45).
Type in expressions to have them evaluated.
Type :help for more information.

scala>
```

You can exit the REPL by typing `:quit` and if you type `:help` you
can get a list of commands the REPL supports (but you won't need these
right now).

The "scala>" prompt allows you to evaluate simple Scala
expressions. For example, we can use Scala as a calculator:

```
scala> 1 + 1
res0: Int = 2

scala> 11 * 22 * 33
res1: Int = 7986

scala> math.sqrt(2385932.325)
res2: Double = 1544.6463430183624
```

The REPL is a great way to test out small snippets of Scala code, and
this course will encourage you to try out new things as you discover
them.

Once you are building larger expressions, or things you will want to
repeatedly use, it can be nice to define them in files, rather than
typing them in manually. The `:load` command allows you to load the
contents of a file, exactly as if you typed in each line.

If you have a file called `foo.repl` that contains:

```
1 + 1
2 * 3
4 * 5 - 6
```

Then here is how you'd load it in the REPL:

```
scala> :load foo.repl
Loading foo...
res0: Int = 2
res1: Int = 6
res2: Int = 14
```

### Using SBT

You can launch SBT (the *Scala Build Tool*) by running the `./sbt`
command. This tool is used by Scala programmers to compile, package,
and run their programs.

There's a lot to learn about SBT but for now you will only need to use
the following commands:

 * `console` Launch the REPL. This is described in the previous section.
 * `compile` Compile the project's source code (files ending in `.scala`).
 * `run` Compile and run the project's source code.

Source code should be kept in `src/main/scala` although SBT will
actually compile `.scala` files found anywhere in the project.

### Lectures

* [doc/class1.md](doc1/class.md)
