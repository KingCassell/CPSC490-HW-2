
#======================================================================
# Bare-bones Bazel BUILD file for MyPL
# CPSC 321
# Spring, 2022
#======================================================================

load("@rules_java//java:defs.bzl", "java_test")

java_binary(
  name = "hw2",
  srcs = glob(["src/*.java"]),
  main_class = "HW2",
)

java_library(
  name = "cpsc490-lib",
  srcs = glob(["src/*.java"]),
)

#----------------------------------------------------------------------
# TEST SUITES:
#----------------------------------------------------------------------

java_test(
  name = "adjacency-matrix-test",
  srcs = ["tests/AdjacencyMatrixTest.java"], 
  test_class = "AdjacencyMatrixTest",
  deps = ["lib/junit-4.13.2.jar", "lib/hamcrest-core-1.3.jar", ":hw2"],
)

java_test(
  name = "adjacency-list-test",
  srcs = ["tests/AdjacencyListTest.java"], 
  test_class = "AdjacencyListTest",
  deps = ["lib/junit-4.13.2.jar", "lib/hamcrest-core-1.3.jar", ":hw2"],
)

java_test(
  name = "bfs-test",
  srcs = ["tests/BFSTest.java"], 
  test_class = "BFSTest",
  deps = ["lib/junit-4.13.2.jar", "lib/hamcrest-core-1.3.jar", ":hw2"],
)




