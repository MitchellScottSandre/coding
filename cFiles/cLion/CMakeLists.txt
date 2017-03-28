cmake_minimum_required(VERSION 3.6)
project(cLion)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp test1.cc)
add_executable(cLion ${SOURCE_FILES})