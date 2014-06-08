patmat-benchmarked
==================

This project shows how to micro-benchmark the encode and quickEncode methods from the week 4 assignment of the Functional Programming in Scala course, by Martin Odersky, on Coursera. The course can be viewed at: https://www.coursera.org/course/progfun .

This benchmark uses Caliper, a library for running micro-benchmarks on the JVM. Running such benchmarks on the JVM faces a number of challenges, as outlined on the Oracle wiki at : https://wikis.oracle.com/display/HotSpotInternals/MicroBenchmarks . Caliper aims to solve those difficulties for us. Check out the Caliper project at: http://code.google.com/p/caliper .

This project is based on a template for using Caliper in a Scala/SBT project, published at: http://www.decodified.com/scala/2011/04/19/microbenchmarking-scala-code . Thanks Mathias for the template!
